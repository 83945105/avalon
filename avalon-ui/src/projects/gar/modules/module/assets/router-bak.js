/*!
 * Title: 备用路由, 当本项目初始化无任何授权时导入本路由可以进行初始授权
 * Description:
 * author: 白超
 * date: 2018/12/17
 * version: v1.0
 */
import {importComponent} from "../../../router/router.js";

export default [
  {
    path: 'resource-manage/:moduleId',
    name: 'ResourceManage',
    props: true,
    component: importComponent('moduleResourceManageA')
  },
  {
    path: 'role-manage/:moduleId',
    name: 'RoleManage',
    props: true,
    component: importComponent('moduleRoleManageA')
  },
  {
    path: 'object-manage/:moduleId',
    name: 'ObjectManage',
    props: true,
    component: importComponent('moduleObjectManageA')
  },
  {
    path: 'sub-module-manage/:moduleId',
    name: 'SubModuleManage',
    props: true,
    component: importComponent('moduleSubModuleManageA')
  },
  {
    path: 'template-manage/:moduleId',
    name: 'TemplateManage',
    props: true,
    component: importComponent('moduleTemplateManageA')
  },
  {
    path: 'route-manage',
    name: 'RouteManage',
    props: false,
    component: importComponent('moduleRouteManageA'),
    children: [
      {
        path: 'route-tree/:moduleId',
        name: 'RouteTree',
        props: {
          left: true,
          right: true
        },
        components: {
          left: importComponent('moduleRouteTreeA'),
          right: importComponent('moduleRouteManageInitA')
        }
      },
      {
        path: 'route-tree-role-template/:moduleId/:routeViewId',
        name: 'RouteTreeRoleTemplate',
        props: {
          left: true,
          right: true
        },
        components: {
          left: importComponent('moduleRouteTreeA'),
          right: importComponent('moduleRouteRoleTemplateA')
        }
      },
      {
        path: 'route-tree-template/:moduleId/:routeViewId',
        name: 'RouteTreeTemplate',
        props: {
          left: true,
          right: true
        },
        components: {
          left: importComponent('moduleRouteTreeA'),
          right: importComponent('moduleRouteTemplateA')
        }
      }
    ]
  },
  {
    path: 'menu-manage',
    name: 'MenuManage',
    props: false,
    component: importComponent('moduleMenuManageA'),
    children: [
      {
        path: 'menu-tree/:moduleId',
        name: 'MenuTree',
        props: {
          left: true,
          right: true
        },
        components: {
          left: importComponent('moduleMenuTreeA'),
          right: importComponent('moduleMenuManageInitA')
        }
      },
      {
        path: 'menu-route-role/:moduleId/:subModuleId/:menuId',
        name: 'MenuRouteRole',
        props: {
          left: true,
          right: true
        },
        components: {
          left: importComponent('moduleMenuTreeA'),
          right: importComponent('moduleMenuRouteRoleA')
        }
      }
    ]
  }
]
