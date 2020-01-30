/*!
 * Title: axios配置
 * Description:
 * author: baichao
 * date: 2017/12/7
 * version: v1.0
 */
const ProjectConfig = require('./project.js');

/**
 * 配置相对请求根路径
 */
const baseUrl = '/api';

/**
 * 配置代理地址
 */
const proxys = {
  '/api': [ProjectConfig.serverPath]
};

/**
 * 获取代理地址配置
 */
module.exports.getProxys = function () {
  let r = {};
  for (let i in proxys) {
    r[i] = {
      target: proxys[i][0],
      changeOrigin: true,
      pathRewrite: function () {
        let pr = {};
        let v = '';
        for (let j = 1; j < proxys[i].length; j++) {
          v += proxys[i][j];
        }
        pr[`^${i}`] = v;
        return pr;
      }()
    }

  }
  return r;
};

/**
 * 获取相对请求根路径
 */
module.exports.getBaseUrl = function () {
  return ProjectConfig.development_environment ? baseUrl : ProjectConfig.serverPath;
};
