/*!
 * Title: 配置扫描
 * Description:
 * author: 白超
 * date: 2018/12/3
 * version: v1.0
 */
const Fs = require('fs');
const Glob = require('glob');
const ProjectConfig = require('./project.js');
const MultiPageHelper = require('../config/multiPageHelper.js');

const configDirectoryName = 'config';
const projectConfigFileName = 'project.conf';
const moduleConfigFileName = 'module.conf';
const lineBreak = '--------------------';

(function () {
    const compileProjectNames = MultiPageHelper.compileProjectNameAliases(ProjectConfig).map(compileProjectNameAlias => compileProjectNameAlias.name);
    const compileModuleNames = MultiPageHelper.compileModuleNames(ProjectConfig);
    if (compileProjectNames.length === 0) return;
    console.log(lineBreak);
    console.log(`配置扫描开始`);
    new Promise((resolve, reject) => {

        console.log(`开始扫描项目...`);
        const projectPaths = Glob.sync(`./${MultiPageHelper.entriesBasePath}/*`);
        resolve(projectPaths);

    }).then(projectPaths => {

        console.log(`分析项目扫描结果...`);
        return Promise.all([...projectPaths.map(projectPath => new Promise((resolve, reject) => {
            //判断文件可用性
            Fs.stat(projectPath, (error, stats) => {
                if (error) {
                    resolve(null);
                    return false;
                }
                if (!stats.isDirectory()) {
                    resolve(null);
                    return false;
                }
                const matches = projectPath.match(new RegExp(`${MultiPageHelper.entriesBasePath}\/(\\S*)`));
                if (!matches || !matches[1]) {
                    resolve(null);
                    return false;
                }
                const projectName = matches[1];
                resolve(projectName);
            });
        }))]).then(projectNames => {
            //根据编译项目过滤
            return projectNames.filter(projectName => compileProjectNames.indexOf(projectName) !== -1);
        });

    }).then(projectNames => {

        console.log(`共扫描出${projectNames.length}个项目:[${projectNames}]`);
        if (projectNames.length === 0) return;
        console.log(lineBreak);
        console.log(`开始项目模块扫描...`);
        //使用reduce动态串行promise
        return projectNames.reduce((pre, projectName) => {
            return pre.then(() => new Promise((resolve, reject) => {

                console.log(lineBreak);
                console.log(`开始扫描项目:[${projectName}]下模块...`);
                const modulePaths = Glob.sync(`./${MultiPageHelper.entriesBasePath}\/${projectName}\/${MultiPageHelper.moduleFolderName}/*`);
                resolve(modulePaths);

            })).then(modulePaths => {

                console.log(`分析项目:[${projectName}]下模块扫描结果...`);
                const currentCompileModuleNames = compileModuleNames[projectName];
                return Promise.all([...modulePaths.map(modulePath => new Promise((resolve, reject) => {
                    //判断文件可用性
                    Fs.stat(modulePath, (error, stats) => {
                        if (error) {
                            resolve(null);
                            return false;
                        }
                        if (!stats.isDirectory()) {
                            resolve(null);
                            return false;
                        }
                        const matches = modulePath.match(new RegExp(`${MultiPageHelper.entriesBasePath}\/${projectName}\/${MultiPageHelper.moduleFolderName}\/(\\S*)`));
                        if (!matches || !matches[1]) {
                            resolve(null);
                            return false;
                        }
                        const moduleName = matches[1];
                        resolve(moduleName);
                    });
                }))]).then(moduleNames => {
                    //根据编译模块过滤
                    return moduleNames.filter(moduleName => {
                        if (!moduleName) return false;
                        return !(currentCompileModuleNames && currentCompileModuleNames.length > 0 && currentCompileModuleNames.indexOf(moduleName) === -1);
                    });
                });
            }).then(moduleNames => {

                console.log(`项目:[${projectName}]共扫描出${moduleNames.length}个模块:[${moduleNames}]`);
                if (moduleNames.length === 0) return;
                console.log(lineBreak);
                console.log(`开始生成项目:[${projectName}]下模块配置文件...`);
                return Promise.all([...moduleNames.map(moduleName => new Promise((resolve, reject) => {
                    const moduleConfigDirectoryPath = `./${MultiPageHelper.entriesBasePath}/${projectName}/${MultiPageHelper.moduleFolderName}/${moduleName}/${configDirectoryName}`;
                    Fs.access(moduleConfigDirectoryPath, err => {
                        if (err) {
                            console.log(`项目:[${projectName}],模块:[${moduleName}]配置文件目录[${moduleConfigDirectoryPath}]不存在...开始创建`);
                            Fs.mkdir(moduleConfigDirectoryPath, err => {
                                if (err) {
                                    console.log(err);
                                }
                                console.log(`项目:[${projectName}],模块:[${moduleName}]配置文件目录[${moduleConfigDirectoryPath}]创建成功`)
                                resolve();
                            });
                        } else {
                            resolve();
                        }
                    });
                }).then(() => {

                    const moduleConfigContent = `//该文件是使用指令生成的文件,无需改动,你可以使用指令 npm run scan:config 重置该文件
          export default {
            projectName: "${projectName}",
            moduleName: "${moduleName}"
          };
        `;
                    const moduleConfigFilePath = `./${MultiPageHelper.entriesBasePath}/${projectName}/${MultiPageHelper.moduleFolderName}/${moduleName}/${configDirectoryName}/${moduleConfigFileName}.js`;
                    return new Promise((resolve, reject) => {
                        Fs.writeFile(moduleConfigFilePath, moduleConfigContent, 'utf8', err => {
                            if (err) {
                                console.log(err)
                                return false;
                            }
                            console.log(`项目:[${projectName}],模块:[${moduleName}]成功生成配置文件至[${moduleConfigFilePath}]`);
                            resolve();
                        });
                    });
                }))]).then(() => {

                    console.log(lineBreak);
                    console.log(`开始生成项目:[${projectName}]配置文件...`);
                    const projectConfigDirectoryPath = `./${MultiPageHelper.entriesBasePath}/${projectName}/${configDirectoryName}`;
                    return new Promise((resolve, reject) => {
                        Fs.access(projectConfigDirectoryPath, err => {
                            if (err) {
                                console.log(`项目:[${projectName}]配置文件目录[${projectConfigDirectoryPath}]不存在...开始创建`);
                                Fs.mkdir(projectConfigDirectoryPath, err => {
                                    if (err) {
                                        console.log(err);
                                    }
                                    console.log(`项目:[${projectName}]配置文件目录[${projectConfigDirectoryPath}]创建成功`);
                                    resolve();
                                });
                            } else {
                                resolve();
                            }
                        });
                    });
                }).then(() => {
                    const projectConfigContent = `//该文件是使用指令生成的文件,无需改动,你可以使用指令 npm run scan:config 重置该文件
          export default {
            projectName: "${projectName}",
            moduleNames: [${moduleNames.map(mn => `"${mn}"`)}]
          };
        `;
                    const projectConfigFilePath = `./${MultiPageHelper.entriesBasePath}/${projectName}/${configDirectoryName}/${projectConfigFileName}.js`;
                    return new Promise((resolve, reject) => {
                        Fs.writeFile(projectConfigFilePath, projectConfigContent, 'utf8', err => {
                            if (err) {
                                console.log(err);
                                return false;
                            }
                            console.log(`项目:[${projectName}]成功生成配置文件至[${projectConfigFilePath}]`);
                            resolve();
                        });
                    });
                });
            });
        }, Promise.resolve([]));

    }).then(() => {
        console.log(`配置扫描结束`);
        console.log(lineBreak);
    });
})();
