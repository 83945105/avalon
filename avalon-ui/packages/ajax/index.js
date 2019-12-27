import Default from './src/default.js';
import Axios from './src/axios.js';
import JQuery from './src/jquery.js';
import {GlobalAjaxOptions} from "./src/ajax.js";
import merge from "../../src/utils/merge.js";
import {isArray, isString} from "../../src/utils/util.js";

const $Ajax = {};

const Ajax = {

    Default: Default,

    Axios: Axios,

    JQuery: JQuery,

    instance: undefined,

    install(Vue, {use = Default, alias = "$Ajax", options} = {use: Default, alias: "$Ajax"}) {

        merge(GlobalAjaxOptions, options);

        this.instance = new use();

        if (isString(alias)) {
            Vue.prototype[alias] = $Ajax;
        } else if (isArray(alias)) {
            for (let idx in alias) {
                Vue.prototype[alias[idx]] = $Ajax;
            }
        }
    }
};

$Ajax.get = function (url, params, options) {
    return Ajax.instance.get(url, params, options);
};

$Ajax.post = function (url, params, options) {
    return Ajax.instance.post(url, params, options);
};

$Ajax.put = function (url, params, options) {
    return Ajax.instance.put(url, params, options);
};

$Ajax.delete = function (url, params, options) {
    return Ajax.instance.delete(url, params, options);
};

$Ajax.download = function (fileName, url, params, options) {
    return Ajax.instance.download(fileName, url, params, options);
};

export {$Ajax};

export default Ajax;
