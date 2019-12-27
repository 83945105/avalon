import {BASE_URL} from '../../../urls/index.js';

const ApiGarResourceUrls = {
  get: {
    listResource: `${BASE_URL}/api-gar-resource/get/list/resource`,
    pageListResource: `${BASE_URL}/api-gar-resource/get/pageList/resource`,
    listRootResource: `${BASE_URL}/api-gar-resource/get/list/rootResource`,
    pageListRootResource: `${BASE_URL}/api-gar-resource/get/pageList/rootResource`,
    listResourceByParentId: `${BASE_URL}/api-gar-resource/get/list/resourceByParentId/{parentId}`,
    listResourceIdAndNameByResourceIds: `${BASE_URL}/api-gar-resource/get/list/resourceIdAndNameByResourceIds/{resourceIds}`
  },
  post: {
    resource: `${BASE_URL}/api-gar-resource/post/resource`
  },
  put: {
    resourceByResourceId: `${BASE_URL}/api-gar-resource/put/resourceByResourceId/{resourceId}`
  },
  delete: {
    resourceByResourceId: `${BASE_URL}/api-gar-resource/delete/resourceByResourceId/{resourceId}`,
    listResourceByResourceIds: `${BASE_URL}/api-gar-resource/delete/list/resourceByResourceIds/{resourceIds}`
  }
};

export default ApiGarResourceUrls;
