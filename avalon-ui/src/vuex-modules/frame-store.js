/*!
 * Title: 框架配置
 * Description:
 * author: baichao
 * date: 2017/12/20
 * version: v1.0
 */

import {isString, isBoolean, isArray, isEmpty} from "../utils/util.js";
import {Data as GarGlobalData} from '../projects/gar/mixins/global.js';
import merge from "../utils/merge.js";

/**
 * 角色
 * @type {Role}
 */
const Role = class Role {

    constructor({id, name, value}) {
        if (isEmpty(id)) throw new Error('Role对象 id 不能为空');
        if (isEmpty(name)) throw new Error('Role对象 name 不能为空');
        if (isEmpty(value)) throw new Error('Role对象 value 不能为空');
        this.id = id;
        this.name = name;
        this.value = value;
    }

    equals(role) {
        if (!role instanceof Role) {
            throw new Error('Role equals 方法参数必须为Role对象');
        }
        return this.value === role.value;
    }

    in(roles = []) {
        for (let i in roles) {
            if (this.equals(roles[i])) {
                return true;
            }
        }
        return false;
    }

};

/**
 * 路由
 * @type {Router}
 */
const Router = class Router {

    constructor({path, name, componentModule, componentName, props, alias}) {
        if (isEmpty(path)) throw new Error('Router对象 path 不能为空');
        if (isEmpty(name)) throw new Error('Router对象 name 不能为空');
        if (isEmpty(componentModule)) throw new Error('Router对象 componentModule 不能为空');
        if (isEmpty(componentName)) throw new Error('Router对象 componentName 不能为空');
        this.path = path;
        this.name = name;
        this.componentModule = componentModule;
        this.componentName = componentName;
        if (props) {
            if (!isBoolean(props)) throw new Error('Router对象 props 类型不正确');
            this.props = props;
        }
        if (alias) {
            if (!isString(alias)) throw new Error('Router对象 alias 类型不正确');
            this.alias = alias;
        }
    }

    equals(router) {
        return this.path === router.path;
    }

};

/**
 * 获取登录数据缓存key
 * @returns {string}
 */
export const getLoginCacheKey = function () {
    return `vue-frame-login-json`;
};

/**
 * 获取主角色数据缓存key
 * @param key 自定义key,一般传用户ID
 * @returns {string}
 */
export const getPrimaryRoleCacheKey = function ({key = ''}) {
    return `vue-frame-primary-role-json-${key}`;
};

/**
 * 获取路由数据缓存key
 * @param projectName 项目名
 * @param moduleName 模块名
 * @param key 自定义key,一般传用户ID
 * @returns {string}
 */
export const getRouteRowsCacheKey = function ({projectName, moduleName, key = ''}) {
    return `vue-${projectName}-${moduleName}-frame-route-json-${key}`;
};

/**
 * 获取头部菜单数据缓存key
 * @param projectName 项目名
 * @param moduleName 模块名
 * @param key 自定义key,一般传用户ID
 * @returns {string}
 */
export const getHeaderMenuRowsCacheKey = function ({projectName, moduleName, key = ''}) {
    return `vue-${projectName}-${moduleName}-frame-header-menu-json-${key}`;
};

/**
 * 获取左侧菜单数据缓存key
 * @param projectName 项目名
 * @param moduleName 模块名
 * @param key 自定义key
 * @returns {string}
 */
export const getLeftMenuRowsCacheKey = function ({projectName, moduleName, key = ''}) {
    return `vue-${projectName}-${moduleName}-frame-left-menu-json-${key}`;
};

/**
 * 解析菜单数据
 * @param rows
 * @param primaryRole
 * @param roles
 * @param frameOptions
 * @returns {Array}
 */
