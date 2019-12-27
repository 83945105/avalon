<template>
  <div>
    <!--头部菜单-->
    <div class="blue-black-layout-header">
      <div class="blue-black-layout-menu-btn" @click.stop="collapseLeftMenu = !collapseLeftMenu">
        <i class="el-icon-menu"></i>
      </div>
      <div class="blue-black-layout-header-logo">
        <img class="system-logo" src="../../../../../../public/gar/images/logo.png"/>
        <h3 class="system-title first-element-introduction">框架平台</h3>
      </div>
      <div class="blue-black-layout-header-system">
        <div class="blue-black-layout-header-system-nav third-element-introduction">
          <el-menu :default-active="$route.name"
                   mode="horizontal"
                   class="el-menu-vertical-demo"
                   background-color="#333333"
                   text-color="#fff"
                   active-text-color="#ffd04b"
                   @select="handleSelectedMenuItem"
          >
            <template v-for="(row, index) in headerMenuData">

              <template v-if="row.children && row.children.length > 0">
                <el-submenu :index="row.value">

                  <template v-if="row.name" slot="title">
                    <i :class="row.iconName"></i>
                    <span slot="title">{{row.name}}</span>
                  </template>

                  <template v-for="(item, idx) in row.children">
                    <el-menu-item :index="item.value">{{item.name}}</el-menu-item>
                  </template>

                </el-submenu>
              </template>

              <template v-else>
                <el-menu-item :index="row.value">
                  <i :class="row.iconName"></i>
                  <span slot="title">{{row.name}}</span>
                </el-menu-item>
              </template>

            </template>
          </el-menu>
        </div>
        <!--用户信息-->
        <!--        <div class="blue-black-layout-header-login-button">点我登录
                </div>-->
        <div class="blue-black-layout-header-system-user">
          <el-menu mode="horizontal"
                   class="el-menu-vertical-demo"
                   background-color="#333333"
                   text-color="#fff"
                   active-text-color="#ffd04b"
          >
            <template v-for="(row, index) in []">
              <template v-if="row.children && row.children.length > 0">
                <el-submenu :index="row.index">
                  <template v-if="row.name" slot="title">
                    <img class="user-face common-radius-all"
                         src="../../../../../../public/frame/images/face/user_face.png"/>
                    <div class="user-name">超级管理员</div>
                  </template>
                  <template v-for="rowSub in row.children">
                    <el-menu-item :index="rowSub.index">
                      {{rowSub.label}}
                    </el-menu-item>
                  </template>
                </el-submenu>
              </template>
            </template>
          </el-menu>
        </div>
        <div class="common-clra"></div>
      </div>
    </div>
    <!--/头部菜单-->

    <!--左侧菜单-->
    <div class="blue-black-layout-left menu-left" :class="{'is-overflow': !collapseLeftMenu}">
      <el-menu :default-active="$route.name"
               style="border: none;"
               class="el-menu-vertical-demo"
               background-color="#252525"
               text-color="#fff"
               active-text-color="#ffd04b"
               :collapse-transition="false"
               :collapse="collapseLeftMenu"
               @select="handleSelectedMenuItem"
      >
        <template v-for="(row, index) in leftMenuData">

          <template v-if="row.children && row.children.length > 0">
            <el-submenu :index="row.value">

              <template v-if="row.name" slot="title">
                <i :class="row.iconName"></i>
                <span slot="title">{{row.name}}</span>
              </template>

              <template v-for="(item, idx) in row.children">
                <el-menu-item :index="item.value">{{item.name}}</el-menu-item>
              </template>

            </el-submenu>
          </template>

          <template v-else>
            <el-menu-item :index="row.value">
              <i :class="row.iconName"></i>
              <span slot="title">{{row.name}}</span>
            </el-menu-item>
          </template>
        </template>

      </el-menu>

    </div>
    <!--/左侧菜单-->

    <!--右侧内容-->
    <div class="blue-black-layout-right helper-layout-right"
         :style="{'left': !collapseLeftMenu ? `${menuWidth}px` : `64px`}">
      <tab-mode v-if="layoutModule.pageMode === 'tab'"
                :data="tabData"
      ></tab-mode>
      <router-view v-else></router-view>
    </div>
    <!--/右侧内容-->

  </div>
</template>

<script>

  import Link from '../../../mixins/link.js';
  import DefineStepsLayout from '../driver/defineSteps-layout.js';

  import LocalHeaderMenuData from '../config/local-header-menu-data.json';
  import LocalLeftMenuData from '../config/local-left-menu-data.json';

  import {mapState} from 'vuex';
  import TabMode from "../../../../../components/frame/tab/src/TabMode.vue";
  import {caseMenusToTabs} from "../../../../../components/frame/menu/src/menu.js";
  import {Menu} from "../../../../../components/frame/menu/src/menu.js";

  export default {

    name: "layout",

    components: {TabMode},

    mixins: [Link],

    data() {
      return {
        collapseLeftMenu: true,
        tabData: [],
        menuWidth: 200
      };
    },

    computed: {
      ...mapState(['frameModule', 'layoutModule']),
      headerMenuData() {
        return LocalHeaderMenuData.map(obj => new Menu(obj));
      },
      leftMenuData() {
        return LocalLeftMenuData.map(obj => new Menu(obj));
      }
    },

    watch: {
      headerMenuData: {
        immediate: true,
        handler(val) {
          this.tabData = caseMenusToTabs(val.concat(this.leftMenuData));
        }
      },
      leftMenuData: {
        immediate: true,
        handler(val) {
          this.tabData = caseMenusToTabs(this.headerMenuData.concat(val));
        }
      }
    },

    methods: {
      handleSelectedMenuItem(index, indexPath) {
        this.$router.push({name: index});
      },
      windowResizeListener(e) {
        this.$store.dispatch('initWindowWidthAsync', e);
        this.$store.dispatch('initWindowHeightAsync', e);
      },
    },

    created() {
      // window.addEventListener('resize', this.windowResizeListener);
    },

    mounted() {
      if (this.layoutModule.isDriverRead === false) {
        this.$store.dispatch('startDriver', DefineStepsLayout);
      }
    },

    beforeDestroy() {
      // window.removeEventListener('resize', this.windowResizeListener);
    }
  }
</script>

<style>
  .menu-left .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }

  .menu-left .el-submenu.is-active {
    color: #ffd04b;
  }
</style>
