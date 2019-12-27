import {BASE_URL} from '../../../urls/index.js';

const ApiGarRoleResourceUrls = {
  get: {
    listRoleResourceByRoleId: `${BASE_URL}/api-gar-role-resource/get/list/roleResourceByRoleId/{roleId}`,
    listRoleResourceByResourceId: `${BASE_URL}/api-gar-role-resource/get/list/roleResourceByResourceId/{resourceId}`
  },
  post: {
    roleResourceByRoleIdAndResourceId: `${BASE_URL}/api-gar-role-resource/post/roleResourceByRoleIdAndResourceId/{roleId}/{resourceId}`,
    listRoleResourceByRoleIdsAndResourceIds: `${BASE_URL}/api-gar-role-resource/post/list/roleResourceByRoleIdsAndResourceIds/{roleIds}/{resourceIds}`
  },
  delete: {
    roleResourceByRoleResourceId: `${BASE_URL}/api-gar-role-resource/delete/roleResourceByRoleResourceId/{roleResourceId}`,
    listRoleResourceByRoleResourceIds: `${BASE_URL}/api-gar-role-resource/delete/list/roleResourceByRoleResourceIds/{roleResourceIds}`,
    roleResourceByRoleIdAndResourceId: `${BASE_URL}/api-gar-role-resource/delete/roleResourceByRoleIdAndResourceId/{roleId}/{resourceId}`,
    listRoleResourceByRoleIdsAndResourceIds: `${BASE_URL}/api-gar-role-resource/delete/list/roleResourceByRoleIdsAndResourceIds/{roleIds}/{resourceIds}`
  }
};

export default ApiGarRoleResourceUrls;
