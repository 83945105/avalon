/*!
 * Title: 构建多页应用
 * Description:
 * author: 白超
 * date: 2017/12/6
 * version: v1.0
 */
function isArray(obj) {
    return Object.prototype.toString.call(obj).toLowerCase() === "[object array]";
}

function isObject(obj) {
    return Object.prototype.toString.call(obj).toLowerCase() === "[object object]";
}

function isString(obj) {
    return Object.prototype.toString.call(obj).toLowerCase() === "[object string]";
}

const path = require('path');
const Webpack = require('webpack');
const Glob = require('glob');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const ProjectConfig = require('./project.js');

/**
 * 入口文件前置路径
 * 开头结尾不要带/
 */
const entriesBasePath = 'src/projects';
/**
 * 模块文件夹名
 */
const moduleFolderName = 'modules';
/**
 * 入口文件名称
 */
const entriesJsName = 'index';
/**
 * 图标文件名
 */
const faviconFileName = 'favicon';
/**
 * html模板名称
 */
const htmlName = 'index';

/**
 * 获取项目配置
 * 数据示例：
 * [{
 *     name: '项目名',
 *     title: '项目标题',
 *     faviconPath: 'ico图标路径',
 *     modules: [{
 *         name: '模块名',
 *         title: '模块标题',
 *         faviconPath: 'ico图标路径,如果不存在则使用所属项目的',
 *         entryPath: '模块入口路径'
 *     }]
 * }]
 */
module.exports.getProjectOptions = function () {
    let opts = [];
    Glob.sync(`./${entriesBasePath}/*/${moduleFolderName}/*/${entriesJsName}.js`).forEach(path => {
        const matches = path.match(new RegExp(`${entriesBasePath}\/(\\S*)\/${moduleFolderName}\/(\\S*)\/${entriesJsName}.js`));
        if (!matches || !matches[1] || !matches[2]) return true;
        const projectName = matches[1];
        const moduleName = matches[2];
        //查找是否已经存在项目对象
        let project;
        opts.forEach(opt => {
            if (opt.name === projectName) {
                project = opt;
                return false;
            }
        });
        if (!project) {
            //搜寻项目名
            let projectTitle = '未设置';
            Glob.sync(`./${entriesBasePath}/${projectName}/*.txt`).forEach(path => {
                const matches = path.match(new RegExp(`${entriesBasePath}\/${projectName}\/(\\S*).txt`));
                if (!matches || !matches[1]) return true;
                projectTitle = matches[1];
            });
            //搜寻项目ico
            let projectFaviconPath = undefined;
            Glob.sync(`./${entriesBasePath}/${projectName}/${faviconFileName}.ico`).forEach(icoPath => {
                projectFaviconPath = icoPath;
            });
            project = {
                name: projectName,
                title: projectTitle,
                faviconPath: projectFaviconPath,
                modules: []
            };
            opts.push(project);
        }
        //搜寻模块标题
        let moduleTitle = "未设置";
        Glob.sync(`./${entriesBasePath}/${projectName}/${moduleFolderName}/${moduleName}/*.txt`).forEach(path => {
            const matches = path.match(new RegExp(`${entriesBasePath}\/${projectName}\/${moduleFolderName}\/${moduleName}\/(\\S*).txt`));
            if (!matches || !matches[1]) return true;
            moduleTitle = matches[1];
        });
        //搜寻模块ico
        let moduleFaviconPath = undefined;
        Glob.sync(`./${entriesBasePath}/${projectName}/${moduleFolderName}/${moduleName}/${faviconFileName}.ico`).forEach(icoPath => {
            moduleFaviconPath = icoPath;
        });
        project.modules.push({
            name: moduleName,
            title: moduleTitle,
            faviconPath: moduleFaviconPath || project.faviconPath,
            entryPath: path
        });
    });
    return opts;
};

/**
 * 获取编译的项目名/别名集合
 * 2019-05-23 修改：添加别名功能,实现动态配置项目名,因此该方法调整后,返回结果由 ['gar'] 调整为 [{name: 'gar', alias: 'gar'}]
 */
