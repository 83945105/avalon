webpackJsonp([45,38,40,42],{"+6Em":function(e,t){e.exports=[]},"1ZU6":function(e,t){},Fvzy:function(e,t){e.exports=[]},IzW0:function(module,__webpack_exports__,__webpack_require__){"use strict";var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_extends__=__webpack_require__("4YfN"),__WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_extends___default=__webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_extends__),__WEBPACK_IMPORTED_MODULE_1_vue__=__webpack_require__("+VlJ"),__WEBPACK_IMPORTED_MODULE_2_element_ui__=__webpack_require__("DVuL"),__WEBPACK_IMPORTED_MODULE_2_element_ui___default=__webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_element_ui__),__WEBPACK_IMPORTED_MODULE_3_element_ui_lib_theme_chalk_index_css__=__webpack_require__("LicG"),__WEBPACK_IMPORTED_MODULE_3_element_ui_lib_theme_chalk_index_css___default=__webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_element_ui_lib_theme_chalk_index_css__),__WEBPACK_IMPORTED_MODULE_4_weview__=__webpack_require__("iaNP"),__WEBPACK_IMPORTED_MODULE_4_weview___default=__webpack_require__.n(__WEBPACK_IMPORTED_MODULE_4_weview__),__WEBPACK_IMPORTED_MODULE_5_weview_lib_theme_chalk_index_css__=__webpack_require__("+8ML"),__WEBPACK_IMPORTED_MODULE_5_weview_lib_theme_chalk_index_css___default=__webpack_require__.n(__WEBPACK_IMPORTED_MODULE_5_weview_lib_theme_chalk_index_css__),__WEBPACK_IMPORTED_MODULE_6_wetools__=__webpack_require__("hYam"),__WEBPACK_IMPORTED_MODULE_6_wetools___default=__webpack_require__.n(__WEBPACK_IMPORTED_MODULE_6_wetools__),__WEBPACK_IMPORTED_MODULE_7__static_gar_css_default_default_css__=__webpack_require__("zxPc"),__WEBPACK_IMPORTED_MODULE_7__static_gar_css_default_default_css___default=__webpack_require__.n(__WEBPACK_IMPORTED_MODULE_7__static_gar_css_default_default_css__),__WEBPACK_IMPORTED_MODULE_8__static_gar_font_iconfont_css__=__webpack_require__("QQqv"),__WEBPACK_IMPORTED_MODULE_8__static_gar_font_iconfont_css___default=__webpack_require__.n(__WEBPACK_IMPORTED_MODULE_8__static_gar_font_iconfont_css__),__WEBPACK_IMPORTED_MODULE_9__mixins_gar_js__=__webpack_require__("42hA"),__WEBPACK_IMPORTED_MODULE_10_vuex__=__webpack_require__("HVJf"),__WEBPACK_IMPORTED_MODULE_11__vuex_modules_frame_store_js__=__webpack_require__("+nXc"),EventEmitter=__webpack_require__("HBrH");window.events=new EventEmitter,window.events.setMaxListeners(100),__WEBPACK_IMPORTED_MODULE_1_vue__.default.use(__WEBPACK_IMPORTED_MODULE_2_element_ui___default.a),__WEBPACK_IMPORTED_MODULE_1_vue__.default.use(__WEBPACK_IMPORTED_MODULE_4_weview___default.a);var GlobalMessage=new __WEBPACK_IMPORTED_MODULE_6_wetools__.Message.WeView;__WEBPACK_IMPORTED_MODULE_1_vue__.default.use(__WEBPACK_IMPORTED_MODULE_6_wetools___default.a,{Ajax:{options:{baseURL:__webpack_require__("vtw5").getBaseUrl(),withCredentials:!0,dataParserOptions:{use:__WEBPACK_IMPORTED_MODULE_6_wetools__.DataParser.DataView,options:{needLoginOptions:{callback:function(e,t){window.events.emit("show-login-window",{data:e,res:t}),GlobalMessage.open(e.resultInfo.message)}},noAuthorityOptions:{callback:function(e,t){GlobalMessage.open(e.resultInfo.message)}},notFoundOptions:{callback:function(e,t){GlobalMessage.open(e.resultInfo.message)}}}}}}}),__webpack_exports__.a={name:"Frame",mixins:[__WEBPACK_IMPORTED_MODULE_9__mixins_gar_js__.a],computed:__WEBPACK_IMPORTED_MODULE_0_babel_runtime_helpers_extends___default()({},Object(__WEBPACK_IMPORTED_MODULE_10_vuex__.c)(["frameModule"])),methods:{loginSuccess:function loginSuccess(_ref){var username=_ref.username,roles=_ref.roles;if(this.$store.dispatch("setLogin",{username:username}),roles&&0!==roles.length){if(1===roles.length)this.$store.dispatch("setPrimaryRoleAndRoles",{primaryRole:new __WEBPACK_IMPORTED_MODULE_11__vuex_modules_frame_store_js__.b(roles[0]),roles:roles.map(function(e){return new __WEBPACK_IMPORTED_MODULE_11__vuex_modules_frame_store_js__.b(e)})});else if(roles.length>1){var primaryRole=localStorage.getItem("vue-gar-primaryRole");this.$store.dispatch("setPrimaryRoleAndRoles",{primaryRole:primaryRole?new __WEBPACK_IMPORTED_MODULE_11__vuex_modules_frame_store_js__.b(eval("("+primaryRole+")")):void 0,roles:roles.map(function(e){return new __WEBPACK_IMPORTED_MODULE_11__vuex_modules_frame_store_js__.b(e)})})}}else this.$store.dispatch("setPrimaryRoleAndRoles",{primaryRole:void 0,roles:[]})}},created:function(){var e=this;this.getOnline({successCallback:function(t){return e.loginSuccess({username:t.records.user.username,roles:t.records.roles})}}),window.events.on("login-success",function(){e.getOnline({successCallback:function(t){return e.loginSuccess({username:t.records.user.username,roles:t.records.roles})}}),e.getRouteTreeBySubModuleValue({moduleId:e.frameModule.moduleId,subModuleValue:e.frameModule.subModuleValue,successCallback:function(t){e.$store.dispatch("setRouteRows",{rows:t.rows})}}),e.getMenuTreeBySubModuleValueMenuGroupType({moduleId:e.frameModule.moduleId,subModuleValue:e.frameModule.subModuleValue,menuGroupType:"HEADER_MENU",successCallback:function(t){e.$store.dispatch("setHeaderMenuRows",{rows:t.rows})}}),e.getMenuTreeBySubModuleValueMenuGroupType({moduleId:e.frameModule.moduleId,subModuleValue:e.frameModule.subModuleValue,menuGroupType:"LEFT_MENU",successCallback:function(t){e.$store.dispatch("setLeftMenuRows",{rows:t.rows})}})})}}},MH3W:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=a("3cXf"),n=a.n(o),_=a("4YfN"),r=a.n(_),s=a("42hA"),i=a("pnBm"),l=[{element:".first-element-introduction",popover:{title:"项目名称",description:"这是我们项目的名称",position:"bottom"}},{element:".second-element-introduction",popover:{title:"左侧菜单",description:"鼠标悬浮到这里可以打开菜单",position:"right"}},{element:".third-element-introduction",popover:{title:"头部菜单",description:"你可以在这里使用一些特殊功能",position:"bottom"}},{element:".last-element-introduction",popover:{title:"再次查看",description:"点击这里可以再次查看本教程",position:"left"}}],u=a("Fvzy"),c=a.n(u),d=a("+6Em"),m=a.n(d),f=a("HVJf"),h=a("NDkD"),p=a("4X+h"),M=a("crN1"),v=a("74wm"),b=a("M1g9"),w=a("C6mD"),E={name:"layout",components:{LoginWindow:b.a,SwitchPrimaryRoleWindow:v.a,HeaderMenu:M.a,LeftMenu:p.a,TabMode:h.a},mixins:[s.a,i.a],data:function(){return{collapseLeftMenu:!0,tabName:"",tabData:[],menuWidth:200,showLogin:!1}},computed:r()({},Object(f.c)(["frameModule","layoutModule"]),Object(f.b)({serverHeaderMenuData:"getHeaderMenuData",serverLeftMenuData:"getLeftMenuData"}),{headerMenuData:function(){return c.a.concat(this.serverHeaderMenuData)},leftMenuData:function(){return m.a.concat(this.serverLeftMenuData)}}),watch:{headerMenuData:function(e){this.tabData=this.caseMenuDataToTabData(e.concat(this.leftMenuData)),this.tabName=this.$route.name},leftMenuData:function(e){this.tabData=this.caseMenuDataToTabData(this.headerMenuData.concat(e)),this.tabName=this.$route.name}},methods:{caseMenuDataToTabData:function(e){if(!e||0===e.length)return[];var t=[];for(var a in e)Object(w.b)(e[a].children)&&e[a].children.length>0?t=t.concat(this.caseMenuDataToTabData(e[a].children)):t.push(e[a]);return t},handleSelectedPrimaryRole:function(e){var t=e.primaryRole,a=(e.index,e.roles);localStorage.setItem("vue-gar-primaryRole",n()(t)),this.$store.dispatch("setPrimaryRoleAndRoles",{primaryRole:t,roles:a})},handleClickHeaderItem:function(e){this.pageToRouter(e.pageName)},handleSelectedMenuItem:function(e){var t=e.row;e.$index;if(!t.routeValues.includes(this.$route.name)){var a=this.$refs.TabMode;a&&a.addTabData(t),t.clickToRouteValue&&(this.tabName=t.clickToRouteValue,this.$router.push({name:t.clickToRouteValue}))}},windowResizeListener:function(e){this.$store.dispatch("initWindowWidthAsync",e),this.$store.dispatch("initWindowHeightAsync",e)},handleClickLogin:function(e){window.events.emit("show-login-window")},handleClickLogout:function(e){var t=this;this.getLogout({successCallback:function(){t.$store.dispatch("setLogout"),window.events.emit("show-login-window")}})}},created:function(){},mounted:function(){!1===this.layoutModule.isDriverRead&&this.$store.dispatch("startDriver",l)},beforeDestroy:function(){window.removeEventListener("resize",this.windowResizeListener)}},D={render:function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",[o("div",{staticClass:"black-layout-header"},[o("div",{staticClass:"black-layout-menu-btn",on:{click:function(t){t.stopPropagation(),e.collapseLeftMenu=!e.collapseLeftMenu}}},[o("we-icon",{attrs:{name:"menu"}})],1),e._v(" "),e._m(0),e._v(" "),o("div",{staticClass:"black-layout-header-system"},[o("div",{staticClass:"black-layout-header-system-nav third-element-introduction"},[o("header-menu",{attrs:{value:e.$route.name,data:e.headerMenuData,width:400,"text-color":"#ffffff","background-color":"#333333","active-text-color":"#2199ed","active-background-color":"#151515","selected-text-color":"#ffffff","selected-background-color":"#151515","hover-text-color":"#ffffff","hover-background-color":"#151515"},on:{"selected-menu-item":e.handleSelectedMenuItem}})],1),e._v(" "),e.frameModule.isLogin?o("div",{staticClass:"black-layout-header-system-user"},[o("we-menu",{attrs:{value:!0,mode:"horizontal","sub-menu-trigger":"hover","text-color":"#ffffff","background-color":"transparent","active-text-color":"#2199ed","active-background-color":"#151515","selected-text-color":"#ffffff","selected-background-color":"#151515","hover-text-color":"#ffffff","hover-background-color":"transparent",width:220}},[o("we-menu-item",{attrs:{icon:!1,"icon-name":"edit"}},[o("template",{slot:"content"},[o("img",{staticClass:"user-face common-radius-all",attrs:{src:a("Mvm9")}}),e._v(" "),o("div",{staticClass:"user-name"},[e._v(e._s(e.frameModule.username))]),e._v(" "),o("div",{staticClass:"common-clear"})]),e._v(" "),o("we-menu",{attrs:{slot:"subMenu","sub-menu-horizontal-offset":-60,width:220,subMenuMode:"open",accordion:!1,placement:"bottom-start"},slot:"subMenu"},[o("we-menu-item",[o("template",{slot:"panel"},[o("div",{staticClass:"black-layout-header-system-user-menu"},[o("div",{staticClass:"user-menu-content"},[o("img",{staticClass:"user-menu-content-face common-radius-all",attrs:{src:a("Mvm9")}}),e._v(" "),o("div",{staticClass:"user-menu-content-info"},[o("h3",{staticClass:"user-info-name"},[e._v(e._s(e.frameModule.username)+"（"+e._s(e.frameModule.primaryRole&&e.frameModule.primaryRole.name)+"）")]),e._v(" "),o("div",{staticClass:"user-info-shop"})]),e._v(" "),o("div",{staticClass:"common-clear"})]),e._v(" "),o("ul",{staticClass:"user-menu-list"},[o("li",[e._v("基本资料")]),e._v(" "),o("li",[e._v("修改密码")]),e._v(" "),o("li",[e._v("切换账号")]),e._v(" "),e.frameModule.roles.length>1?o("li",{on:{click:function(t){e.frameModule.showSwitchPrimaryRole=!0}}},[e._v("切换角色\n                      ")]):e._e(),e._v(" "),o("li",[e._v("系统设置")])]),e._v(" "),o("div",{staticClass:"user-menu-sign-out",on:{click:e.handleClickLogout}},[e._v("安全退出平台")])])])],2)],1)],2)],1)],1):o("div",{staticClass:"black-layout-header-login-button",on:{click:function(t){e.showLogin=!0}}},[e._v("点我登录")]),e._v(" "),o("div",{staticClass:"common-clra"})])]),e._v(" "),o("div",{staticClass:"black-layout-left",class:{"is-overflow":!e.collapseLeftMenu}},[o("left-menu",{attrs:{value:e.$route.name,data:e.leftMenuData,collapse:e.collapseLeftMenu,width:200,"text-color":"#cccccc","background-color":"#252525","active-text-color":"#ffffff","active-background-color":"#151515","selected-text-color":"#ffffff","selected-background-color":"#2199ed","hover-text-color":"#2199ed","hover-background-color":"#151515"},on:{"selected-menu-item":e.handleSelectedMenuItem}})],1),e._v(" "),o("div",{staticClass:"black-layout-right yl-layout-right",style:{left:e.collapseLeftMenu?"50px":e.menuWidth+"px"}},["tab"===e.layoutModule.pageMode?o("tab-mode",{ref:"TabMode",attrs:{data:e.tabData},on:{"tab-click":function(t){t.$index;var a=t.name,o=t.data;return e.$router.push({name:o.clickToRouteValue||a})}}}):o("router-view")],1),e._v(" "),o("login-window",{model:{value:e.showLogin,callback:function(t){e.showLogin=t},expression:"showLogin"}}),e._v(" "),o("switch-primary-role-window",{attrs:{roles:e.frameModule.roles,"primary-role":e.frameModule.primaryRole},on:{"selected-role":e.handleSelectedPrimaryRole},model:{value:e.frameModule.showSwitchPrimaryRole,callback:function(t){e.$set(e.frameModule,"showSwitchPrimaryRole",t)},expression:"frameModule.showSwitchPrimaryRole"}})],1)},staticRenderFns:[function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"black-layout-header-logo"},[t("img",{staticClass:"system-logo",attrs:{src:a("LCIz")}}),this._v(" "),t("h3",{staticClass:"system-title first-element-introduction"},[this._v("恒大电商平台")])])}]},g=a("C7Lr")(E,D,!1,null,null,null);t.default=g.exports},P6M4:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=a("IHPB"),n=a.n(o),_=a("3cXf"),r=a.n(_),s=a("4YfN"),i=a.n(s),l=a("+VlJ"),u=a("KGCO"),c=a("HVJf"),d=a("+nXc"),m=a("g83z"),f=a("XWWi"),h=(a("f8Q/"),{state:{pageMode:"tab",keepAliveCacheForTabEnabled:!0,closeTabAndRemoveKeepAliveCacheEnabled:!0,tabKeepAliveCache:[],driver:new f({animate:!0,opacity:.75,padding:10,allowClose:!1,overlayClickNext:!0,doneBtnText:"完成",closeBtnText:"关闭",stageBackground:"rgba(255, 255, 255, 0.4)",nextBtnText:"下一个",prevBtnText:"上一个",showButtons:!0,keyboardControl:!0,scrollIntoViewOptions:{},onHighlightStarted:function(e){},onHighlighted:function(e){},onDeselected:function(e){},onReset:function(e){},onNext:function(e){},onPrevious:function(e){}}),isDriverRead:!0,windowWidth:window.innerWidth||document.documentElement.clientWidth||document.body.clientWidth,windowHeight:window.innerHeight||document.documentElement.clientHeight||document.body.clientHeight},actions:{addTabKeepAliveCache:function(e,t){-1===e.state.tabKeepAliveCache.indexOf(t)&&e.state.tabKeepAliveCache.push(t)},removeTabKeepAliveCache:function(e,t){if(-1!==e.state.tabKeepAliveCache.indexOf(t))for(var a=0;a<e.state.tabKeepAliveCache.length;a++)if(t===e.state.tabKeepAliveCache[a])return void e.state.tabKeepAliveCache.splice(a,1)},startDriver:function(e,t){t||alert("未设置Driver步骤"),e.state.driver.defineSteps(t),e.state.driver.start()},initWindowWidthAsync:function(e,t){e.state.windowWidth=window.innerWidth||document.documentElement.clientWidth||document.body.clientWidth},initWindowHeightAsync:function(e){e.state.windowHeight=window.innerHeight||document.documentElement.clientHeight||document.body.clientHeight}}});
/*!
 * Title: 状态管理
 * Description:
 * author: 白超
 * date: 2017/12/20
 * version: v1.0
 */
l.default.use(c.a);var p=new c.a.Store({modules:{frameModule:d.a,userModule:m.a,layoutModule:h}}),M=a("f/Ut"),v=a("yY85"),b=a("MH3W"),w=a("E4pr");
/*!
 * Title:
 * Description: 路由管理
 * author: 白超
 * date: 2017/12/5
 * version: v1.0
 */
l.default.use(u.a);var E=[{path:"/403",name:"403",component:function(){return a.e(1).then(a.bind(null,"Q0jC"))}},{path:"/404",name:"404",component:function(){return a.e(2).then(a.bind(null,"xRUP"))}},{path:"/500",name:"500",component:function(){return a.e(3).then(a.bind(null,"D7kp"))}},{path:"/noData",name:"NoData",component:function(){return a.e(0).then(a.bind(null,"usaL"))}}],D=function e(t){if(!t)return[];var a=[];return t.forEach(function(t){var o={path:t.path,name:t.name,props:t.props,components:{}};if(t.components)for(var n in t.components)o.components[n]=Object(w.a)(""+t.components[n].subModuleValue+t.components[n].value);t.children&&(o.children=e(t.children)),a.push(o)}),a},g=localStorage.getItem("vue-router-"+Object(d.c)()+"-"+Object(d.d)()+"-dynamic-route-data"),O=new u.a({routes:[{path:"/",meta:{type:"base"},redirect:{name:"Frame"}},{path:"/",name:"Frame",component:v.default,meta:{type:"base"},redirect:{name:"Layout"},children:[{path:"/",name:"Layout",component:b.default,meta:{type:"base"},redirect:{name:"Home"},children:[].concat(E,n()(D(g?JSON.parse(g):[])))}]}]}),P=a("C6mD");
/*!
 * Title: 系统模块
 * Description:
 * author: 白超
 * date: 2017/12/5
 * version: v1.0
 */
l.default.use(u.a),O.beforeEach(function(e,t,a){0===e.matched.length?a({name:"404"}):a()}),new l.default({router:O,store:p,render:function(e){return e(M.default)},computed:i()({},Object(c.c)(["frameModule"]),Object(c.b)({routeData:"getRouteData"})),watch:{routeData:function(e){var t="vue-router-"+this.frameModule.moduleId+"-"+this.frameModule.subModuleValue+"-dynamic-route-data";Object(P.b)(e)&&(localStorage.getItem(t)?localStorage.setItem(t,r()(e)):(localStorage.setItem(t,r()(e)),this.$router.addRoutes([{path:"/",meta:{type:"base"},redirect:{name:"Frame"}},{path:"/",name:"Frame2",component:v.default,meta:{type:"base"},redirect:{name:"Layout"},children:[{path:"/",name:"Layout2",component:b.default,meta:{type:"base"},redirect:{name:"Home"},children:[].concat(n()(D(e)))}]}])))}}}).$mount("#index")},"f/Ut":function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{attrs:{id:"index"}},[t("router-view")],1)},staticRenderFns:[]},n=a("C7Lr")({name:"index"},o,!1,null,null,null);t.default=n.exports},yY85:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=a("IzW0"),n={render:function(){var e=this.$createElement;return(this._self._c||e)("router-view")},staticRenderFns:[]};var _=function(e){a("1ZU6")},r=a("C7Lr")(o.a,n,!1,_,null,null);t.default=r.exports}},["P6M4"]);