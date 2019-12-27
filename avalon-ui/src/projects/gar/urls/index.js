/*!
 * Title: 登录
 * Description:
 * author: 白超
 * date: 2018/4/12
 * version: v1.0
 */

import ProjectConfig from '../../../../config/project.js';

// 这是通用权限模块   集成进某个项目要把根路径改为该项目
const MODULE_ID = "gar";

export const BASE_URL = `${ProjectConfig.microService ? '/route-' : '/'}${MODULE_ID}`;

