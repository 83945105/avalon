<template>
  <div>
    <error-page title="抱歉暂无数据，请点击左侧菜单树查询数据。"></error-page>
  </div>
</template>

<script>

  import ErrorPage from "../../../../../../components/error-page/src/ErrorPage.vue";

  export default {
    name: "menu-manage-init",

    components: {ErrorPage}

  }
</script>
