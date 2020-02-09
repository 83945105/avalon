import argsMatch from "../../../src/utils/args-match.js";
import merge from "../../../src/utils/merge.js";

// 消息类型
export const MessageType = {
    /** 默认*/
    DEFAULT: 'default',
    /** 失败 */
    FAIL: 0,
    /** 成功 */
    SUCCESS: 1,
    /** 警告 */
    WARN: 2,
    /** 信息 */
    INFO: 3,
    /** 错误 */
    ERROR: 4,
    /** 需要登录 */
    NEED_LOGIN: 5,
    /** 没有权限 */
    NO_AUTHORITY: 6,
    /** 找不到 */
    NOT_FOUND: 404
};

// 消息默认配置(该配置不可被覆盖)
export const DefaultMessageOptions = {
    type: MessageType.DEFAULT,//消息类型
    html: false,//是否转义成html
    customClass: undefined,//自定义样式
    duration: 3000,//持续时间
    showClose: false,//是否显示关闭按钮
    center: false,//是否居中
    onClose: undefined//关闭回调
};

// 全局消息配置(该配置可被覆盖)
export const GlobalMessageOptions = merge({content: ''}, DefaultMessageOptions);

// 参数配置
function initArgsOptions() {
    return [{
        name: 'content',
        type: 'string',
        count: 1,
        default: GlobalMessageOptions.content
    }, {
        name: 'customClass',
        type: 'string',
        count: 2,
        default: GlobalMessageOptions.customClass
    }, {
        name: 'showClose',
        type: 'boolean',
        count: 1,
        default: GlobalMessageOptions.showClose
    }, {
        name: 'center',
        type: 'boolean',
        count: 2,
        default: GlobalMessageOptions.center
    }, {
        name: 'html',
        type: 'boolean',
        count: 3,
        default: GlobalMessageOptions.html
    }, {
        name: 'duration',
        type: 'number',
        count: 1,
        default: GlobalMessageOptions.duration
    }, {
        name: 'onClose',
        type: 'function',
        count: 1,
        default: GlobalMessageOptions.onClose
    }];
}

export default class Message {

    open(args) {
        return argsMatch(initArgsOptions(), args);
    };

    success(args) {
        return argsMatch(initArgsOptions(), args);
    };

    fail(args) {
        return argsMatch(initArgsOptions(), args);
    };

    error(args) {
        return argsMatch(initArgsOptions(), args);
    };

    warn(args) {
        return argsMatch(initArgsOptions(), args);
    };

    info(args) {
        return argsMatch(initArgsOptions(), args);
    };

    close(instance) {
    };

    closeAll() {
    };
}
