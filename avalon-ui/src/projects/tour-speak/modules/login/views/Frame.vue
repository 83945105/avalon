<script>
    /*模块全局配置,用于参考*/
    const EventEmitter = require('events');//全局事件对象
    window.events = new EventEmitter();
    window.events.setMaxListeners(100);//设置最大监听数

    //全局注册一些第三方组件,这里以element-ui和weview举例
    import Vue from 'vue';

    import Element from 'element-ui';
    import 'element-ui/lib/theme-chalk/index.css';

    Vue.use(Element);

    import weview from 'weview';
    import 'weview/lib/theme-chalk/index.css';

    Vue.use(weview);

    //使用Packages包下工具
    import Packages from '../../../../../../packages/index.js';
    import {DataParser, $Message} from '../../../../../../packages/index.js';

    Vue.use(Packages, {
        Ajax: {
            options: {
                mock: false,//开启模拟数据
                mockTimeout: 1000,
                baseURL: require('../../../../../../config/axios.js').getBaseUrl(),
                withCredentials: true,//允许跨域携带cookie
                dataParserOptions: {
                    use: DataParser.DataView,
                    options: {
                        needLoginOptions: {//需要登录
                            callback: (data, res) => {
                                // $Message(data.resultInfo.message);
                                window.events.emit('routerToIndex');
                            }
                        },
                        noAuthorityOptions: {//无权限
                            callback(data, res) {
                                $Message(data.resultInfo.message);
                            }
                        },
                        notFoundOptions: {//404
                            callback(data, res) {
                                $Message(data.resultInfo.message);
                            }
                        }
                    }
                }
            }
        }
    });

    //导入一些自定义样式
    import Link from '../../../mixins/link.js';
    import {mapState} from 'vuex';
    import ApiGarUrls from "../../../../gar/urls/api-gar-urls.js";

    export default {

        name: "Frame",

        mixins: [Link],

        computed: {
            ...mapState(['frameModule'])
        },

        methods: {
            initModule() {
                // 初始化模块
                // 1：判断用户是否登录
                // 1-1：如果登录了则转到载入配置页面
                // 1-2：如果没登录则转到登录页面
                new Promise((resolve, reject) => {
                    this.$Ajax.get(ApiGarUrls.get.isOnline)
                        .success(true, resolve)
                        .needLogin(true)
                        .notSuccess(reject)
                        .catch(reject);
                }).then(() => {
                    this.routerToLoginOptionsLoading();
                }).catch(() => {
                    this.routerToLoginIndex();
                });
            }
        },

        created() {
            this.initModule();
            window.events.on('routerToIndex', () => this.routerToLoginIndex());
            window.events.on('login-success', () => this.initModule());
        }

    }
</script>
<!--这里是一种示例,根据自己实际情况自己设计页面布局,这里只做参考-->
<template>
    <router-view></router-view>
</template>

<style lang="less" type="text/less">
    @import "../../../theme-chalk/index.less";
</style>
