<template>
    <div>
        <!--查询-->
        <div class="tour-search-bar" style="padding: 0 0 15px;">
            <!--查询条件-->
            <ul class="tour-search-bar-list">
                <li>
                    <em class="list-left list-label-4">报名时间：</em>
                    <div class="list-right">
                        <el-date-picker v-model="pubTimeRange"
                                        type="datetimerange"
                                        size="small"
                                        value-format="yyyy-MM-dd HH:mm:ss"
                                        range-separator="至"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期"
                                        style="width: 240px">
                        </el-date-picker>
                    </div>
                </li>
                <li>
                    <em class="list-left list-label-4">签到情况：</em>
                    <div class="list-right">
                        <sign-in-select v-model="params.sign" show-all-option style="width: 100px;"></sign-in-select>
                    </div>
                </li>
                <li>
                    <em class="list-left list-label-4">签到时间：</em>
                    <div class="list-right">
                        <el-date-picker v-model="signTimeRange"
                                        type="datetimerange"
                                        size="small"
                                        value-format="yyyy-MM-dd HH:mm:ss"
                                        range-separator="至"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期"
                                        style="width: 240px">
                        </el-date-picker>
                    </div>
                </li>
                <li>
                    <em class="list-left list-label-4">关键字：</em>
                    <div class="list-right">
                        <el-input v-model="params.likeText" size="small" clearable
                                  style="width: 120px"
                                  placeholder="请输入关键字"></el-input>
                    </div>
                </li>
                <li class="spacing">
                    <we-button :loading="tableLoading" size="small" type="primary"
                               @click="reGetRows">查询
                    </we-button>

                </li>
            </ul>
            <div class="common-clear"></div>
            <!--/查询条件-->
        </div>
        <!--/查询-->

        <!--列表-->
        <div style="height: 600px">
            <we-skeleton v-model="tableSkeleton" animation :paragraph-rows="10"
                         :title-width="'50%'" :paragraph-width="'100%'"
                         @reload="getRows">
                <div v-loading="!tableSkeleton && tableLoading">
                    <el-table :data="tableRows"
                              style="width: 100%"
                              border
                              height="600px">
                        <template slot="empty">
                            <error-page :image-width="180" :image-height="180"></error-page>
                        </template>
                        <el-table-column prop="realname"
                                         label="姓名"
                                         show-overflow-tooltip
                                         min-width="120">
                        </el-table-column>
                        <el-table-column prop="card"
                                         label="身份证号"
                                         show-overflow-tooltip
                                         min-width="150">
                        </el-table-column>
                        <el-table-column prop="phone"
                                         label="联系电话"
                                         show-overflow-tooltip
                                         width="120">
                        </el-table-column>
                        <el-table-column prop="orgname"
                                         label="所在学校"
                                         show-overflow-tooltip
                                         min-width="150">
                        </el-table-column>
                        <el-table-column prop="pubTime"
                                         label="报名时间"
                                         show-overflow-tooltip
                                         width="150">
                            <template slot-scope="scope">
                                {{formatterTime(scope.row.pubTime)}}
                            </template>
                        </el-table-column>
                        <el-table-column prop="realTimeSign"
                                         label="签到情况"
                                         show-overflow-tooltip
                                         width="100">
                            <template slot-scope="scope">
                                <el-tag v-if="scope.row.realTimeSign === dict.signValue.Y" type="success" size="small">
                                    已签到
                                </el-tag>
                                <el-tag v-else-if="scope.row.realTimeSign === dict.signValue.N" type="info"
                                        size="small">未签到
                                </el-tag>
                                <el-tag v-else-if="scope.row.realTimeSign === dict.signValue.LATE" type="danger"
                                        size="small">迟到
                                </el-tag>
                            </template>
                        </el-table-column>
                        <el-table-column prop="signTime"
                                         label="签到时间"
                                         show-overflow-tooltip
                                         width="150">
                            <template slot-scope="scope">
                                {{formatterTime(scope.row.signTime)}}
                            </template>
                        </el-table-column>
                        <el-table-column prop="operation"
                                         fixed="right"
                                         label="操作"
                                         width="100">
                            <template slot-scope="scope">
                                <we-popover title="操作" type="confirm"
                                            placement="top-start"
                                            :manual="scope.row.deleteLoading"
                                            confirm-button-text="取消吧"
                                            :cancel-button-text="scope.row.deleteLoading ? '正在取消' : '我再想想'"
                                            :cancel-button-options="{disabled: scope.row.deleteLoading}"
                                            :confirm-button-options="{loading: scope.row.deleteLoading}"
                                            @click-cancel-button="(e, vm) => vm.close()"
                                            @click-confirm-button="(e, vm) => handleCancelRow(e, vm, scope)">
                                    <template slot="content">
                                        <template v-if="scope.row.deleteLoading">
                                            <span style="color: red">取消中...</span>取消完成后将自动关闭气泡
                                        </template>
                                        <template v-else>
                                            您确定要<span style="color: red">取消</span>该教师的报名资格吗？
                                        </template>
                                    </template>
                                    <we-button type="text" size="small">取消报名</we-button>
                                </we-popover>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </we-skeleton>
        </div>
        <!--/列表-->
        <!--分页-->
        <div class="tour-paging-main tour-paging-size-small common-tac">
            <el-pagination layout="total, sizes, prev, pager, next, jumper"
                           :current-page="limit.currentPage"
                           :page-size="limit.pageSize"
                           :total="limit.total"
                           :page-count="limit.pageCount"
                           :page-sizes="[10, 20, 50]"
                           @current-change="v => {limit.currentPage = v;getRows();}"
                           @size-change="v => {limit.pageSize = v;getRows();}"
            >
            </el-pagination>
        </div>
        <!--/分页-->
    </div>
