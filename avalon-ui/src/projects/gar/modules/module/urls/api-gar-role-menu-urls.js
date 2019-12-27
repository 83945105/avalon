import {BASE_URL} from '../../../urls/index.js';

const ApiGarRoleMenuUrls = {
  get: {
    listRoleMenu: `${BASE_URL}/api-gar-role-menu/get/list/roleMenu`,
    listRoleMenuByRoleId: `${BASE_URL}/api-gar-role-menu/get/list/roleMenuByRoleId/{roleId}`,
    listRoleMenuByMenuId: `${BASE_URL}/api-gar-role-menu/get/list/roleMenuByMenuId/{menuId}`,
  },
  post: {
    roleMenuByRoleIdAndMenuId: `${BASE_URL}/api-gar-role-menu/post/roleMenuByRoleIdAndMenuId/{roleId}/{menuId}`
  },
  delete: {
    roleMenuByRoleMenuId: `${BASE_URL}/api-gar-role-menu/delete/roleMenuByRoleMenuId/{roleMenuId}`,
    listRoleMenuByRoleMenuIds: `${BASE_URL}/api-gar-role-menu/delete/list/roleMenuByRoleMenuIds/{roleMenuIds}`,
    roleMenuByRoleIdAndMenuId: `${BASE_URL}/api-gar-role-menu/delete/roleMenuByRoleIdAndMenuId/{roleId}/{menuId}`,
    listRoleMenuByRoleIdsAndMenuIds: `${BASE_URL}/api-gar-role-menu/delete/list/roleMenuByRoleIdsAndMenuIds/{roleIds}/{menuIds}`
  }
};

export default ApiGarRoleMenuUrls;
