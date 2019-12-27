<template>
  <div>
    <!--头部菜单-->
    <div class="black-layout-header">
      <div class="black-layout-menu-btn" @click.stop="collapseLeftMenu = !collapseLeftMenu">
        <we-icon name="menu"></we-icon>
      </div>
      <div class="black-layout-header-logo">
        <!--<img class="system-logo" src="../../../../../../public/gar/images/logo.png"/>-->
        <h3 class="system-title first-element-introduction">线上支付</h3>
      </div>
      <div class="black-layout-header-system">
        <div class="black-layout-header-system-nav third-element-introduction">
          <we-menu :value="true"
                   mode="horizontal"
                   text-color="#ffffff"
                   background-color="transparent"
                   active-text-color="#2199ed"
                   active-background-color="#151515"
                   selected-text-color="#ffffff"
                   selected-background-color="#151515"
                   hover-text-color="#ffffff"
                   hover-background-color="#151515"
          >
            <template v-for="(row, index) in HeaderJson">
              <template v-if="row.name">
                <we-menu-item
                  :value="(row.selectedRouterNames && row.selectedRouterNames.includes($route.name)) || row.name === $route.name"
                  @selected="handleSelectedLeftItem(row)"
                >{{row.label}}
                </we-menu-item>
              </template>

            </template>
          </we-menu>
        </div>
        <!--用户信息-->
        <div class="black-layout-header-login-button" @click="showLoginLayer = true">点我登录</div>
        <div class="black-layout-header-system-user">
          <we-menu :value="true"
                   mode="horizontal"
                   sub-menu-trigger="hover"
                   text-color="#ffffff"
                   background-color="transparent"
                   active-text-color="#2199ed"
                   active-background-color="#151515"
                   selected-text-color="#ffffff"
                   selected-background-color="#151515"
                   hover-text-color="#ffffff"
                   hover-background-color="transparent"
                   :width="220"
          >
            <we-menu-item :icon="false" icon-name="edit">
              <template slot="content">
                <img class="user-face common-radius-all"
                     src="../../../../../../public/frame/images/face/user_face.png"/>
                <div class="user-name">超级管理员</div>
                <div class="common-clear"></div>
              </template>
              <we-menu :sub-menu-horizontal-offset="-60" :width="220" subMenuMode="open" slot="subMenu"
                       :accordion="false" placement="bottom-start">
                <we-menu-item>
                  <template slot="panel">
                    <div class="black-layout-header-system-user-menu">
                      <div class="user-menu-content">
                        <img class="user-menu-content-face common-radius-all"
                             src="../../../../../../public/frame/images/face/user_face.png"/>
                        <div class="user-menu-content-info">
                          <h3 class="user-info-name">超级管理员</h3>
                          <div class="user-info-shop">港味潮人馆</div>
                        </div>
                        <div class="common-clear"></div>
                      </div>
                      <ul class="user-menu-list">
                        <li>基本资料</li>
                        <li>修改密码</li>
                        <li>切换账号</li>
                        <li>切换组织</li>
                      </ul>
                      <div class="user-menu-sign-out">安全退出平台</div>
                    </div>
                  </template>
                </we-menu-item>
              </we-menu>
            </we-menu-item>
          </we-menu>
        </div>
        <div class="common-clra"></div>
      </div>
    </div>
    <!--/头部菜单-->

    <!--左侧菜单-->
    <div class="black-layout-left" :class="{'is-overflow': !collapseLeftMenu}">
      <we-menu :value="true"
               :sub-menu-trigger="collapseLeftMenu ? 'hover' : 'click'"
               :collapse="collapseLeftMenu"
               text-color="#cccccc"
               background-color="#252525"
               active-text-color="#ffffff"
               active-background-color="#151515"
               selected-text-color="#ffffff"
               selected-background-color="#2199ed"
               hover-text-color="#2199ed"
               hover-background-color="#151515"
               :width="menuWidth"
      >
        <template v-for="(row, index) in LeftJson">

          <template v-if="row.children && row.children.length > 0">
            <we-menu-item
              :value="(row.selectedRouterNames && row.selectedRouterNames.includes($route.name)) || row.name === $route.name"
              @selected="handleSelectedLeftItem(row)">
              <template slot="icon">
                <we-icon :name="row.iconName" style="font-size: 18px;"></we-icon>
              </template>
              {{row.label}}
              <template slot="subMenu">
                <we-tooltip :manual="!collapseLeftMenu" :content="row.label" placement="top-start">
                  <we-menu :width="menuWidth">
                    <template v-for="(item, idx) in row.children">
                      <we-menu-item
                        :value="(item.selectedRouterNames && item.selectedRouterNames.includes($route.name)) || item.name === $route.name"
                        @selected="handleSelectedLeftItem(item)"
                      >{{item.label}}
                      </we-menu-item>
                    </template>
                  </we-menu>
                </we-tooltip>
              </template>
            </we-menu-item>
          </template>

          <template v-else>
            <we-tooltip :manual="!collapseLeftMenu" :content="row.label" placement="right">
              <we-menu-item
                :value="(row.selectedRouterNames && row.selectedRouterNames.includes($route.name)) || row.name === $route.name"
                @selected="handleSelectedLeftItem(row)">
                <template slot="icon">
                  <we-icon :name="row.iconName" style="font-size: 18px;"></we-icon>
                </template>
                {{row.label}}
                <template v-if="row.children && row.children.length > 0">
                  <we-menu slot="subMenu" :width="menuWidth">
                    <template v-for="(item, idx) in row.children">
                      <we-menu-item
                        :value="(item.selectedRouterNames && item.selectedRouterNames.includes($route.name)) || item.name === $route.name"
                        @selected="handleSelectedLeftItem(item)"
                      >{{item.label}}
                      </we-menu-item>
                    </template>
                  </we-menu>
                </template>
              </we-menu-item>
            </we-tooltip>
          </template>

        </template>
      </we-menu>
    </div>
    <!--/左侧菜单-->

    <!--右侧内容-->
    <div class="black-layout-right"
         :style="{'left': !collapseLeftMenu ? `${menuWidth}px` : `50px`}">
      <tab-mode v-if="layoutModule.pageMode === 'tab'"
                v-model="tabName"
                :data="tabData"
                @tab-click="(i, name) => $router.push({name: name})"
                @tab-remove="handleClickRemoveTab"
      ></tab-mode>
      <router-view v-else></router-view>
    </div>
    <!--/右侧内容-->

  </div>
