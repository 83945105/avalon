'use strict';
// require('./check-versions')();

process.env.NODE_ENV = 'production';

const ora = require('ora');
const rm = require('rimraf');
const chalk = require('chalk');
const webpack = require('webpack');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');
const path = require('path');
const glob = require('glob');

function resolve(dir) {
    return path.join(__dirname, '..', dir)
}

const styleLoaders = function (options) {
    const output = [];
    const loaders = exports.cssLoaders(options);
    for (const extension in loaders) {
        const loader = loaders[extension];
        output.push({
            test: new RegExp('\\.' + extension + '$'),
            use: loader
        });
    }
    return output;
};

const assetsPath = function (_path) {
    return path.posix.join('', _path)
};

const webpackConfig = {
    entry: {
        ...function () {
            let entries = {
                index: './packages/index.js'
            };
            glob.sync(`./packages/*/index.js`).forEach(f => {
                let name = f.match(new RegExp(`packages\/(\\S*)\/index.js`))[1];
                entries[`${name}`] = f;
            });
            return entries;
        }()
    },
    context: path.resolve(__dirname, '../'),
    devtool: false,
    output: {
        path: path.resolve(__dirname, '../lib'),
        filename: path.posix.join('', '[name].js'),
        publicPath: '/',
        library: 'wetools',
        libraryTarget: 'umd',
        umdNamedDefine: true
    },
    externals: {
        vue: {
            root: 'Vue',
            commonjs: 'vue',
            commonjs2: 'vue',
            amd: 'vue'
        },
        weview: {
            root: 'weview',
            commonjs: 'weview',
            commonjs2: 'weview',
            amd: 'weview'
        },
        'element-ui': {
            root: 'element-ui',
            commonjs: 'element-ui',
            commonjs2: 'element-ui',
            amd: 'element-ui'
        },
        'axios': {
            root: 'axios',
            commonjs: 'axios',
            commonjs2: 'axios',
            amd: 'axios'
        }
    },
    resolve: {
        extensions: ['.js', '.vue', '.json'],
        alias: {
            '@': resolve('packages'),
        }
    },
    node: {
        setImmediate: false,
        dgram: 'empty',
        fs: 'empty',
        net: 'empty',
        tls: 'empty',
        child_process: 'empty'
    },
    plugins: [
        new UglifyJsPlugin({
            uglifyOptions: {
                compress: {
                    warnings: false
                }
            },
            sourceMap: false,
            parallel: true
        }),
        new webpack.optimize.ModuleConcatenationPlugin()
    ],
    module: {
        rules: [
            /*            ...styleLoaders({
                            sourceMap: false,
                            extract: true,
                            usePostCSS: true
                        }),*/
            {
                test: /\.vue$/,
                loader: 'vue-loader',
                /*options: {
                    loaders: utils.cssLoaders({
                        sourceMap: false,
                        extract: true
                    }),
                    cssSourceMap: false,
                    cacheBusting: true,
                    transformToRequire: {
                        video: ['examples', 'poster'],
                        source: 'examples',
                        img: 'examples',
                        image: 'xlink:href'
                    }
                }*/
            },
            {
                test: /\.js$/,
                loader: 'babel-loader',
                include: [resolve('examples'), resolve('packages'), resolve('test'), resolve('node_modules/webpack-dev-server/client')]
            },
            {
                test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
                loader: 'url-loader',
                options: {
                    limit: 10000,
                    name: assetsPath('img/[name].[hash:7].[ext]')
                }
            },
            {
                test: /\.(mp4|webm|ogg|mp3|wav|flac|aac)(\?.*)?$/,
                loader: 'url-loader',
                options: {
                    limit: 10000,
                    name: assetsPath('media/[name].[hash:7].[ext]')
                }
            },
            {
                test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
                loader: 'url-loader',
                options: {
                    limit: 10000,
                    name: assetsPath('fonts/[name].[hash:7].[ext]')
                }
            },
            {
                test: /\.less$/,
                loaders: ['style-loader', 'css-loader', 'less-loader']
            }
        ]
    }
};

const spinner = ora('building for production...');
spinner.start();

rm(path.resolve(__dirname, '../lib'), err => {
    if (err) throw err;
    webpack(webpackConfig, (err, stats) => {
        spinner.stop();
        if (err) throw err;
        process.stdout.write(stats.toString({
            colors: true,
            modules: false,
            children: false, // If you are using ts-loader, setting this to true will make TypeScript errors show up during build.
            chunks: false,
            chunkModules: false
        }) + '\n\n');

        if (stats.hasErrors()) {
            console.log(chalk.red('  Build failed with errors.\n'));
            process.exit(1)
        }

        console.log(chalk.cyan('  Build complete.\n'));
        console.log(chalk.yellow(
            '  Tip: built files are meant to be served over an HTTP server.\n' +
            '  Opening index.html over file:// won\'t work.\n'
        ))
    })
});