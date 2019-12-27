<template>
  <div>
    <!--左侧-->
    <div class="gar-layout-tree-left">
      <router-view name="left"></router-view>
    </div>
    <!--/左侧-->

    <!--右侧-->
    <div class="gar-layout-tree-right">
      <router-view name="right"></router-view>
    </div>
    <!--/右侧-->
  </div>
</template>

<script>

  export default {
    name: "route-manage"
  }
</script>
