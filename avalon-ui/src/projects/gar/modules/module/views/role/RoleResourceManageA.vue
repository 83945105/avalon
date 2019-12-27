<template>
  <div>
    <div class="avalon-merlin-default-search-bar full avalon-merlin-a-scroll">
      <el-input v-model="searchResourceTree.searchTreeText" size="small" clearable placeholder="请输入内容"
                prefix-icon="el-icon-search" style="width: 200px">
      </el-input>
      <span class="btn-p-left">
          <el-button type="success" size="small" :loading="searchResourceTreeLoading"
                     @click="getSearchResourceTreeList">根节点搜索</el-button>
        </span>
    </div>

    <div :style="{'height':tableHeight+'px'}" style="margin-top: 15px;">
      <div v-if="searchResourceTreeList.length > 0" class="list-tools avalon-merlin-hidden">
        <!--<div v-if="searchResourceTreeList.length === 0" class="avalon-merlin-lfloat">-->
        <!--<el-tag size="small">未搜索到节点</el-tag>-->
        <!--</div>-->
        <div class="avalon-merlin-lfloat">
          <el-tag size="small">双击节点名称可以展开/收缩节点</el-tag>
        </div>
        <div class="avalon-merlin-lfloat" style="margin-left: 10px">
          <el-tag size="small">点击鼠标右键开启快捷菜单(未实现)</el-tag>
        </div>
        <div class="avalon-merlin-lfloat" style="margin-left: 10px">
          <el-tag size="small">勾选复选框启用资源</el-tag>
        </div>
        <!--        <div class="avalon-merlin-rfloat">
                  <span class="btn-p-left">
                    <el-button type="danger" size="small" disabled>导出</el-button>
                  </span>
                </div>-->
      </div>
      <div class="tree-module" :style="{'top':treeTopNum+'px'}">
        <el-autocomplete v-show="searchResourceTreeList.length > 0"
                         size="small" placeholder="资源定位"
                         v-model="searchLocationResource"
                         :debounce="1000"
                         value-key="locationName"
                         :fetch-suggestions="handleLocationSearchResourceTree"
                         @select="handleSelectLocationSearchResource"
                         prefix-icon="el-icon-search" style="width: 40%">
        </el-autocomplete>
        <div class="tree-module-inner avalon-merlin-v-scroll">
          <el-tree :data="searchResourceTreeList"
                   :highlight-current="true"
                   :draggable="true"
                   :check-strictly="true"
                   lazy
                   :expand-on-click-node="false"
                   :props="{
                    label: 'name',
                    isLeaf: 'leaf'
                   }"
                   :load="getSearchResourceTreeChildNodeList"
                   node-key="id"
                   ref="searchResourceTree"
                   empty-text="暂无资源"
          >
                <span class="custom-tree-node" slot-scope="{node, data}">
                  <span>
                    <el-checkbox v-if="data.type !== 'NODE'"
                                 :value="data.checked"
                                 @change="handleCheckedSearchResourceTreeNode(node, data, $event)"></el-checkbox>
                    <i v-if="!node.expanded && data.type === dict.resourceType.node" class="we-icon-folder-o"></i>
                    <i v-else-if="node.expanded && data.type === dict.resourceType.node"
                       class="we-icon-folder-open-o"></i>
                    <i v-else-if="data.type === dict.resourceType.url" class="we-icon-link"></i>
                    <i v-else-if="data.type === dict.resourceType.permission" class="we-icon-jurisdiction-shield"></i>
                    <em @dblclick="handleDblClickTreeNode(node, data)">{{node.label}}</em>
                  </span>
                  <span v-if="data.type === 'NODE' && data.childCount > 0">
                    <el-button :loading="data.grantNodeChildLoading" style="color: #409eff" type="text" size="mini"
                               @click.stop="handleClickGrantResourcesInNodeToRole(node, data)">使用该节点下所有资源</el-button>
                    <em>/</em>
                    <el-button :loading="data.unGrantNodeChildLoading" style="color: #409eff" type="text" size="mini"
                               @click.stop="handleClickUnGrantResourcesInNodeToRole(node, data)">取消该节点下所有资源</el-button>
                    <em>&nbsp;&nbsp;&nbsp;&nbsp;</em>
                    <el-button :loading="data.grantNodeAllChildLoading" style="color: #f56c6c" type="text" size="mini"
                               @click.stop="handleClickGrantAllChildResourcesInNode(node, data)">使用该节点及所有子节点下所有资源</el-button>
                    <em>/</em>
                    <el-button :loading="data.unGrantNodeAllChildLoading" style="color: #f56c6c" type="text" size="mini"
                               @click.stop="handleClickUnGrantAllChildResourcesInNode(node, data)">取消该节点及所有子节点下所有资源</el-button>
                  </span>
                </span>
          </el-tree>
        </div>
      </div>
    </div>

  </div>
