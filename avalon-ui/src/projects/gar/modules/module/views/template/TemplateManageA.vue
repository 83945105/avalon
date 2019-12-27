<template>
  <div>
    <!--查询-->
    <div class="gar-search-bar">
      <!--查询条件-->
      <ul class="gar-search-bar-list">
        <li>
          <em class="list-left">关键字：</em>
          <div class="list-right common-w-large">
            <el-input v-model="params.likeText" size="small"
                      placeholder="请输入关键字" clearable></el-input>
          </div>
        </li>
        <li>
          <em class="list-left list-label-4">所属子模块：</em>
          <div class="list-right">
            <sub-module-multiple-select v-model="subModuleIdSet" :module-id="moduleId"></sub-module-multiple-select>
          </div>
        </li>
        <li>
          <em class="list-left list-label-4">模板类型：</em>
          <div class="list-right common-w">
            <view-type-select v-model="viewType" show-all-option></view-type-select>
          </div>
        </li>
        <li class="spacing">
          <we-button :loading="loading" size="small" @click="$refs.table.reloadTableCellEditRows()">查询</we-button>
        </li>
      </ul>
      <div class="common-float-right">
        <we-button size="small" type="success"
                   @click="$refs.table.showAdd()">新增模板
        </we-button>
      </div>
      <div class="common-clear"></div>
      <!--/查询条件-->
    </div>
    <!--/查询-->

    <!--列表-->
    <div class="gar-list-main is-border is-paging-size-mini">
      <template-cell-edit-table ref="table"
                                :module-id="moduleId"
                                :reload-table-cell-edit="reload"
                                @table-cell-edit-add-success="handleTableCellEditAddSuccess"
      >
          <template slot="table-column-before">
              <el-table-column
                      type="index"
                      width="50">
              </el-table-column>
          </template>
      </template-cell-edit-table>
      <div class="gar-paging-infinite is-absolute common-tac">
        <div class="gar-paging-infinite-inner gar-paging-infinite-inner-size-small size-height-mini">
          <we-loading :value="moreLoading" size="mini"></we-loading>
          <span v-if="limit.currentPage * limit.pageSize > limit.total" class="common-fc-text-3">没有更多了</span>
          <we-button v-else size="small" type="text" @click="limit.currentPage++;loadMore()">加载更多...
          </we-button>
        </div>
      </div>
    </div>
    <!--/列表-->

  </div>
</template>

<script>

  import merge from "../../../../../../utils/merge.js";
  import SubModuleMultipleSelect from "../../components/sub-module-select/src/SubModuleMultipleSelect.vue";
  import ViewTypeSelect from "../../components/sub-module-select/src/ViewTypeSelect.vue";
  import ApiGarTemplateUrls from "../../urls/api-gar-template-urls.js";
  import TemplateCellEditTable from "./TemplateCellEditTableA.vue";

  const Limit = {
    currentPage: 1,
    pageSizes: [10, 20, 50, 100],
    total: 0,
    pageCount: 1
  };

  const Params = {
    likeText: ''
  };

  export default {

    name: "template-manage",

    components: {TemplateCellEditTable, ViewTypeSelect, SubModuleMultipleSelect},

    props: {
      moduleId: {
        type: String,
        required: true
      }
    },

    data() {
      return {
        subModuleIdSet: [],
        viewType: '',
        limit: merge({pageSize: 20}, Limit),
        params: merge({}, Params),

        loading: false,
        moreLoading: false
      }
    },

    methods: {
      reload(resolve, reject) {
        this.loading = true;
        merge(this.limit, Limit);
        let params = merge({moduleId: this.moduleId}, this.limit, this.params);

        if (this.subModuleIdSet.length > 0) {
          params.subModuleIdSet = this.subModuleIdSet.toString();
        }
        if (this.viewType) {
          params.viewType = this.viewType;
        }
        this.$Ajax
          .get(ApiGarTemplateUrls.get.pageListTemplate, params)
          .success(true, data => {
            merge(this.limit, data.limit);
            resolve(data.rows);
          })
          .notSuccess(reject)
          .catch(reject)
          .finally(() => this.loading = false);
      },
      loadMore() {
        this.moreLoading = true;
        let params = merge({moduleId: this.moduleId}, this.limit, this.params);

        if (this.subModuleIdSet.length > 0) {
          params.subModuleIdSet = this.subModuleIdSet.toString();
        }
        if (this.viewType) {
          params.viewType = this.viewType;
        }
        this.$Ajax
          .get(ApiGarTemplateUrls.get.pageListTemplate, params)
          .success(true, data => {
            merge(this.limit, data.limit);
            this.$refs.table.appendRows(data.rows);
          })
          .finally(() => this.moreLoading = false);
      },
      handleTableCellEditAddSuccess({row, vm}) {
        if (this.limit.currentPage * this.limit.pageSize > this.limit.total) {
          this.$refs.table.appendTableCellEditRow(row);
        }
      }
    },
    mounted() {
      this.$refs.table.reloadTableCellEditRows();
    }
  }
</script>