module.exports.compileProjectNameAliases = function (projectConfig) {
    const projectOptions = this.getProjectOptions();
    let includeProjectNames = [];
    if (!projectConfig.includeProjectNames || (isArray(projectConfig.includeProjectNames) && projectConfig.includeProjectNames.length === 0)) {
        projectOptions.forEach(projectOption => includeProjectNames.push({
            name: projectOption.name,
            alias: projectOption.name
        }));
    } else if (isArray(projectConfig.includeProjectNames)) {
        projectConfig.includeProjectNames.forEach(includeProjectName => {
            if (isString(includeProjectName)) {
                includeProjectNames.push({
                    name: includeProjectName,
                    alias: includeProjectName
                });
            } else if (isObject(includeProjectName)) {
                includeProjectNames.push({
                    name: includeProjectName.name,
                    alias: includeProjectName.alias
                });
            } else {
                throw new Error('includeProjectName type is wrong.');
            }
        });
    } else if (isString(projectConfig.includeProjectNames)) {
        includeProjectNames.push({
            name: projectConfig.includeProjectNames,
            alias: projectConfig.includeProjectNames
        });
    } else if (isObject(projectConfig.includeProjectNames)) {
        includeProjectNames.push({
            name: projectConfig.includeProjectNames.name,
            alias: projectConfig.includeProjectNames.alias
        });
    } else {
        throw new Error('includeProjectNames type is wrong.');
    }
    let projectNames = [];
    if (!projectConfig.excludeProjectNames || (isArray(projectConfig.excludeProjectNames) && projectConfig.excludeProjectNames.length === 0)) {
        return includeProjectNames;
    } else if (isArray(projectConfig.excludeProjectNames)) {
        includeProjectNames.forEach(includeProjectName => {
            for (let i in projectConfig.excludeProjectNames) {
                let excludeProjectName = projectConfig.excludeProjectNames[i];
                if (isString(excludeProjectName)) {
                    if (excludeProjectName === includeProjectName.alias) return true;
                } else if (isObject(excludeProjectName)) {
                    if (excludeProjectName.alias === includeProjectName.alias) return true;
                } else {
                    throw new Error('excludeProjectName type is wrong.');
                }
            }
            projectNames.push(includeProjectName);
        });
    } else if (isString(projectConfig.excludeProjectNames)) {
        includeProjectNames.forEach(includeProjectName => {
            if (includeProjectName.alias === projectConfig.excludeProjectNames) return true;
            projectNames.push(includeProjectName);
        });
    } else if (isObject(projectConfig.excludeProjectNames)) {
        includeProjectNames.forEach(includeProjectName => {
            if (includeProjectName.alias === projectConfig.excludeProjectNames.alias) return true;
            projectNames.push(includeProjectName);
        });
    } else {
        throw new Error('excludeProjectNames type is wrong.');
    }
    return projectNames;
};

/**
 * 获取编译的模块名集合
 */
module.exports.compileModuleNames = function (projectConfig) {
    if (!isObject(projectConfig.includeModuleNames)) {
        throw new Error('includeModuleNames type is wrong.');
    }
    if (!isObject(projectConfig.excludeModuleNames)) {
        throw new Error('excludeModuleNames type is wrong.');
    }
    const projectOptions = this.getProjectOptions();
    const projectNames = this.compileProjectNameAliases(projectConfig).map(compileProjectNameAlias => compileProjectNameAlias.name);
    let modules = {};
    projectNames.forEach(projectName => {
        let moduleOptions = undefined;
        projectOptions.forEach(projectOption => {
            if (projectOption.name === projectName) {
                moduleOptions = projectOption.modules;
                return false;
            }
        });
        if (!moduleOptions || moduleOptions.length === 0) return true;
        let includeModuleNames = [];
        if (!projectConfig.includeModuleNames[projectName] || (isArray(projectConfig.includeModuleNames[projectName]) && projectConfig.includeModuleNames[projectName].length === 0)) {
            moduleOptions.forEach(moduleOption => includeModuleNames.push(moduleOption.name));
        } else if (isArray(projectConfig.includeModuleNames[projectName])) {
            projectConfig.includeModuleNames[projectName].forEach(name => includeModuleNames.push(name));
        } else if (isString(projectConfig.includeModuleNames[projectName])) {
            includeModuleNames.push(projectConfig.includeModuleNames[projectName]);
        } else {
            throw new Error(`includeModuleNames ${projectName} type is wrong.`);
        }
        let moduleNames = [];
        if (!projectConfig.excludeModuleNames[projectName] || (isArray(projectConfig.excludeModuleNames[projectName]) && projectConfig.excludeModuleNames[projectName].length === 0)) {
            modules[projectName] = includeModuleNames;
        } else if (isArray(projectConfig.excludeModuleNames[projectName])) {
            includeModuleNames.forEach(name => {
                for (let i in projectConfig.excludeModuleNames[projectName]) {
                    if (projectConfig.excludeModuleNames[projectName][i] === name) return true;
                }
                moduleNames.push(name);
            });
        } else if (isString(projectConfig.excludeModuleNames[projectName])) {
            includeModuleNames.forEach(name => {
                if (name === projectConfig.excludeModuleNames[projectName]) return true;
                moduleNames.push(name);
            });
        } else {
            throw new Error(`excludeModuleNames ${projectName} type is wrong.`);
        }
        modules[projectName] = moduleNames;
    });
    return modules;
};

