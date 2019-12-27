<template>
  <error-page type="404"></error-page>
</template>

<script>
  import ErrorPage from "./ErrorPage.vue";

  export default {
    name: "not-found",
    components: {ErrorPage}
  }
</script>
