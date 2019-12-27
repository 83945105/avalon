const MultiPageHelper = require('./config/multiPageHelper.js');
const AxiosConfig = require('./config/axios.js');

module.exports = {
    devServer: {
        clientLogLevel: 'warning',
        historyApiFallback: true,
        hot: true,
        // contentBase: false, // since we use CopyWebpackPlugin.
        compress: true,
        host: 'localhost',
        port: '8000',
        open: true,
        overlay: {warnings: false, errors: true},
        publicPath: '/',
        proxy: {
            ...AxiosConfig.getProxys()
        },
        quiet: true, // necessary for FriendlyErrorsPlugin
        watchOptions: {
            poll: false,
        },
        disableHostCheck: true//允许使用外网访问
    },
    publicPath: MultiPageHelper.publicPath(),
    //当运行 vue-cli-service build 时生成的生产环境构建文件的目录。注意目标目录在构建之前会被清除 (构建时传入 --no-clean 可关闭该行为)。
    outputDir: MultiPageHelper.outputDir(),
    //放置生成的静态资源 (js、css、img、fonts) 的 (相对于 outputDir 的) 目录。
    assetsDir: MultiPageHelper.assetsDir(),
    pages: {
        ...MultiPageHelper.getPagesForVueCli3()
    },
    productionSourceMap: false,
    pwa: MultiPageHelper.pwa(),
    chainWebpack: config => {
        // 移除不需要prefetch模块的相关代码
        MultiPageHelper.getExcludePrefetchModuleNames().forEach(name => config.plugins.delete(`prefetch-${name}`));
        // 移除不需要preload模块的相关代码
        MultiPageHelper.getExcludePreloadModuleNames().forEach(name => config.plugins.delete(`preload-${name}`));
        // 对于public文件夹,只拷贝打包项目+frame文件夹资源
        config
            .plugin('copy')
            .tap(args => {
                return [[
                    {
                        // 拷贝frame文件夹
                        from: 'public/frame',
                        to: `${MultiPageHelper.getPublicDirCopyBasePath()}/frame`,
                        toType: 'dir',
                        ignore: [
                            '.DS_Store'
                        ]
                    },
                    {
                        from: `public/${MultiPageHelper.prodProjectName()}`,
                        to: `${MultiPageHelper.getPublicDirCopyBasePath()}/${MultiPageHelper.prodProjectName()}`,
                        toType: 'dir',
                        ignore: [
                            '.DS_Store'
                        ]
                    }
                ]];
            });

        config
            .module
            .rule('vue')
            .use('vue-loader')
            .loader('vue-loader')
            .tap(options => {
                options.transformAssetUrls = {
                    audio: 'src',// 转换audio标签src路径
                };
                return options;
            });


    }
};
