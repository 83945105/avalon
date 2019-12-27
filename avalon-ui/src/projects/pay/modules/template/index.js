/*!
 * Title: 系统模块
 * Description:
 * author: 白超
 * date: 2017/12/5
 * version: v1.0
 */

/*该文件不要做任何修改,重要的事情说三遍*/
/*该文件不要做任何修改,重要的事情说三遍*/
/*该文件不要做任何修改,重要的事情说三遍*/

import Vue from 'vue';

import {initRouter} from './router/router.js';

//注入vuex 状态管理
import store from './vuex/store.js';

let router = initRouter();

router.beforeEach((to, from, next) => {
  if (to.matched.length === 0) {
    next({name: '404'});
  } else {
    next();
  }
});

import Index from './Index.vue';

new Vue({
  router,
  store,
  render: h => h(Index),
  created() {
  }
}).$mount("#index");
