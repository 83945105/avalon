import {BASE_URL} from '../../../urls/index.js';

const ApiGarRoleUrls = {
  get: {
    validateValueCanUse: `${BASE_URL}/api-gar-role/get/validateValueCanUse/{value}`,
    pageListRole: `${BASE_URL}/api-gar-role/get/pageList/role`,
    listRole: `${BASE_URL}/api-gar-role/get/list/role`
  },
  post: {
    role: `${BASE_URL}/api-gar-role/post/role`
  },
  put: {
    roleByRoleId: `${BASE_URL}/api-gar-role/put/roleByRoleId/{roleId}`
  },
  delete: {
    roleByRoleId: `${BASE_URL}/api-gar-role/delete/roleByRoleId/{roleId}`,
    listRoleByRoleIds: `${BASE_URL}/api-gar-role/delete/list/roleByRoleIds/{roleIds}`
  }
};

export default ApiGarRoleUrls;