export const parseMenuRows = function ({rows, primaryRole, roles, frameOptions}) {
    if (!rows) return [];
    if (!primaryRole) return [];
    if (!roles || roles.length === 0) return [];
    if (!frameOptions) return [];
    let {multiRoleMenuRules} = frameOptions;
    let {id: primaryRoleId} = primaryRole;
    let roleIds = roles.map(role => role.id);
    let menus = [];
    rows.forEach(row => {
        let menu = new Menu(row);
        merge(menu, {
            tabKey: row.id,
            tabLabel: row.name,
            tabName: row.value,
            //TODO 后端待添加该属性
            lazyInTab: false,
            //TODO 后端待添加该属性
            disabledInTab: false
        });
        if (row.menuSelectedRoutes && row.menuSelectedRoutes.length > 0) {
            menu.routeValues = row.menuSelectedRoutes.map(route => route.value);
        }
        if (row.menuClickToRoute) {
            menu.clickToRouteValue = row.menuClickToRoute.value;
            menu.tabName = row.menuClickToRoute.value;
        }
        if (row.subMenus && row.subMenus.length > 0) {
            menu.children = parseMenuRows({rows: row.subMenus, primaryRole, roles, frameOptions});
        }
        // 根据框架配置规则, 解析角色菜单
        // 没有角色可以看到该菜单
        if (!row.roles || row.roles.length === 0) return true;
        const rowRoleIds = row.roles.map(role => role.id);
        // 主角色可以看到该菜单
        if (rowRoleIds.includes(primaryRoleId)) {
            menus.push(menu);
            return true;
        } else if (multiRoleMenuRules === 'merge') {
            // 菜单规则为合并, 看看拥有的其它角色是否可以看到该菜单
            for (let i in rowRoleIds) {
                if (roleIds.includes(rowRoleIds[i])) {
                    menus.push(menu);
                    return true;
                }
            }
        }
    });
    return menus;
};

/**
 * 解析路由数据
 * @param rows
 * @param primaryRole
 * @param roles
 * @param frameOptions
 * @returns {Array}
 */
export const parseRouteRows = function ({rows, primaryRole, roles, frameOptions}) {
    if (!rows) return [];
    if (!primaryRole) return [];
    if (!roles || roles.length === 0) return [];
    if (!frameOptions) return [];
    let {id: primaryRoleId} = primaryRole;
    let roleIds = roles.map(role => role.id);
    let routes = [];
    let routeViews;
    let templates;
    let routeRoles;
    rows.forEach(row => {
        let route = {
            id: row.id,
            path: row.path,
            name: row.value,
            alias: row.alias,
            meta: {cache: row.cache === GarGlobalData.dict.routeCacheValue.Y}
        };
        //不管路由有没有模板, 先添加
        routes.push(route);
        if (isArray(row.subRoutes) && row.subRoutes.length > 0) {
            let children = parseRouteRows({rows: row.subRoutes, primaryRole, roles, frameOptions});
            if (children.length > 0) {
                route.children = children;
            }
        }
        routeViews = row.routeViews;
        if (!isArray(routeViews) || routeViews.length === 0) return true;
        for (let i in routeViews) {
            templates = routeViews[i].templates;
            if (!isArray(templates) || templates.length === 0) return true;

            // 匹配模板, 只能匹配到一个模板, 若匹配不符合要求,则该视图放弃
            let matchTemplates = [];
            let matchTemplate = void 0;
            for (let j in templates) {
                routeRoles = templates[j].roles;
                if (!isArray(routeRoles) || routeRoles.length === 0) return true;
                // 能走到这里表示路由视图的模板具备授予角色, 根据规则开始和现有角色匹配
                //如果多角色模板冲突,则使用当前角色模板
                const routeRoleIds = routeRoles.map(role => role.id);
                // 先用当前角色匹配
                if (routeRoleIds.includes(primaryRoleId)) {
                    matchTemplate = templates[j];
                    break;
                }
                // 继续用所有角色匹配
                for (let k in routeRoleIds) {
                    if (roleIds.includes(routeRoleIds[k])) {
                        matchTemplates.push(templates[j]);
                        break;
                    }
                }
            }
            if (!matchTemplate && matchTemplates.length === 1) {
                matchTemplate = matchTemplates[0];
            }
            if (!matchTemplate) return true;
            // 如果匹配到了模板, 表示当前路由视图、模板有效, 写入路由属性
            if (!route.components) {
                // 如果构建的路由没有components属性,表示第一次匹配到可用模板
                route.components = {};
                route.props = {};
            }
            route.components[routeViews[i].value] = matchTemplate;
            route.props[routeViews[i].value] = routeViews[i].props === 'true';
        }
    });
    return routes;
};

