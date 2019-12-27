import {BASE_URL} from '../../../urls/index.js';

const ApiGarRouteUrls = {
  get: {
    validateValueCanUseBySubModuleId: `${BASE_URL}/api-gar-route/get/validateValueCanUseBySubModuleId/{value}/{subModuleId}`,
    validatePathCanUseBySubModuleId: `${BASE_URL}/api-gar-route/get/validatePathCanUseBySubModuleId/{subModuleId}`,
    validateAliasCanUseBySubModuleId: `${BASE_URL}/api-gar-route/get/validateAliasCanUseBySubModuleId/{alias}/{subModuleId}`,
    routeByRouteId: `${BASE_URL}/api-gar-route/get/routeByRouteId/{routeId}`,
    listRoute: `${BASE_URL}/api-gar-route/get/list/route`,
    pageListRoute: `${BASE_URL}/api-gar-route/get/pageList/route`,
    listRouteBySubModuleId: `${BASE_URL}/api-gar-route/get/list/routeBySubModuleId/{subModuleId}`,
    listRootRouteBySubModuleId: `${BASE_URL}/api-gar-route/get/list/rootRouteBySubModuleId/{subModuleId}`,
    listRouteByParentId: `${BASE_URL}/api-gar-route/get/list/routeByParentId/{parentRouteId}`,
    routeTreeBySubModuleValue: `${BASE_URL}/api-gar-route/get/routeTreeBySubModuleValue/{subModuleValue}`
  },
  post: {
    route: `${BASE_URL}/api-gar-route/post/route`
  },
  put: {
    routeByRouteId: `${BASE_URL}/api-gar-route/put/routeByRouteId/{routeId}`,
    switchRouteIndexByRouteId: `${BASE_URL}/api-gar-route/put/switchRouteIndexByRouteId/{sourceRouteId}/{targetRouteId}`,
    dragRouteTreeNode: `${BASE_URL}/api-gar-route/put/dragRouteTreeNode/{dragRouteId}/{dropRouteId}/{dropType}`
  },
  delete: {
    routeByRouteId: `${BASE_URL}/api-gar-route/delete/routeByRouteId/{routeId}`,
    listRouteByRouteIds: `${BASE_URL}/api-gar-route/delete/list/routeByRouteIds/{routeIds}`
  }
};

export default ApiGarRouteUrls;
