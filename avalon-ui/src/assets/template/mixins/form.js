export default {

  data() {
    return {
      formRefValue$: 'form',
      formLoading: false
    };
  },


  methods: {
    resetForm() {
      this.$refs[this.formRefValue$].resetFields();
    }
  }

}
