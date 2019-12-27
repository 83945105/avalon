export default {

  props: {
    value: Array
  },

  data() {
    return {
      multipleSelectValues: this.value || [],
      multipleSelectRows: []
    };
  },

  watch: {
    value(val) {
      this.multipleSelectValues = val;
    },
    multipleSelectValues(val) {
      this.$emit('input', val);
    }
  },

  methods: {
    handleChangeSelectValue(value) {
      this.$emit('change', value, this);
    }
  }

}