const MODULE_FRAME = {
    state: {
        //角色
        roles: [],
        //主角色
        primaryRole: undefined,
        //是否进行切换主角色
        showSwitchPrimaryRole: false,
        //是否登录
        isLogin: false,
        //用户ID
        userId: '',
        //真实姓名
        username: '',
        //服务端头部菜单数据
        serverHeaderMenuData: [],
        //本地头部菜单数据
        localHeaderMenuData: [],
        //服务端左侧菜单数据
        serverLeftMenuData: [],
        //本地左侧菜单数据
        localLeftMenuData: [],
        //左侧菜单展示
        collapseLeftMenu: true,
        /**
         * 存在多个角色时,菜单规则
         * default - 只显示当前角色菜单
         * merge - 合并
         *
         */
        multiRoleMenuRules: 'merge',

        pageMode: 'tab', //页面模式 tab 选项卡模式
        keepAliveCacheForTabEnabled: true,//是否开启选项卡缓存
        closeTabAndRemoveKeepAliveCacheEnabled: true,//关闭选项卡的时候是否移除组件缓存
        tabKeepAliveNames: [], //选项卡 keep-alive 组件缓存
    },

    getters: {
        /**
         * 获取当前头部菜单数据
         * @param state
         * @returns {Array}
         */
        getHeaderMenuData(state) {
            return state.localHeaderMenuData.concat(state.serverHeaderMenuData);
        },
        /**
         * 获取当前左侧菜单数据
         * @param state
         * @returns {Array}
         */
        getLeftMenuData(state) {
            return state.localLeftMenuData.concat(state.serverLeftMenuData);
        },
        /**
         * 获取选项卡缓存名称
         * @param state
         * @returns {Array}
         */
        getTabKeepAliveNames(state) {
            return state.tabKeepAliveNames;
        }
    },

    actions: {
        /**
         * 设置登录
         * @param context
         * @param userId
         * @param username
         * @returns {Promise<any>}
         */
        setLogin(context, {userId, username}) {
            return new Promise((resolve, reject) => {
                if (!isString(userId)) {
                    throw new Error('userId 类型不正确');
                }
                if (!isString(username)) {
                    throw new Error('username 类型不正确');
                }
                if (isEmpty(userId)) {
                    throw new Error('userId 不能为空');
                }
                if (isEmpty(username)) {
                    throw new Error('username 不能为空');
                }
                context.state.userId = userId;
                context.state.username = username;
                context.state.isLogin = true;
                resolve();
            });
        },
        /**
         * 设置登出
         * @param context
         * @returns {Promise<any>}
         */
        setLogout(context) {
            return new Promise((resolve, reject) => {
                context.state.userId = '';
                context.state.username = '';
                context.state.roles = [];
                context.state.primaryRole = undefined;
                context.state.isLogin = false;
                resolve();
            });
        },
        /**
         * 设置主角色和所有角色集合
         * @param context
         * @param primaryRole 主角色
         * @param roles 角色集合
         * @returns {Promise<any>}
         */
        setPrimaryRoleAndRoles(context, {primaryRole, roles}) {
            if (primaryRole && !(primaryRole instanceof Role)) {
                throw new Error('frame-store setPrimaryRoleAndRoles 参数 primaryRole 不是 Role 对象');
            }
            roles && roles.forEach(role => {
                if (!(role instanceof Role)) {
                    throw new Error('frame-store setPrimaryRoleAndRoles 参数 roles 含有不是 Role 对象元素');
                }
            });
            return new Promise((resolve, reject) => {
                if (roles && roles.length === 1) {
                    context.state.primaryRole = roles[0];
                    context.state.roles = roles;
                } else if (roles && roles.length > 1) {
                    context.state.roles = roles;
                    if (!primaryRole || roles.map(val => val.id).indexOf(primaryRole.id) === -1) {
                        context.state.showSwitchPrimaryRole = true;
                    } else {
                        context.state.primaryRole = primaryRole;
                    }
                } else {
                    context.state.primaryRole = undefined;
                    context.state.roles = [];
                }
                resolve();
            });
        },
        /**
         * 设置服务端头部菜单数据
         * @param context
         * @param rows
         * @returns {Promise<any>}
         */
        setServerHeaderMenuData(context, {rows}) {
            return new Promise((resolve, reject) => {
                context.state.serverHeaderMenuData = rows;
                resolve();
            });
        },
        /**
         * 设置本地头部菜单数据
         * @param context
         * @param rows
         * @returns {Promise<any>}
         */
        setLocalHeaderMenuData(context, {rows}) {
            return new Promise((resolve, reject) => {
                context.state.localHeaderMenuData = rows;
                resolve();
            });
        },
        /**
         * 设置服务端左侧菜单Json数据
         * @param context
         * @param rows
         * @returns {Promise<any>}
         */
        setServerLeftMenuData(context, {rows}) {
            return new Promise((resolve, reject) => {
                context.state.serverLeftMenuData = rows;
                resolve();
            });
        },
        /**
         * 设置本地左侧菜单Json数据
         * @param context
         * @param rows
         * @returns {Promise<any>}
         */
        setLocalLeftMenuData(context, {rows}) {
            return new Promise((resolve, reject) => {
                context.state.localLeftMenuData = rows;
                resolve();
            });
        },
        /**
         * 添加选项卡缓存
         * @param module
         * @param name
         */
        addTabKeepAliveName(module, name) {
            if (module.state.keepAliveCacheForTabEnabled !== true) return;
            if (module.state.tabKeepAliveNames.indexOf(name) !== -1) return;
            module.state.tabKeepAliveNames.push(name);
        },
        /**
         * 移除选项卡缓存
         * @param module
         * @param name
         */
        removeTabKeepAliveName(module, name) {
            if (module.state.keepAliveCacheForTabEnabled !== true) return;
            if (module.state.closeTabAndRemoveKeepAliveCacheEnabled !== true) return;
            if (module.state.tabKeepAliveNames.indexOf(name) === -1) return;
            for (let i = 0; i < module.state.tabKeepAliveNames.length; i++) {
                if (name === module.state.tabKeepAliveNames[i]) {
                    module.state.tabKeepAliveNames.splice(i, 1);
                    return;
                }
            }
        }
    }
};

