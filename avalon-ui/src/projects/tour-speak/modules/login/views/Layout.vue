<template>
  <router-view></router-view>
</template>

<script>

  import Gar from '../../../../gar/mixins/gar.js';
  import Link from '../../../mixins/link.js';
  import {mapState} from 'vuex';

  export default {

    name: "layout",

    mixins: [Gar, Link],

    computed: {
      ...mapState(['frameModule', 'layoutModule'])
    }

  }
</script>
