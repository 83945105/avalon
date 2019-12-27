<template>
  <gar-login @login-success="handleLoginSuccess"></gar-login>
</template>

<script>

  import Login from "../../../components/login/src/Login.vue";

  export default {

    components: {GarLogin: Login},

    name: "Login",

    methods: {
      handleLoginSuccess() {
        window.events.emit('login-success');
      }
    }
  }
</script>