</template>

<script>

  import Global from '../../../../mixins/global.js';

  const GlobalData = Global.data();

  const Limit = {
    currentPage: 1,
    pageCount: 1,
    pageSize: 10,
    total: 0
  };

  export default {

    name: "role-resource-manage",

    mixins: [Global],

    data() {
      return {
        tableHeight: 0,

        roleResourceList: [],

        searchType: GlobalData.dict.searchTypes[0].value,
        searchResourceTreeLoading: false,
        searchResourceTree: {
          searchTreeText: ''
        },
        searchLocationResource: '',
        searchLocationExpandIds: [],
        searchResourceTreeList: [],
        resourceList: [],
        limit: Object.assign({}, Limit),
        searchResource: {
          searchText: ''
        },
        searchResourceTreeModel: false
      };
    },

    props: {
      moduleId: {
        type: String,
        required: true
      },
      roleId: {
        type: String,
        required: true
      },
      role: {
        type: String,
        required: true
      },
      roleName: {
        type: String,
        required: true
      }
    },

    computed: {
      treeTopNum() {
        if (this.searchResourceTreeList.length > 0) {
          return 113;
        }
        return 62;
      },
      searchResourceTreeRootIds() {
        let ids = [];
        for (let tree of this.searchResourceTreeList) {
          ids.push(tree.id);
        }
        return ids;
      },
      hasResourceIdsMap() {//拥有的资源ID Map
        let map = new Map();
        for (let rr of this.roleResourceList) {
          map.set(rr.resId, true);
        }
        return map;
      }
    },

    methods: {
      getRoleResourceListByRoleId() {
        this.$Ajax.get(RoleResourceManagerUrl.get.roleResourceListByRoleId, {
          moduleId: this.moduleId,
          roleId: this.roleId
        })
          .success(true, data => {
            this.roleResourceList = data.rows;
          });
      },
      searchResourceTreeListProcess(resourceTreeList) {
        let rows = [];
        let map = this.hasResourceIdsMap;
        resourceTreeList.forEach(row => {
          row.children = row.children ? row.children : [];
          row.leaf = !row.childCount || row.childCount.length === 0;
          row.disabled = !row.status || row.status === this.dict.status.disabled || row.type !== 'NODE';
          row.checked = !!map.get(row.id);
          row.grantNodeChildLoading = false;
          row.unGrantNodeChildLoading = false;
          row.grantNodeAllChildLoading = false;
          row.unGrantNodeAllChildLoading = false;
          rows.push(row);
        });
        return rows;
      },
      formatterResourceType(row, column, cellValue, index) {
        for (let i in this.dict.resourceTypes) {
          if (this.dict.resourceTypes[i].value === cellValue) {
            return this.dict.resourceTypes[i].text;
          }
        }
        return '未知';
      },
      handleSizeChange(pageSize) {
        this.limit.pageSize = pageSize;
        this.getResourcePageList();
      },
      handleCurrentChange(currentPage) {
        this.limit.currentPage = currentPage;
        this.getResourcePageList();
      },
      getSearchResourceTreeList() {
        this.searchResourceTreeLoading = true;
        this.$Ajax.get(ResourceManagerUrl.get.rootResourceList, {
          moduleId: this.moduleId,
          searchText: this.searchResourceTree.searchTreeText
        })
          .success(true, data => {
            this.searchResourceTreeList = this.searchResourceTreeListProcess(data.rows);
          })
          .finally(() => this.searchResourceTreeLoading = false);
      },
      handleLocationSearchResourceTree(queryString, cb) {
        if (!queryString) {
          cb([]);
          return;
        }
        this.$Ajax.get(ResourceManagerUrl.get.locationResourceList, {
          moduleId: this.moduleId,
          rootIds: this.searchResourceTreeRootIds.toString(),
          searchText: queryString
        }).success(true, data => {
          cb(data.rows);
        });
      },
      handleSelectLocationSearchResource(row) {
        this.searchLocationExpandIds = row.parentIds.substring(1).split("/");
        this.expandSearchResourceNode();
      },
      expandSearchResourceNode() {//该方法需要getResourceTreeChildNodeList方法success回调异步配合
        //先尝试找到父节点的第一个节点
        let node = this.$refs.searchResourceTree.getNode(this.searchLocationExpandIds[0]);
        if (node) {
          //如果找到就把当前节点id从存储的节点中移除
          this.searchLocationExpandIds = this.searchLocationExpandIds.slice(1);
          if (node.expanded === false) {
            //如果节点未展开先展开
            node.expand();
            //继续尝试展开下一级节点
            this.expandSearchResourceNode();
          } else {
            //已经展开过了,继续尝试展开下一级节点
            this.expandSearchResourceNode();
          }
        }
        //节点不存在,由getResourceTreeChildNodeList异步回调继续尝试展开
      },
      getSearchResourceTreeChildNodeList(node, resolve) {
        this.$Ajax.get(ResourceManagerUrl.get.childResourceList, {
          moduleId: this.moduleId,
          parentId: node.data.id
        })
          .success(true, data => {
            let rows = this.searchResourceTreeListProcess(data.rows);
            node.data.children = rows;
            node.data.childCount = rows.length;
            node.data.open = true;//设置状态为展开
            resolve(rows);
            if (this.searchLocationExpandIds && this.searchLocationExpandIds.length > 0) {
              let node = this.$refs.searchResourceTree.getNode(this.searchLocationExpandIds[0]);
              this.searchLocationExpandIds = this.searchLocationExpandIds.slice(1);
              if (node && node.expanded === false) {
                node.expand();
              }
            }
            if (this.newLocationExpandIds && this.newLocationExpandIds.length > 0) {
              let node = this.$refs.newResourceTree.getNode(this.newLocationExpandIds[0]);
              this.newLocationExpandIds = this.newLocationExpandIds.slice(1);
              if (node && node.expanded === false) {
                node.expand();
              }
            }
          });
      },
      handleCheckedSearchResourceTreeNode(node, row, value) {
        if (value) {//选中
          this.$Ajax.post(RoleResourceManagerUrl.post.grantResourceToRole, {
            moduleId: this.moduleId,
            roleId: this.roleId,
            resourceId: row.id
          })
            .success(true, () => {
              row.checked = true;
              this.hasResourceIdsMap.set(row.id, true);
            });
        } else {
          this.$Ajax.post(RoleResourceManagerUrl.post.unGrantResourceToRole, {
            moduleId: this.moduleId,
            roleId: this.roleId,
            resourceId: row.id
          })
            .success(true, () => {
              row.checked = false;
              this.hasResourceIdsMap.delete(row.id);
            });
        }
      },
      handleDblClickTreeNode(node, data) {
        if (data.childCount > 0) {
          if (node.expanded) {
            node.collapse();
          } else {
            node.expand();
          }
        }
      },
      handleClickGrantResourcesInNodeToRole(node, data) {
        data.grantNodeChildLoading = true;
        this.$Ajax.post(RoleResourceManagerUrl.post.grantResourcesInNodeToRole, {
          moduleId: this.moduleId,
          nodeId: data.id,
          roleId: this.roleId
        })
          .success(true, res => {
            if (data.children) {
              data.children.forEach(c => c.checked = true);
            }
            res.rows.forEach(id => {
              this.hasResourceIdsMap.set(id, true);
            });
          })
          .finally(() => data.grantNodeChildLoading = false);
      },
      handleClickUnGrantResourcesInNodeToRole(node, data) {
        data.unGrantNodeChildLoading = true;
        this.$Ajax.post(RoleResourceManagerUrl.post.unGrantResourcesInNodeToRole, {
          moduleId: this.moduleId,
          nodeId: data.id,
          roleId: this.roleId
        })
          .success(true, res => {
            if (data.children) {
              data.children.forEach(c => c.checked = false);
            }
            res.rows.forEach(id => {
              this.hasResourceIdsMap.delete(id);
            });
          })
          .finally(() => data.unGrantNodeChildLoading = false);
      },
      checkedChildNode(children) {
        for (let c of children) {
          c.checked = true;
          if (c.children) {
            this.checkedChildNode(c.children);
          }
        }
      },
      handleClickGrantAllChildResourcesInNode(node, data) {
        data.grantNodeAllChildLoading = true;
        this.$Ajax.post(RoleResourceManagerUrl.post.grantAllChildResourcesInNodeToRole, {
          moduleId: this.moduleId,
          nodeId: data.id,
          roleId: this.roleId
        })
          .success(true, res => {
            if (data.children) {
              this.checkedChildNode(data.children);
            }
            res.rows.forEach(id => {
              this.hasResourceIdsMap.set(id, true);
            });
          })
          .finally(() => data.grantNodeAllChildLoading = false);
      },
      unCheckedChildNode(children) {
        for (let c of children) {
          c.checked = false;
          if (c.children) {
            this.unCheckedChildNode(c.children);
          }
        }
      },
      handleClickUnGrantAllChildResourcesInNode(node, data) {
        data.unGrantNodeAllChildLoading = true;
        this.$Ajax.post(RoleResourceManagerUrl.post.unGrantAllChildResourcesInNodeToRole, {
          moduleId: this.moduleId,
          nodeId: data.id,
          roleId: this.roleId
        })
          .success(true, res => {
            if (data.children) {
              this.unCheckedChildNode(data.children);
            }
            res.rows.forEach(id => {
              this.hasResourceIdsMap.delete(id);
            });
          })
          .finally(() => data.unGrantNodeAllChildLoading = false);
      }
    },

    created() {
      this.getRoleResourceListByRoleId();
    },

    mounted() {
      this.tableHeight = document.body.clientHeight - 220;
    }
  }
