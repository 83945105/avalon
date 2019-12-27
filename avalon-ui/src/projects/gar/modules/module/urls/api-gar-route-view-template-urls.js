import {BASE_URL} from '../../../urls/index.js';

const ApiGarRouteViewTemplateUrls = {
  get: {
    listRouteViewTemplateByRouteViewId: `${BASE_URL}/api-gar-route-view-template/get/list/routeViewTemplateByRouteViewId/{routeViewId}`,
    pageListRouteViewTemplateByRouteViewId: `${BASE_URL}/api-gar-route-view-template/get/pageList/routeViewTemplateByRouteViewId/{routeViewId}`,
    listRouteViewTemplateByTemplateId: `${BASE_URL}/api-gar-route-view-template/get/list/routeViewTemplateByTemplateId/{templateId}`,
    pageListRouteViewTemplateByTemplateId: `${BASE_URL}/api-gar-route-view-template/get/pageList/routeViewTemplateByTemplateId/{templateId}`
  },
  post: {
    routeViewTemplateByRouteViewIdAndTemplateId: `${BASE_URL}/api-gar-route-view-template/post/routeViewTemplateByRouteViewIdAndTemplateId/{routeViewId}/{templateId}`,
    listRouteViewTemplateByRouteViewIdsAndTemplateIds: `${BASE_URL}/api-gar-route-view-template/post/list/routeViewTemplateByRouteViewIdsAndTemplateIds/{routeViewIds}/{templateIds}`
  },
  delete: {
    routeViewTemplateByRouteViewTemplateId: `${BASE_URL}/api-gar-route-view-template/delete/routeViewTemplateByRouteViewTemplateId/{routeViewTemplateId}`,
    listRouteViewTemplateByRouteViewTemplateIds: `${BASE_URL}/api-gar-route-view-template/delete/list/routeViewTemplateByRouteViewTemplateIds/{routeViewTemplateIds}`,
    routeViewTemplateByRouteViewIdAndTemplateId: `${BASE_URL}/api-gar-route-view-template/delete/routeViewTemplateByRouteViewIdAndTemplateId/{routeViewId}/{templateId}`
  }
};

export default ApiGarRouteViewTemplateUrls;
