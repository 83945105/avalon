<!--异步树-->
<template>
  <div>
    <el-button :loading="treeAsyncLoading" type="primary" size="small" @click="getTreeAsyncRootRows">重新加载</el-button>
    <div v-loading="treeAsyncLoading && treeAsyncSkeleton === false">
      <we-skeleton v-model="treeAsyncSkeleton"
                   animation
                   :paragraph-rows="8"
                   @reload="getTreeAsyncRootRows">
        <el-tree :data="treeAsyncRootRows"
                 :props="treeAsyncProps"
                 :node-key="treeAsyncNodeKey"
                 :lazy="true"
                 :load="getTreeAsyncNodes"
                 empty-text="暂无资源"
                 highlight-current
        >
        </el-tree>
      </we-skeleton>
    </div>
  </div>
</template>

<script>

  import TreeAsync from '../../../../../../assets/template/mixins/treeAsync.js';

  export default {
    name: "element-ui-tree-async",
    mixins: [TreeAsync],

    data() {
      return {
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
      getTreeAsyncRootRows() {
        this.treeAsyncLoading = true;
        this.$Ajax
          .mock(require('./ElementUITreeAsync.json').getTreeAsyncRootRows)
          //TODO 写你的逻辑
          .get('')
          .success(true, data => {
            this.treeAsyncSkeleton = false;
            //由于骨架屏原因会导致tree俩次初始化,这里如果不设置异步,那么tree第二次初始化的时候找不到data属性
            //所以要先关闭骨架屏,异步给data设置值
            this.$nextTick(() => {
              this.treeAsyncRootRows = this.formatterTreeAsyncRows(data.rows, {
                //TODO 写你的格式化叶子节点逻辑
                formatterNodeIsLeaf: row => row.childCount === 0
              });
            });
          })
          .catch(() => this.treeAsyncSkeleton = 500)
          .finally(() => this.treeAsyncLoading = false);
      },
      getTreeAsyncNodes(node, resolve) {
        //加载子节点,level代表树层级数
        if (node.level >= 1) {
          this.$Ajax
            .mock(require('./ElementUITreeAsync.json').getTreeAsyncChildNodes)
            //TODO 写你的逻辑
            .get('')
            .success(true, data => {
              resolve(this.formatterTreeAsyncRows(data.rows, {
                //TODO 写你的格式化叶子节点逻辑
                formatterNodeIsLeaf: row => row.childCount === 0
              }));
            });
        }
      },
    },

    created() {
      this.getTreeAsyncRootRows();
    }
  }
</script>

<style scoped>

</style>
