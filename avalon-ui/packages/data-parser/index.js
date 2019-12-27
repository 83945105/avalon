import Default from '../data-parser/src/default.js';
import DataView from "../data-parser/src/dataview.js";
import {GlobalParserOptions} from "./src/parser.js";
import merge from "../../src/utils/merge.js";
import {isArray, isString} from "../../src/utils/util.js";

const $DataParser = {};

const DataParser = {

  Default: Default,

  DataView: DataView,

  instance: undefined,

  install(Vue, {use = Default, alias = "$DataParser", options} = {use: Default, alias: "$DataParser"}) {

    merge(GlobalParserOptions, options);

    this.instance = new use();

    if (isString(alias)) {
      Vue.prototype[alias] = $DataParser;
    } else if (isArray(alias)) {
      for (let idx in alias) {
        Vue.prototype[alias[idx]] = $DataParser;
      }
    }
  }
};

$DataParser.newInstance = function (properties) {
  return DataParser.instance.newInstance(properties);
};

export {$DataParser};

export default DataParser;
