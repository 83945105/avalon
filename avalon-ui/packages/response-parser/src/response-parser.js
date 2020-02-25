import {DefaultMessageOptions} from "../../message/src/message.js";
import merge from "../../../src/utils/merge.js";
import argsMatch from "../../../src/utils/args-match.js";
import {isFunction} from "../../../src/utils/util.js";
import deepClone from "../../../src/utils/deep-clone.js";

// 解析器默认配置(该配置不可被覆盖)
export const DefaultResponseParserOptions = {
    successOptions: merge({
        close: false,
        callback: undefined
    }, DefaultMessageOptions),
    failOptions: merge({
        close: false,
        callback: undefined
    }, DefaultMessageOptions),
    errorOptions: merge({
        close: false,
        callback: undefined
    }, DefaultMessageOptions),
    notSuccessOptions: merge({
        close: true,
        callback: undefined
    }, DefaultMessageOptions),
    beforeOptions: merge({
        close: true,
        callback: undefined
    }, DefaultMessageOptions),
    catchOptions: merge({
        close: true,
        callback: undefined
    }, DefaultMessageOptions),
    finallyOptions: merge({
        close: true,
        callback: undefined
    }, DefaultMessageOptions),
    needLoginOptions: merge({
        close: true,
        callback: undefined
    }, DefaultMessageOptions),
    noAuthorityOptions: merge({
        close: true,
        callback: undefined
    }, DefaultMessageOptions)
};

// 全局解析器配置(该配置可被覆盖)
export const GlobalResponseParserOptions = deepClone(DefaultResponseParserOptions);

// 参数配置
function initArgsOptions() {
    return [{
        name: 'content',
        type: 'string',
        count: 1
    }, {
        name: 'customClass',
        type: 'string',
        count: 2
    }, {
        name: 'duration',
        type: 'number',
        count: 1
    }, {
        name: 'close',
        type: 'boolean',
        count: 1
    }, {
        name: 'showClose',
        type: 'boolean',
        count: 2
    }, {
        name: 'center',
        type: 'boolean',
        count: 3
    }, {
        name: 'html',
        type: 'boolean',
        count: 4
    }, {
        name: 'callback',
        type: 'function',
        count: 1
    }, {
        name: 'onClose',
        type: 'function',
        count: 2
    }];
}

export default class ResponseParser {

    _options = undefined;

    constructor(properties = {}) {
        const opts = deepClone(GlobalResponseParserOptions);
        this._options = merge(opts, properties);
    }

    newInstance(properties) {
        throw new Error("没有设置使用的ResponseParser");
    };

    parse({data, response}) {
        alert("没有设置使用的ResponseParser");
        return this;
    };

    static argsMatch(args) {
        return argsMatch(initArgsOptions(), args);
    };

    before(...args) {
        merge(this._options.beforeOptions, ResponseParser.argsMatch(args));
        return this;
    };

    executeBefore({data, limit, response}) {
        let {callback: beforeCallback} = this._options.beforeOptions;
        isFunction(beforeCallback) && beforeCallback(data, response);
    };

    success(...args) {
        merge(this._options.successOptions, ResponseParser.argsMatch(args));
        return this;
    };

    executeSuccess({data, limit, response}) {
        let {callback: successCallback} = this._options.successOptions;
        isFunction(successCallback) && successCallback(data, response);
    };

    catch(...args) {
        merge(this._options.catchOptions, ResponseParser.argsMatch(args));
        return this;
    };

    executeCatch({error}) {
        let {callback: catchCallback} = this._options.catchOptions;
        isFunction(catchCallback) && catchCallback(error);
    };

    finally(...args) {
        merge(this._options.finallyOptions, ResponseParser.argsMatch(args));
        return this;
    };

    executeFinally({data, limit, response}) {
        let {callback: finallyCallback} = this._options.finallyOptions;
        isFunction(finallyCallback) && finallyCallback(data, response);
    };
}