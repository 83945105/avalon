import Message from "./message.js";
import {isObject} from "../../../src/utils/util.js";

const Weview = require('weview');

const WeViewMessage = Weview ? Weview.$Message : window.weview ? window.weview.$Message : () => {
    alert('未检测到weview组件');
    return void 0;
};

export default class WeView extends Message {

    _weview = undefined;

    constructor() {
        super();
        this._weview = WeViewMessage;
    }

    open(args) {
        let opts = super.open(args);
        return this._weview(opts);
    }

    success(args) {
        let opts = super.success(args);
        opts.type = 'success';
        return this._weview(opts);
    }

    fail(args) {
        let opts = super.fail(args);
        opts.type = 'fail';
        return this._weview(opts);
    }

    error(args) {
        let opts = super.error(args);
        opts.type = 'error';
        return this._weview(opts);
    }

    warn(args) {
        let opts = super.warn(args);
        opts.type = 'warn';
        return this._weview(opts);
    }

    info(args) {
        let opts = super.info(args);
        opts.type = 'info';
        return this._weview(opts);
    }

    close(instance) {
        if (isObject(instance)) {
            instance.close();
        }
    };

    closeAll() {
        this._weview.closeAll();
    };
}
