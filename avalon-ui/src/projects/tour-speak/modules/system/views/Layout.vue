<template>
    <div>
        <div class="hw-ui-layout-header">
            <div class="common-w-main-1 common-margin-auto">
                <div class="hw-ui-layout-header-logo">
                    <!--<img class="system-logo" src="../../../../../../public/dms/images/logo.png"/>-->
                    <h3 class="system-title first-element-introduction">淮山课堂管理平台</h3>
                </div>
                <!--头部菜单-->
                <div class="hw-ui-layout-header-system">
                    <div class="hw-ui-layout-header-system-nav third-element-introduction">
                        <header-menu value="System"
                                     :data="headerMenuData"
                                     :width="400"
                                     text-color="#ffffff"
                                     background-color="#269be0"
                                     active-background-color="#2083bd"
                                     active-line-color="#2083bd"
                                     selected-text-color="#ffffff"
                                     selected-background-color="#2083bd"
                                     hover-text-color="#ffffff"
                                     hover-background-color="#2083bd"
                                     @selected-menu-item="handleSelectedHeaderMenuItem"
                                     style="height: 60px;"
                        ></header-menu>
                    </div>
                </div>
                <!--/头部菜单-->
                <!--用户信息-->
                <div v-if="frameModule.isLogin" class="hw-ui-layout-header-user">
                    <we-menu :value="true"
                             mode="horizontal"
                             sub-menu-trigger="hover"
                             text-color="#ffffff"
                             background-color="#269be0"
                             active-text-color="#2199ed"
                             active-background-color="#2083bd"
                             selected-text-color="#ffffff"
                             selected-background-color="#2083bd"
                             hover-text-color="#ffffff"
                             hover-background-color="#2083bd"
                             :width="220"
                             style="height: 60px"
                    >
                        <we-menu-item :icon="false" icon-name="edit">
                            <template slot="content">
                                <img class="user-face common-radius-all"
                                     src="../../../../../../public/frame/images/face/user_face.png"/>
                                <div class="user-name common-ellipsis">{{frameModule.username}}</div>
                                <div class="common-clear"></div>
                            </template>
                            <we-menu :sub-menu-vertical-offset="0" :width="172" subMenuMode="open" slot="subMenu"
                                     :accordion="false" placement="bottom-end">
                                <we-menu-item>
                                    <template slot="panel">
                                        <div class="hw-ui-layout-header-user-menu">
                                            <ul class="user-menu-list">
                                                <li v-if="frameModule.roles.length > 1"
                                                    @click="frameModule.showSwitchPrimaryRole = true">切换角色
                                                </li>
                                                <li @click="handleClickLogout">安全退出平台</li>
                                            </ul>

                                        </div>
                                    </template>
                                </we-menu-item>
                            </we-menu>
                        </we-menu-item>
                    </we-menu>
                </div>
                <div v-else class="hw-ui-layout-header-login-button" @click="showLogin = true">点我登录</div>
                <div class="common-clra"></div>
            </div>
        </div>

        <div class="common-full common-scroll-v" style="top: 60px; padding-top: 15px; background-color: transparent;">
            <div class="common-w-main-1 common-margin-auto">
                <!--左侧菜单-->
                <div class="hw-ui-layout-left">
                    <left-menu :value="$route.name"
                               :data="leftMenuData"
                               width="160px"
                               text-color="#666666"
                               background-color="#ffffff"
                               active-text-color="#ffffff"
                               active-background-color="#f9f9f9"
                               active-line-color="#2083bd"
                               selected-text-color="#ffffff"
                               selected-background-color="#269be0"
                               hover-text-color="#269be0"
                               hover-background-color="#e4f5ff"
                               @selected-menu-item="handleSelectedMenuItem"
                    ></left-menu>
                </div>
                <!--/左侧菜单-->

                <!--右侧内容-->
                <div class="hw-ui-layout-right tour-layout-right">
                    <!--<tab-mode v-show="false" v-if="layoutModule.pageMode === 'tab'"-->
                    <!--:data="tabData"-->
                    <!--&gt;</tab-mode>-->
                    <router-view></router-view>
                </div>
                <!--/右侧内容-->
                <div class="common-clra"></div>
            </div>
        </div>
        <!--登录-->
        <we-layer v-model="showLogin"
                  :width="100"
                  :height="100"
                  :show-header="false"
                  :show-footer="false"
                  :show-close="false"
                  :mask-closable="false"
                  :esc-closeable="false"
        >
            <login @login-success="handleLoginSuccess"></login>
        </we-layer>
        <!--/登录-->

        <!--主角色切换-->
        <switch-primary-role-window v-model="frameModule.showSwitchPrimaryRole"
                                    :roles="frameModule.roles"
                                    :primary-role="frameModule.primaryRole"
                                    @selected-role="handleSelectedPrimaryRole"
        ></switch-primary-role-window>
        <!--/主角色切换-->

    </div>
