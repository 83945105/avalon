import {BASE_URL} from '../../../urls/index.js';

const ApiGarSubModuleUrls = {
  get: {
    validateValueCanUse: `${BASE_URL}/api-gar-sub-module/get/validateValueCanUse/{value}`,
    subModuleBySubModuleId: `${BASE_URL}/api-gar-sub-module/get/subModuleBySubModuleId/{subModuleId}`,
    listSubModule: `${BASE_URL}/api-gar-sub-module/get/list/subModule`,
    pageListSubModule: `${BASE_URL}/api-gar-sub-module/get/pageList/subModule`,
    listSubModuleAndSubModuleRouteCount: `${BASE_URL}/api-gar-sub-module/get/list/subModuleAndSubModuleRouteCount`,
    listSubModuleAndSubModuleMenuGroupCount: `${BASE_URL}/api-gar-sub-module/get/list/subModuleAndSubModuleMenuGroupCount`
  },
  post: {
    subModule: `${BASE_URL}/api-gar-sub-module/post/subModule`
  },
  put: {
    subModuleBySubModuleId: `${BASE_URL}/api-gar-sub-module/put/subModuleBySubModuleId/{subModuleId}`,
    switchSubModuleIndexBySubModuleId: `${BASE_URL}/api-gar-sub-module/put/switchSubModuleIndexBySubModuleId/{sourceSubModuleId}/{targetSubModuleId}`
  },
  delete: {
    subModuleBySubModuleId: `${BASE_URL}/api-gar-sub-module/delete/subModuleBySubModuleId/{subModuleId}`,
    listSubModuleBySubModuleIds: `${BASE_URL}/api-gar-sub-module/delete/list/subModuleBySubModuleIds/{subModuleIds}`
  }
};

export default ApiGarSubModuleUrls;
