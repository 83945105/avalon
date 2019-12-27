<template>
    <div class="common-float-right">
        <!--语言选择-->
        <div v-if="frameModule.isLogin" class="blue-black-layout-header-system-menu">
            <we-menu :value="true"
                     mode="horizontal"
                     sub-menu-trigger="hover"
                     text-color="#ffffff"
                     background-color="#252525"
                     active-text-color="#ffffff"
                     active-background-color="#000000"
                     selected-text-color="#ffffff"
                     selected-background-color="#000000"
                     hover-text-color="#ffffff"
                     hover-background-color="#000000"
                     :width="140"
            >
                <we-menu-item :icon="false" icon-name="edit">
                    <template slot="content">
                        <div class="menu-text">{{languageTypeLabel}}</div>
                    </template>
                    <we-menu :sub-menu-horizontal-offset="-60" :width="140" subMenuMode="open"
                             slot="subMenu"
                             :accordion="false" placement="bottom-start">
                        <we-menu-item>
                            <template slot="panel">
                                <div class="blue-black-layout-header-system-menu-list" style="width: 140px">
                                    <ul class="menu-list">
                                        <li v-for="row in languageTypeList" @click="chooseLanguage(row)">
                                            {{row.label}}
                                        </li>
                                    </ul>
                                </div>
                            </template>
                        </we-menu-item>
                    </we-menu>
                </we-menu-item>
            </we-menu>
        </div>
        <!--/语言选择-->

        <div v-if="frameModule.isLogin" class="blue-black-layout-header-system-menu">
            <we-menu :value="true"
                     mode="horizontal"
                     sub-menu-trigger="hover"
                     text-color="#ffffff"
                     background-color="#252525"
                     active-text-color="#ffffff"
                     active-background-color="#000000"
                     selected-text-color="#ffffff"
                     selected-background-color="#000000"
                     hover-text-color="#ffffff"
                     hover-background-color="#000000"
                     :width="220"
            >
                <we-menu-item :icon="false" icon-name="edit">
                    <template slot="content">
                        <img class="menu-picture common-radius-all"
                             src="../../../../../../public/frame/images/face/user_face.png"/>
                        <div class="menu-text">{{frameModule.username}}</div>
                        <div class="common-clear"></div>
                    </template>
                    <we-menu :sub-menu-horizontal-offset="-60" :width="220" subMenuMode="open" slot="subMenu"
                             :accordion="false" placement="bottom-start">
                        <we-menu-item>
                            <template slot="panel">
                                <div class="blue-black-layout-header-system-menu-list">
                                    <div class="menu-list-content">
                                        <img class="menu-list-content-picture common-radius-all"
                                             src="../../../../../../public/frame/images/face/user_face.png"/>
                                        <div class="menu-list-content-info">
                                            <h3 class="menu-list-content-info-name">
                                                {{frameModule.username}}（{{frameModule.primaryRole &&
                                                frameModule.primaryRole.name}}）</h3>
                                            <div class="menu-list-content-info-shop"></div>
                                        </div>
                                        <div class="common-clear"></div>
                                    </div>
                                    <ul class="menu-list">
                                        <li>基本资料</li>
                                        <li>修改密码</li>
                                        <li>切换账号</li>
                                        <li v-if="frameModule.roles.length > 1"
                                            @click="frameModule.showSwitchPrimaryRole = true">切换角色
                                        </li>
                                        <li>系统设置</li>
                                    </ul>
                                    <div class="menu-list-sign-out" @click="handleClickLogout">安全退出平台</div>
                                </div>
                            </template>
                        </we-menu-item>
                    </we-menu>
                </we-menu-item>
            </we-menu>
        </div>
        <div v-else class="blue-black-layout-header-login-button" @click="showLogin = true">点我登录</div>
        <div class="common-clra"></div>
    </div>
</template>

<script>
    import {mapState} from 'vuex';
    import ApiGarUrls from "../../../../gar/urls/api-gar-urls.js";

    export default {

        name: "header-menu",

        data() {
            return {
                showLogin: false,
                languageTypeLabel: "中文",
                languageTypeList: [{
                    label: "中文",
                    value: "CHS"
                }, {
                    label: "English",
                    value: "English"
                }, {
                    label: "Español",
                    value: "Español"
                }]
            };
        },

        computed: {
            ...mapState(['frameModule']),
        },

        methods: {
            handleClickLogout(e) {
                new Promise((resolve, reject) => {
                    this.$Ajax.get(ApiGarUrls.get.logout)
                        .success('您已安全退出', resolve);
                }).then(() => {
                    return new Promise((resolve, reject) => {
                        this.$store.dispatch('setLogout').then(resolve);
                    });
                }).then(() => {
                    //退出成功
                    window.events.emit('logout-success');
                });
            },
        }
    }
</script>

<style scoped>

</style>