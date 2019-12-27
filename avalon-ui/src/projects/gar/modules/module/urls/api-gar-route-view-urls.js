import {BASE_URL} from '../../../urls/index.js';

const ApiGarRouteViewUrls = {
  get: {
    validateValueCanUseByRouteId: `${BASE_URL}/api-gar-route-view/get/validateValueCanUseByRouteId/{value}/{routeId}`,
    routeViewByRouteViewId: `${BASE_URL}/api-gar-route-view/get/routeViewByRouteViewId/{routeViewId}`,
    listRouteView: `${BASE_URL}/api-gar-route-view/get/list/routeView`,
    pageListRouteView: `${BASE_URL}/api-gar-route-view/get/pageList/routeView`
  },
  post: {
    routeView: `${BASE_URL}/api-gar-route-view/post/routeView`
  },
  put: {
    routeViewByRouteViewId: `${BASE_URL}/api-gar-route-view/put/routeViewByRouteViewId/{routeViewId}`,
    switchRouteViewIndexByRouteViewId: `${BASE_URL}/api-gar-route-view/put/switchRouteViewIndexByRouteViewId/{sourceRouteViewId}/{targetRouteViewId}`
  },
  delete: {
    routeViewByRouteViewId: `${BASE_URL}/api-gar-route-view/delete/routeViewByRouteViewId/{routeViewId}`,
    listRouteViewByRouteViewIds: `${BASE_URL}/api-gar-route-view/delete/list/routeViewByRouteViewIds/{routeViewIds}`
  }
};

export default ApiGarRouteViewUrls;
