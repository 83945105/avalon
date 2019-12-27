/**
 * 参数匹配
 */

import {isArray, isBoolean, isExist, isFunction, isNull, isNumber, isObject, isString, isUndefined} from "./util.js";

function ___argsMatch(strings, numbers, booleans, objects, arrays, functions, nulls, undefineds, htmlElements, param) {
  if (isArray(param)) {
    for (let idx in param) {
      __argsMatch(strings, numbers, booleans, objects, arrays, functions, nulls, undefineds, htmlElements, param[idx]);
    }
  } else if (isObject(param)) {
    for (let name in param) {
      objects[name] = param[name];
    }
  }
}

function __argsMatch(strings, numbers, booleans, objects, arrays, functions, nulls, undefineds, htmlElements, param) {
  if (isString(param)) {
    strings.push(param);
  } else if (isNumber(param)) {
    numbers.push(param);
  } else if (isBoolean(param)) {
    booleans.push(param);
  } else if (isFunction(param)) {
    functions.push(param);
  } else if (isNull(param)) {
    nulls.push(param);
  } else if (isUndefined(param)) {
    undefineds.push(param);
  } else if (param instanceof HTMLElement) {
    htmlElements.push(param);
  } else {
    ___argsMatch(strings, numbers, booleans, objects, arrays, functions, nulls, undefineds, htmlElements, param);
  }
}

export default function argsMatch(matches = [], params = []) {
  let strings = [], numbers = [], booleans = [], objects = {}, arrays = [], functions = [], nulls = [], undefineds = [],
    htmlElements = [];
  if (!isArray(params)) {
    params = [params];
  }
  for (let idx in params) {
    __argsMatch(strings, numbers, booleans, objects, arrays, functions, nulls, undefineds, htmlElements, params[idx]);
  }

  let opts = {};

  for (let idx in matches) {
    let match = matches[idx];
    if (isExist(objects[match.name])) {
      opts[match.name] = objects[match.name];
      continue;
    }
    switch (match.type) {
      case 'string':
        if (isExist(strings[match.count - 1])) {
          opts[match.name] = strings[match.count - 1];
        } else {
          opts[match.name] = match.default;
        }
        break;
      case 'number':
        if (isExist(numbers[match.count - 1])) {
          opts[match.name] = numbers[match.count - 1];
        } else {
          opts[match.name] = match.default;
        }
        break;
      case 'boolean':
        if (isExist(booleans[match.count - 1])) {
          opts[match.name] = booleans[match.count - 1];
        } else {
          opts[match.name] = match.default;
        }
        break;
      case 'function':
        if (isExist(functions[match.count - 1])) {
          opts[match.name] = functions[match.count - 1];
        } else {
          opts[match.name] = match.default;
        }
        break;
      case 'null':
        if (isExist(nulls[match.count - 1])) {
          opts[match.name] = nulls[match.count - 1];
        } else {
          opts[match.name] = match.default;
        }
        break;
      case 'undefined':
        if (isExist(undefineds[match.count - 1])) {
          opts[match.name] = undefineds[match.count - 1];
        } else {
          opts[match.name] = match.default;
        }
        break;
      case 'htmlElement':
        if (isExist(htmlElements[match.count - 1])) {
          opts[match.name] = htmlElements[match.count - 1];
        } else {
          opts[match.name] = match.default;
        }
        break;
    }

  }
  return opts;
}
