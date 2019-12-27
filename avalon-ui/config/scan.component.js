/*!
 * Title: 组件扫描
 * Description:
 * author: 白超
 * date: 2018/12/3
 * version: v1.0
 */
const Fs = require('fs');
const Glob = require('glob');
const ProjectConfig = require('./project.js');
const MultiPageHelper = require('../config/multiPageHelper.js');

const routerDirectoryName = 'router';
const routerFileName = 'router';
const lineBreak = '--------------------';

(function () {
  const compileProjectNames = MultiPageHelper.compileProjectNameAliases(ProjectConfig).map(compileProjectNameAlias => compileProjectNameAlias.name);
  if (compileProjectNames.length === 0) return;
  console.log(lineBreak);
  console.log(`组件扫描开始`);

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
    console.log(`开始项目组件扫描...`);
    //使用reduce动态串行promise
    return projectNames.reduce((pre, projectName) => {
      return pre.then(() => new Promise((resolve, reject) => {

        console.log(lineBreak);
        console.log(`开始扫描项目:[${projectName}]下文件...`);
        const filePaths = Glob.sync(`./${MultiPageHelper.entriesBasePath}\/${projectName}\/${MultiPageHelper.moduleFolderName}/**`);
        resolve(filePaths);

      }).then(filePaths => {

        console.log(lineBreak);
        console.log(`项目:[${projectName}]共扫描出${filePaths.length}个文件...${filePaths.length > 0 ? '开始分析...' : ''}`);
        //组件信息
        const components = [];
        filePaths.forEach(filePath => {
          if (!filePath.endsWith('.vue')) return true;
          const matches = filePath.match(new RegExp(`./${MultiPageHelper.entriesBasePath}\\/${projectName}\/${MultiPageHelper.moduleFolderName}/(\\S*)\\.vue`));
          if (!matches || !matches[1]) return true;
          const names = matches[1].split("/");
          components.push({
            key: `${names[0]}${names[names.length - 1]}`,
            moduleName: names[0],
            componentName: names[names.length - 1],
            componentPath: filePath,
            componentRelativePath: matches[1]
          });
        });
        return components;

      }).then(components => {
        return new Promise((resolve, reject) => {

          console.log(lineBreak);
          console.log(`项目:[${projectName}]共分析出${components.length}个组件...${components.length > 0 ? '开始校验...' : ''}`);
          //组件合法性校验
          let validate = {};
          components.forEach(component => {
            if (!validate[component.key]) {
              validate[component.key] = [];
            }
            validate[component.key].push(component.componentPath);
          });
          let success = true;
          let error = {};
          for (let name in validate) {
            if (validate[name].length > 1) {
              success = false;
              error[name] = validate[name];
            }
          }
          if (!success) {
            console.log(`项目:[${projectName}]组件校验异常...同一模块下存在同名组件...`);
            console.log(error);
            reject();
            return;
          }
          console.log(`项目:[${projectName}]组件校验成功...`);
          resolve(components);
        });
      }).then(components => {

        console.log(lineBreak);
        console.log(`开始生成项目:[${projectName}]路由配置文件...`);
        const routerDirectoryPath = `./${MultiPageHelper.entriesBasePath}/${projectName}/${routerDirectoryName}`;
        return new Promise((resolve, reject) => {
          Fs.access(routerDirectoryPath, err => {
            if (err) {
              console.log(`项目:[${projectName}]路由配置文件目录[${routerDirectoryPath}]不存在...开始创建`);
              Fs.mkdir(routerDirectoryPath, err => {
                if (err) {
                  console.log(err);
                }
                console.log(`项目:[${projectName}]路由配置文件目录[${routerDirectoryPath}]创建成功`);
                resolve(components);
              });
            } else {
              resolve(components);
            }
          });
        });

      }).then(components => {

        //生成路由配置文件
        const routerContent = `//该文件是使用指令生成的文件,无需改动,你可以使用指令 npm run scan:component 重置该文件
          export function importComponent(componentName) {
            switch (componentName) {
              ${(() => {
          let content = '';
          components.forEach(component => {
            content += `
                  case "${component.moduleName}${component.componentName}":
                    return () => import("../${MultiPageHelper.moduleFolderName}/${component.componentRelativePath}.vue");`;
          });
          return content;
        })()}
                  default:
            }
          }
        `;
        const routerFilePath = `./${MultiPageHelper.entriesBasePath}/${projectName}/${routerDirectoryName}/${routerFileName}.js`;
        return new Promise((resolve, reject) => {
          Fs.writeFile(routerFilePath, routerContent, 'utf8', err => {
            if (err) {
              console.log(err);
              return false;
            }
            console.log(`项目:[${projectName}]成功生成路由配置文件至[${routerFilePath}]`);
            resolve();
          });
        });

      }));
    }, Promise.resolve([]));

  }).then(() => {
    console.log(`组件扫描结束`);
    console.log(lineBreak);
  }).catch(() => {
    console.log(lineBreak);
    throw new Error('组件扫描异常');
  });
})();
