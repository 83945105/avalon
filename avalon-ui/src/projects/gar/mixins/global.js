import merge from "../../../utils/merge.js";
import Util from "../../../utils/util.js";

export const Data = {
  dict: {
    statusLabel: {
      NORMAL: '正常',
      ENABLED: '开启',
      DISABLED: '禁用',
      DELETED: '删除'
    },
    statusValue: {
      NORMAL: 'NORMAL',
      ENABLED: 'ENABLED',
      DISABLED: 'DISABLED',
      DELETED: 'DELETED'
    },
    statuses: [{
      label: '正常',
      value: 'NORMAL'
    }, {
      label: '已开启',
      value: 'ENABLED'
    }, {
      label: '已禁用',
      value: 'DISABLED'
    }, {
      label: '已删除',
      value: 'DELETED'
    }],
    searchTypes: [{
      text: '全局查询',
      value: 'GLOBAL_SEARCH'
    }, {
      text: '树查询',
      value: 'TREE_SEARCH'
    }],
    resourceTypeLabel: {
      URL: '统一资源定位符',
      PERMISSION: '资源许可(Shiro注解用)',
      NODE: '节点'
    },
    resourceTypeValue: {
      URL: 'URL',
      PERMISSION: 'PERMISSION',
      NODE: 'NODE'
    },
    resourceTypes: [{
      label: '统一资源定位符',
      value: 'URL'
    }, {
      label: '资源许可(Shiro注解用)',
      value: 'PERMISSION'
    }, {
      label: '节点',
      value: 'NODE'
    }],
    roleTypeLabel: {
      LOCAL: '本地角色'
    },
    roleTypeValue: {
      LOCAL: 'LOCAL'
    },
    roleTypes: [{
      label: '本地角色',
      value: 'LOCAL'
    }],
    templateTypeLabel: {
      VUE_COMPONENT: 'vue组件'
    },
    templateTypeValue: {
      VUE_COMPONENT: 'VUE_COMPONENT'
    },
    templateTypes: [{
      label: 'vue组件',
      value: 'VUE_COMPONENT'
    }],
    routeCacheLabel: {
      Y: '是',
      N: '否'
    },
    routeCacheValue: {
      Y: 'Y',
      N: 'N'
    },
    routeCaches: [{
      label: '是',
      value: 'Y'
    }, {
      label: '否',
      value: 'N'
    }],
    routeViewLabel: {
      'true': '启用',
      'false': '禁用'
    },
    routeViewValue: {
      'true': 'true',
      'false': 'false'
    },
    routeViewProps: [{
      label: '启用',
      value: 'true'
    }, {
      label: '禁用',
      value: 'false'
    }],
    menuGroupTypeLabel: {
      HEADER_MENU: '头部菜单',
      LEFT_MENU: '左侧菜单'
    },
    menuGroupTypeValue: {
      HEADER_MENU: 'HEADER_MENU',
      LEFT_MENU: 'LEFT_MENU'
    },
    menuGroupTypes: [{
      label: '头部菜单',
      value: 'HEADER_MENU'
    }, {
      label: '左侧菜单',
      value: 'LEFT_MENU'
    }],
    menuGroupUseLabel: {
      TRUE: '使用',
      FALSE: '不使用'
    },
    menuGroupUseValue: {
      TRUE: 'TRUE',
      FALSE: 'FALSE'
    },
    menuGroupUses: [{
      label: '使用',
      value: 'TRUE'
    }, {
      label: '不使用',
      value: 'FALSE'
    }],
    menuUseTabLabel: {
      'true': '使用',
      'false': '不使用'
    },
    menuUseTabValue: {
      'true': 'true',
      'false': 'false'
    },
    menuUseTabs: [{
      label: '使用',
      value: 'true'
    }, {
      label: '不使用',
      value: 'false'
    }],
    menuRouteRelationLabel: {
      MENU_SELECTED: '菜单选中',
      MENU_CLICK_TO_ROUTE: '点击菜单时跳转到该路由'
    },
    menuRouteRelationValue: {
      MENU_SELECTED: 'MENU_SELECTED',
      MENU_CLICK_TO_ROUTE: 'MENU_CLICK_TO_ROUTE'
    },
    menuRouteRelations: [{
      label: '菜单选中',
      value: 'MENU_SELECTED'
    }, {
      label: '点击菜单时跳转到该路由',
      value: 'MENU_CLICK_TO_ROUTE'
    }]
  }
};

export default {
  data() {
    return merge({}, Data)
  },

  mixins: [Util],

  methods: {
    formatterTime(timeStr, len = 19) {
      return timeStr.replace("T", " ").substring(0, len);
    }
  }

}
