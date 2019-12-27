<template>
  <el-select v-model="selectValue" :size="size" @change="handleChangeSelectValue" :clearable="clearable">
    <el-option v-if="showAllOption" :label="allOptionLabel" :value="allOptionValue"></el-option>
    <el-option v-for="(row, index) in selectRows"
               :key="index"
               :label="row.label"
               :value="row.value">
    </el-option>
  </el-select>
</template>

<script>

  import Select from '../../../../../../../assets/template/mixins/select.js';
  import {Data as GlobalData} from "../../../../../mixins/global.js";

  export default {

    name: "sign-in-select",

    mixins: [Select],

    props: {
      size: {
        type: String,
        default: 'small'
      },
      clearable: {
        type: Boolean,
        default: true
      }
    },

    methods: {
      getSelectRows() {
        setTimeout(() => {
          this.selectRows = GlobalData.dict.signs;
          if (this.initSelectedFirst && this.selectRows.length > 0) {
            this.selectValue = this.selectRows[0].value;
          }
        }, 0);
      }
    },

    created() {
      this.getSelectRows();
    }

  }
</script>
