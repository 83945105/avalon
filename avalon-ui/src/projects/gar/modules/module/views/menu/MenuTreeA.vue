<template>
    <div>
        <div class="common-wp-75 common-float-left">
            <el-input v-model="params.likeText"
                      size="small"
                      placeholder="请输入关键字"
                      clearable>
            </el-input>
        </div>
        <div class="common-float-left"
             style="width: 24%; margin-left:1%;">
            <we-button :loading="treeAsyncLoading" size="small" @click="getTreeAsyncRows" class="common-w-all">查询
            </we-button>
        </div>
        <div class="common-clear"></div>

        <div class="gar-layout-custom common-padding-all-small common-scroll-v" style="top: 42px; padding-top: 0;">
            <we-skeleton v-model="treeAsyncSkeleton"
                         animation
                         :paragraph-rows="8"
                         @reload="getTreeAsyncRows">
                <el-tree :ref="treeAsyncRef"
                         :data="treeAsyncRows"
                         :props="treeAsyncProps"
                         :node-key="treeAsyncNodeKey"
                         :lazy="true"
                         :load="getTreeAsyncNodes"
                         :expand-on-click-node="false"
                         :check-strictly="true"
                         empty-text="暂无菜单数据"
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
                         class="common-margin-t-small gar-tree-custom"
                         @current-change="handleSelectedTreeNode">
                    <span class="custom-tree-node" slot-scope="{node, data}">
                        <span class="custom-tree-node-left" @dblclick="handleDblClickTreeAsyncNode({node, data})">

                            <!--菜单组复选框开始-->
                            <template v-if="data.dataType === 'MenuGroup'">
                                <el-checkbox v-model="data.checked"
                                             :disabled="menuGroupCheckedLoading"
                                             @change="val => handleChangeMenuGroupChecked({node, data, value: val})"></el-checkbox>
                            </template>
                            <!--菜单组复选框结束-->

                            <we-icon v-if="data.dataType === 'Menu'" :name="data.iconName"></we-icon>
                            <we-icon v-else-if="data.dataType === 'MenuGroup'" name="gar-type"></we-icon>
                            <we-icon v-else-if="data.dataType === 'SubModule'" name="gar-sub-module"></we-icon>
                            {{node.label}}
                        </span>
                        <span class="custom-tree-node-right">
                            <template v-if="data.dataType === 'Menu'">
                                <we-popover title="查看" type="message"
                                            minWidth="400px"
                                            placement="right">
                                    <template slot="content">
                                        <ul class="gar-form-view label-width-12">
                                            <li>
                                                <em class="view-left">菜单名称：</em>
                                                <div class="view-right">{{data.name}}</div>
                                            </li>
                                            <li>
                                                <em class="view-left">菜单唯一标识符：</em>
                                                <div class="view-right">{{data.value}}</div>
                                            </li>
                                            <li>
                                                <em class="view-left">图标名称：</em>
                                                <div class="view-right">{{data.iconName}}</div>
                                            </li>
                                            <li>
                                                <em class="view-left">是否使用选项卡：</em>
                                                <div class="view-right">{{dict.menuUseTabLabel[data.useTab]}}</div>
                                            </li>
                                            <template v-if="data.useTab === dict.menuUseTabValue.true">
                                                <li>
                                                    <em class="view-left">是否缓存选项卡：</em>
                                                    <div class="view-right">{{data.cacheInTab === 'true' ? '是' : '否'}}
                                                    </div>
                                                </li>
                                                <li>
                                                    <em class="view-left">是否在选项卡中默认打开：</em>
                                                    <div class="view-right">{{data.initOpenInTab === 'true' ? '是' :
                                                        '否'}}
                                                    </div>
                                                </li>
                                                <li>
                                                    <em class="view-left">是否可以关闭选项卡：</em>
                                                    <div class="view-right">{{data.closableInTab === 'true' ? '是' :
                                                        '否'}}
                                                    </div>
                                                </li>
                                            </template>
                                        </ul>
                                    </template>
                                    <we-button type="text" size="small" iconName="search-o"></we-button>
                                </we-popover>
                            </template>
                            <we-button type="text"
                                       size="small"
                                       iconName="plus"
                                       style="margin-left: 6px"
                                       @click="handleClickAddIcon({node, data})">
                            </we-button>
                            <template v-if="data.dataType === 'Menu' || data.dataType === 'MenuGroup'">
                                <we-button type="text"
                                           size="small"
                                           iconName="edit"
                                           style="margin-left: 6px"
                                           @click="handleClickEditIcon({node, data})">
                                </we-button>
                                <we-popover title="操作" type="confirm"
                                            placement="right"
                                            :manual="data[treeAsyncNodeDeleteLoading$]"
                                            confirm-button-text="删了吧"
                                            :cancel-button-text="data[treeAsyncNodeDeleteLoading$] ? '请稍等' : '我再想想'"
                                            :cancel-button-options="{disabled: data[treeAsyncNodeDeleteLoading$]}"
                                            :confirm-button-options="{loading: data[treeAsyncNodeDeleteLoading$]}"
                                            @click-cancel-button="(e, vm) => vm.close()"
                                            @click-confirm-button="(e, vm) => handleDeleteRow(e, vm, {node, data})">
                                    <template slot="content">
                                        <template v-if="data[treeAsyncNodeDeleteLoading$]">
                                            <span style="color: red">删除中...</span>删除完成后将自动关闭气泡
                                        </template>
                                        <template v-else>
                                            您确定要<span style="color: red">删除</span>该菜单{{data.dataType === 'MenuGroup' ?
                                            '组' : ''}}<span
                                                style="color: red">及相关数据</span>吗？
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
                        <div class="common-clear"></div>
                    </span>
                </el-tree>
            </we-skeleton>
        </div>

        <!--新增菜单组-->
        <we-layer v-model="menuGroupShowAdd"
                  title="新增菜单组"
                  :width="50"
                  :height="80"
                  :min-width="500"
                  :show-footer="false"
                  drag
                  resize>
            <menu-group-add :module-id="moduleId"
                            :sub-module-id="selectedSubModuleId"
                            @submit-success="handleMenuGroupAddSuccess">
                <template slot="button">
                    <el-button @click="menuGroupShowAdd = false">关闭</el-button>
                </template>
            </menu-group-add>
        </we-layer>
        <!--/新增菜单组-->

        <!--编辑菜单组-->
        <we-layer v-model="menuGroupShowEdit"
                  title="编辑菜单组"
                  :width="50"
                  :height="80"
                  :min-width="500"
                  :show-footer="false"
                  drag
                  resize>
            <menu-group-edit :module-id="moduleId"
                             :sub-module-id="selectedSubModuleId"
                             :data="selectedMenuGroupData"
                             @submit-success="handleMenuGroupEditSuccess">
                <template slot="button">
                    <el-button @click="menuGroupShowEdit = false">关闭</el-button>
                </template>
            </menu-group-edit>
        </we-layer>
        <!--/编辑菜单组-->

        <!--新增菜单-->
        <we-layer v-model="treeAsyncShowAdd"
                  title="新增菜单"
                  :width="50"
                  :height="80"
                  :min-width="500"
                  :show-footer="false"
                  drag
                  resize>
            <menu-add :module-id="moduleId"
                      :sub-module-id="selectedSubModuleId"
                      :menu-group-id="selectedMenuGroupId"
                      :parent-menu-id="selectedMenuId"
                      @submit-success="handleMenuAddSuccess">
                <template slot="button">
                    <el-button @click="treeAsyncShowAdd = false">关闭</el-button>
                </template>
            </menu-add>
        </we-layer>
        <!--/新增菜单-->

        <!--编辑菜单-->
        <we-layer v-model="treeAsyncShowEdit"
                  title="编辑菜单"
                  :width="50"
                  :height="80"
                  :min-width="500"
                  :show-footer="false"
                  drag
                  resize>
            <menu-edit :module-id="moduleId"
                       :sub-module-id="selectedSubModuleId"
                       :menu-group-id="selectedMenuGroupId"
                       :data="treeAsyncSelectedNodeData"
                       @submit-success="handleMenuEditSuccess">
                <template slot="button">
                    <el-button @click="treeAsyncShowEdit = false">关闭</el-button>
                </template>
            </menu-edit>
        </we-layer>
        <!--/编辑菜单-->
    </div>
