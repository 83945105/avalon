import Default from '../response-parser/src/default.js';
import ResponseView from "../response-parser/src/response-view.js";
import {GlobalResponseParserOptions} from "./src/response-parser.js";
import merge from "../../src/utils/merge.js";
import {isArray, isString} from "../../src/utils/util.js";

const $ResponseParser = {};

const ResponseParser = {

    Default: Default,

    ResponseView: ResponseView,

    instance: undefined,

    install(Vue, {use = Default, alias = "$ResponseParser", options} = {
        use: Default,
        alias: "$ResponseParser"
    }) {

        merge(GlobalResponseParserOptions, options);

        this.instance = new use();

        if (isString(alias)) {
            Vue.prototype[alias] = $ResponseParser;
        } else if (isArray(alias)) {
            for (let idx in alias) {
                Vue.prototype[alias[idx]] = $ResponseParser;
            }
        }
    }
};

$ResponseParser.newInstance = function (properties) {
    return ResponseParser.instance.newInstance(properties);
};

export {$ResponseParser};

export default ResponseParser;