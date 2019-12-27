import {BASE_URL} from '../../../urls/index.js';

const ApiGarMenuRouteUrls = {
  get: {
    listMenuRoute: `${BASE_URL}/api-gar-menu-route/get/list/menuRoute`,
    listMenuRouteByMenuId: `${BASE_URL}/api-gar-menu-route/get/list/menuRouteByMenuId/{menuId}`,
    listMenuRouteByRouteId: `${BASE_URL}/api-gar-menu-route/get/list/menuRouteByRouteId/{routeId}`
  },
  post: {
    menuRouteByMenuIdAndRouteId: `${BASE_URL}/api-gar-menu-route/post/menuRouteByMenuIdAndRouteId/{menuId}/{routeId}`
  },
  put: {
    menuRouteByMenuRouteId: `${BASE_URL}/api-gar-menu-route/put/menuRouteByMenuRouteId/{menuRouteId}`,
    menuRouteByMenuIdAndRouteId: `${BASE_URL}/api-gar-menu-route/put/menuRouteByMenuIdAndRouteId/{menuId}/{routeId}`
  },
  delete: {
    menuRouteByMenuRouteId: `${BASE_URL}/api-gar-menu-route/delete/menuRouteByMenuRouteId/{menuRouteId}`,
    listMenuRouteByMenuRouteIds: `${BASE_URL}/api-gar-menu-route/delete/list/menuRouteByMenuRouteIds/{menuRouteIds}`,
    menuRouteByMenuIdAndRouteId: `${BASE_URL}/api-gar-menu-route/delete/menuRouteByMenuIdAndRouteId/{menuId}/{routeId}`,
    menuRouteByMenuIdAndRouteIdAndMenuRouteRelation: `${BASE_URL}/api-gar-menu-route/delete/menuRouteByMenuIdAndRouteIdAndMenuRouteRelation/{menuId}/{routeId}/{menuRouteRelation}`,
    listMenuRouteByMenuIdsAndRouteIds: `${BASE_URL}/api-gar-menu-route/delete/list/menuRouteByMenuIdsAndRouteIds/{menuIds}/{routeIds}`
  }
};

export default ApiGarMenuRouteUrls;
