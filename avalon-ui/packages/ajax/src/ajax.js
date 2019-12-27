import Default from "../../data-parser/src/default.js";
import {DefaultParserOptions} from "../../data-parser/src/parser.js";
import merge from "../../../src/utils/merge.js";
import {isArray, isFormData, isFunction, isObject, isString} from "../../../src/utils/util.js";
import deepClone from "../../../src/utils/deep-clone.js";

export const CONTENT_TYPE = {
    APPLICATION_JSON: 'application/json',
    APPLICATION_JSON_UTF8: 'application/json;charset=UTF-8',
    APPLICATION_FORM_URLENCODED: 'application/x-www-form-urlencoded'
};

const Qs = require('qs');

export const Encoder = {
    json: function (obj) {
        if (isObject(obj)) {
            return JSON.stringify(obj);
        }
        if (isString(obj) && obj.toString().includes("=")) {
            return JSON.stringify(Qs.parse(obj));
        }
        return obj;
    },
    formUrlEncoded: function (obj) {
        if (isObject(obj)) {
            return Qs.stringify(obj);
        }
        if (isString(obj) && obj.toString().includes("{")) {
            return Qs.stringify(JSON.parse(obj));
        }
        return obj;
    }
};

// Ajax默认配置(该配置不可被覆盖)
export const DefaultAjaxOptions = {
    //模拟开关
    mock: false,
    //模拟时间
    mockTimeout: 0,
    baseURL: '',
    params: {},
    headers: {},
    timeout: 60000,
    showWaitPrompt: true,
    waitPromptTimeout: 10000,
    waitPromptText: '您的网速貌似不太给力...',
    showErrorPrompt: true,
    errorPromptText: '服务器繁忙...请稍后再试',
    responseType: 'json',
    dataParserOptions: {
        use: Default,
        options: merge({}, DefaultParserOptions)
    }
};

// 全局Ajax配置(该配置可被覆盖)
export const GlobalAjaxOptions = deepClone(DefaultAjaxOptions);

export default class Ajax {

    mock(mockData) {
        this.mockData = mockData;
        return this;
    };

    /**
     * GET 请求
     * @param url
     * @param params
     * @param options
     */
    get(url, params, options) {
        if (!isFunction(url) && !isString(url) && !isArray(url) && !isObject(url)) {
            throw new Error(`Ajax get(): url必须为Function、String、Array、Object类型`);
        }
        if (params && !isFunction(params) && !isObject(params)) {
            throw new Error(`Ajax get(): params必须为Function、Object类型`);
        }
        if (options && !isObject(options)) {
            throw new Error(`Ajax get(): options必须为Object类型`);
        }
    };

    /**
     * POST 请求
     * @param url
     * @param params
     * @param options
     */
    post(url, params, options) {
        if (!isFunction(url) && !isString(url) && !isArray(url) && !isObject(url)) {
            throw new Error(`Ajax post(): url必须为Function、String、Array、Object类型`);
        }
        if (params && !isFunction(params) && !isString(params) && !isObject(params) && !isFormData(params)) {
            throw new Error(`Ajax post(): params必须为Function、String、Object、FormData类型`);
        }
        if (options && !isObject(options)) {
            throw new Error(`Ajax post(): options必须为Object类型`);
        }
    };

    /**
     * PUT 请求
     * @param url
     * @param params
     * @param options
     */
    put(url, params, options) {
        if (!isFunction(url) && !isString(url) && !isArray(url) && !isObject(url)) {
            throw new Error(`Ajax put(): url必须为Function、String、Array、Object类型`);
        }
        if (params && !isFunction(params) && !isString(params) && !isObject(params) && !isFormData(params)) {
            throw new Error(`Ajax put(): params必须为Function、String、Object、FormData类型`);
        }
        if (options && !isObject(options)) {
            throw new Error(`Ajax put(): options必须为Object类型`);
        }
    };

    /**
     * DELETE 请求
     * @param url
     * @param params
     * @param options
     */
    delete(url, params, options) {
        if (!isFunction(url) && !isString(url) && !isArray(url) && !isObject(url)) {
            throw new Error(`Ajax delete(): url必须为Function、String、Array、Object类型`);
        }
        if (params && !isFunction(params) && !isObject(params)) {
            throw new Error(`Ajax delete(): params必须为Function、Object类型`);
        }
        if (options && !isObject(options)) {
            throw new Error(`Ajax delete(): options必须为Object类型`);
        }
    };

