<template>
    <div class="scene">
        <svg version="1.1"
             id="dc-spinner"
             xmlns="http://www.w3.org/2000/svg"
             x="0px" y="0px"
             width="38"
             height="38"
             viewBox="0 0 38 38"
             preserveAspectRatio="xMinYMin meet"
        >
            <text x="10" y="19" font-family="Monaco" font-size="2.8px" style="letter-spacing:0.6" fill="grey">正在加载配置
                <animate attributeName="opacity"
                         values="0;1;0" dur="1.8s"
                         repeatCount="indefinite">
                </animate>
            </text>
            <text x="15.5" y="24" font-family="Monaco" font-size="2.8px" style="letter-spacing:0.6" fill="grey">请稍后
                <animate
                        attributeName="opacity"
                        values="0;1;0" dur="1.8s"
                        repeatCount="indefinite">
                </animate>
            </text>
            <path fill="#dddddd" d="M20,35c-8.271,0-15-6.729-15-15S11.729,5,20,5s15,6.729,15,15S28.271,35,20,35z M20,5.203
    C11.841,5.203,5.203,11.841,5.203,20c0,8.159,6.638,14.797,14.797,14.797S34.797,28.159,34.797,20
    C34.797,11.841,28.159,5.203,20,5.203z">
            </path>

            <path fill="#dddddd" d="M20,33.125c-7.237,0-13.125-5.888-13.125-13.125S12.763,6.875,20,6.875S33.125,12.763,33.125,20
    S27.237,33.125,20,33.125z M20,7.078C12.875,7.078,7.078,12.875,7.078,20c0,7.125,5.797,12.922,12.922,12.922
    S32.922,27.125,32.922,20C32.922,12.875,27.125,7.078,20,7.078z">
            </path>

            <path fill="#009ccc" stroke="#009ccc" stroke-width="0.6027" stroke-miterlimit="10" d="M5.203,20
			c0-8.159,6.638-14.797,14.797-14.797V5C11.729,5,5,11.729,5,20s6.729,15,15,15v-0.203C11.841,34.797,5.203,28.159,5.203,20z">
                <animateTransform attributeName="transform"
                                  type="rotate"
                                  from="0 20 20"
                                  to="360 20 20"
                                  calcMode="spline"
                                  keySplines="0.4, 0, 0.2, 1"
                                  keyTimes="0;1"
                                  dur="2s"
                                  repeatCount="indefinite"></animateTransform>
            </path>

            <path fill="#d83800" stroke="#d83800" stroke-width="0.2027" stroke-miterlimit="10" d="M7.078,20
  c0-7.125,5.797-12.922,12.922-12.922V6.875C12.763,6.875,6.875,12.763,6.875,20S12.763,33.125,20,33.125v-0.203
  C12.875,32.922,7.078,27.125,7.078,20z">
                <animateTransform attributeName="transform"
                                  type="rotate"
                                  from="0 20 20"
                                  to="360 20 20"
                                  dur="1.8s"
                                  repeatCount="indefinite"></animateTransform>
            </path>
        </svg>
        <!--主角色切换-->
        <switch-primary-role-window v-model="showSwitchPrimaryRole"
                                    :roles="roles"
                                    :primary-role="primaryRole"
                                    @selected-role="handleSelectedPrimaryRole"
        ></switch-primary-role-window>
        <!--/主角色切换-->
    </div>
</template>

