import Ajax, {GlobalAjaxOptions, replaceUrlParams} from "./ajax.js";
import deepMerge from "../../../src/utils/deep-merge.js";
import {$Message} from "../../message/index.js";
import merge from "../../../src/utils/merge.js";
import {isFunction} from "../../../src/utils/util";
import {Encoder, encoderParams, encoderUrl, spliceUrlParams} from "./ajax";

const jquery = require('jquery');

const Default = {};

export default class JQuery extends Ajax {

    _jquery = undefined;

    constructor() {
        super();
        this._jquery = jquery ? jquery : window.jQuery ? window.jQuery : () => {
            alert('未检测到jquery组件');
            return void 0;
        };
    }

    get(url, params, options) {
        super.get(url, params, options);
        if (isFunction(url)) {
            url = url({});
        }
        url = encoderUrl(url);
        if (isFunction(params)) {
            params = params({});
        }
        url = spliceUrlParams(url, params);

        let opts = deepMerge(Default, GlobalAjaxOptions, options);
        let $parser = new opts.dataParserOptions.use(opts.dataParserOptions.options);

        let waitId = undefined;
        let instance = undefined;
        let status = 'pending';
        if (opts.showWaitPrompt) {
            waitId = setTimeout(() => {
                if (status === 'pending') {
                    instance = $Message(opts.waitPromptText);
                }
            }, opts.waitPromptTimeout);
        }
        /*模拟开始*/
        if (opts.mock === true) {
            setTimeout(() => {
                if (opts.showWaitPrompt) {
                    waitId && clearTimeout(waitId);
                    instance && $Message.close(instance);
                }
                $parser.parse({data: this.mockData, response: this.mockData});
            }, opts.mockTimeout);
            return $parser;
        }
        /*模拟结束*/
        this._jquery.ajax({
            type: 'GET',
            url: `${opts.baseURL || ''}${url}`,
            data: opts.params || {},
            headers: opts.headers || {},
            timeout: opts.timeout,
            dataTypeString: opts.responseType,
            success: (data, textStatus, jqXHR) => {
                status = 'resolved';
                if (opts.showWaitPrompt) {
                    waitId && clearTimeout(waitId);
                    instance && $Message.close(instance);
                }
                $parser.parse({data: data, response: jqXHR});
            },
            error: (jqXHR, textStatus, error) => {
                $parser.executeBefore({data: null, response: jqXHR});
                $parser.executeCatch({error: jqXHR});
                status = 'rejected';
                if (opts.showWaitPrompt) {
                    waitId && clearTimeout(waitId);
                    instance && $Message.close(instance);
                }
                console.log(jqXHR);
                try {
                    console.log(JSON.parse(JSON.stringify(jqXHR)));
                } catch (e) {
                }
                if (opts.showErrorPrompt) {
                    $Message(opts.errorPromptText);
                }
                $parser.executeFinally({data: null, response: jqXHR});
            }
        });
        return $parser;
    }

    post(url, params, options) {
        super.post(url, params, options);
        if (isFunction(url)) {
            url = url({});
        }
        url = encoderUrl(url);
        if (isFunction(params)) {
            params = params({encoder: Encoder});
        }

        let opts = deepMerge(Default, GlobalAjaxOptions, options);

        let contentType;
        [contentType, params] = encoderParams(opts.headers['Content-Type'], params);
        if (contentType) {
            opts.headers['Content-Type'] = contentType;
        }

        let $parser = new opts.dataParserOptions.use(opts.dataParserOptions.options);

        let waitId = undefined;
        let instance = undefined;
        let status = 'pending';
        if (opts.showWaitPrompt) {
            waitId = setTimeout(() => {
                if (status === 'pending') {
                    instance = $Message(opts.waitPromptText);
                }
            }, opts.waitPromptTimeout);
        }
        /*模拟开始*/
        if (opts.mock === true) {
            setTimeout(() => {
                if (opts.showWaitPrompt) {
                    waitId && clearTimeout(waitId);
                    instance && $Message.close(instance);
                }
                $parser.parse({data: this.mockData, response: this.mockData});
            }, opts.mockTimeout);
            return $parser;
        }
        /*模拟结束*/
        this._jquery.ajax({
            type: 'POST',
            url: `${opts.baseURL || ''}${url}`,
            data: merge({}, params, opts.params || {}),
            headers: opts.headers || {},
            timeout: opts.timeout,
            dataTypeString: opts.responseType,
            success: (data, textStatus, jqXHR) => {
                status = 'resolved';
                if (opts.showWaitPrompt) {
                    waitId && clearTimeout(waitId);
                    instance && $Message.close(instance);
                }
                $parser.parse({data: data, response: jqXHR});
            },
            error: (jqXHR, textStatus, error) => {
                $parser.executeBefore({data: null, response: jqXHR});
                $parser.executeCatch({error: jqXHR});
                status = 'rejected';
                if (opts.showWaitPrompt) {
                    waitId && clearTimeout(waitId);
                    instance && $Message.close(instance);
                }
                console.log(jqXHR);
                try {
                    console.log(JSON.parse(JSON.stringify(jqXHR)));
                } catch (e) {
                }
                if (opts.showErrorPrompt) {
                    $Message(opts.errorPromptText);
                }
                $parser.executeFinally({data: null, response: jqXHR});
            }
        });
        return $parser;
    }

