<template>
    <router-view></router-view>
</template>

<script>

    import Gar from '../../../../gar/mixins/gar.js';
    import Link from '../../../mixins/link.js';

    export default {

        name: "layout",

        mixins: [Gar, Link]

    }
</script>
