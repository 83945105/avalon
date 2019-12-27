/**
 * 将字符串转换为大写
 * @param str 字符串
 * @param len 转换长度
 * @returns {string}
 */
export function toUpperCase(str = "", len) {
  if (isNaN(len)) {
    return str.toUpperCase();
  }
  len = parseInt(len);
  return `${str.substring(0, len).toUpperCase()}${str.substring(len)}`;
}

/**
 * 将字符串转驼峰
 * @param str
 */
export function toHumpFormat(str = "") {
  let re = /-(\w)/g;
  str = str.replace(re, function ($0, $1) {
    return $1.toUpperCase();
  });
  return str;
}

export function isString(obj) {
  return Object.prototype.toString.call(obj).toLowerCase() === "[object string]";
}

export function isNumber(obj) {
  return Object.prototype.toString.call(obj).toLowerCase() === "[object number]";
}

export function isBoolean(obj) {
  return Object.prototype.toString.call(obj).toLowerCase() === "[object boolean]";
}

export function isObject(obj) {
  return Object.prototype.toString.call(obj).toLowerCase() === "[object object]";
}

export function isFormData(obj) {
  return Object.prototype.toString.call(obj) === "[object FormData]";
}

export function isArray(obj) {
  return Object.prototype.toString.call(obj).toLowerCase() === "[object array]";
}

export function isFunction(obj) {
  return Object.prototype.toString.call(obj).toLowerCase() === "[object function]";
}

export function isNull(obj) {
  return Object.prototype.toString.call(obj).toLowerCase() === "[object null]";
}

export function isUndefined(obj) {
  return Object.prototype.toString.call(obj).toLowerCase() === "[object undefined]";
}

export function isExist(obj) {
  return !(isNull(obj) || isUndefined(obj));
}

export function isArrayString(str) {
  return (/^\[(.*?)\]$/.test(str));
}

export function humpToString(str = "") {
  return str.replace(/([A-Z])/g, "-$1").toLowerCase();
}

export function stringToHump(str = "") {
  return str.replace(/\-(\w)/g, function (all, letter) {
    return letter.toUpperCase();
  });
}

/**
 * 判断一个数组是否包含某个元素/某个数组所有元素
 * @param arr
 * @param target
 * @returns {boolean}
 */
export function isArrayContains(arr, target) {
  if (typeof target === 'string') {
    for (let i in arr) {
      if (arr[i] === target) return true;
    }
    return false;
  } else if (Array.isArray(target)) {
    for (let i in target) {
      if (arr.indexOf(target[i]) === -1) return false;
    }
    return true;
  }
}

/**
 * 判断俩个数组是否有交集
 * @param arr1
 * @param arr2
 * @returns {boolean}
 */
export function isArrayIntersection(arr1, arr2) {
  for (let i in arr1) {
    for (let j in arr2) {
      if (arr1[i] === arr2[j]) return true;
    }
  }
  return false;
}

/**
 * 判断是否为空
 * undefined => true
 * null => true
 * [] => true
 * {} => true
 * "" => true
 * "    " => true
 * @param target 目标
 * @returns {boolean}
 */
export function isEmpty(target) {
  if (target === undefined) return true;
  if (target === null) return true;
  if (isArray(target) && target.length === 0) return true;
  if (isObject(target) && Object.getOwnPropertyNames(target).length === 0) return true;
  if (`${target}`.trim().length === 0) return true;
  return false;
}

/**
 * 移除对象的空属性
 * @param obj 对象
 * @param apply 接收属性值,如果返回true则强制移除
 * @returns {*}
 */
export function removeObjectEmptyFields(obj, apply) {
  for (let name in obj) {
    const val = obj[name];
    if ((apply && apply(val) === true) || isEmpty(val)) delete obj[name];
  }
  return obj;
}

export function getCurrentFormatDate() {
  let date = new Date();
  let year = date.getFullYear();
  let month = date.getMonth() + 1;
  month = month < 10 ? ("0" + month) : month;
  let day = date.getDate();
  day = day < 10 ? ("0" + day) : day;
  let hours = date.getHours();
  hours = hours < 10 ? ("0" + hours) : hours;
  let minutes = date.getMinutes();
  minutes = minutes < 10 ? ("0" + minutes) : minutes;
  let seconds = date.getSeconds();
  seconds = seconds < 10 ? ("0" + seconds) : seconds;
  return (year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds);
}

export default {
  methods: {
    toUpperCase: toUpperCase,
    toHumpFormat: toHumpFormat,
    isString: isString,
    isNumber: isNumber,
    isBoolean: isBoolean,
    isObject: isObject,
    isFormData: isFormData,
    isArray: isArray,
    isFunction: isFunction,
    isNull: isNull,
    isUndefined: isUndefined,
    isExist: isExist,
    isArrayString: isArrayString,
    humpToString: humpToString,
    stringToHump: stringToHump,
    isArrayContains: isArrayContains,
    isArrayIntersection: isArrayIntersection,
    isEmpty: isEmpty,
    removeObjectEmptyFields: removeObjectEmptyFields,
    getCurrentFormatDate: getCurrentFormatDate
  }
};