<script>

    //使用混合
    import Gar from '../../../../gar/mixins/gar.js';
    import Link from '../../../mixins/link.js';
    import {mapState} from 'vuex';
    import ProjectConf from '../../../config/project.conf.js';
    import ApiGarUrls from "../../../../gar/urls/api-gar-urls.js";
    import {
        Role,
        parseMenuRows,
        parseRouteRows,
        getLoginCacheKey,
        getPrimaryRoleCacheKey,
        getHeaderMenuRowsCacheKey,
        getLeftMenuRowsCacheKey,
        getRouteRowsCacheKey
    } from "../../../../../vuex-modules/frame-store.js";
    import SwitchPrimaryRoleWindow from "../../../../../components/frame/role/SwitchPrimaryRoleWindow.vue";
    import {getCache, removeCache, setCache} from "../../../../../utils/cache.js";

    export default {

        name: "options-loading",
        components: {SwitchPrimaryRoleWindow},
        mixins: [Gar, Link],

        data() {
            return {
                user: {},
                showSwitchPrimaryRole: false,
                primaryRole: undefined,
                roles: []
            };
        },

        computed: {
            ...mapState(['frameModule']),
            userId() {
                return this.user.id;
            },
            admin() {
                return this.user.admin;
            }
        },

        methods: {
            getOnline() {
                //获取用户信息
                this.$Ajax.get(ApiGarUrls.get.online)
                    .success(true, data => {
                        this.user = data.user;
                        // 缓存登录信息
                        const loginCacheKey = getLoginCacheKey();
                        setCache({key: loginCacheKey, data: JSON.stringify({userId: this.userId})});
                        const roles = data.roles;
                        //解析角色
                        const primaryRoleCacheKey = getPrimaryRoleCacheKey({key: this.userId});
                        if (!roles || roles.length === 0) {
                            // 没有角色
                            this.primaryRole = undefined;
                            this.roles = [];
                            // 当前用户没有角色, 清除当前用户主角色缓存
                            removeCache({key: primaryRoleCacheKey});
                            this.loadOptions();
                        } else if (roles.length === 1) {
                            // 只有一个角色
                            this.primaryRole = new Role(roles[0]);
                            this.roles = roles.map(role => new Role(role));
                            // 覆盖掉当前用户的缓存角色
                            setCache({key: primaryRoleCacheKey, data: JSON.stringify(this.primaryRole)});
                            this.loadOptions();
                        } else if (roles.length > 1) {
                            // 有多个角色
                            this.roles = roles.map(role => new Role(role));
                            // 从缓存中尝试拿出主角色
                            const primaryRoleJson = getCache({key: primaryRoleCacheKey});
                            if (!primaryRoleJson) {
                                //没有缓存, 让用户选择
                                this.showSwitchPrimaryRole = true;
                                return;
                            }
                            // 判断缓存的主角色是否为当前用户拥有的角色,判断方式,字符串比对
                            for (let i in this.roles) {
                                if (JSON.stringify(this.roles[i]) === primaryRoleJson) {
                                    // 缓存主角色是当前用户已有角色
                                    this.primaryRole = new Role(this.roles[i]);
                                    this.loadOptions();
                                    return;
                                }
                            }
                            // 走到这一步,说明用户缓存的主角色不是当前已有角色列表中的,让用户选择
                            this.showSwitchPrimaryRole = true;
                        }
                    });
            },
            handleSelectedPrimaryRole({primaryRole, index, roles}) {
                // 用户选择完主角色, 将主角色缓存起来
                this.primaryRole = primaryRole;
                const primaryRoleCacheKey = getPrimaryRoleCacheKey({key: this.userId});
                setCache({key: primaryRoleCacheKey, data: JSON.stringify(primaryRole)});
                this.loadOptions();
            },
            loadOptions() {
                // 1：加载各个模块的用户数据、路由数据、菜单数据
                Promise.all([...ProjectConf.moduleNames.filter(moduleName => moduleName !== 'login').map(moduleName => new Promise((moduleResolve, moduleReject) => {
                    Promise.all([new Promise((resolve, reject) => {
                        //获取路由信息
                        this.getRouteTreeBySubModuleValue({
                            moduleId: ProjectConf.projectName,
                            subModuleValue: moduleName,
                            successCallback: data => resolve(data)
                        });
                    }), new Promise((resolve, reject) => {
                        // 获取头部菜单信息
                        this.getMenuTreeBySubModuleValueMenuGroupType({
                            moduleId: ProjectConf.projectName,
                            subModuleValue: moduleName,
                            menuGroupType: 'HEADER_MENU',
                            successCallback: data => resolve(data)
                        });
                    }), new Promise((resolve, reject) => {
                        // 获取左侧菜单信息
                        this.getMenuTreeBySubModuleValueMenuGroupType({
                            moduleId: ProjectConf.projectName,
                            subModuleValue: moduleName,
                            menuGroupType: 'LEFT_MENU',
                            successCallback: data => resolve(data)
                        });
                    })]).then(([routeData, headerMenuData, leftMenuData]) => {
                        // 2：解析数据,设置各个模块数据缓存用于各个模块读取
                        // 2-1：解析路由数据
                        const routeRows = parseRouteRows({
                            rows: routeData,
                            primaryRole: this.primaryRole,
                            roles: this.roles,
                            frameOptions: this.frameModule
                        });
                        // 2-1：将解析过的路由数据缓存起来
                        const routeRowsCacheKey = getRouteRowsCacheKey({
                            projectName: ProjectConf.projectName,
                            moduleName: moduleName,
                            key: this.userId
                        });
                        setCache({key: routeRowsCacheKey, data: JSON.stringify(routeRows)});
                        // 2-3：解析头部菜单数据
                        const headerRows = parseMenuRows({
                            rows: headerMenuData,
                            primaryRole: this.primaryRole,
                            roles: this.roles,
                            frameOptions: this.frameModule
                        });
                        // 2-4：将解析过的头部菜单数据缓存起来
                        const headerMenuRowsCacheKey = getHeaderMenuRowsCacheKey({
                            projectName: ProjectConf.projectName,
                            moduleName: moduleName,
                            key: this.userId
                        });
                        setCache({key: headerMenuRowsCacheKey, data: JSON.stringify(headerRows)});
                        // 2-5：解析左侧菜单数据
                        const leftRows = parseMenuRows({
                            rows: leftMenuData,
                            primaryRole: this.primaryRole,
                            roles: this.roles,
                            frameOptions: this.frameModule
                        });
                        // 2-6：将解析过的左侧菜单数据缓存起来
                        const leftMenuRowsCacheKey = getLeftMenuRowsCacheKey({
                            projectName: ProjectConf.projectName,
                            moduleName: moduleName,
                            key: this.userId
                        });
                        setCache({key: leftMenuRowsCacheKey, data: JSON.stringify(leftRows)});
                        //当前模块初始化完毕
                        moduleResolve();
                    }).catch(moduleReject);

                }))]).then(() => {
                    //全部准备完毕
                    this.pageToModule('Index');
                });
            }
        },

        created() {
            this.getOnline();
        }
    }
