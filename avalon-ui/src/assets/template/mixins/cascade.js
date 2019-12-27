export default {

  props: {
    value: Array
  },

  data() {
    return {
      selectValues: this.value || []
    };
  },

  watch: {
    value(val) {
      this.selectValues = val;
    },
    selectValues(val) {
      this.$emit('input', val);
    }
  },

  methods: {
    handleChangeSelectValues(values) {
      this.$emit('change', values, this);
    }
  }

}
