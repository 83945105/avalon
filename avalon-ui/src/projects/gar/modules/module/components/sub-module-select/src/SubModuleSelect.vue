<template>
  <el-select v-model="selectValue" class="common-w-large" size="small" @change="handleChangeSelectValue">
    <el-option v-if="showAllOption" :label="allOptionLabel" :value="allOptionValue"></el-option>
    <el-option v-for="(row, index) in selectRows"
               :key="row.id"
               :label="row.name"
               :value="row.id">
    </el-option>
  </el-select>
</template>

<script>

  import Select from '../../../../../../../assets/template/mixins/select.js';
  import ApiGarSubModuleUrls from "../../../urls/api-gar-sub-module-urls.js";
  import merge from "../../../../../../../utils/merge.js";

  export default {
    name: "sub-module-select",

    mixins: [Select],

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
            this.selectRows = data.rows;
            if (this.initSelectedFirst && this.selectRows.length > 0) {
              this.selectValue = this.selectRows[0].id;
            }
          });
      },
    },

    created() {
      this.getSelectRows();
    }
  }
</script>
