import {mapState} from 'vuex';
import ApiGarMenuUrls from "../modules/module/urls/api-gar-menu-urls.js";
import ApiGarRouteUrls from "../modules/module/urls/api-gar-route-urls.js";

export default {

  computed: {
    ...mapState(['userModule'])
  },

  methods: {
    getMenuTreeBySubModuleValueMenuGroupType({moduleId, subModuleValue, menuGroupType, successCallback}) {
      this.$Ajax.get(ApiGarMenuUrls.get.menuTreeBySubModuleValueAndMenuGroupType, [subModuleValue, menuGroupType], {
        params: {moduleId: moduleId}
      })
        .success(true, successCallback);
    },
    getRouteTreeBySubModuleValue({moduleId, subModuleValue, successCallback}) {
      this.$Ajax.get(ApiGarRouteUrls.get.routeTreeBySubModuleValue, [subModuleValue], {
        params: {moduleId: moduleId}
      })
        .success(true, successCallback);
    }

  }
}
