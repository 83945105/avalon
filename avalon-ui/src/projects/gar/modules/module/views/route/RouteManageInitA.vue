<template>
  <error-page>
    <template slot="title">
      请点击路由树下路由视图
      <we-icon name="gar-view" class="common-fc-primary"></we-icon>
      获取信息。
    </template>
  </error-page>
</template>

<script>

  import ErrorPage from "../../../../../../components/error-page/src/ErrorPage.vue";

  export default {
    name: "route-manage-init",

    components: {ErrorPage}

  }
</script>
