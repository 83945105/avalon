<template>
    <div>
        <div>
            <div class="common-wp-75 common-float-left">
                <el-input v-model="params.likeText"
                          size="small"
                          placeholder="请输入关键字"
                          clearable>
                </el-input>
            </div>
            <div class="common-float-left" style="width: 24%; margin-left:1%;">
                <we-button :loading="treeAsyncLoading" size="small" @click="getTreeAsyncRows" class="common-w-all">查询
                </we-button>
            </div>
            <div class="common-clear"></div>
        </div>

        <div class="gar-layout-custom common-padding-all-small common-scroll-v" style="top: 42px;">
            <we-skeleton v-model="treeAsyncSkeleton"
                         animation
                         :paragraph-rows="8"
                         @reload="getTreeAsyncRows">
                <div v-loading="!treeAsyncSkeleton && treeAsyncLoading">
                    <el-tree :ref="treeAsyncRef"
                             :data="treeAsyncRows"
                             :props="treeAsyncProps"
                             :node-key="treeAsyncNodeKey"
                             :lazy="true"
                             :load="getTreeAsyncNodes"
                             :expand-on-click-node="false"
                             :check-strictly="true"
                             empty-text="暂无路由数据"
                             highlight-current
                             draggable
                             @node-drag-start="handleDragStart"
                             @node-drag-enter="handleDragEnter"
                             @node-drag-leave="handleDragLeave"
                             @node-drag-over="handleDragOver"
                             @node-drag-end="handleDragEnd"
                             @node-drop="handleDrop"
                             :allow-drop="allowDrop"
                             :allow-drag="allowDrag"
                             class="common-margin-t-small"
                             @current-change="handleSelectedTreeNode">
                      <span class="custom-tree-node" slot-scope="{node, data}">
                        <template v-if="data.dataType === 'RouteView'">
                          <div class="tree-node-left">
                            <we-icon name="gar-view" style="float: left"></we-icon>
                              <div class="tree-node-left-title" :title="node.label">{{node.label}}</div>
                          </div>
                        </template>
                        <template v-else>
                          <div class="tree-node-left" @dblclick="handleDblClickTreeAsyncNode({node, data})">
                            <we-icon v-if="data.dataType === 'SubModule'" name="gar-sub-module" class="tree-node-left-icon"></we-icon>
                            <we-icon v-if="data.dataType === 'Route'" name="gar-route" class="tree-node-left-icon"></we-icon>
                            <div class="tree-node-left-title" :title="node.label">{{node.label}}</div>
                          </div>
                        </template>
                        <span class="tree-node-right">
                          <template v-if="data.dataType === 'Route'">
                            <we-popover title="查看" type="message"
                                        minWidth="300px"
                                        placement="right">
                              <template slot="content">
                                <ul class="gar-form-view label-width-5">
                                  <li>
                                    <em class="view-left">子模块名称：</em>
                                    <div class="view-right">{{data.subModuleName}}</div>
                                  </li>
                                  <li>
                                    <em class="view-left">路由名称：</em>
                                    <div class="view-right">{{data.name}}</div>
                                  </li>
                                  <li>
                                    <em class="view-left">路由地址：</em>
                                    <div class="view-right">{{data.path}}</div>
                                  </li>
                                  <li>
                                    <em class="view-left">是否缓存：</em>
                                    <div class="view-right">{{dict.routeCacheLabel[data.cache]}}</div>
                                  </li>
                                </ul>
                              </template>
                              <we-button type="text" size="small" iconName="search-o"></we-button>
                          </we-popover>
                          </template>
                          <template v-if="data.dataType === 'Route' || data.dataType === 'SubModule'">
                            <we-button type="text"
                                       size="small"
                                       iconName="plus"
                                       style="margin-left: 6px"
                                       @click="() => {
                                        selectedRouteId = data.dataType === 'Route' ? data.id : '';
                                        selectedSubModuleId = data.dataType === 'Route' ? data.subModuleId : data.id
                                        treeAsyncShowAdd = true;
                                       }">
                            </we-button>
                          </template>
                          <template v-if="data.dataType === 'Route'">
                            <we-button type="text"
                                       size="small"
                                       iconName="edit"
                                       style="margin-left: 6px"
                                       @click="() => {
                                        treeAsyncSelectedNode = node;
                                        treeAsyncSelectedNodeData = data;
                                        selectedRouteId = data.dataType === 'Route' ? data.id : '';
                                        selectedSubModuleId = data.dataType === 'Route' ? data.subModuleId : data.id
                                        treeAsyncShowEdit = true;
                                       }">
                            </we-button>

                            <we-popover title="操作" type="confirm"
                                        placement="right"
                                        :manual="data[treeAsyncNodeDeleteLoading$]"
                                        confirm-button-text="删了吧"
                                        :cancel-button-text="data[treeAsyncNodeDeleteLoading$] ? '请稍等' : '我再想想'"
                                        :cancel-button-options="{disabled: data[treeAsyncNodeDeleteLoading$]}"
                                        :confirm-button-options="{loading: data[treeAsyncNodeDeleteLoading$]}"
                                        @click-cancel-button="(e, vm) => vm.close()"
                                        @click-confirm-button="(e, vm) => handleDeleteRow(e, vm, {row: data})">
                                  <template slot="content">
                                    <template v-if="data[treeAsyncNodeDeleteLoading$]">
                                      <span style="color: red">删除中...</span>删除完成后将自动关闭气泡
                                    </template>
                                    <template v-else>
                                      您确定要<span style="color: red">删除</span>该路由<span style="color: red">及相关数据</span>吗？
                                      <div style="color: red">删除后不可恢复！！！</div>
                                    </template>
                                  </template>
                                  <we-button type="text"
                                             size="small"
                                             iconName="trash"
                                             style="margin-left: 6px">
                                  </we-button>
                            </we-popover>
                          </template>
                        </span>
                      </span>
                    </el-tree>
                </div>
            </we-skeleton>
        </div>
        <!--新增路由-->
        <we-layer v-model="treeAsyncShowAdd"
                  title="新增路由"
                  :width="60"
                  :height="85"
                  :min-width="500"
                  :show-footer="false"
                  drag
                  resize>
            <route-add :module-id="moduleId"
                       :sub-module-id="selectedSubModuleId"
                       :parent-route-id="selectedRouteId"
                       @submit-success="handleRouteAddSuccess">
                <template slot="button">
                    <el-button @click="treeAsyncShowAdd = false">关闭</el-button>
                </template>
            </route-add>
        </we-layer>
        <!--/新增路由-->

        <!--编辑路由-->
        <we-layer v-model="treeAsyncShowEdit"
                  title="编辑路由"
                  :width="60"
                  :height="85"
                  :min-width="500"
                  :show-footer="false"
                  drag
                  resize>
            <div class="common-padding-all" style="padding-bottom: 0;">
                <route-edit :module-id="moduleId"
                            :sub-module-id="selectedSubModuleId"
                            :data="treeAsyncSelectedNodeData"
                            @submit-success="handleRouteEditSuccess">
                    <template slot="button">
                        <el-button @click="treeAsyncShowEdit = false">关闭</el-button>
                    </template>
                </route-edit>
            </div>
        </we-layer>
        <!--/编辑路由-->
    </div>
