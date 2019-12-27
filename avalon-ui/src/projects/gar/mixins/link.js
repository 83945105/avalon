/*!
 * Title: 跳转
 * Description:
 * author: 白超
 * date: 2018/1/26
 * version: v1.0
 */
export default {

    methods: {
        /*登录模块开始*/
        routerToLoginIndex() {
            this.$router.push({name: 'Index'});
        },
        routerToLoginOptionsLoading() {
            this.$router.push({name: 'OptionsLoading'});
        },
        /*登录模块结束*/


        /**
         * 跳转到设备详情页面（app）
         */
        routerToMessage() {
            this.$router.push({name: 'Message'});
        },

        /**
         * 跳转到路由树页面
         * @param moduleId
         */
        routerToRouteTree({moduleId}) {
            this.$router.push({name: 'RouteTree', params: {moduleId: moduleId}});
        },
        /**
         * 跳转到路由树角色模板页面
         * @param moduleId
         * @param routeViewId
         */
        routerToRouteTreeRoleTemplate({moduleId, routeViewId}) {
            this.$router.push({name: 'RouteTreeRoleTemplate', params: {moduleId: moduleId, routeViewId: routeViewId}});
        },
        /**
         * 跳转到路由树模板页面
         * @param moduleId
         * @param routeViewId
         */
        routerToRouteTreeTemplate({moduleId, routeViewId}) {
            this.$router.push({name: 'RouteTreeTemplate', params: {moduleId: moduleId, routeViewId: routeViewId}});
        },

        /**
         * 跳转到菜单树页面
         * @param moduleId
         */
        routerToMenuTree({moduleId}) {
            this.$router.push({name: 'MenuTree', params: {moduleId: moduleId}});
        },
        /**
         * 跳转到菜单路由角色页面
         * @param moduleId
         * @param subModuleId
         * @param menuId
         */
        routerToMenuRouteRole({moduleId, subModuleId, menuId}) {
            this.$router.push({
                name: 'MenuRouteRole',
                params: {moduleId: moduleId, subModuleId: subModuleId, menuId: menuId}
            });
        },

        openResourceManage(moduleId) {
            window.open(`/gar/view/module#/resource-manage/${moduleId}`);
        },

        pageToModule(pageName) {
            switch (pageName) {
                case 'Login':
                    window.location.href = `/gar/view/login`;
                    break;
                case 'Index':
                    window.location.href = `/gar/view/index`;
                    break;
            }
        }
    }
};
