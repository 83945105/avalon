import Default from '../message/src/default.js';
import Element from '../message/src/element.js';
import WeView from '../message/src/weview.js';
import {GlobalMessageOptions} from "./src/message.js";
import merge from "../../src/utils/merge.js";
import {isArray, isString} from "../../src/utils/util.js";

let $Message;

const Message = {

    Default: Default,

    Element: Element,

    WeView: WeView,

    instance: undefined,

    install(Vue, {use = Default, alias = "$Message", options} = {use: Default, alias: "$Message"}) {

        merge(GlobalMessageOptions, options);

        this.instance = new use();

        if (isString(alias)) {
            Vue.prototype[alias] = $Message;
        } else if (isArray(alias)) {
            for (let idx in alias) {
                Vue.prototype[alias[idx]] = $Message;
            }
        }
    }
};

$Message = function (opts) {
    return Message.instance.open(opts);
};

$Message.open = function (opts) {
    return Message.instance.open(opts);
};
$Message.success = function (opts) {
    return Message.instance.success(opts);
};
$Message.fail = function (opts) {
    return Message.instance.fail(opts);
};
$Message.error = function (opts) {
    return Message.instance.error(opts);
};
$Message.info = function (opts) {
    return Message.instance.info(opts);
};
$Message.warn = function (opts) {
    return Message.instance.warn(opts);
};
$Message.close = function (instance) {
    Message.instance.close(instance);
};
$Message.closeAll = function () {
    Message.instance.closeAll();
};

export {$Message};

export default Message;