    put(url, params, options) {
        super.put(url, params, options);
        if (isFunction(url)) {
            url = url({});
        }
        url = encoderUrl(url);
        if (isFunction(params)) {
            params = params({encoder: Encoder});
        }

        let opts = deepMerge(Default, GlobalAjaxOptions, options);

        let contentType;
        [contentType, params] = encoderParams(opts.headers['Content-Type'], params);
        if (contentType) {
            opts.headers['Content-Type'] = contentType;
        }

        let $parser = new opts.dataParserOptions.use(opts.dataParserOptions.options);

        let waitId = undefined;
        let instance = undefined;
        let status = 'pending';
        if (opts.showWaitPrompt) {
            waitId = setTimeout(() => {
                if (status === 'pending') {
                    instance = $Message(opts.waitPromptText);
                }
            }, opts.waitPromptTimeout);
        }
        /*模拟开始*/
        if (opts.mock === true) {
            setTimeout(() => {
                if (opts.showWaitPrompt) {
                    waitId && clearTimeout(waitId);
                    instance && $Message.close(instance);
                }
                $parser.parse({data: this.mockData, response: this.mockData});
            }, opts.mockTimeout);
            return $parser;
        }
        /*模拟结束*/
        this._jquery.ajax({
            type: 'PUT',
            url: `${opts.baseURL || ''}${url}`,
            data: merge({}, params, opts.params || {}),
            headers: opts.headers || {},
            timeout: opts.timeout,
            dataTypeString: opts.responseType,
            success: (data, textStatus, jqXHR) => {
                status = 'resolved';
                if (opts.showWaitPrompt) {
                    waitId && clearTimeout(waitId);
                    instance && $Message.close(instance);
                }
                $parser.parse({data: data, response: jqXHR});
            },
            error: (jqXHR, textStatus, error) => {
                $parser.executeBefore({data: null, response: jqXHR});
                $parser.executeCatch({error: jqXHR});
                status = 'rejected';
                if (opts.showWaitPrompt) {
                    waitId && clearTimeout(waitId);
                    instance && $Message.close(instance);
                }
                console.log(jqXHR);
                try {
                    console.log(JSON.parse(JSON.stringify(jqXHR)));
                } catch (e) {
                }
                if (opts.showErrorPrompt) {
                    $Message(opts.errorPromptText);
                }
                $parser.executeFinally({data: null, response: jqXHR});
            }
        });
        return $parser;
    }

    delete(url, params, options) {
        super.delete(url, params, options);
        if (isFunction(url)) {
            url = url({});
        }
        url = encoderUrl(url);
        if (isFunction(params)) {
            params = params({});
        }
        url = spliceUrlParams(url, params);

        let opts = deepMerge(Default, GlobalAjaxOptions, options);
        let $parser = new opts.dataParserOptions.use(opts.dataParserOptions.options);

        let waitId = undefined;
        let instance = undefined;
        let status = 'pending';
        if (opts.showWaitPrompt) {
            waitId = setTimeout(() => {
                if (status === 'pending') {
                    instance = $Message(opts.waitPromptText);
                }
            }, opts.waitPromptTimeout);
        }
        /*模拟开始*/
        if (opts.mock === true) {
            setTimeout(() => {
                if (opts.showWaitPrompt) {
                    waitId && clearTimeout(waitId);
                    instance && $Message.close(instance);
                }
                $parser.parse({data: this.mockData, response: this.mockData});
            }, opts.mockTimeout);
            return $parser;
        }
        /*模拟结束*/
        this._jquery.ajax({
            type: 'DELETE',
            url: `${opts.baseURL || ''}${url}`,
            data: merge({}, params, opts.params || {}),
            headers: opts.headers || {},
            timeout: opts.timeout,
            dataTypeString: opts.responseType,
            success: (data, textStatus, jqXHR) => {
                status = 'resolved';
                if (opts.showWaitPrompt) {
                    waitId && clearTimeout(waitId);
                    instance && $Message.close(instance);
                }
                $parser.parse({data: data, response: jqXHR});
            },
            error: (jqXHR, textStatus, error) => {
                $parser.executeBefore({data: null, response: jqXHR});
                $parser.executeCatch({error: jqXHR});
                status = 'rejected';
                if (opts.showWaitPrompt) {
                    waitId && clearTimeout(waitId);
                    instance && $Message.close(instance);
                }
                console.log(jqXHR);
                try {
                    console.log(JSON.parse(JSON.stringify(jqXHR)));
                } catch (e) {
                }
                if (opts.showErrorPrompt) {
                    $Message(opts.errorPromptText);
                }
                $parser.executeFinally({data: null, response: jqXHR});
            }
        });
        return $parser;
    }

    download(fileName, url, params, options) {
        alert('暂未实现');
    }
};