<template>
    <div>
        <!--左侧菜单-->
        <div class="blue-black-layout-left" :class="{'is-overflow': !collapseLeftMenu}">
            <left-menu :value="$route.name"
                       :data="leftMenus"
                       :collapse="collapseLeftMenu"
                       :width="200"
                       text-color="#cccccc"
                       background-color="#252525"
                       active-text-color="#2199ed"
                       active-background-color="transparent"
                       selected-text-color="#2199ed"
                       selected-background-color="transparent"
                       hover-text-color="#2199ed"
                       hover-background-color="#151515"
                       @selected-menu-item="handleSelectedMenuItem"
            ></left-menu>
        </div>
        <!--/左侧菜单-->

        <!--右侧内容-->
        <div class="blue-black-layout-right gar-layout-right"
             :style="{'left': !collapseLeftMenu ? `${menuWidth}px` : `50px`}">

            <!--头部-->
            <div class="blue-black-layout-header">
                <div class="blue-black-layout-menu-btn" @click.stop="collapseLeftMenu = !collapseLeftMenu">
                    <we-icon name="menu"></we-icon>
                </div>
                <div class="blue-black-layout-header-logo">
                    <img class="system-logo" src="../../../../public/gar/images/logo.png"/>
                    <h3 class="system-title first-element-introduction">权限管理</h3>
                </div>
                <div class="blue-black-layout-header-system">
                    <!--头部菜单-->
                    <div class="blue-black-layout-header-system-nav third-element-introduction">
                        <header-menu :value="$route.name"
                                     :data="headerMenus"
                                     :width="400"
                                     text-color="#ffffff"
                                     background-color="#252525"
                                     active-text-color="#ffffff"
                                     active-background-color="#000000"
                                     selected-text-color="#ffffff"
                                     selected-background-color="#000000"
                                     hover-text-color="#ffffff"
                                     hover-background-color="#000000"
                                     @selected-menu-item="handleSelectedMenuItem"
                        ></header-menu>
                    </div>
                    <!--/头部菜单-->


                    <!--用户信息-->
                    <router-view name="header"/>
                    <!--/用户信息-->

                </div>
            </div>
            <!--/头部-->

            <div v-if="frameModule.pageMode === 'tab'" class="blue-black-layout-tabs">
                <tab-mode :data="tabData"></tab-mode>
                <div class="blue-black-layout-right-content">
                    <!--<transition name="fade" mode="out-in">-->
                    <!--<keep-alive :include="tabKeepAliveNames">-->
                        <router-view/>
                    <!--</keep-alive>-->
                    <!--</transition>-->
                </div>
            </div>
            <div v-else class="blue-black-layout-right-content" style="top: 65px">
                <router-view/>
            </div>
        </div>
        <!--/右侧内容-->

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

    import {mapState, mapGetters} from 'vuex';
    import TabMode from "./TabMode.vue";
    import LeftMenu from "./LeftMenu.vue";
    import HeaderMenu from "./HeaderMenu.vue";
    import SwitchPrimaryRoleWindow from "../../frame/role/SwitchPrimaryRoleWindow.vue";
    import {Menu, caseMenusToTabs} from "../../../vuex-modules/frame-store.js";

    export default {

        name: "weview-layout",

        components: {SwitchPrimaryRoleWindow, HeaderMenu, LeftMenu, TabMode},

        data() {
            return {
                collapseLeftMenu: true,
                tabData: [],
                menuWidth: 200
            };
        },

        computed: {
            ...mapState(['frameModule']),
            ...mapGetters({
                headerMenuData: 'getHeaderMenuData',
                leftMenuData: 'getLeftMenuData',
                tabKeepAliveNames: 'getTabKeepAliveNames'
            }),
            headerMenus() {
                return this.headerMenuData.map(obj => new Menu(obj));
            },
            leftMenus() {
                return this.leftMenuData.map(obj => new Menu(obj));
            }
        },

        watch: {
            headerMenus(val) {
                this.tabData = caseMenusToTabs(val.concat(this.leftMenus));
            },
            leftMenus(val) {
                this.tabData = caseMenusToTabs(this.headerMenus.concat(val));
            }
        },

        methods: {
            handleSelectedPrimaryRole({primaryRole, index, roles}) {
                window.events.emit('selected-primary-role', {primaryRole, index, roles});
            },
            handleSelectedMenuItem({row, $index}) {
                if (row.routeValues.includes(this.$route.name)) return;
                if (row.clickToRouteValue) {
                    this.$router.push({name: row.clickToRouteValue});
                }
            }
        }
    }
</script>
