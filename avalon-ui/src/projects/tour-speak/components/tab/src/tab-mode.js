import {isEmpty} from "../../../../../utils/util.js";

/**
 * 选项卡对象
 * @type {Tab}
 */
const Tab = class Tab {

    constructor({key, label, name, closable, lazy, disabled, initOpen, initRouterValue = '', routerValues = []}) {
        if (isEmpty(key)) throw new Error('Tab对象 key 不能为空');
        if (isEmpty(label)) throw new Error('Tab对象 label 不能为空');
        if (isEmpty(name)) throw new Error('Tab对象 name 不能为空');
        if (isEmpty(closable)) throw new Error('Tab对象 closable 不能为空');
        if (isEmpty(lazy)) throw new Error('Tab对象 lazy 不能为空');
        if (isEmpty(disabled)) throw new Error('Tab对象 disabled 不能为空');
        if (isEmpty(initOpen)) throw new Error('Tab对象 initOpen 不能为空');

        this.key = key;
        this.label = label;
        this.name = name;
        this.closable = closable === true || closable === "true";
        this.lazy = lazy === true || lazy === "true";
        this.disabled = disabled === true || disabled === "true";
        this.initOpen = initOpen === true || initOpen === "true";
        this.initRouterValue = initRouterValue;
        this.routerValues = routerValues;
        this.routerEnabled = {};
        this.routerValues.forEach(routerValue => this.routerEnabled[routerValue] = routerValue === this.initRouterValue);
        // 需要缓存的路由
        this.cacheRouterValues = void 0;

    }

};

export {Tab};
