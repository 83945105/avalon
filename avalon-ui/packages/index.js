import Ajax from "./ajax/index.js";
import {$Ajax} from "./ajax/index.js";
import DataParser from "./data-parser/index.js";
import {$DataParser} from "./data-parser/index.js";
import Message from "./message/index.js";
import {$Message} from "./message/index.js";

const plugins = {
  //数据解析器
  DataParser: DataParser,
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
  DataParser,
  $DataParser
}

export default install;
