//该文件是使用指令生成的文件,无需改动,你可以使用指令 npm run scan:component 重置该文件
          export function importComponent(componentName) {
            switch (componentName) {
              
                  case "templateIndex":
                    return () => import("../modules/template/Index.vue");
                  case "templateApp":
                    return () => import("../modules/template/views/app/App.vue");
                  case "templateH5CallCameraAndAlbum":
                    return () => import("../modules/template/views/app/H5CallCameraAndAlbum.vue");
                  case "templateFrame":
                    return () => import("../modules/template/views/Frame.vue");
                  case "templateHome":
                    return () => import("../modules/template/views/home/Home.vue");
                  case "templateLayout":
                    return () => import("../modules/template/views/Layout.vue");
                  case "templateElementUITable":
                    return () => import("../modules/template/views/table/ElementUITable.vue");
                  case "templateElementUITableCellEdit":
                    return () => import("../modules/template/views/table/ElementUITableCellEdit.vue");
                  case "templateTable":
                    return () => import("../modules/template/views/table/Table.vue");
                  case "templateTabMode":
                    return () => import("../modules/template/views/TabMode.vue");
                  case "templateElementUITree":
                    return () => import("../modules/template/views/tree/ElementUITree.vue");
                  case "templateElementUITreeAsync":
                    return () => import("../modules/template/views/tree/ElementUITreeAsync.vue");
                  case "templateTree":
                    return () => import("../modules/template/views/tree/Tree.vue");
                  default:
            }
          }
        