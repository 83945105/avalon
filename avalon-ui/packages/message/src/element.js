import Message from "./message.js";
import {isObject} from "../../../src/utils/util.js";

const ElementUI = require('element-ui');

const ElementMessage = ElementUI ? ElementUI.Message : window.ELEMENT ? window.ELEMENT.Message : () => {
    alert('未检测到ElementUI组件');
    return void 0;
};

export default class Element extends Message {

    _element = undefined;

    constructor() {
        super();
        this._element = ElementMessage;
    }

    open(args) {
        let opts = super.open(args);
        opts.dangerouslyUseHTMLString = opts.html;
        return this._element(opts);
    }

    success(args) {
        let opts = super.success(args);
        opts.type = 'success';
        opts.dangerouslyUseHTMLString = opts.html;
        return this._element(opts);
    }

    fail(args) {
        let opts = super.fail(args);
        opts.type = 'error';
        opts.dangerouslyUseHTMLString = opts.html;
        return this._element(opts);
    }

    error(args) {
        let opts = super.error(args);
        opts.type = 'error';
        opts.dangerouslyUseHTMLString = opts.html;
        return this._element(opts);
    }

    warn(args) {
        let opts = super.warn(args);
        opts.type = 'warning';
        opts.dangerouslyUseHTMLString = opts.html;
        return this._element(opts);
    }

    info(args) {
        let opts = super.info(args);
        opts.type = 'info';
        opts.dangerouslyUseHTMLString = opts.html;
        return this._element(opts);
    }

    close(instance) {
        if (isObject(instance)) {
            instance.close();
        }
    };

    closeAll() {
        this._element.closeAll();
    };
}
