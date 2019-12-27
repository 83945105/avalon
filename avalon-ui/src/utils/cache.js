/**
 * 获取数据缓存
 * @param rowsCacheKey
 * @returns {string}
 */
import {isString} from "./util.js";

export const getCache = function ({key}) {
  if (!key) throw new Error('getCache key cannot be null.');
  return localStorage.getItem(key);
};

/**
 * 设置数据缓存
 * @param rowsCacheKey
 * @param data
 */
export const setCache = function ({key, data}) {
  if (!key) throw new Error('setCache key cannot be null.');
  if (!isString(data)) throw new Error('setCache data type must string.');
  localStorage.setItem(key, data);
};

/**
 * 移除数据缓存
 * @param key
 */
export const removeCache = function ({key}) {
  if (!key) throw new Error('setCache key cannot be null.');
  localStorage.removeItem(key);
};

export default {
  getCache: getCache,
  setCache: setCache,
  removeCache: removeCache
};
