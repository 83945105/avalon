import Parser from "./parser.js";
import {MessageType} from "../../message/src/message.js";
import {$Message} from "../../message/index.js";
import merge from "../../../src/utils/merge.js";
import {isFunction} from "../../../src/utils/util.js";

export default class DataView extends Parser {

    newInstance(properties) {
        return new DataView(properties);
    };

    executeBefore({data, response, message}) {
        let {close: beforeClose, callback: beforeCallback} = this._options.beforeOptions;
        if (beforeClose !== true) {
            $Message.open(merge({content: message}, this._options.beforeOptions));
        }
        isFunction(beforeCallback) && beforeCallback(data, response);
    };

    executeSuccess({data, response, message}) {
        let {close: successClose, callback: successCallback} = this._options.successOptions;
        if (successClose !== true) {
            $Message.success(merge({content: message}, this._options.successOptions));
        }
        isFunction(successCallback) && successCallback(data, message);
    };

    fail(...args) {
        merge(this._options.failOptions, Parser.argsMatch(args));
        return this;
    };

    executeFail({data, response, message}) {
        let {close: failClose, callback: failCallback} = this._options.failOptions;
        if (failClose !== true) {
            $Message.fail(merge({content: message}, this._options.failOptions));
        }
        isFunction(failCallback) && failCallback(data, response);
    };

    error(...args) {
        merge(this._options.errorOptions, Parser.argsMatch(args));
        return this;
    };

    executeError({data, response, message}) {
        let {close: errorClose, callback: errorCallback} = this._options.errorOptions;
        if (errorClose !== true) {
            $Message.error(merge({content: message}, this._options.errorOptions));
        }
        isFunction(errorCallback) && errorCallback(data, response);
    };

    info(...args) {
        merge(this._options.infoOptions, Parser.argsMatch(args));
        return this;
    };

    executeInfo({data, response, message}) {
        let {close: infoClose, callback: infoCallback} = this._options.infoOptions;
        if (infoClose !== true) {
            $Message.info(merge({content: message}, this._options.infoOptions));
        }
        isFunction(infoCallback) && infoCallback(data, response);
    };

    warn(...args) {
        merge(this._options.warnOptions, Parser.argsMatch(args));
        return this;
    };

    executeWarn({data, response, message}) {
        let {close: warnClose, callback: warnCallback} = this._options.warnOptions;
        if (warnClose !== true) {
            $Message.warn(merge({content: message}, this._options.warnOptions));
        }
        isFunction(warnCallback) && warnCallback(data, response);
    };

    notSuccess(...args) {
        merge(this._options.notSuccessOptions, Parser.argsMatch(args));
        return this;
    };

    executeNotSuccess({data, response, message}) {
        let {close: notSuccessClose, callback: notSuccessCallback} = this._options.notSuccessOptions;
        if (notSuccessClose !== true) {
            $Message.open(merge({content: message}, this._options.notSuccessOptions));
        }
        isFunction(notSuccessCallback) && notSuccessCallback(data, response);
    };

    needLogin(...args) {
        merge(this._options.needLoginOptions, Parser.argsMatch(args));
        return this;
    };

    executeNeedLogin({data, response, message}) {
        let {close: needLoginClose, callback: needLoginCallback} = this._options.needLoginOptions;
        if (needLoginClose !== true) {
            $Message.open(merge({content: message}, this._options.needLoginOptions));
        }
        isFunction(needLoginCallback) && needLoginCallback(data, response);
    };

    noAuthority(...args) {
        merge(this._options.noAuthorityOptions, Parser.argsMatch(args));
        return this;
    };

    executeNoAuthority({data, response, message}) {
        let {close: noAuthorityClose, callback: noAuthorityCallback} = this._options.noAuthorityOptions;
        if (noAuthorityClose !== true) {
            $Message.open(merge({content: message}, this._options.noAuthorityOptions));
        }
        isFunction(noAuthorityCallback) && noAuthorityCallback(data, response);
    };

    notFound(...args) {
        merge(this._options.notFoundOptions, Parser.argsMatch(args));
        return this;
    };

    executeNotFound({data, response, message}) {
        let {close: notFoundClose, callback: notFoundCallback} = this._options.notFoundOptions;
        if (notFoundClose !== true) {
            $Message.open(merge({content: message}, this._options.notFoundOptions));
        }
        isFunction(notFoundCallback) && notFoundCallback(data, response);
    };

    executeFinally({data, response, message}) {
        let {close: finallyClose, callback: finallyCallback} = this._options.finallyOptions;
        if (finallyClose !== true) {
            $Message.open(merge({content: message}, this._options.finallyOptions));
        }
        isFunction(finallyCallback) && finallyCallback({data, response});
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
        let {message, type} = data.resultInfo;
        this.executeBefore({data, response, message});
        switch (type) {
            case MessageType.SUCCESS:
                this.executeSuccess({data, response, message});
                break;
            case MessageType.FAIL:
                this.executeFail({data, response, message});
                this.executeNotSuccess({data, response, message});
                break;
            case MessageType.WARN:
                this.executeWarn({data, response, message});
                this.executeNotSuccess({data, response, message});
                break;
            case MessageType.INFO:
                this.executeInfo({data, response, message});
                this.executeNotSuccess({data, response, message});
                break;
            case MessageType.ERROR:
                this.executeError({data, response, message});
                this.executeNotSuccess({data, response, message});
                break;
            case MessageType.NEED_LOGIN:
                this.executeNeedLogin({data, response, message});
                this.executeNotSuccess({data, response, message});
                break;
            case MessageType.NO_AUTHORITY:
                this.executeNoAuthority({data, response, message});
                this.executeNotSuccess({data, response, message});
                break;
            case MessageType.NOT_FOUND:
                this.executeNotFound({data, response, message});
                this.executeNotSuccess({data, response, message});
                break;
            default:
                throw new Error(`消息类型不正确,MessageType:${type}`);
        }
        this.executeFinally({data, response, message});
        return this;
    };

}