    /**
     * 下载
     * @param fileName
     * @param url
     * @param params
     * @param options
     */
    download(fileName, url, params, options) {
        if (!isString(fileName)) {
            throw new Error(`Ajax download(): fileName必须为string类型`);
        }
        if (!isFunction(url) && !isString(url) && !isArray(url) && !isObject(url)) {
            throw new Error(`Ajax download(): url必须为Function、String、Array、Object类型`);
        }
        if (params && !isFunction(params) && !isString(params) && !isObject(params) && !isFormData(params)) {
            throw new Error(`Ajax download(): params必须为Function、String、Object、FormData类型`);
        }
        if (options && !isObject(options)) {
            throw new Error(`Ajax download(): options必须为Object类型`);
        }
    };

}

/**
 * 占位符正则表达式
 */
const PLACEHOLDER_REG_EXP = /\{[^\}]+\}*/g;

/**
 * 根据参数下标替换url占位符参数
 * @param url url地址,有可能包含占位符{*}
 * @param params 参数
 * @returns {string}
 */
export const indexReplaceUrlParams = function (url = '', params = []) {
    let i = 0;
    return url.replace(PLACEHOLDER_REG_EXP, (val, start, res) => {
        let param = params[i++];
        if (param === void 0) {
            throw new Error(`占位符: ${val} 没有对应下标的参数.`);
        }
        return param;
    });
};

/**
 * 根据参数属性名替换url占位符参数
 * @param url url地址,有可能包含占位符{*}
 * @param params 参数
 * @returns {string}
 */
export const nameReplaceUrlParams = function (url = '', params = {}) {
    if (isObject(params)) {
        return url.replace(/\{[^\}]+\}*/g, (val, start, res) => {
            return params[val.match(/^\{(\S*)+\}$/)[1]];
        });
    }
    return url;
};

/**
 * 对url参数进行编码
 * @param url
 */
export const encoderUrl = function (url) {
    // url是字符串 直接返回
    if (isString(url)) {
        return url;
    }
    if (isArray(url)) {
        if (!isString(url[0])) {
            throw new Error('数组的第一位必须是url字符串.');
        }
        if (url.length === 2 && isObject(url[1])) {
            // ['/get/user/{id}', {id: 1}]
            return nameReplaceUrlParams(url[0], url[1]);
        }
        // ['/get/user/{id}', 1, 2, 3]
        return indexReplaceUrlParams(url[0], url.splice(1));
    }
    if (isObject(url)) {
        let params = url.params;
        url = url.url;
        if (!url) {
            throw new Error('未找到url属性.');
        }
        if (!params) {
            throw new Error('未找到params属性.');
        }
        if (isArray(params)) {
            // {url: '/get/user/{id}', params: [1,2,3]}
            return indexReplaceUrlParams(url, params);
        }
        if (isObject(params)) {
            // {url: '/get/user/{id}', params: {id: 1}}
            return nameReplaceUrlParams(url, params);
        }
        throw new Error('params只能为数组或对象.');
    }
    throw new Error('url类型不正确.');
};

/**
 * 对参数进行编码
 * @param contentType 编码方式
 * @param params 参数
 * @returns [contentType, params]
 */
export const encoderParams = function (contentType, params) {
    // 未指定编码方式
    if (!contentType) {
        if (isObject(params)) {
            // 参数为对象类型, 默认使用 application/x-www-form-urlencoded 编码方式
            return [CONTENT_TYPE.APPLICATION_FORM_URLENCODED, Encoder.formUrlEncoded(params)];
        }
        if (isString(params) && params.toString().includes("=")) {
            // 参数为 = 拼接字符串, 表示想使用 application/x-www-form-urlencoded 编码方式
            return [CONTENT_TYPE.APPLICATION_FORM_URLENCODED, params];
        }
        if (isString(params) && params.toString().includes("{")) {
            // 参数为 = 拼接字符串, 表示想使用 application/json 编码方式
            return [CONTENT_TYPE.APPLICATION_JSON, params];
        }
        // 让axios根据传的参数自行判断(axios自带功能)
        return [undefined, params];
    }
    // 指定使用 application/x-www-form-urlencoded 编码方式, 将参数转为对应格式
    if (contentType === CONTENT_TYPE.APPLICATION_FORM_URLENCODED) {
        return [contentType, Encoder.formUrlEncoded(params)];
    }
    // 指定使用 application/json 编码方式, 将参数转为对应格式
    if (contentType.startsWith(CONTENT_TYPE.APPLICATION_JSON)) {
        return [contentType, Encoder.json(params)];
    }
    throw new Error('不支持该ContentType.');
};

/**
 * 拼接url参数
 */
export const spliceUrlParams = function (url = '', params = {}) {
    if (isObject(params) && Object.getOwnPropertyNames(params).length > 0) {
        let idx = url.indexOf("?");
        if (idx === -1) {
            url += '?';
        } else if (idx !== url.length - 1) {
            url += '&';
        }
        for (let name in params) {
            url += `${name}=${params[name]}&`;
        }
        return url.substring(0, url.length - 1);
    }
    return url;
};