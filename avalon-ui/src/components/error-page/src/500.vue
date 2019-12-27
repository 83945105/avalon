<template>
  <error-page type="500"></error-page>
</template>

<script>
  import ErrorPage from "./ErrorPage.vue";

  export default {
    name: "load-fail",
    components: {ErrorPage}
  }
</script>
