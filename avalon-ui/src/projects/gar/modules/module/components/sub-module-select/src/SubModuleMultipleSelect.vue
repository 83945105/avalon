<!--子模块下拉框-->
<template>
  <el-select v-model="multipleSelectValues" class="common-w-large" size="small" multiple
             @change="handleChangeSelectValue">
    <el-option v-for="(row, index) in multipleSelectRows"
               :key="row.id"
               :label="row.name"
               :value="row.id">
    </el-option>
  </el-select>
</template>

<script>

  import MultipleSelect from '../../../../../../../assets/template/mixins/multipleSelect.js';
  import ApiGarSubModuleUrls from "../../../urls/api-gar-sub-module-urls.js";
  import merge from "../../../../../../../utils/merge.js";

  export default {
    name: "sub-module-multiple-select",

    mixins: [MultipleSelect],

    props: {
      moduleId: {
        type: String,
        required: true
      }
    },

    methods: {
      getSelectRows() {
        this.$Ajax.get(ApiGarSubModuleUrls.get.listSubModule, merge({moduleId: this.moduleId}))
          .success(true, data => {
            this.multipleSelectRows = data.rows;
          });
      },
    },

    created() {
      this.getSelectRows();
    }
  }
</script>
