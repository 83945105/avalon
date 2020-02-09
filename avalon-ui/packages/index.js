import Ajax from "./ajax/index.js";
import {$Ajax} from "./ajax/index.js";
import ResponseParser from "./response-parser/index.js";
import {$ResponseParser} from "./response-parser/index.js";
import Message from "./message/index.js";
import {$Message} from "./message/index.js";

const plugins = {
    //响应解析器
    ResponseParser: ResponseParser,
    // Ajax
    Ajax: Ajax,
    //消息提示
    Message: Message
};

const install = function (Vue, opts = {}) {

    for (let name in plugins) {
        plugins[name].install(Vue, opts[name]);
    }

};

export {
    Ajax,
    $Ajax,
    Message,
    $Message,
    ResponseParser,
    $ResponseParser
}

export default install;