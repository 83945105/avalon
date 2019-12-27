import Ajax, {Encoder, encoderParams, encoderUrl, GlobalAjaxOptions, spliceUrlParams} from "./ajax.js";
import {$Message} from "../../message/index.js";
import deepMerge from "../../../src/utils/deep-merge.js";
import {isFunction} from "../../../src/utils/util.js";
import merge from "../../../src/utils/merge.js";

const AxiosAjax = require('axios');

const Default = {
    headers: {
        'X-Requested-With': 'XMLHttpRequest'
    }
};

export class Axios extends Ajax {

    _axios = undefined;

    constructor() {
        super();
        this._axios = AxiosAjax;
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
        this._axios.get(url, opts).then(res => {
            status = 'resolved';
            if (opts.showWaitPrompt) {
                waitId && clearTimeout(waitId);
                instance && $Message.close(instance);
            }
            $parser.parse({data: res.data, response: res});
        }).catch(err => {
            $parser.executeBefore({data: null, response: err});
            $parser.executeCatch({error: err});
            status = 'rejected';
            if (opts.showWaitPrompt) {
                waitId && clearTimeout(waitId);
                instance && $Message.close(instance);
            }
            console.log(err);
            try {
                console.log(JSON.parse(JSON.stringify(err)));
            } catch (e) {
            }
            if (opts.showErrorPrompt) {
                $Message(opts.errorPromptText);
            }
            $parser.executeFinally({data: null, response: err});
        });
        return $parser;
    };

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
        this._axios.post(url, params, opts).then(res => {
            status = 'resolved';
            if (opts.showWaitPrompt) {
                waitId && clearTimeout(waitId);
                instance && $Message.close(instance);
            }
            $parser.parse({data: res.data, response: res});
        }).catch(err => {
            $parser.executeCatch({error: err});
            $parser.executeBefore({data: null, response: err});
            status = 'rejected';
            if (opts.showWaitPrompt) {
                waitId && clearTimeout(waitId);
                instance && $Message.close(instance);
            }
            console.log(err);
            try {
                console.log(JSON.parse(JSON.stringify(err)));
            } catch (e) {
            }
            if (opts.showErrorPrompt) {
                $Message(opts.errorPromptText);
            }
            $parser.executeFinally({data: null, response: err});
        });
        return $parser;
    };

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
        this._axios.put(url, params, opts).then(res => {
            status = 'resolved';
            if (opts.showWaitPrompt) {
                waitId && clearTimeout(waitId);
                instance && $Message.close(instance);
            }
            $parser.parse({data: res.data, response: res});
        }).catch(err => {
            $parser.executeCatch({error: err});
            $parser.executeBefore({data: null, response: err});
            status = 'rejected';
            if (opts.showWaitPrompt) {
                waitId && clearTimeout(waitId);
                instance && $Message.close(instance);
            }
            console.log(err);
            try {
                console.log(JSON.parse(JSON.stringify(err)));
            } catch (e) {
            }
            if (opts.showErrorPrompt) {
                $Message(opts.errorPromptText);
            }
            $parser.executeFinally({data: null, response: err});
        });
        return $parser;
    };

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
        opts.params = deepMerge(params, opts.params);
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
        this._axios.delete(url, opts).then(res => {
            status = 'resolved';
            if (opts.showWaitPrompt) {
                waitId && clearTimeout(waitId);
                instance && $Message.close(instance);
            }
            $parser.parse({data: res.data, response: res});
        }).catch(err => {
            $parser.executeCatch({error: err});
            $parser.executeBefore({data: null, response: err});
            status = 'rejected';
            if (opts.showWaitPrompt) {
                waitId && clearTimeout(waitId);
                instance && $Message.close(instance);
            }
            console.log(err);
            try {
                console.log(JSON.parse(JSON.stringify(err)));
            } catch (e) {
            }
            if (opts.showErrorPrompt) {
                $Message(opts.errorPromptText);
            }
            $parser.executeFinally({data: null, response: err});
        });
        return $parser;
    };

    download(fileName, url, params, options) {
        super.download(fileName, url, params, options);
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
        params = encoderParams(opts.headers['Content-Type'], params);
        merge(opts, {
            responseType: 'blob',
            headers: {
                'Content-Type': 'application/json'
            }
        });
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
        this._axios.post(url, params, opts).then(res => {
            if (opts.showWaitPrompt) {
                waitId && clearTimeout(waitId);
                instance && $Message.close(instance);
            }
            status = 'resolved';
            $parser.parse({
                data: {
                    data: res.data,
                    fileName: fileName,
                    methodName: 'download',
                    // 模拟一个成功消息
                    resultInfo: {
                        error: false,
                        exceptionMessage: null,
                        fail: false,
                        message: "下载成功",
                        messageCode: 0,
                        resultCode: "SUCCESS",
                        resultDetails: null,
                        success: true,
                        type: 1
                    }
                },
                response: res
            });
        }).catch(err => {
            $parser.executeCatch({error: err});
            $parser.executeBefore({data: null, response: err});
            status = 'rejected';
            if (opts.showWaitPrompt) {
                waitId && clearTimeout(waitId);
                instance && $Message.close(instance);
            }
            console.log(err);
            try {
                console.log(JSON.parse(JSON.stringify(err)));
            } catch (e) {
            }
            if (opts.showErrorPrompt) {
                $Message(opts.errorPromptText);
            }
            $parser.executeFinally({data: null, response: err});
        });
        return $parser;
    };
}