</template>

<script>

    import Global from '../../../mixins/global.js';
    import Table from '../../../../../assets/template/mixins/table.js';
    import ErrorPage from "../../../../../components/error-page/src/ErrorPage.vue";
    import Link from '../../../mixins/link.js';
    import merge from "../../../../../utils/merge.js";
    import SignInSelect from "../components/select/src/SignInSelect.vue";
    import FamSignUrls from "../../../urls/fam-sign-urls.js";

    const Limit = {
        currentPage: 1,
        pageSize: 20,
        total: 0,
        pageCount: 1
    };

    export default {

        name: "student-manage-list",

        components: {ErrorPage, SignInSelect},

        mixins: [Global, Link, Table],

        props: {
            courseId: String
        },

        data() {
            return {
                pubTimeRange: [],
                signTimeRange: [],
                limit: merge({}, Limit),
                params: {
                    minSignTime: '',
                    maxSignTime: '',
                    minPubTime: '',
                    maxPubTime: '',
                    sign: '',
                    likeText: ''
                },
            }
        },

        watch: {
            pubTimeRange(val) {
                if (this.isEmpty(val)) {
                    merge(this.params, {
                        minPubTime: '',
                        maxPubTime: ''
                    });
                    return;
                }
                merge(this.params, {
                    minPubTime: val[0],
                    maxPubTime: val[1]
                });
            },
            signTimeRange(val) {
                if (this.isEmpty(val)) {
                    merge(this.params, {
                        minSignTime: '',
                        maxSignTime: ''
                    });
                    return;
                }
                merge(this.params, {
                    minSignTime: val[0],
                    maxSignTime: val[1]
                });
            }
        },

        methods: {
            reGetRows() {
                merge(this.limit, Limit);
                this.getRows();
            },
            formatterRows(rows) {
                rows.forEach(row => {
                    row.deleteLoading = false;
                });
                return rows;
            },
            getRows() {
                this.tableLoading = true;
                this.$Ajax.get(FamSignUrls.get.pageListSignUserByClassId, [this.courseId], {
                    params: merge({}, this.params, this.limit)
                })
                    .success(true, data => {
                        this.tableSkeleton = false;
                        this.formatterRows(data.rows);
                        this.tableRows = data.rows;
                        merge(this.limit, data.limit);
                    })
                    .finally(() => this.tableLoading = false);
            },
            handleCancelRow(e, vm, {row}) {
                row.deleteLoading = true;
                this.$Ajax.put(FamSignUrls.put.unFamSignByClassIdAndUserId, {
                    classId: this.courseId,
                    userId: row.id
                })
                    .success('取消成功', () => {
                        vm.close();
                        this.reGetRows();
                    })
                    .finally(() => row.deleteLoading = false);
            }
        },

        created() {
            this.getRows();
        }

    }
</script>
