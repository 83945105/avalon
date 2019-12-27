export default {

  props: {
    value: String,
    // 初始化是否选中第一条数据
    initSelectedFirst: Boolean,
    // 是否显示全部选项
    showAllOption: Boolean,
    allOptionLabel: {
      type: String,
      default: '全部'
    },
    allOptionValue: {
      default: ''
    },
    clearable: Boolean,
    // 排除的值
    excludeValues: {
      type: Array,
      default() {
        return [];
      }
    },
    // 指定包含的值, 优先级大于排除的值, 为空时表示所有都包含
    includeValues: {
      type: Array,
      default() {
        return [];
      }
    }
  },

  data() {
    return {
      selectValue: this.value || '',
      selectRows: []
    };
  },

  computed: {
    // 计算出实际列表值
    rows() {
      let rows = [];
      if (this.selectRows.length === 0) {
        rows = this.selectRows;
      } else if (this.includeValues.length > 0) {
        rows = this.selectRows.filter(row => this.includeValues.indexOf(row.value) !== -1);
      } else if (this.excludeValues.length > 0) {
        rows = this.selectRows.filter(row => this.excludeValues.indexOf(row.value) === -1);
      } else {
        rows = this.selectRows;
      }
      return rows;
    }
  },

  watch: {
    value(val) {
      this.selectValue = val;
    },
    selectValue(val) {
      this.$emit('input', val);
    }
  },

  methods: {
    handleChangeSelectValue(value) {
      this.$emit('change', value, this);
    }
  }

}
