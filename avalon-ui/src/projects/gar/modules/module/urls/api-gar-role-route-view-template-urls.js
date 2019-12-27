import {BASE_URL} from '../../../urls/index.js';

const ApiGarRoleRouteViewTemplateUrls = {
  get: {
    roleByRouteViewId: `${BASE_URL}/api-gar-role-route-view-template/get/list/roleByRouteViewId/{routeViewId}`
  },
  post: {
    roleRouteViewTemplateByRoleIdAndRouteViewIdAndTemplateId: `${BASE_URL}/api-gar-role-route-view-template/post/roleRouteViewTemplateByRoleIdAndRouteViewIdAndTemplateId/{roleId}/{routeViewId}/{templateId}`,
    roleRouteViewTemplateByRoleIdsAndRouteViewIdAndTemplateId: `${BASE_URL}/api-gar-role-route-view-template/post/roleRouteViewTemplateByRoleIdsAndRouteViewIdAndTemplateId/{roleIds}/{routeViewId}/{templateId}`
  },
  delete: {
    roleRouteViewTemplateByRoleRouteViewTemplateId: `${BASE_URL}/api-gar-role-route-view-template/delete/roleRouteViewTemplateByRoleRouteViewTemplateId/{roleRouteViewTemplateId}`,
    listRoleRouteViewTemplateByRoleRouteViewTemplateIds: `${BASE_URL}/api-gar-role-route-view-template/delete/list/roleRouteViewTemplateByRoleRouteViewTemplateIds/{roleRouteViewTemplateIds}`,
    roleRouteViewTemplateByRoleIdAndRouteViewIdAndTemplateId: `${BASE_URL}/api-gar-role-route-view-template/delete/roleRouteViewTemplateByRoleIdAndRouteViewIdAndTemplateId/{roleId}/{routeViewId}/{templateId}`
  }
};

export default ApiGarRoleRouteViewTemplateUrls;
