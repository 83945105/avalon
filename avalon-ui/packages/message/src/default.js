import Message from "./message.js";

export default class Default extends Message {

    open(args) {
        let opts = super.open(args);
        alert(opts.content || '未实现');
    }

    success(args) {
        let opts = super.success(args);
        alert(opts.content || '未实现');
    }

    fail(args) {
        let opts = super.fail(args);
        alert(opts.content || '未实现');
    }

    error(args) {
        let opts = super.error(args);
        alert(opts.content || '未实现');
    }

    warn(args) {
        let opts = super.warn(args);
        alert(opts.content || '未实现');
    }

    info(args) {
        let opts = super.info(args);
        alert(opts.content || '未实现');
    }

    close(instance) {
    }

    closeAll() {
    }
}