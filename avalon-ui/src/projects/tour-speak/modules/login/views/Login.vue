<template>
    <gar-login ref="login" @login-success="handleLoginSuccess"></gar-login>
</template>

<script>

    import Login from "../../../components/login/src/Login.vue";

    export default {

        components: {GarLogin: Login},

        name: "Login",

        methods: {
            onKeyUpEvent(e) {
                if (e.keyCode === 13 || e.which === 13 || e.key === 'Enter') {
                    this.$refs.login.submit(e);
                }
            },
            handleLoginSuccess() {
                window.events.emit('login-success');
            }
        },

        beforeDestroy() {
            window.removeEventListener('keyup', this.onKeyUpEvent);
        },

        created() {
            window.addEventListener('keyup', this.onKeyUpEvent);
        }
    }
</script>
