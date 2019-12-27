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

import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

// 异常组件路由
export const ErrorRouters = [{
  path: '/403',
  name: '403',
  component: () => import('../../../../../components/error-page/src/403.vue')
}, {
  path: '/404',
  name: '404',
  component: () => import('../../../../../components/error-page/src/404.vue')
}, {
  path: '/500',
  name: '500',
  component: () => import('../../../../../components/error-page/src/500.vue')
}, {
  path: '/noData',
  name: 'NoData',
  component: () => import('../../../../../components/error-page/src/NoData.vue')
}];

import RouterAuthorization from '../config/router-authorization.json';

import {importComponent} from '../../../router/router.js';

export const buildRoleRouters = function (routerAuthorizations) {
  let routers = [];
  if (!routerAuthorizations) {
    return routers;
  }
  let r;
  for (let name in routerAuthorizations) {
    r = routerAuthorizations[name];
    if (!r) {
      continue;
    }
    if (r.children) {
      routers.push({
        path: r.path,
        name: r.name,
        props: r.props,
        component: importComponent(`${r.componentModule}${r.componentName}`),
        meta: {type: 'menu'},
        children: buildRoleRouters(r.children)
      });
    } else {
      routers.push({
        path: r.path,
        name: r.name,
        props: r.props,
        component: importComponent(`${r.componentModule}${r.componentName}`),
        meta: {type: 'menu'},
      });
    }
  }
  return routers;
};

import Frame from '../views/Frame.vue';
import Layout from '../views/Layout.vue';

const Home = () => import('../views/home/Home.vue');

export function initRouter() {
  // 先从 localStorage 尝试拿到路由角色
  let routerRole = localStorage.getItem("routerRole");

  //TODO 临时授予routerRole
  routerRole = 'developer';

  let routerAuthorizations = RouterAuthorization[routerRole];

  if (routerRole) {
    //TODO 存在路由角色就直接渲染出对应路由
    return new VueRouter({
      routes: [{
        path: '/',
        meta: {type: 'base'},
        redirect: {name: 'Frame'}
      }, {
        path: '/',
        name: 'Frame',
        component: Frame,
        meta: {type: 'base'},
        redirect: {name: 'Layout'},
        children: [{
          path: '/',
          name: 'Layout',
          component: Layout,
          meta: {type: 'base'},
          redirect: {name: 'Home'},
          children: [
            {
              path: 'home',
              name: 'Home',
              component: Home,
              meta: {type: 'menu'},
            },
            ...ErrorRouters,
            ...buildRoleRouters(routerAuthorizations)
          ]
        }]
      }]
    });
  }
  //不存在路由角色就渲染出默认路由
  return new VueRouter({
    routes: [{
      path: '/',
      meta: {type: 'base'},
      redirect: {name: 'Frame'}
    }, {
      path: '/',
      name: 'Frame',
      component: Frame,
      meta: {type: 'base'},
      redirect: {name: 'Layout'},
      children: [{
        path: '/',
        name: 'Layout',
        component: Layout,
        meta: {type: 'base'},
        redirect: {name: 'Home'},
        children: [
          {
            path: 'home',
            name: 'Home',
            component: Home,
            meta: {type: 'menu'},
          },
          ...ErrorRouters
        ]
      }]
    }]
  });
}
