<template>
  <el-select v-model="selectValue" placeholder="请选择视图类型" :size="size" @change="handleChangeSelectValue">
    <el-option v-if="showAllOption" :label="allOptionLabel" :value="allOptionValue"></el-option>
    <el-option v-for="(row, index) in selectRows"
               :key="row.value"
               :label="row.label"
               :value="row.value">
    </el-option>
  </el-select>
</template>

<script>

  import Global from '../../../../../mixins/global.js';
  import Select from '../../../../../../../assets/template/mixins/select.js';

  export default {

    name: "view-type-select",

    mixins: [Global, Select],

    props: {
      size: {
        type: String,
        default: 'small'
      }
    },

    created() {
      this.selectRows = this.dict.templateTypes;
      if (this.initSelectedFirst && this.selectRows.length > 0) {
        this.selectValue = this.selectRows[0].value;
      }
    }

  }
</script>