</template>

<script>

    import Gar from '../../../../gar/mixins/gar.js';
    import Link from '../../../mixins/link.js';
    import DefineStepsLayout from '../driver/defineSteps-layout.js';

    import LocalHeaderMenuData from '../config/local-header-menu-data.json';
    import LocalLeftMenuData from '../config/local-left-menu-data.json';

    import {mapState, mapGetters} from 'vuex';
    import TabMode from "../../../components/tab/src/TabMode.vue";
    import LeftMenu from "../../../components/menu/src/LeftMenu.vue";
    import HeaderMenu from "../../../components/menu/src/HeaderMenu.vue";
    import SwitchPrimaryRoleWindow from "../../../../../components/frame/role/SwitchPrimaryRoleWindow.vue";
    import Login from "../../../components/login/src/Login.vue";
    import ApiGarUrls from "../../../../gar/urls/api-gar-urls.js";
    import {caseMenusToTabs, Menu} from "../../../components/menu/src/menu.js";

    export default {

        name: "layout",

        components: {Login, SwitchPrimaryRoleWindow, HeaderMenu, LeftMenu, TabMode},

        mixins: [Gar, Link],

        data() {
            return {
                collapseLeftMenu: true,

                tabData: [],

                showLogin: false,

                showBasicData: false,

                showModifyPassword: false

            };
        },

        computed: {
            ...mapState(['frameModule', 'layoutModule']),
            ...mapGetters({
                serverHeaderMenuData: 'getCurrentHeaderMenuData',
                serverLeftMenuData: 'getCurrentLeftMenuData'
            }),
            headerMenuData() {
                return LocalHeaderMenuData.map(obj => new Menu(obj)).concat(this.serverHeaderMenuData.map(obj => new Menu(obj)));
            },
            leftMenuData() {
                return LocalLeftMenuData.map(obj => new Menu(obj)).concat(this.serverLeftMenuData.map(obj => new Menu(obj)));
            }
        },

        watch: {
            headerMenuData(val) {
                this.tabData = caseMenusToTabs(val.concat(this.leftMenuData));
            },
            leftMenuData(val) {
                this.tabData = caseMenusToTabs(this.headerMenuData.concat(val));
            }
        },

        methods: {
            handleSelectedPrimaryRole({primaryRole, index, roles}) {
                window.events.emit('selected-primary-role', {primaryRole, index, roles});
            },
            handleSelectedHeaderMenuItem({row, $index}) {
                if (row.value === 'System') return;
                this.pageToModule(row.value);
            },
            handleSelectedMenuItem({row, $index}) {
                if (row.routeValues.includes(this.$route.name)) return;
                if (row.clickToRouteValue) {
                    this.$router.push({name: row.clickToRouteValue});
                }
            },
            windowResizeListener(e) {
                this.$store.dispatch('initWindowWidthAsync', e);
                this.$store.dispatch('initWindowHeightAsync', e);
            },
            handleClickLogin(e) {
                window.events.emit('show-login-window');
            },
            handleClickLogout(e) {
                new Promise((resolve, reject) => {
                    this.$Ajax.get(ApiGarUrls.get.logout)
                        .success('您已安全退出', resolve);
                }).then(data => {
                    return new Promise((resolve, reject) => {
                        this.$store.dispatch('setLogout').then(() => resolve(data));
                    });
                }).then(data => {
                    if (data.records.logoutUrl) {
                        window.location.href = data.records.logoutUrl;
                    } else {
                        //退出成功
                        window.events.emit('logout-success');
                    }
                });
            },
            handleLoginSuccess() {
                window.events.emit('login-success');
                this.showLogin = false;
            }
        },

        created() {
            // window.addEventListener('resize', this.windowResizeListener);
            window.events.on('show-login-window', () => {
                this.showLogin = true;
            });
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
