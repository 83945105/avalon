import {DefaultMessageOptions} from "../../message/src/message.js";
import merge from "../../../src/utils/merge.js";
import argsMatch from "../../../src/utils/args-match.js";
import {isFunction} from "../../../src/utils/util.js";
import deepClone from "../../../src/utils/deep-clone.js";

// 解析器默认配置(该配置不可被覆盖)
export const DefaultParserOptions = {
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
  infoOptions: merge({
    close: false,
    callback: undefined
  }, DefaultMessageOptions),
  warnOptions: merge({
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
  }, DefaultMessageOptions),
  notFoundOptions: merge({
    close: true,
    callback: undefined
  }, DefaultMessageOptions)
};

// 全局解析器配置(该配置可被覆盖)
export const GlobalParserOptions = deepClone(DefaultParserOptions);

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

export default class Parser {

  _options = undefined;

  constructor(properties = {}) {
    const opts = deepClone(GlobalParserOptions);
    this._options = merge(opts, properties);
  }

  newInstance(properties) {
    throw new Error("没有设置使用的DataParser");
  };

  parse({data, response}) {
    alert("没有设置使用的DataParser");
    return this;
  };

  static argsMatch(args) {
    return argsMatch(initArgsOptions(), args);
  };

  before(...args) {
    merge(this._options.beforeOptions, Parser.argsMatch(args));
    return this;
  };

  executeBefore({data, response}) {
    let {callback: beforeCallback} = this._options.beforeOptions;
    isFunction(beforeCallback) && beforeCallback(data, response);
  };

  success(...args) {
    merge(this._options.successOptions, Parser.argsMatch(args));
    return this;
  };

  executeSuccess({data, response}) {
    let {callback: successCallback} = this._options.successOptions;
    isFunction(successCallback) && successCallback(data, response);
  };

  catch(...args) {
    merge(this._options.catchOptions, Parser.argsMatch(args));
    return this;
  };

  executeCatch({error}) {
    let {callback: catchCallback} = this._options.catchOptions;
    isFunction(catchCallback) && catchCallback(error);
  };

  finally(...args) {
    merge(this._options.finallyOptions, Parser.argsMatch(args));
    return this;
  };

  executeFinally({data, response}) {
    let {callback: finallyCallback} = this._options.finallyOptions;
    isFunction(finallyCallback) && finallyCallback(data, response);
  };

}
