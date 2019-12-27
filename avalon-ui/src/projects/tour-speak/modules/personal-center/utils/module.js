import {
  getRouteRowsCacheKey,
  getHeaderMenuRowsCacheKey,
  getLeftMenuRowsCacheKey,
  getLoginCacheKey
} from "../../../../../vuex-modules/frame-store.js";
import ModuleConfig from '../config/module.conf.js';
import {isExist} from "../../../../../utils/util.js";
import {getCache, removeCache} from "../../../../../utils/cache.js";

/**
 * 获取当前路由数据缓存key
 * @param key 自定义key
 * @returns {string}
 */
export const getCurrentRouteRowsCacheKey = function ({key = ''}) {
  return getRouteRowsCacheKey({projectName: ModuleConfig.projectName, moduleName: ModuleConfig.moduleName, key});
};

/**
 * 获取当前头部菜单数据缓存key
 * @param key 自定义key
 * @returns {string}
 */
export const getCurrentHeaderMenuRowsCacheKey = function ({key = ''}) {
  return getHeaderMenuRowsCacheKey({projectName: ModuleConfig.projectName, moduleName: ModuleConfig.moduleName, key});
};

/**
 * 获取当前左侧菜单数据缓存key
 * @param key 自定义key
 * @returns {string}
 */
export const getCurrentLeftMenuRowsCacheKey = function ({key = ''}) {
  return getLeftMenuRowsCacheKey({projectName: ModuleConfig.projectName, moduleName: ModuleConfig.moduleName, key});
};

/**
 * 所有当前缓存是否存在
 * 该方法用于判断浏览器是否存在业务系统所需的所有缓存
 * @param key
 */
export const allCurrentCacheExist = function ({key = ''}) {
  return isExist(getCache({key: getLoginCacheKey()}))
    && isExist(getCache({key: getCurrentRouteRowsCacheKey({key})}))
    && isExist(getCache({key: getCurrentHeaderMenuRowsCacheKey({key})}))
    && isExist(getCache({key: getCurrentLeftMenuRowsCacheKey({key})}));
};

/**
 * 删除当前所有业务系统缓存
 * @param key
 */
export const removeAllCurrentCache = function ({key = ''}) {
  removeCache({key: getCurrentRouteRowsCacheKey({key})});
  removeCache({key: getCurrentHeaderMenuRowsCacheKey({key})});
  removeCache({key: getCurrentLeftMenuRowsCacheKey({key})});
};