/**
 * 获取需要编译的项目配置
 * 数据示例：
 * [{
 *     name: '项目名',
 *     title: '项目标题',
 *     faviconPath: 'ico图标路径',
 *     modules: [{
 *         name: '模块名',
 *         title: '模块标题',
 *         faviconPath: 'ico图标路径,如果不存在则使用所属项目的',
 *         entryPath: '模块入口路径'
 *     }]
 * }]
 */
module.exports.getCompileProjectOptions = function (projectConfig) {
    const projectNameAliases = this.compileProjectNameAliases(projectConfig);
    const modules = this.compileModuleNames(projectConfig);
    const projectOptions = this.getProjectOptions();
    if (projectNameAliases.length === 0) {
        throw new Error('No project can be compiled.');
    }
    let projectNames = projectNameAliases.map(projectNameAlias => projectNameAlias.name);
    let compileProjectOptions = [];
    projectOptions.forEach(projectOption => {
        if (projectNames.indexOf(projectOption.name) === -1) return true;
        let projectNameAlias = projectNameAliases.filter(projectNameAlias => projectNameAlias.name === projectOption.name)[0];
        let moduleNames = modules[projectNameAlias.name];
        let compileProjectOption = {
            name: projectNameAlias.name,
            alias: projectNameAlias.alias,
            title: projectOption.title,
            faviconPath: projectOption.faviconPath,
            modules: []
        };
        projectOption.modules.forEach(module => {
            if (moduleNames && moduleNames.length > 0 && moduleNames.indexOf(module.name) === -1) return true;
            compileProjectOption.modules.push({
                name: module.name,
                title: module.title,
                faviconPath: module.faviconPath,
                entryName: `${projectNameAlias.name}-${module.name}`,
                entryAlias: `${projectNameAlias.alias}-${module.name}`,
                entryPath: module.entryPath
            });
        });
        compileProjectOptions.push(compileProjectOption);
    });
    return compileProjectOptions;
};

/**
 * 获取入口配置对象
 */
module.exports.getEntries = function (projectConfig = ProjectConfig) {
    let entries = {};
    this.getCompileProjectOptions(projectConfig).forEach(project => {
        project.modules.forEach(module => {
            entries[module.entryName] = module.entryPath;
        });
    });
    // entries.vendor = Object.keys(PackageJson.dependencies);
    return entries;
};

/**
 * 获取dev时html配置信息
 */
