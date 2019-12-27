<template>
  <error-page type="403"></error-page>
</template>

<script>
  import ErrorPage from "./ErrorPage.vue";

  export default {
    name: "no-authority",
    components: {ErrorPage}
  }
</script>