/**
 * 选项卡对象
 * @type {Tab}
 */
const Tab = class Tab {

    constructor({key, label, name, closable, lazy, disabled, initOpen, initRouterValue = '', routerValues = []}) {
        if (isEmpty(key)) throw new Error('Tab对象 key 不能为空');
        if (isEmpty(label)) throw new Error('Tab对象 label 不能为空');
        if (isEmpty(name)) throw new Error('Tab对象 name 不能为空');
        if (isEmpty(closable)) throw new Error('Tab对象 closable 不能为空');
        if (isEmpty(lazy)) throw new Error('Tab对象 lazy 不能为空');
        if (isEmpty(disabled)) throw new Error('Tab对象 disabled 不能为空');
        if (isEmpty(initOpen)) throw new Error('Tab对象 initOpen 不能为空');

        this.key = key;
        this.label = label;
        this.name = name;
        this.closable = closable === true || closable === "true";
        this.lazy = lazy === true || lazy === "true";
        this.disabled = disabled === true || disabled === "true";
        this.initOpen = initOpen === true || initOpen === "true";
        this.initRouterValue = initRouterValue;
        this.routerValues = routerValues;
        this.routerEnabled = {};
        this.routerValues.forEach(routerValue => this.routerEnabled[routerValue] = routerValue === this.initRouterValue);
        // 需要缓存的路由
        this.cacheRouterValues = void 0;

    }

};