</template>

<script>
  import Link from '../../../mixins/link.js';
  import HeaderJson from '../config/header.json';
  import LeftJson from '../config/left.json';
  import DefineStepsLayout from '../driver/defineSteps-layout.js';

  import {mapState} from 'vuex';
  import TabMode from "./TabMode.vue";
  import {humpToString} from "../../../../../utils/util.js";

  export default {

    name: "Layout",
    components: {TabMode},
    mixins: [Link],

    data() {
      return {
        collapseLeftMenu: true,
        openUserMenu: false,
        HeaderJson: HeaderJson,
        LeftJson: LeftJson,

        tabName: '',
        tabData: [],

        menuWidth: 300
      };
    },

    computed: {
      ...mapState(['userModule', 'layoutModule'])
    },

    methods: {
      handleSelectedLeftItem(item) {
        if (item.children) {
          return;
        }
        if (this.layoutModule.pageMode === 'tab') {
          for (let i in this.tabData) {
            if (this.tabData[i].name === item.name) {
              this.tabName = item.name;
              this.$router.push({name: item.name});
              return;
            }
          }
          this.tabData.push(item);
          this.tabName = item.name;
          if (this.layoutModule.keepAliveCacheForTabEnabled) {
            if (item.cache !== false) {
            }
          }
          this.$router.push({name: item.name});
        } else {
          this.$router.push({name: item.name});
        }
      },
      initTabData(leftJson) {
        let tabData = [];
        for (let i in leftJson) {
          if (leftJson[i].open === true) {
            tabData.push(leftJson[i]);
          }
          if (leftJson[i].children && leftJson[i].children.length > 0) {
            tabData.concat(this.initTabData(leftJson[i].children));
          }
        }
        return tabData;
      },
      searchLeftJsonItem(name, leftJson) {
        for (let i in leftJson) {
          if (leftJson[i].name === name) {
            return leftJson[i];
          } else if (leftJson[i].children) {
            let l = this.searchLeftJsonItem(name, leftJson[i].children);
            if (l !== undefined) {
              return l;
            }
          }
        }
        return undefined;
      },
      handleClickRemoveTab(i, name) {
        if (this.tabData.length < 2) {
          return;
        }
        if (i > 0) {
          this.tabName = this.tabData[i - 1].name;
          this.$router.push({name: this.tabName});
          this.tabData.splice(i, 1);
        } else if (i === 0) {
          this.tabName = this.tabData[1].name;
          this.$router.push({name: this.tabName});
          this.tabData.splice(i, 1);
        }
        if (this.layoutModule.closeTabAndRemoveKeepAliveCacheEnabled) {
          for (let j = 0; j < this.layoutModule.tabKeepAliveNames.length; j++) {
            if (name === this.layoutModule.tabKeepAliveNames[j]) {
              this.layoutModule.tabKeepAliveNames.splice(j, 1);
              break;
            }
          }
        }
      },
      windowResizeListener(e) {
        this.$store.dispatch('initWindowWidthAsync', e);
        this.$store.dispatch('initWindowHeightAsync', e);
      },
    },

    created() {
      // window.addEventListener('resize', this.windowResizeListener);

      let tabData = this.initTabData(this.LeftJson);
      if (this.$route.meta.type && this.$route.meta.type === 'menu') {
        let name = this.$route.name;
        for (let i in tabData) {
          if (tabData[i].name === name) {
            tabData.splice(i, 1);
            break;
          }
        }
        let router = this.searchLeftJsonItem(name, this.LeftJson);
        if (router) {
          tabData.push(router);
        }
        if (this.layoutModule.keepAliveCacheForTabEnabled) {
          tabData.forEach(tab => {
            if (tab.cache !== false) {
            }
          });
        }
        this.tabData = tabData;
        this.tabName = name;
      }

    },

    mounted() {
      if (this.layoutModule.isDriverRead === false) {
        this.$store.dispatch('startDriver', DefineStepsLayout);
      }
    },

    beforeDestroy() {
      window.removeEventListener('resize', this.windowResizeListener);
    }
  }
</script>

<style scoped>

</style>
