import lodash from 'lodash';

/**
 * 深拷贝
 * @param target
 * @returns {*}
 */
export default function deepClone(target) {
  return lodash.cloneDeep(target);
};
