<template>
  <div class="black-layout-tabs">
    <el-tabs v-model="name" type="card"
             @tab-click="handleClickTabPane"
             @tab-remove="handleRemoveTabPane">
      <template v-for="(row, index) in data">
        <el-tab-pane :key="row.name"
                     :label="row.label"
                     :name="row.name"
                     :lazy="row.lazy"
                     :closable="row.closable && data.length > 1"
        >
        </el-tab-pane>
      </template>
    </el-tabs>
    <!--<transition name="fade" mode="out-in">-->
    <div class="black-layout-right-content">
      <keep-alive :include="layoutModule.tabKeepAliveNames">
        <router-view/>
      </keep-alive>
    </div>
    <!--</transition>-->
  </div>
</template>

<script>

  import {mapState} from 'vuex';

  export default {
    name: "TabMode",

    props: {
      value: {
        type: String,
        default: ''
      },
      data: {
        type: Array,
        default() {
          return [];
        }
      }
    },

    data() {
      return {
        name: this.value
      };
    },

    computed: {
      ...mapState(['layoutModule'])
    },

    watch: {
      value(v) {
        this.name = v;
      },
      name(v) {
        this.$emit('input', v);
      }
    },

    methods: {
      key() {
        return this.$route.name;
      },
      handleClickTabPane(pane) {
        let name = pane.name;
        let len = this.data.length;
        for (let i = 0; i < len; i++) {
          if (name === this.data[i].name) {
            this.$emit('tab-click', i, name);
            return;
          }
        }
      },
      handleRemoveTabPane(name) {
        let len = this.data.length;
        for (let i = 0; i < len; i++) {
          if (name === this.data[i].name) {
            this.$emit('tab-remove', i, name);
            return;
          }
        }
      }
    }

  }
</script>

<style scoped>

</style>
