const buildConf = require('./build.conf');

/**
 * 服务端地址
 */
const server_path = 'http://localhost:8887/';

const serverPath = function () {
  return buildConf.existsBasePath ? '' : server_path;
}();

module.exports = {
  /**
   * 服务端地址
   */
  serverPath: serverPath
};
