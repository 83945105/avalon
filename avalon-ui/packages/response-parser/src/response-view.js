import ResponseParser from "./response-parser.js";
import {$Message} from "../../message/index.js";
import merge from "../../../src/utils/merge.js";
import {isFunction} from "../../../src/utils/util.js";

export default class ResponseView extends ResponseParser {

    newInstance(properties) {
        return new ResponseView(properties);
    };

    executeBefore({data, limit, response, message}) {
        let {close: beforeClose, callback: beforeCallback} = this._options.beforeOptions;
        if (beforeClose !== true) {
            $Message.open(merge({content: message}, this._options.beforeOptions));
        }
        isFunction(beforeCallback) && beforeCallback({data, limit, response, message});
    };

    executeSuccess({data, limit, response, message}) {
        let {close: successClose, callback: successCallback} = this._options.successOptions;
        if (successClose !== true) {
            $Message.success(merge({content: message}, this._options.successOptions));
        }
        isFunction(successCallback) && successCallback({data, limit, response, message});
    };

    fail(...args) {
        merge(this._options.failOptions, ResponseParser.argsMatch(args));
        return this;
    };

    executeFail({data, limit, response, message}) {
        let {close: failClose, callback: failCallback} = this._options.failOptions;
        if (failClose !== true) {
            $Message.fail(merge({content: message}, this._options.failOptions));
        }
        isFunction(failCallback) && failCallback({data, limit, response, message});
    };

    error(...args) {
        merge(this._options.errorOptions, ResponseParser.argsMatch(args));
        return this;
    };

    executeError({data, limit, response, message}) {
        let {close: errorClose, callback: errorCallback} = this._options.errorOptions;
        if (errorClose !== true) {
            $Message.error(merge({content: message}, this._options.errorOptions));
        }
        isFunction(errorCallback) && errorCallback({data, limit, response, message});
    };

    notSuccess(...args) {
        merge(this._options.notSuccessOptions, ResponseParser.argsMatch(args));
        return this;
    };

    executeNotSuccess({data, limit, response, message}) {
        let {close: notSuccessClose, callback: notSuccessCallback} = this._options.notSuccessOptions;
        if (notSuccessClose !== true) {
            $Message.open(merge({content: message}, this._options.notSuccessOptions));
        }
        isFunction(notSuccessCallback) && notSuccessCallback({data, limit, response, message});
    };

    needLogin(...args) {
        merge(this._options.needLoginOptions, ResponseParser.argsMatch(args));
        return this;
    };

    executeNeedLogin({data, limit, response, message}) {
        let {close: needLoginClose, callback: needLoginCallback} = this._options.needLoginOptions;
        if (needLoginClose !== true) {
            $Message.open(merge({content: message}, this._options.needLoginOptions));
        }
        isFunction(needLoginCallback) && needLoginCallback({data, limit, response, message});
    };

    noAuthority(...args) {
        merge(this._options.noAuthorityOptions, ResponseParser.argsMatch(args));
        return this;
    };

    executeNoAuthority({data, limit, response, message}) {
        let {close: noAuthorityClose, callback: noAuthorityCallback} = this._options.noAuthorityOptions;
        if (noAuthorityClose !== true) {
            $Message.open(merge({content: message}, this._options.noAuthorityOptions));
        }
        isFunction(noAuthorityCallback) && noAuthorityCallback({data, limit, response, message});
    };

    executeFinally({data, limit, response, message}) {
        let {close: finallyClose, callback: finallyCallback} = this._options.finallyOptions;
        if (finallyClose !== true) {
            $Message.open(merge({content: message}, this._options.finallyOptions));
        }
        isFunction(finallyCallback) && finallyCallback({data, limit, response, message});
    };

    parse({data, response}) {
        if (data.methodName === 'download') {
            //new Blob([res])中不加data就会返回下图中[objece objece]内容（少取一层）
            const blob = new Blob([data.data]);
            const eLink = document.createElement('a');
            eLink.download = data.fileName;
            eLink.style.display = 'none';
            eLink.href = URL.createObjectURL(blob);
            document.body.appendChild(eLink);
            eLink.click();
            // 释放URL 对象
            URL.revokeObjectURL(eLink.href);
            document.body.removeChild(eLink);
        }
        if (!data.resultInfo) return;
        let {entity = {}, limit = {}, resultInfo} = data;
        let {message, responseType} = resultInfo;
        this.executeBefore({data: entity, limit, response, message});
        switch (responseType) {
            case "SUCCESS":
                this.executeSuccess({data: entity, limit, response, message});
                break;
            case "FAIL":
                this.executeFail({data: entity, limit, response, message});
                this.executeNotSuccess({data: entity, limit, response, message});
                break;
            case "ERROR":
                this.executeError({data: entity, limit, response, message});
                this.executeNotSuccess({data: entity, limit, response, message});
                break;
            case "PROXY_AUTHENTICATION_REQUIRED":
                this.executeNeedLogin({data: entity, limit, response, message});
                this.executeNotSuccess({data: entity, limit, response, message});
                break;
            case "UNAUTHORIZED":
                this.executeNoAuthority({data: entity, limit, response, message});
                this.executeNotSuccess({data: entity, limit, response, message});
                break;
            default:
                throw new Error(`消息类型不正确,responseType:${responseType}`);
        }
        this.executeFinally({data: entity, limit, response, message});
        return this;
    };
}