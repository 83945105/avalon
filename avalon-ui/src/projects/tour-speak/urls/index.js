/*!
 * Title: 登录
 * Description:
 * author: baichao
 * date: 2018/4/12
 * version: v1.0
 */

import ProjectConfig from '../../../../config/project.js';

const MODULE_ID = "tourSpeak";

export const BASE_URL = `${ProjectConfig.microService ? '' : `${MODULE_ID}`}`;

const microUrl = ProjectConfig.microService ? '/route-tourSpeak' : '';
const IndexUrls = {
  get: {
    isOnline: `${BASE_URL}${microUrl}/index/get/isOnline`,
    isAdmin: `${BASE_URL}${microUrl}/index/get/isAdmin`
  }
};

export default IndexUrls;
