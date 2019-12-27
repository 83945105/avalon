<template>
  <div>
    <div>
      <el-input v-model="params.likeText"
                size="small"
                clearable
                placeholder="请输入内容"
                prefix-icon="el-icon-search"
                style="width: 200px">
      </el-input>
      <span class="btn-p-left">
        <el-button type="success"
                   size="small"
                   :loading="treeAsyncLoading"
                   @click="getTreeAsyncRows">根节点搜索</el-button>
      </span>
    </div>
    <div v-loading="treeAsyncLoading && treeAsyncSkeleton === false"
         class="tree-module-default text-selection"
         style="width: 480px;margin-top: 5px;">
      <el-autocomplete disabled
                       size="small"
                       placeholder="定位资源"
                       :debounce="1000"
                       value-key="locationName"
                       prefix-icon="el-icon-search" style="width: 50%">
      </el-autocomplete>
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
                 show-checkbox
                 empty-text="暂无资源"
                 highlight-current
                 @check="handleCheckTreeAsyncNode"
        >
          <span slot-scope="{node, data}">
            <i v-if="!node.expanded && data.type === dict.resourceTypeValue.NODE" class="we-icon-folder-o"></i>
            <i v-else-if="node.expanded && data.type === dict.resourceTypeValue.NODE"
               class="we-icon-folder-open-o"></i>
            <i v-else-if="data.type === dict.resourceTypeValue.URL" class="we-icon-link"></i>
            <i v-else-if="data.type === dict.resourceTypeValue.PERMISSION"
               class="we-icon-jurisdiction-shield"></i>
            <span @dblclick="handleDblClickTreeAsyncNode({node, data})">{{node.label}}</span>
          </span>
        </el-tree>
      </we-skeleton>
    </div>
  </div>
</template>

<script>

  import Global from '../../../../mixins/global.js';

  const Params = {
    likeText: ''
  };

  import TreeAsync from '../../../../../../assets/template/mixins/treeAsync.js';
  import ApiGarResourceUrls from "../../urls/api-gar-resource-urls.js";
  import merge from "../../../../../../utils/merge.js";

  export default {

    name: "resource-tree",

    mixins: [Global, TreeAsync],

    props: {
      moduleId: {
        type: String,
        required: true
      },
      queryParams: Object,
      queryChildParams: Object
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
        treeAsyncNodeKey: 'id'

      };
    },

    methods: {
      append(row, parentKey) {
        if (!parentKey) return;
        row = this.formatterTreeAsyncRow(row, {
          formatterRow: row => row.expanded = false,
          //TODO 写你的格式化叶子节点逻辑
          formatterNodeIsLeaf: row => row.childCount === 0
        });
        const Tree = this.$refs[this.treeAsyncRef];
        Tree.append(row, Tree.getNode(parentKey));
        // this.addTreeAsyncRowChild(this.findRow(this.treeAsyncRows, parentKey), row);
      },
      insertAfterRoot(row) {
        row = this.formatterTreeAsyncRow(row, {
          formatterRow: row => row.expanded = false,
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
        this.$emit('check', {}, {checkedNodes: [], checkedKeys: [], halfCheckedKeys: [], halfCheckedNodes: []}, this);
        this.treeAsyncLoading = true;
        let params = merge({moduleId: this.moduleId}, this.params);
        if (this.queryParams) {
          merge(params, this.queryParams);
        }
        this.$Ajax
          .get(ApiGarResourceUrls.get.listRootResource, params)
          .success(true, data => {
            this.treeAsyncSkeleton = false;
            //由于骨架屏原因会导致tree俩次初始化,这里如果不设置异步,那么tree第二次初始化的时候找不到data属性
            //所以要先关闭骨架屏,异步给data设置值
            this.$nextTick(() => {
              this.treeAsyncRows = this.formatterTreeAsyncRows(data.rows, {
                formatterRow: row => row.expanded = false,
                //TODO 写你的格式化叶子节点逻辑
                formatterNodeIsLeaf: row => row.childCount === 0
              });
            });
          })
          .notSuccess(() => this.treeAsyncSkeleton = 500)
          .catch(() => this.treeAsyncSkeleton = 500)
          .finally(() => this.treeAsyncLoading = false);
      },
      getTreeAsyncNodes(node, resolve) {
        let params = {
          moduleId: this.moduleId
        };
        if (this.queryChildParams) {
          merge(params, this.queryChildParams);
        }
        //加载子节点,level代表树层级数
        if (node.level >= 1) {
          this.$Ajax
            .get(ApiGarResourceUrls.get.listResourceByParentId, [node.data.id], {
              params: params
            })
            .success(true, data => {
              const rows = this.formatterTreeAsyncRows(data.rows, {
                formatterRow: row => row.expanded = false,
                //TODO 写你的格式化叶子节点逻辑
                formatterNodeIsLeaf: row => row.childCount === 0
              });
              resolve(rows);
            });
        }
      },
      handleCheckTreeAsyncNode(data, state) {
        this.$emit('check', data, state, this);
      },
      handleDblClickTreeAsyncNode({node, data}) {
        if (data.childCount > 0) {
          if (node.expanded) {
            node.collapse();
          } else {
            node.expand();
          }
        }
      }
    },

    created() {
      this.getTreeAsyncRows();
    }
  }
</script>

<style scoped>
  .btn-p-left {
    padding-left: 3px;
  }

  .tree-module-default {
    padding: 10px;
    border: 1px solid #E5E5E5;
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

  .text-selection *::selection {
    background-color: transparent;
  }

  .text-selection *::-moz-selection {
    background-color: transparent;
  }
</style>