</template>

<script>

    import Global from '../../../../mixins/global.js';
    import Link from '../../../../mixins/link.js';

    import TreeAsync from '../../../../../../assets/template/mixins/treeAsync.js';
    import ErrorPage from "../../../../../../components/error-page/src/ErrorPage.vue";
    import MenuAdd from "./MenuAddA.vue";
    import MenuEdit from "./MenuEditA.vue";
    import MenuGroupAdd from "./MenuGroupAddA.vue";
    import MenuGroupEdit from "./MenuGroupEditA.vue";
    import merge from "../../../../../../utils/merge.js";
    import ApiGarSubModuleUrls from "../../urls/api-gar-sub-module-urls";
    import ApiGarMenuUrls from "../../urls/api-gar-menu-urls";
    import ApiGarMenuGroupUrls from "../../urls/api-gar-menu-group-urls";

    const Params = {
        likeText: ''
    };

    export default {
        name: "menu-tree",

        componentName: "MenuTree",

        components: {ErrorPage, MenuAdd, MenuEdit, MenuGroupAdd, MenuGroupEdit},

        mixins: [Global, Link, TreeAsync],

        props: {
            moduleId: {
                type: String,
                default: ''
            },
            subModuleId: String,
            menuId: String
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
                selectedMenuGroupId: '',
                selectedMenuId: '',
                menuGroupShowAdd: false,
                menuGroupShowEdit: false,
                selectedMenuGroupData: {},
                menuGroupCheckedLoading: false
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
                        row.dataType = 'Menu';
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
            getRow() {
                this.$Ajax.get(ApiGarMenuUrls.get.menuByMenuId, [this.menuId], {
                    params: {moduleId: this.moduleId}
                })
                    .success(true, data => {
                        const menu = data.records.menu;
                        let expandIds = [this.subModuleId, menu.menuGroupId];
                        if (menu.parentIds) {
                            menu.parentIds.split("/").forEach(id => {
                                if (!id) return;
                                expandIds.push(id);
                            })
                        }
                        this.expandTreeNodeByKeys({key: expandIds[0]});
                    });
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
                this.$Ajax
                    .get(ApiGarSubModuleUrls.get.listSubModuleAndSubModuleMenuGroupCount, merge({moduleId: this.moduleId}, this.params))
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
                                formatterNodeIsLeaf: row => row.menuGroupCount === 0
                            });
                        });
                    })
                    .notSuccess(() => this.treeAsyncSkeleton = 500)
                    .catch(() => this.treeAsyncSkeleton = 500)
                    .finally(() => this.treeAsyncLoading = false);
            },
            getTreeAsyncNodes(node, resolve) {
                //加载子节点,level代表树层级数
                if (node.level === 1) {
                    this.$Ajax
                        .get(ApiGarMenuGroupUrls.get.listMenuGroupBySubModuleId, [node.data.id], {
                            params: {moduleId: this.moduleId}
                        })
                        .success(true, data => {
                            //记录为展开过状态
                            node.data.expanded = true;
                            const rows = this.formatterTreeAsyncRows(data.rows, {
                                formatterRow: row => {
                                    row.expanded = false;
                                    row.dataType = 'MenuGroup';
                                    row.checked = row.use === this.dict.menuGroupUseValue.TRUE;
                                },
                                //TODO 写你的格式化叶子节点逻辑
                                formatterNodeIsLeaf: row => row.rootMenuCount === 0
                            });
                            resolve(rows);
                        });
                } else if (node.level === 2) {
                    this.$Ajax
                        .get(ApiGarMenuUrls.get.listRootMenuBySubModuleIdAndMenuGroupId, [node.data.subModuleId, node.data.id], {
                            params: {moduleId: this.moduleId}
                        })
                        .success(true, data => {
                            //记录为展开过状态
                            node.data.expanded = true;
                            const rows = this.formatterTreeAsyncRows(data.rows, {
                                formatterRow: row => {
                                    row.expanded = false;
                                    row.dataType = 'Menu';
                                },
                                //TODO 写你的格式化叶子节点逻辑
                                formatterNodeIsLeaf: row => row.childCount === 0
                            });
                            resolve(rows);
                        });
                } else if (node.level > 2) {
                    this.$Ajax
                        .get(ApiGarMenuUrls.get.listMenuByParentId, [node.data.id], {
                            params: {moduleId: this.moduleId}
                        })
                        .success(true, data => {
                            //记录为展开过状态
                            node.data.expanded = true;
                            const rows = this.formatterTreeAsyncRows(data.rows, {
                                formatterRow: row => {
                                    row.expanded = false;
                                    row.dataType = 'Menu';
                                },
                                //TODO 写你的格式化叶子节点逻辑
                                formatterNodeIsLeaf: row => row.childCount === 0
                            });
                            resolve(rows);
                        });
                }
            },
            handleClickAddIcon({node, data}) {
                if (data.dataType === 'Menu') {
                    this.selectedMenuGroupId = data.menuGroupId;
                    this.selectedMenuId = data.id;
                    this.selectedSubModuleId = data.subModuleId;
                    this.treeAsyncShowAdd = true;
                }
                if (data.dataType === 'MenuGroup') {
                    this.selectedMenuGroupId = data.id;
                    this.selectedMenuId = '';
                    this.selectedSubModuleId = data.subModuleId;
                    this.treeAsyncShowAdd = true;
                }
                if (data.dataType === 'SubModule') {
                    this.selectedMenuGroupId = '';
                    this.selectedMenuId = '';
                    this.selectedSubModuleId = data.id;
                    this.menuGroupShowAdd = true;
                }
            },
            handleClickEditIcon({node, data}) {
                if (data.dataType === 'Menu') {
                    this.treeAsyncSelectedNodeData = data;
                    this.selectedMenuGroupId = data.menuGroupId;
                    this.selectedMenuId = data.id;
                    this.selectedSubModuleId = data.subModuleId;
                    this.treeAsyncShowEdit = true;
                }
                if (data.dataType === 'MenuGroup') {
                    this.selectedMenuGroupData = data;
                    this.selectedMenuGroupId = data.id;
                    this.selectedMenuId = '';
                    this.selectedSubModuleId = data.subModuleId;
                    this.menuGroupShowEdit = true;
                }
            },
            handleMenuGroupAddSuccess(menuGroup, vm) {
                let row = this.formatterTreeAsyncRow(menuGroup, {
                    formatterRow: data => {
                        data.expanded = false;
                        data.dataType = 'MenuGroup';
                    },
                    //TODO 写你的格式化叶子节点逻辑
                    formatterNodeIsLeaf: row => row.rootMenuCount === 0
                });
                this.append(row, row.subModuleId);
            },
            handleMenuGroupEditSuccess(menuGroup, vm) {
                merge(this.selectedMenuGroupData, menuGroup);
                if (this.selectedMenuGroupData.expanded) {
                    // 展开过了,要重置menu节点
                    //TODO 未实现
                }
            },
            handleMenuAddSuccess(menu, vm) {
                let row = this.formatterTreeAsyncRow(menu, {
                    formatterRow: data => {
                        data.expanded = false;
                        data.dataType = 'Menu';
                    },
                    //TODO 写你的格式化叶子节点逻辑
                    formatterNodeIsLeaf: row => row.childCount === 0
                });
                this.append(row, row.parentId ? row.parentId : row.menuGroupId);
            },
            handleMenuEditSuccess(menu, vm) {
                merge(this.treeAsyncSelectedNodeData, menu);
                if (this.treeAsyncSelectedNodeData.expanded) {
                    // 展开过了,要重置menu节点
                    //TODO 未实现
                }
            },
            handleDeleteRow(e, vm, {node, data}) {
                data[this.treeAsyncNodeDeleteLoading$] = true;
                if (data.dataType === 'Menu') {
                    this.$Ajax.delete(ApiGarMenuUrls.delete.menuByMenuId, {
                        moduleId: this.moduleId,
                        menuId: data.id
                    })
                        .before(() => data[this.treeAsyncNodeDeleteLoading$] = false)
                        .success('删除成功', () => {
                            this.remove(data);
                            vm.close();
                            this.routerToMenuTree({moduleId: this.moduleId});
                        });
                } else if (data.dataType === 'MenuGroup') {
                    this.$Ajax.delete(ApiGarMenuGroupUrls.delete.menuGroupByMenuGroupId, {
                        moduleId: this.moduleId,
                        menuGroupId: data.id
                    })
                        .before(() => data[this.treeAsyncNodeDeleteLoading$] = false)
                        .success('删除成功', () => {
                            this.remove(data);
                            vm.close();
                            this.routerToMenuTree({moduleId: this.moduleId});
                        });
                }
            },
            handleDblClickTreeAsyncNode({node, data}) {
                if (node.expanded) {
                    node.collapse();
                } else {
                    node.expand();
                }
            },
            handleSelectedTreeNode(data, node) {
                if (data.dataType === 'Menu') {
                    this.routerToMenuRouteRole({
                        moduleId: this.moduleId,
                        subModuleId: data.subModuleId,
                        menuId: data.id
                    });
                } else {
                    this.routerToMenuTree({moduleId: this.moduleId});
                }
            },
            handleChangeMenuGroupChecked({node, data, value}) {
                if (value) {
                    this.menuGroupCheckedLoading = true;
                    this.$Ajax.put(ApiGarMenuGroupUrls.put.menuGroupByMenuGroupId, {
                        moduleId: this.moduleId,
                        menuGroupId: data.id,
                        use: this.dict.menuGroupUseValue.TRUE
                    })
                        .success(true, () => {
                            data.use = this.dict.menuGroupUseValue.TRUE;
                        })
                        .notSuccess(() => data.checked = false)
                        .finally(() => this.menuGroupCheckedLoading = false);
                } else {
                    this.$confirm({
                        content: `如果您取消使用该菜单组,该菜单组下<span style="color: red">所有菜单</span>将解除与路由之间的关系,您确定要这么做吗？`,
                        width: '500px',
                        showClose: false,
                        onClickConfirmButton: (e, vm) => {
                            this.menuGroupCheckedLoading = true;
                            this.$Ajax.put(ApiGarMenuGroupUrls.put.menuGroupByMenuGroupId, {
                                moduleId: this.moduleId,
                                menuGroupId: data.id,
                                use: this.dict.menuGroupUseValue.FALSE
                            })
                                .success(true, () => {
                                    data.use = this.dict.menuGroupUseValue.FALSE;
                                    vm.close();
                                })
                                .notSuccess(() => data.checked = true)
                                .finally(() => this.menuGroupCheckedLoading = false);
                        },
                        onClickCancelButton: (e, vm) => {
                            data.checked = true;
                        }
                    });
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
            updateChildNodesMenuGroup(node, {menuGroupId}) {
                node.data.menuGroupId = menuGroupId;
                if (node.childNodes && node.childNodes.length > 0) {
                    node.childNodes.forEach(row => this.updateChildNodesMenuGroup(row, {menuGroupId}));
                }
            },
            handleDrop(draggingNode, dropNode, dropType, ev) {
                if (draggingNode.data.subModuleId !== dropNode.data.subModuleId) {
                    this.$message({content: '暂不支持跨模块拖动'});
                    return false;
                }
                this.treeAsyncLoading = true;
                this.$Ajax.put(ApiGarMenuUrls.put.dragMenuTreeNode, {
                    dragMenuId: draggingNode.data.id,
                    dropMenuId: dropNode.data.id,
                    dropType: dropType,
                    moduleId: this.moduleId,
                    dragSubModuleId: draggingNode.data.subModuleId,
                    dropSubModuleId: dropNode.data.subModuleId,
                    dragMenuGroupId: draggingNode.data.menuGroupId,
                    dropMenuGroupId: dropNode.data.menuGroupId
                })
                    .success('拖拽成功', () => {
                        if (draggingNode.data.subModuleId !== dropNode.data.subModuleId) {
                            //将drag及子孙node的subModuleId修改
                            this.updateChildNodesSubModule(draggingNode, dropNode.data);
                            //TODO 重置菜单路由 , 改变了父级会删除对应菜单路由数据
                        }
                        if (draggingNode.data.menuGroupId !== dropNode.data.menuGroupId) {
                            this.updateChildNodesMenuGroup(draggingNode, dropNode.data);
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
                        if (draggingNode.data.dataType === 'Menu' && dropNode.data.dataType === 'Menu') return true;
                        break;
                    case 'inner':
                        if (draggingNode.data.dataType === 'Menu' && dropNode.data.dataType === 'Menu') return true;
                        break;
                    case 'next':
                        if (draggingNode.data.dataType === 'Menu' && dropNode.data.dataType === 'Menu') return true;
                        break;
                    default:
                        throw new Error('拖动类型不正确');
                }
                return false;
            },
            allowDrag(draggingNode) {
                if (draggingNode.data.dataType === 'Menu') return true;
                return false;
            }
        },

        created() {
            this.getTreeAsyncRows();
        }
    }
</script>
