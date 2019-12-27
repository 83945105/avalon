import {BASE_URL} from '../../../urls/index.js';

const ApiGarMenuGroupUrls = {
  get: {
    menuGroupByMenuGroupId: `${BASE_URL}/api-gar-menu-group/get/menuGroupByMenuGroupId/{menuGroupId}`,
    listMenuGroup: `${BASE_URL}/api-gar-menu-group/get/list/menuGroup`,
    pageListMenuGroup: `${BASE_URL}/api-gar-menu-group/get/pageList/menuGroup`,
    listMenuGroupBySubModuleId: `${BASE_URL}/api-gar-menu-group/get/list/menuGroupBySubModuleId/{subModuleId}`
  },
  post: {
    menuGroup: `${BASE_URL}/api-gar-menu-group/post/menuGroup`
  },
  put: {
    menuGroupByMenuGroupId: `${BASE_URL}/api-gar-menu-group/put/menuGroupByMenuGroupId/{menuGroupId}`,
    menuGroupUseByMenuGroupId: `${BASE_URL}/api-gar-menu-group/put/menuGroupUseByMenuGroupId/{menuGroupId}/{menuGroupUse}`,
    switchMenuGroupIndexByMenuGroupId: `${BASE_URL}/api-gar-menu-group/put/switchMenuGroupIndexByMenuGroupId/{sourceMenuGroupId}/{targetMenuGroupId}`
  },
  delete: {
    menuGroupByMenuGroupId: `${BASE_URL}/api-gar-menu-group/delete/menuGroupByMenuGroupId/{menuGroupId}`,
    listMenuGroupByMenuGroupIds: `${BASE_URL}/api-gar-menu-group/delete/list/menuGroupByMenuGroupIds/{menuGroupIds}`
  }
};

export default ApiGarMenuGroupUrls;
