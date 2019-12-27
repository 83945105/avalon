import merge from "../../../utils/merge.js";
import Util from "../../../utils/util.js";

export const Data = {
    dict: {
        statusLabel: {
            NORMAL: '正常',
            ENABLED: '已开启',
            DISABLED: '已禁用',
            DELETED: '已删除'
        },
        statusValue: {
            NORMAL: 'NORMAL',
            ENABLED: 'ENABLED',
            DISABLED: 'DISABLED',
            DELETED: 'DELETED'
        },
        status: {
            'NORMAL': '正常',
            'ENABLED': '已开启',
            'DISABLED': '已禁用',
            'DELETED': '已删除'
        },
        classStatusLabel: {
            NOT_START: '未开始',
            SIGNING: '报名中',
            SIGN_OVER: '报名结束',
            CLASSING: '上课中',
            CLASS_OVER: '课程结束'
        },
        classStatusValue: {
            NOT_START: 'NOT_START',
            SIGNING: 'SIGNING',
            SIGN_OVER: 'SIGN_OVER',
            CLASSING: 'CLASSING',
            CLASS_OVER: 'CLASS_OVER'
        },
        classStatus: [{
            label: '未开始',
            value: 'NOT_START'
        }, {
            label: '报名中',
            value: 'SIGNING'
        }, {
            label: '报名结束',
            value: 'SIGN_OVER'
        }, {
            label: '上课中',
            value: 'CLASSING'
        }, {
            label: '课程结束',
            value: 'CLASS_OVER'
        }],
        famClassStatusLabel: {
            SAVE: '保存',
            PUBLISH: '发布',
            CANCEL: '取消',
            DELETE: '删除'
        },
        famClassStatusValue: {
            SAVE: 'SAVE',
            PUBLISH: 'PUBLISH',
            CANCEL: 'CANCEL',
            DELETE: 'DELETE'
        },
        famClassStatus: [{
            label: '保存',
            value: 'SAVE'
        }, {
            label: '发布',
            value: 'PUBLISH'
        }, {
            label: '取消',
            value: 'CANCEL'
        }, {
            label: '删除',
            value: 'DELETE'
        }],
        signStatusLabel: {
            SIGNED: '已报名',
            UNSIGN: '取消报名',
            BEUNSIGN: '被取消报名',
            DELETE: '删除'
        },
        signStatusValue: {
            SIGNED: 'SIGNED',
            UNSIGN: 'UNSIGN',
            BEUNSIGN: 'BEUNSIGN',
            DELETE: 'DELETE'
        },
        signStatus: [{
            label: '已报名',
            value: 'SIGNED'
        }, {
            label: '取消报名',
            value: 'UNSIGN'
        }, {
            label: '被取消报名',
            value: 'BEUNSIGN'
        }, {
            label: '删除',
            value: 'DELETE'
        }],
        signLabel: {
            Y: '已签到',
            N: '未签到',
            LATE: '迟到'
        },
        signValue: {
            Y: 'Y',
            N: 'N',
            LATE: 'LATE'
        },
        signs: [{
            label: '已签到',
            value: 'Y'
        }, {
            label: '未签到',
            value: 'N'
        }, {
            label: '迟到',
            value: 'LATE'
        }],


        courseStatusValue: {
            NOT: 'NOT',
            ENROLMENT: 'ENROLMENT',
            IN_CLASS: 'IN_CLASS',
            COMPLETE: 'COMPLETE'
        },
        signInStatusLabel: {
            'YQD': '已签到',
            'WQD': '未签到',
            'CD': '迟到',
        },
        signInStatusValue: {
            YQD: 'YQD',
            WQD: 'WQD',
            CD: 'CD'
        },
        signInStatus: [{
            label: '已签到',
            value: 'YQD'
        }, {
            label: '未签到',
            value: 'WQD'
        }, {
            label: '迟到',
            value: 'CD'
        }],
        handInStatusLabel: {
            'YSJ': '已上交',
            'WSJ': '未上交'
        },
        handInStatusValue: {
            YSJ: 'YSJ',
            WSJ: 'WSJ'
        },
        handInStatus: [{
            label: '已上交',
            value: 'YSJ'
        }, {
            label: '未上交',
            value: 'WSJ'
        }],
    }
};


export default {

    data() {
        return merge({}, Data)
    },

    mixins: [Util],

    methods: {
        formatterTime(timeStr, len = 19) {
            if (!timeStr) return;
            return timeStr.replace("T", " ").substring(0, len);
        }
    }

}