</script>

<style scoped>
  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 8px;
  }

  .btn-p-left {
    padding-left: 3px;
  }

  .tree-module {
    position: absolute;
    top: 138px;
    bottom: 15px;
    left: 15px;
    right: 15px;
    padding: 10px;
    border: 1px solid #E5E5E5;
  }

  .tree-module-inner {
    position: absolute;
    top: 52px;
    bottom: 10px;
    left: 11px;
    right: 8px;
  }

  .tree-module-default {
    padding: 10px;
    border: 1px solid #E5E5E5;
  }

  .list-tools {
    padding: 13px;
    background-color: #FAFAFA;
    border-top: 1px solid #EEEEEE;
  }

  .module-tools {
    display: block;
    position: absolute;
    top: -6px;
    left: 0;
    right: 0;
    z-index: 2;
    color: #FFFFFF;
  }

  .module-tools i {
    cursor: pointer;
    font-size: 11px;
    width: 14px;
    height: 14px;
    line-height: 14px;
    text-align: center;
    display: inline-block;
    /*background-color: rgba(0, 0, 0, 0.45);*/
    background-color: #AAAAAA;
  }

  .module-tools i:hover {
    /*background-color: rgba(0, 0, 0, 0.6);*/
    background-color: #999999;
  }

  .module-tools .tools-left {
    float: left;
  }

  .module-tools .tools-right {
    float: right;
  }

  .el-table__body-wrapper {
    overflow: inherit;
  }
</style>
