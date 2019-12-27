//该文件是使用指令生成的文件,无需改动,你可以使用指令 npm run scan:component 重置该文件
          export function importComponent(componentName) {
            switch (componentName) {
              
                  case "templateIndex":
                    return () => import("../modules/template/Index.vue");
                  case "templateAlipay":
                    return () => import("../modules/template/views/alipay/Alipay.vue");
                  case "templateAlipayComputerWebsite":
                    return () => import("../modules/template/views/alipay/AlipayComputerWebsite.vue");
                  case "templateAlipayQrCode":
                    return () => import("../modules/template/views/alipay/AlipayQrCode.vue");
                  case "templateFrame":
                    return () => import("../modules/template/views/Frame.vue");
                  case "templateHome":
                    return () => import("../modules/template/views/home/Home.vue");
                  case "templateLayout":
                    return () => import("../modules/template/views/Layout.vue");
                  case "templateTabMode":
                    return () => import("../modules/template/views/TabMode.vue");
                  case "templateWeChat":
                    return () => import("../modules/template/views/weChat/WeChat.vue");
                  case "templateWeChatQrCode":
                    return () => import("../modules/template/views/weChat/WeChatQrCode.vue");
                  default:
            }
          }
        