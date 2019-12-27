/*!
 * Title: 状态管理
 * Description:
 * author: 白超
 * date: 2017/12/20
 * version: v1.0
 */
import Vue from 'vue';
import Vuex from 'vuex';

import {MODULE_USER} from '../../../vuex-modules/user-store.js';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    userModule: MODULE_USER
  }
});
