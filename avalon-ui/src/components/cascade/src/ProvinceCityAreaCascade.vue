<template>
  <el-cascader v-model="selectValues"
               :options="region"
               size="small"
               expand-trigger="hover"
               change-on-select
               class="common-w-all"
               style="height: 32px"
               @change="handleChangeSelectValues">
  </el-cascader>
</template>

<script>

  import Region from '../../../assets/json/region.json';
  import Cascade from '../../../assets/template/mixins/cascade.js';

  export default {

    name: "province-city-area-cascade",

    mixins: [Cascade],

    data() {
      return {
        region: Region
      };
    }
  }
</script>