</script>
<style scoped>
    /* Demo Styles - It's all in the SVG */
    html {
        height: 100%;
        min-height: 100%;
        overflow: hidden;
    }

    html body {
        background: #f3f3f3;
        background-size: 163px;
        font: 14px/21px Monaco, sans-serif;
        color: #999;
        font-smoothing: antialiased;
        -webkit-text-size-adjust: 100%;
        -moz-text-size-adjust: 100%;
        -ms-text-size-adjust: 100%;
        text-size-adjust: 100%;
        height: 100%;
        min-height: 100%;
    }

    html body a, html body a:visited {
        text-decoration: none;
        color: #FF805F;
    }

    html body h4 {
        margin: 0;
        color: #666;
    }

    .scene {
        width: 100%;
        height: 100%;
        display: block;
        position: absolute;
        top: 50%;
        left: 50%;
        margin-left: -150px;
        margin-top: -180px;
    }

    .scene svg {
        width: 300px;
        height: 300px;
    }

    .dc-logo {
        position: fixed;
        right: 10px;
        bottom: 10px;
    }

    .dc-logo:hover svg {
        -webkit-transform-origin: 50% 50%;
        transform-origin: 50% 50%;
        -webkit-animation: arrow-spin 2.5s 0s cubic-bezier(0.165, 0.84, 0.44, 1) infinite;
        animation: arrow-spin 2.5s 0s cubic-bezier(0.165, 0.84, 0.44, 1) infinite;
    }

    .dc-logo:hover:hover:before {
        content: '\2764';
        padding: 6px;
        font: 10px/1 Monaco, sans-serif;
        font-size: 10px;
        color: #00fffe;
        text-transform: uppercase;
        position: absolute;
        left: -70px;
        top: -30px;
        white-space: nowrap;
        z-index: 20;
        box-shadow: 0px 0px 4px #222;
        background: rgba(0, 0, 0, 0.4);
    }

    .dc-logo:hover:hover:after {
        content: 'Digital Craft';
        padding: 6px;
        font: 10px/1 Monaco, sans-serif;
        font-size: 10px;
        color: #6E6F71;
        text-transform: uppercase;
        position: absolute;
        right: 0;
        top: -30px;
        white-space: nowrap;
        z-index: 20;
        box-shadow: 0px 0px 4px #222;
        background: rgba(0, 0, 0, 0.4);
        background-image: none;
    }

    @-webkit-keyframes arrow-spin {
        50% {
            -webkit-transform: rotateY(360deg);
            transform: rotateY(360deg);
        }
    }

    @keyframes arrow-spin {
        50% {
            -webkit-transform: rotateY(360deg);
            transform: rotateY(360deg);
        }
    }
</style>