module.exports.getDevHtmlWebpackPlugins = function (projectConfig = ProjectConfig) {
    let dev = [
        // vendor - 将第三方库文件打入vendor
        new Webpack.optimize.CommonsChunkPlugin({
            name: 'vendor',
            minChunks(module, count) {
                // console.log(module.resource, `引用次数${count}`);
                //将所有在node_modules下的第三方代码抽取至vendor
                return (
                    module.resource &&
                    /\.js$/.test(module.resource) &&
                    module.resource.indexOf(
                        path.join(__dirname, '../node_modules')
                    ) === 0
                )
            }
        }),
        // runtime - 从vendor抽离webpack运行文件
        // 因为如果引用的第三方库不变,每次打包只有该运行文件代码会改变,如果打入vendor,vendor的hash会改变,导致浏览器缓存失效
        new Webpack.optimize.CommonsChunkPlugin({
            name: 'runtime',
            chunks: ['vendor']
        }),
        // common - 从所有入口js抽取公众代码至common内,使浏览器能够缓存
        new Webpack.optimize.CommonsChunkPlugin({
            name: 'common',
            chunks: (() => {
                let chunks = [];
                this.getCompileProjectOptions(projectConfig).forEach(project => {
                    project.modules.forEach(module => chunks.push(module.entryName));
                });
                return chunks;
            })()
        })
    ];
    this.getCompileProjectOptions(projectConfig).forEach(project => {
        project.modules.forEach(module => {
            dev.push(new HtmlWebpackPlugin({
                title: module.title,
                //该路径影响访问路径和打包路径
                filename: `${project.name}/${projectConfig.htmlSubDirectory}/${module.name}/${htmlName}.html`,
                template: `${entriesBasePath}/${project.name}/${moduleFolderName}/${module.name}/${htmlName}.html`,
                favicon: module.faviconPath,
                inject: true,
                chunksSortMode: 'manual',//手动排序chunks
                chunks: ['runtime', 'common', 'vendor', module.entryName]
            }));
        });
    });
    return dev;
};

/**
 * 获取prod时html配置信息
 */
module.exports.getProdHtmlWebpackPlugins = function (projectConfig = ProjectConfig) {
    let prod = [
        // vendor - 将第三方库文件打入vendor
        new Webpack.optimize.CommonsChunkPlugin({
            name: 'vendor',
            minChunks(module, count) {
                // console.log(module.resource, `引用次数${count}`);
                //将所有在node_modules下的第三方代码抽取至vendor
                return (
                    module.resource &&
                    /\.js$/.test(module.resource) &&
                    module.resource.indexOf(
                        path.join(__dirname, '../node_modules')
                    ) === 0
                )
            }
        }),
        // runtime - 从vendor抽离webpack运行文件
        // 因为如果引用的第三方库不变,每次打包只有该运行文件代码会改变,如果打入vendor,vendor的hash会改变,导致浏览器缓存失效
        new Webpack.optimize.CommonsChunkPlugin({
            name: 'runtime',
            chunks: ['vendor']
        }),
        // common - 从所有入口js抽取公众代码至common内,使浏览器能够缓存
        new Webpack.optimize.CommonsChunkPlugin({
            name: 'common',
            chunks: (() => {
                let chunks = [];
                this.getCompileProjectOptions(projectConfig).forEach(project => {
                    project.modules.forEach(module => chunks.push(module.entryName));
                });
                return chunks;
            })()
        })
    ];
    this.getCompileProjectOptions(projectConfig).forEach(project => {
        project.modules.forEach(module => {
            prod.push(new HtmlWebpackPlugin({
                title: module.title,
                //该路径影响访问路径和打包路径
                filename: `${project.name}/${projectConfig.htmlSubDirectory}/${module.name}/${htmlName}.html`,
                template: `${entriesBasePath}/${project.name}/${moduleFolderName}/${module.name}/${htmlName}.html`,
                favicon: module.faviconPath,
                inject: true,
                chunksSortMode: 'manual',//手动排序chunks
                chunks: ['runtime', 'common', 'vendor', module.entryName]
            }));
        });
    });
    return prod;
};

/**
 * 前端打包后的项目名
 * @param projectConfig
 * @returns {string}
 */
module.exports.prodProjectName = function (projectConfig = ProjectConfig) {
    const compileProjectNameAliases = this.compileProjectNameAliases(projectConfig);
    if (process.env.NODE_ENV === 'production' && compileProjectNameAliases.length > 1) {
        throw new Error(`You can only pack one project at a time, but you configure [${compileProjectNameAliases}].`);
    }
    return `${compileProjectNameAliases[0].name}`;
};

/**
 * 前端打包后的项目别名
 * @param projectConfig
 * @returns {string}
 */
module.exports.prodProjectAlias = function (projectConfig = ProjectConfig) {
    const compileProjectNameAliases = this.compileProjectNameAliases(projectConfig);
    if (process.env.NODE_ENV === 'production' && compileProjectNameAliases.length > 1) {
        throw new Error(`You can only pack one project at a time, but you configure [${compileProjectNameAliases}].`);
    }
    return `${compileProjectNameAliases[0].alias}`;
};

