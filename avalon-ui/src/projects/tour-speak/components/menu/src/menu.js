/**
 * 将菜单数据转化为选项卡数据
 * @param menuData
 * @returns {Array}
 */
import {isArray, isEmpty} from "../../../../../utils/util.js";
import {Tab} from "../../tab/src/tab-mode.js";

/**
 * 菜单对象
 * @type {Menu}
 */
const Menu = class Menu {

    constructor({
                    id,
                    name,
                    value,
                    iconName = '',
                    useTab = false,
                    tabKey,
                    tabLabel,
                    tabName,
                    initOpenInTab,
                    closableInTab,
                    lazyInTab,
                    disabledInTab,
                    clickToRouteValue = '',
                    routeValues = [],
                    roleValues = [],
                    children = []
                }) {

        if (isEmpty(id)) throw new Error('Menu对象 id 不能为空');
        if (isEmpty(name)) throw new Error('Menu对象 name 不能为空');
        if (isEmpty(value)) throw new Error('Menu对象 value 不能为空');
        if (isEmpty(useTab)) throw new Error('Menu对象 id 不能为空');
        if (useTab === true) {
            if (isEmpty(tabKey)) throw new Error('Menu对象 tabKey 不能为空');
            if (isEmpty(tabLabel)) throw new Error('Menu对象 tabLabel 不能为空');
            if (isEmpty(tabName)) throw new Error('Menu对象 tabName 不能为空');
            if (isEmpty(initOpenInTab)) throw new Error('Menu对象 initOpenInTab 不能为空');
            if (isEmpty(closableInTab)) throw new Error('Menu对象 closableInTab 不能为空');
            if (isEmpty(lazyInTab)) throw new Error('Menu对象 lazyInTab 不能为空');
            if (isEmpty(disabledInTab)) throw new Error('Menu对象 disabledInTab 不能为空');
        }
        if (isEmpty(id)) throw new Error('Menu对象 id 不能为空');
        if (isEmpty(id)) throw new Error('Menu对象 id 不能为空');

        this.id = id;
        this.name = name;
        this.value = value;
        this.iconName = iconName;
        this.useTab = useTab === true || useTab === "true";
        this.tabKey = tabKey;
        this.tabLabel = tabLabel;
        this.tabName = tabName;
        this.initOpenInTab = initOpenInTab === true || initOpenInTab === "true";
        this.closableInTab = closableInTab === true || closableInTab === "true";
        this.lazyInTab = lazyInTab === true || lazyInTab === "true";
        this.disabledInTab = disabledInTab === true || disabledInTab === "true";
        this.clickToRouteValue = clickToRouteValue;
        this.routeValues = routeValues;
        this.roleValues = roleValues;
        this.children = children;

    }

    /**
     * 转为Tab对象
     * @returns {Tab}
     */
    toTab() {
        return new Tab({
            key: this.tabKey,
            label: this.tabLabel,
            name: this.tabName,
            closable: this.closableInTab,
            lazy: this.lazyInTab,
            disabled: this.disabledInTab,
            initOpen: this.initOpenInTab,
            initRouterValue: this.clickToRouteValue,
            routerValues: this.routeValues
        });
    }

};

/**
 * 将菜单数据转为选项卡数据
 * @param menus
 * @returns {Array}
 */
export const caseMenusToTabs = function (menus = []) {
    if (!menus || menus.length === 0) return [];
    let tabs = [];
    let menu;
    for (let i in menus) {
        menu = menus[i];
        if (!(menu instanceof Menu)) {
            menu = new Menu(menu);
        }
        if (!(menu instanceof Menu)) {
            throw new Error('caseMenusToTabs menu 对象必须是Menu类型...');
        }
        if (menu.useTab !== true) {
            continue;
        }
        //如果有子菜单,父菜单不参与选项卡
        if (isArray(menu.children) && menu.children.length > 0) {
            tabs = tabs.concat(caseMenusToTabs(menu.children));
        } else {
            tabs.push(menu.toTab());
        }
    }
    return tabs;
};

export {Menu};
