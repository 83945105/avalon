import merge from "../../../utils/merge.js";
import Util from "../../../utils/util.js";

export const Data = {
    dict: {
        status: {
            'NORMAL': '正常',
            'ENABLED': '已开启',
            'DISABLED': '已禁用',
            'DELETED': '已删除'
        },
        searchType: {
            globalSearch: 'GlobalSearch',
            treeSearch: 'TreeSearch'
        },
        searchTypes: [{
            text: '全局查询',
            value: 'GlobalSearch'
        }, {
            text: '树查询',
            value: 'TreeSearch'
        }],
        resourceType: {
            url: 'URL',
            permission: 'PERMISSION',
            node: 'NODE'
        },
        resourceTypes: [{
            text: '统一资源定位符',
            value: 'URL'
        }, {
            text: '资源许可(Shiro注解用)',
            value: 'PERMISSION'
        }, {
            text: '节点',
            value: 'NODE'
        }],
        roleType: {
            local: 'LOCAL'
        },
        roleTypes: [{
            text: '本地角色',
            value: 'LOCAL'
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
            return this.isEmpty(timeStr) ? '' : timeStr.replace("T", " ").substring(0, len);
        }
    }

}
