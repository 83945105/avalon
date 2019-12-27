import {BASE_URL} from '../../../urls/index.js';

const ApiGarMenuUrls = {
  get: {
    validateValueCanUseBySubModuleId: `${BASE_URL}/api-gar-menu/get/validateValueCanUseBySubModuleId/{value}/{subModuleId}`,
    menuByMenuId: `${BASE_URL}/api-gar-menu/get/menuByMenuId/{menuId}`,
    listMenu: `${BASE_URL}/api-gar-menu/get/list/menu`,
    pageListMenu: `${BASE_URL}/api-gar-menu/get/pageList/menu`,
    listMenuBySubModuleId: `${BASE_URL}/api-gar-menu/get/list/menuBySubModuleId/{subModuleId}`,
    listRootMenuBySubModuleIdAndMenuGroupId: `${BASE_URL}/api-gar-menu/get/list/rootMenuBySubModuleIdAndMenuGroupId/{subModuleId}/{menuGroupId}`,
    listMenuByParentId: `${BASE_URL}/api-gar-menu/get/list/menuByParentId/{parentMenuId}`,
    menuTreeBySubModuleValueAndMenuGroupType: `${BASE_URL}/api-gar-menu/get/menuTreeBySubModuleValueAndMenuGroupType/{subModuleValue}/{menuGroupType}`
  },
  post: {
    menu: `${BASE_URL}/api-gar-menu/post/menu`
  },
  put: {
    menuByMenuId: `${BASE_URL}/api-gar-menu/put/menuByMenuId/{menuId}`,
    switchMenuIndexByMenuId: `${BASE_URL}/api-gar-menu/put/switchMenuIndexByMenuId/{sourceMenuId}/{targetMenuId}`,
    dragMenuTreeNode: `${BASE_URL}/api-gar-menu/put/dragMenuTreeNode/{dragMenuId}/{dropMenuId}/{dropType}`
  },
  delete: {
    menuByMenuId: `${BASE_URL}/api-gar-menu/delete/menuByMenuId/{menuId}`,
    listMenuByMenuIds: `${BASE_URL}/api-gar-menu/delete/list/menuByMenuIds/{menuIds}`
  }
};

export default ApiGarMenuUrls;
