/*!
 * Title:
 * Description: 路由管理
 * author: 白超
 * date: 2017/12/5
 * version: v1.0
 */
/*以下内容根据自己模块情况,根据自己设计的layout布局,自行配置路由*/

/**
 * 参考:https://router.vuejs.org/zh-cn/essentials/getting-started.html
 */

const NoAuthorityComponent = () => import('../../../../../components/error-page/src/403.vue');

const NotFoundComponent = () => import('../../../../../components/error-page/src/404.vue');

const ErrorComponent = () => import('../../../../../components/error-page/src/500.vue');

const NoDataComponent = () => import('../../../../../components/error-page/src/NoData.vue');

// 异常组件路由
export const ErrorRouters = [{
    path: '/403',
    name: '403',
    component: NoAuthorityComponent,
    meta: {cache: false}
}, {
    path: '/404',
    name: '404',
    component: NotFoundComponent,
    meta: {cache: false}
}, {
    path: '/500',
    name: '500',
    component: ErrorComponent,
    meta: {cache: false}
}, {
    path: '/noData',
    name: 'NoData',
    component: NoDataComponent,
    meta: {cache: false}
}];

import Panel from '../views/Panel.vue';
import {importComponent} from "../../../router/router.js";

export const buildRoutes = function (routeData = []) {
    if (!routeData) return [];
    let routes = [];
    routeData.forEach(row => {
        let route = {
            path: row.path,
            name: row.name,
            props: row.props || {},
            meta: row.meta || {}
        };
        let component;
        if (row.components) {
            route.components = {};
            for (let name in row.components) {
                component = row.components[name];
                route.components[name] = importComponent(`${component.subModuleValue}${component.value}`);
            }
        } else {
            //没有组件属性,构建指向403
            route.components = {
                default: NoAuthorityComponent
            };
        }
        if (row.children) {
            route.children = buildRoutes(row.children);
        }
        routes.push(route);
    });
    return routes;
};

const routes = [{
    path: '/',
    meta: {cache: false},
    redirect: {name: 'Panel'}
}, {
    path: '/',
    name: 'Panel',
    component: Panel,
    meta: {cache: false},
    redirect: {name: 'Index'},
    children: [
        {
            path: 'index',
            name: 'Index',
            meta: {cache: false},
            component: importComponent('loginLogin')
        },
        {
            path: 'optionsLoading',
            name: 'OptionsLoading',
            meta: {cache: false},
            component: importComponent('loginOptionsLoading')
        },
        ...ErrorRouters
    ]
}];

export default routes;
