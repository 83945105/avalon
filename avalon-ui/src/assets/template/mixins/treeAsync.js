export default {

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
      treeAsyncNodeKey: 'id',

      treeAsyncRef: 'treeAsync',
      treeAsyncSkeleton: true,
      treeAsyncLoading: false,
      treeAsyncRows: [],

      //节点删除加载特效
      treeAsyncNodeDeleteLoading$: 'delete_loading$',

      treeAsyncShowAdd: false,
      treeAsyncAddLoading: false,
      treeAsyncShowEdit: false,
      treeAsyncEditLoading: false,
      treeAsyncSelectedNode: {},
      treeAsyncSelectedNodeData: {},

      // 自动展开键值
      treeAsyncAutoExpandKeys: []


    };
  },

  methods: {
    formatterTreeAsyncRow(row, {formatterRow, formatterNodeIsLeaf, formatterNodeChildren} = {}) {
      row[this.treeAsyncNodeDeleteLoading$] = false;
      formatterRow && formatterRow(row);
      if (formatterNodeIsLeaf) {
        row[this.treeAsyncProps.isLeaf] = formatterNodeIsLeaf(row);
      } else {
        row[this.treeAsyncProps.isLeaf] = true;
      }
      if (formatterNodeChildren) {
        row[this.treeAsyncProps.children] = formatterNodeChildren(row);
      } else {
        row[this.treeAsyncProps.children] = [];
      }
      return row;
    },
    formatterTreeAsyncRows(rows, {formatterRow, formatterNodeIsLeaf} = {}) {
      rows.forEach(row => this.formatterTreeAsyncRow(row, {formatterRow, formatterNodeIsLeaf}));
      return rows;
    },
    /**
     * 查找数据
     * @param key
     */
    findRow(rows, key) {
      if (!rows || rows.length === 0 || !key) return;
      let row;
      for (let i in rows) {
        if (`${rows[i][this.treeAsyncNodeKey]}` === `${key}`) return rows[i];
        row = this.findRow(rows[i][this.treeAsyncProps.children], key);
        if (row) return row;
      }
      return undefined;
    },
    isTreeAsyncRowHasChildren(row) {
      return row[this.treeAsyncProps.children] && row[this.treeAsyncProps.children].length > 0;
    },
    setTreeAsyncRowChildren(row, children) {
      if (!children) return;
      row[this.treeAsyncProps.children] = children;
    },
    addTreeAsyncRowChild(row, child) {
      if (!child) return;
      if (!row[this.treeAsyncProps.children]) {
        this.$set(row, this.treeAsyncProps.children, []);
      }
      row[this.treeAsyncProps.children].push(child);
    },
    expandTreeNodeByKeys({key}) {
      this.$refs[this.treeAsyncRef].store.nodesMap[key].expand();
    }
  }

}
