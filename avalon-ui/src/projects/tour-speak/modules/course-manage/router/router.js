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

import Frame from '../views/Frame.vue';
import Layout from '../views/Layout.vue';
import {importComponent} from "../../../router/router.js";
import {getCurrentRouteRowsCacheKey} from "../utils/module.js";
import {getCache} from "../../../../../utils/cache.js";
import {getLoginCacheKey} from "../../../../../vuex-modules/frame-store.js";

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

let routeRows = [];
const loginCacheKey = getLoginCacheKey();
const loginCacheJson = getCache({key: loginCacheKey});
if (loginCacheJson) {
    const {userId} = JSON.parse(loginCacheJson);
    const routeRowsCacheKey = getCurrentRouteRowsCacheKey({key: userId});
    const routeRowsJson = getCache({key: routeRowsCacheKey});
    if (routeRowsJson) {
        routeRows = JSON.parse(routeRowsJson);
    }
}

import staticRoute from '../routes/routes.json';

const routes = [{
    path: '/',
    meta: {cache: false},
    redirect: {name: 'Frame'}
}, {
    path: '/',
    name: 'Frame',
    component: Frame,
    meta: {cache: false},
    redirect: {name: 'Layout'},
    children: [{
        path: '/',
        name: 'Layout',
        component: Layout,
        meta: {cache: false},
        redirect: {name: 'CourseManage'},
        children: [
            ...ErrorRouters,
            ...buildRoutes(routeRows),
            ...buildRoutes(staticRoute)
        ]
    }]
}];

export default routes;