/**
 * 获取public文件夹被 copy-webpack-plugin 插件复制的目标路径前缀
 */
module.exports.getPublicDirCopyBasePath = function () {
    return 'public';
};

module.exports.publicPath = function () {
    return process.env.NODE_ENV === 'production'
        ? '../../'
        : '/';
};

module.exports.outputDir = function () {
    /**
     * 开发时将所有文件打包至dist
     * 打包时将不同项目分别打入dist下对应项目文件夹
     */
    return process.env.NODE_ENV === 'production' ? `./dist/${this.prodProjectAlias()}` : './dist';
};

module.exports.assetsDir = function () {
    return process.env.NODE_ENV === 'production' ? './' : 'static';
};

module.exports.iconPaths = function () {
    return {
        favicon32: `${this.getPublicDirCopyBasePath()}/${this.prodProjectAlias()}/favicon.ico`,
        favicon16: `${this.getPublicDirCopyBasePath()}/${this.prodProjectAlias()}/favicon.ico`,
        appleTouchIcon: `${this.getPublicDirCopyBasePath()}/${this.prodProjectAlias()}/favicon.ico`,
        maskIcon: `${this.getPublicDirCopyBasePath()}/${this.prodProjectAlias()}/favicon.ico`,
        msTileImage: `${this.getPublicDirCopyBasePath()}/${this.prodProjectAlias()}/favicon.ico`
    }
};

module.exports.pwa = function () {
    return {
        name: this.prodProjectAlias(),
        iconPaths: this.iconPaths(),
        manifestPath: `${this.getPublicDirCopyBasePath()}/${this.prodProjectAlias()}/manifest.json`
    };
};

/**
 * 获取vue-cli 3.0 pages配置
 * @param projectConfig
 */
module.exports.getPagesForVueCli3 = function (projectConfig = ProjectConfig) {
    let pages = {};
    this.getCompileProjectOptions(projectConfig).forEach(project => {
        project.modules.forEach(module => {
            pages[module.entryName] = {
                // page 的入口
                entry: module.entryPath,
                // 模板来源
                template: `${entriesBasePath}/${project.name}/${moduleFolderName}/${module.name}/${htmlName}.html`,
                // 在 dist/index.html 的输出
                filename: `${process.env.NODE_ENV === 'production' ? '' : `${project.alias}/`}${projectConfig.htmlSubDirectory}/${module.name}/${htmlName}.html`,
                // 当使用 faviconPath 选项时，
                // template 中的 icon 标签需要是 <title><%= htmlWebpackPlugin.options.faviconPath %></title>
                faviconPath: module.faviconPath,
                // 当使用 title 选项时，
                // template 中的 title 标签需要是 <title><%= htmlWebpackPlugin.options.title %></title>
                title: module.title,
                // 在这个页面中包含的块，默认情况下会包含
                // 提取出来的通用 chunk 和 vendor chunk。
                chunks: ['chunk-vendors', 'chunk-common', module.entryName]
            };
        });
    });
    return pages;
};

module.exports.getExcludePrefetchModuleNames = function (projectConfig = ProjectConfig) {
    let names = [];
    let excludePrefetchModuleNames = projectConfig.excludePrefetchModuleNames;
    for (let projectName in excludePrefetchModuleNames) {
        excludePrefetchModuleNames[projectName].forEach(moduleName => names.push(`${projectName}-${moduleName}`));
    }
    return names;
};

module.exports.getExcludePreloadModuleNames = function (projectConfig = ProjectConfig) {
    let names = [];
    let excludePreloadModuleNames = projectConfig.excludePreloadModuleNames;
    for (let projectName in excludePreloadModuleNames) {
        excludePreloadModuleNames[projectName].forEach(moduleName => names.push(`${projectName}-${moduleName}`));
    }
    return names;
};

module.exports.entriesBasePath = entriesBasePath;
module.exports.moduleFolderName = moduleFolderName;
module.exports.entriesJsName = entriesJsName;
module.exports.faviconFileName = faviconFileName;
module.exports.htmlName = htmlName;
