<template>
  <div>
    <!--左侧-->
    <div class="gar-layout-tree-left" style="width: 400px">
      <router-view name="left"></router-view>
    </div>
    <!--/左侧-->

    <!--右侧-->
    <div class="gar-layout-tree-right" style="left: 421px">
      <router-view name="right"></router-view>
    </div>
    <!--/右侧-->
  </div>
</template>

<script>

  import ErrorPage from "../../../../../../components/error-page/src/ErrorPage.vue";

  export default {
    name: "menu-manage",

    components: {ErrorPage}

  }
</script>