/**
 * 菜单对象
 * @type {Menu}
 */
const Menu = class Menu {

    constructor({
                    id,
                    name,
                    value,
                    iconName = '',
                    useTab = false,
                    tabKey,
                    tabLabel,
                    tabName,
                    initOpenInTab,
                    closableInTab,
                    lazyInTab,
                    disabledInTab,
                    clickToRouteValue = '',
                    routeValues = [],
                    roleValues = [],
                    children = []
                }) {

        if (isEmpty(id)) throw new Error('Menu对象 id 不能为空');
        if (isEmpty(name)) throw new Error('Menu对象 name 不能为空');
        if (isEmpty(value)) throw new Error('Menu对象 value 不能为空');
        if (isEmpty(useTab)) throw new Error('Menu对象 id 不能为空');
        if (useTab === true) {
            if (isEmpty(tabKey)) throw new Error('Menu对象 tabKey 不能为空');
            if (isEmpty(tabLabel)) throw new Error('Menu对象 tabLabel 不能为空');
            if (isEmpty(tabName)) throw new Error('Menu对象 tabName 不能为空');
            if (isEmpty(initOpenInTab)) throw new Error('Menu对象 initOpenInTab 不能为空');
            if (isEmpty(closableInTab)) throw new Error('Menu对象 closableInTab 不能为空');
            if (isEmpty(lazyInTab)) throw new Error('Menu对象 lazyInTab 不能为空');
            if (isEmpty(disabledInTab)) throw new Error('Menu对象 disabledInTab 不能为空');
        }
        if (isEmpty(id)) throw new Error('Menu对象 id 不能为空');
        if (isEmpty(id)) throw new Error('Menu对象 id 不能为空');

        this.id = id;
        this.name = name;
        this.value = value;
        this.iconName = iconName;
        this.useTab = useTab === true || useTab === "true";
        this.tabKey = tabKey;
        this.tabLabel = tabLabel;
        this.tabName = tabName;
        this.initOpenInTab = initOpenInTab === true || initOpenInTab === "true";
        this.closableInTab = closableInTab === true || closableInTab === "true";
        this.lazyInTab = lazyInTab === true || lazyInTab === "true";
        this.disabledInTab = disabledInTab === true || disabledInTab === "true";
        this.clickToRouteValue = clickToRouteValue;
        this.routeValues = routeValues;
        this.roleValues = roleValues;
        this.children = children;

    }

    /**
     * 转为Tab对象
     * @returns {Tab}
     */
    toTab() {
        return new Tab({
            key: this.tabKey,
            label: this.tabLabel,
            name: this.tabName,
            closable: this.closableInTab,
            lazy: this.lazyInTab,
            disabled: this.disabledInTab,
            initOpen: this.initOpenInTab,
            initRouterValue: this.clickToRouteValue,
            routerValues: this.routeValues
        });
    }

};

/**
 * 将菜单数据转为选项卡数据
 * @param menus
 * @returns {Array}
 */
export const caseMenusToTabs = function (menus = []) {
    if (!menus || menus.length === 0) return [];
    let tabs = [];
    let menu;
    for (let i in menus) {
        menu = menus[i];
        if (!(menu instanceof Menu)) {
            menu = new Menu(menu);
        }
        if (!(menu instanceof Menu)) {
            throw new Error('caseMenusToTabs menu 对象必须是Menu类型...');
        }
        if (menu.useTab !== true) {
            continue;
        }
        //如果有子菜单,父菜单不参与选项卡
        if (isArray(menu.children) && menu.children.length > 0) {
            tabs = tabs.concat(caseMenusToTabs(menu.children));
        } else {
            tabs.push(menu.toTab());
        }
    }
    return tabs;
};

export {MODULE_FRAME, Role, Router, Tab, Menu};