</template>

<script>

    import Global from '../../../../mixins/global.js';
    import Link from '../../../../mixins/link.js';

    import TreeAsync from '../../../../../../assets/template/mixins/treeAsync.js';

    import ErrorPage from "../../../../../../components/error-page/src/ErrorPage.vue";
    import merge from "../../../../../../utils/merge.js";
    import ApiGarSubModuleUrls from "../../urls/api-gar-sub-module-urls.js";
    import ApiGarRouteUrls from "../../urls/api-gar-route-urls.js";
    import RouteAdd from "../route/RouteAddA.vue";
    import RouteEdit from "../route/RouteEditA.vue";

    const Params = {
        likeText: ''
    };

    export default {

        name: "route-tree",

        components: {RouteEdit, RouteAdd, ErrorPage},

        mixins: [Global, Link, TreeAsync],

        props: {
            moduleId: {
                type: String,
                default: ''
            }
        },

        data() {
            return {
                params: merge({}, Params),

                //TODO 编写你的异步树节点属性
                treeAsyncProps: {
                    label: 'name',
                    isLeaf: 'leaf',
                    children: 'children',
                    disabled: 'disabled'
                },
                //TODO 设置树节点唯一标识属性
                treeAsyncNodeKey: 'id',
                selectedSubModuleId: '',
                selectedRouteId: ''
            }
        },

        methods: {
            remove(row) {
                this.$refs[this.treeAsyncRef].remove(row);
            },
            append(row, parentKey) {
                if (!parentKey) return;
                row = this.formatterTreeAsyncRow(row, {
                    formatterRow: row => {
                        row.expanded = false;
                        row.dataType = 'Route';
                    },
                    //TODO 写你的格式化叶子节点逻辑
                    formatterNodeIsLeaf: row => row.childCount === 0
                });
                const Tree = this.$refs[this.treeAsyncRef];
                Tree.append(row, Tree.getNode(parentKey));
                // this.addTreeAsyncRowChild(this.findRow(this.treeAsyncRows, parentKey), row);
            },
            insertAfterRoot(row) {
                row = this.formatterTreeAsyncRow(row, {
                    formatterRow: row => {
                        row.expanded = false;
                        row.dataType = 'Route';
                    },
                    //TODO 写你的格式化叶子节点逻辑
                    formatterNodeIsLeaf: row => row.childCount === 0
                });
                const Tree = this.$refs[this.treeAsyncRef];
                Tree.insertAfter(row, Tree.getNode(this.treeAsyncRows[this.treeAsyncRows.length - 1]));
                // this.treeAsyncRows.push(row);
            },
            getCheckedNodes(leafOnly, includeHalfChecked) {
                return this.$refs[this.treeAsyncRef].getCheckedNodes(leafOnly, includeHalfChecked);
            },
            getCheckedKeys(leafOnly) {
                return this.$refs[this.treeAsyncRef].getCheckedKeys(leafOnly);
            },
            getTreeAsyncRows() {
                //清空
                this.$emit('check', {}, {
                    checkedNodes: [],
                    checkedKeys: [],
                    halfCheckedKeys: [],
                    halfCheckedNodes: []
                }, this);
                this.treeAsyncLoading = true;
                let params = merge({moduleId: this.moduleId}, this.params);
                if (this.queryParams) {
                    merge(params, this.queryParams);
                }
                this.$Ajax
                    .get(ApiGarSubModuleUrls.get.listSubModuleAndSubModuleRouteCount, params)
                    .success(true, data => {
                        this.treeAsyncSkeleton = false;
                        //由于骨架屏原因会导致tree俩次初始化,这里如果不设置异步,那么tree第二次初始化的时候找不到data属性
                        //所以要先关闭骨架屏,异步给data设置值
                        this.$nextTick(() => {
                            this.treeAsyncRows = this.formatterTreeAsyncRows(data.rows, {
                                formatterRow: row => {
                                    row.expanded = false;
                                    row.dataType = 'SubModule';
                                },
                                //TODO 写你的格式化叶子节点逻辑
                                formatterNodeIsLeaf: row => row.routeCount === 0
                            });
                        });
                    })
                    .notSuccess(() => this.treeAsyncSkeleton = 500)
                    .catch(() => this.treeAsyncSkeleton = 500)
                    .finally(() => this.treeAsyncLoading = false);
            },
            getTreeAsyncNodes(node, resolve) {
                if (!this.moduleId) {
                    resolve([]);
                }
                let params = {
                    moduleId: this.moduleId
                };
                if (this.queryChildParams) {
                    merge(params, this.queryChildParams);
                }
                //加载子节点,level代表树层级数
                if (node.level >= 1) {
                    const url = node.level === 1 ? ApiGarRouteUrls.get.listRootRouteBySubModuleId : ApiGarRouteUrls.get.listRouteByParentId;
                    this.$Ajax
                        .get(url, [node.data.id], {
                            params: params
                        })
                        .success(true, data => {
                            //记录为展开过状态
                            node.data.expanded = true;
                            const rows = this.formatterTreeAsyncRows(data.rows, {
                                formatterRow: row => {
                                    row.expanded = false;
                                    row.dataType = 'Route';
                                },
                                //TODO 写你的格式化叶子节点逻辑
                                formatterNodeIsLeaf: row => row.childCount === 0 && (!row.routeViews || row.routeViews.length === 0)
                            });
                            if (node.data.routeViews && node.data.routeViews.length > 0) {
                                for (let i = node.data.routeViews.length - 1; i >= 0; i--) {
                                    node.data.routeViews[i][this.treeAsyncProps.isLeaf] = true;
                                    node.data.routeViews[i].dataType = 'RouteView';
                                    rows.unshift(node.data.routeViews[i]);
                                }
                            }
                            resolve(rows);
                        });
                }
            },
            handleRouteAddSuccess(route, vm) {
                const row = this.formatterTreeAsyncRow(route, {
                    formatterRow: row => {
                        row.expanded = false;
                        row.dataType = 'Route';
                    },
                    //TODO 写你的格式化叶子节点逻辑
                    formatterNodeIsLeaf: row => row.childCount === 0 && (!row.routeViews || row.routeViews.length === 0)
                });
                this.append(row, row.parentId ? row.parentId : row.subModuleId);
            },
            handleRouteEditSuccess(route, vm) {
                merge(this.treeAsyncSelectedNodeData, route);
                if (this.treeAsyncSelectedNodeData.expanded) {
                    // 展开过了,要重置routeView节点
                    //TODO 未实现
                }
            },
            handleDeleteRow(e, vm, {row}) {
                if (!this.moduleId) {
                    return;
                }
                row[this.treeAsyncNodeDeleteLoading$] = true;
                this.$Ajax.delete(ApiGarRouteUrls.delete.routeByRouteId, {
                    moduleId: this.moduleId,
                    routeId: row.id
                })
                    .before(() => row[this.treeAsyncNodeDeleteLoading$] = false)
                    .success('删除成功', () => {
                        this.remove(row);
                        vm.close();
                    });
            },
            handleDblClickTreeAsyncNode({node, data}) {
                if (node.expanded) {
                    node.collapse();
                } else {
                    node.expand();
                }
            },
            handleSelectedTreeNode(data, node) {
                if (!this.moduleId) {
                    return;
                }
                if (data.dataType === 'RouteView') {
                    this.routerToRouteTreeRoleTemplate({moduleId: this.moduleId, routeViewId: data.id})
                } else {
                    this.routerToRouteTree({moduleId: this.moduleId});
                }
            },
            // 拖动节点
            handleDragStart(node, ev) {
            },
            handleDragEnter(draggingNode, dropNode, ev) {
            },
            handleDragLeave(draggingNode, dropNode, ev) {
            },
            handleDragOver(draggingNode, dropNode, ev) {
            },
            handleDragEnd(draggingNode, dropNode, dropType, ev) {
            },
            updateChildNodesSubModule(node, {subModuleId, subModuleName}) {
                node.data.subModuleId = subModuleId;
                node.data.subModuleName = subModuleName;
                if (node.childNodes && node.childNodes.length > 0) {
                    node.childNodes.forEach(row => this.updateChildNodesSubModule(row, {subModuleId, subModuleName}));
                }
            },
            handleDrop(draggingNode, dropNode, dropType, ev) {
                if (!this.moduleId) {
                    return;
                }
                if (draggingNode.data.subModuleId !== dropNode.data.subModuleId) {
                    this.$message({content: '暂不支持跨模块拖动'});
                    return false;
                }
                this.treeAsyncLoading = true;
                this.$Ajax.put(ApiGarRouteUrls.put.dragRouteTreeNode, {
                    dragRouteId: draggingNode.data.id,
                    dropRouteId: dropNode.data.id,
                    dropType: dropType,
                    moduleId: this.moduleId,
                    dragSubModuleId: draggingNode.data.subModuleId,
                    dropSubModuleId: dropNode.data.subModuleId
                })
                    .success('拖拽成功', () => {
                        if (draggingNode.data.subModuleId !== dropNode.data.subModuleId) {
                            //将drag及子孙node的subModuleId修改
                            this.updateChildNodesSubModule(draggingNode, dropNode.data);
                        }

                    })
                    .notSuccess(() => this.getTreeAsyncRows())
                    .finally(() => this.treeAsyncLoading = false);
            },
            allowDrop(draggingNode, dropNode, type) {
                if (draggingNode.data.subModuleId !== dropNode.data.subModuleId) {
                    return false;
                }
                switch (type) {
                    case 'prev':
                        if (draggingNode.data.dataType === 'Route' && dropNode.data.dataType === 'Route') return true;
                        break;
                    case 'inner':
                        if (draggingNode.data.dataType === 'Route' && dropNode.data.dataType === 'Route') return true;
                        break;
                    case 'next':
                        if (draggingNode.data.dataType === 'Route' && dropNode.data.dataType === 'Route') return true;
                        break;
                    default:
                        throw new Error('拖动类型不正确');
                }
                return false;
            },
            allowDrag(draggingNode) {
                if (draggingNode.data.dataType === 'Route') return true;
                return false;
            }
        },
        created() {
            this.getTreeAsyncRows();
        }

    }
</script>
