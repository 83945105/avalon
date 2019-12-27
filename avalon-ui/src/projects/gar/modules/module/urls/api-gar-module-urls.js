import {BASE_URL} from '../../../urls/index.js';

const ApiGarModuleUrls = {
  get: {
    moduleByModuleId: `${BASE_URL}/api-gar-module/get/moduleByModuleId/{moduleId}`,
    validateModuleIdCanUse: `${BASE_URL}/api-gar-module/get/validateModuleIdCanUse/{moduleId}`,
    validatePathCanUse: `${BASE_URL}/api-gar-module/get/validatePathCanUse/{path}`,
    validateServiceIdCanUse: `${BASE_URL}/api-gar-module/get/validateServiceIdCanUse/{serviceId}`,
    listModule: `${BASE_URL}/api-gar-module/get/list/module`
  },
  post: {
    module: `${BASE_URL}/api-gar-module/post/module`
  },
  put: {
    moduleByModuleId: `${BASE_URL}/api-gar-module/put/moduleByModuleId/{moduleId}`
  },
  delete: {
    moduleByModuleId: `${BASE_URL}/api-gar-module/delete/moduleByModuleId/{moduleId}`
  }
};

export default ApiGarModuleUrls;
