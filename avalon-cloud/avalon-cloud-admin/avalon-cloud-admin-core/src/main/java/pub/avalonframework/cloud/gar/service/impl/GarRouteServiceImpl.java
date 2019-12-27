package pub.avalonframework.cloud.gar.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pub.avalon.beans.EnumMethods;
import pub.avalon.beans.Time;
import pub.avalon.holygrail.response.beans.Status;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalon.holygrail.utils.StringUtil;
import pub.avalon.sqlhelper.core.beans.FunctionColumnType;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.beans.RouteCache;
import pub.avalonframework.cloud.gar.dc.*;
import pub.avalonframework.cloud.gar.entity.*;
import pub.avalonframework.cloud.gar.model.ModuleModel;
import pub.avalonframework.cloud.gar.model.RouteModel;
import pub.avalonframework.cloud.gar.model.RouteViewModel;
import pub.avalonframework.cloud.gar.model.SubModuleModel;
import pub.avalonframework.cloud.gar.service.*;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import java.util.*;

/**
 * @author 白超
 * @date 2018/12/6
 */
@Service
public class GarRouteServiceImpl implements GarRouteService {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarRouteViewService routeViewService;
    @Autowired
    private GarRouteViewTemplateService routeViewTemplateService;
    @Autowired
    private GarRoleRouteViewTemplateService roleRouteViewTemplateService;
    @Autowired
    private GarMenuRouteService menuRouteService;

    @Override
    public boolean getValidateValueCanUseBySubModuleId(String moduleId, String subModuleId, String value, List<String> excludeValues) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(subModuleId)) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        if (StringUtil.isEmpty(value)) {
            ExceptionUtil.throwFailException("路由唯一标识符不能为空");
        }
        if (!value.matches(TableUtils.ROUTE_VALUE_REGEX)) {
            ExceptionUtil.throwFailException("路由唯一标识符格式不正确");
        }
        return this.jdbcEngine.queryCount(MySqlDynamicEngine.query(RouteModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId)
                        .subModuleId().equalTo(subModuleId)
                        .value().equalTo(value)
                        .value().notInS(excludeValues)))) == 0;
    }

    public Set<String> getChildrenIds(String id) {
        if (StringUtil.isEmpty(id)) {
            return new HashSet<>(0);
        }
        Set<String> ids = new HashSet<>();
        // 查出子级
        ids.addAll(this.jdbcEngine.queryColumnList(RouteModel.id_alias, String.class, MySqlDynamicEngine.query(RouteModel.class)
                .column(RouteModel.Column::id)
                .where((condition, mainTable) -> condition.and(mainTable.parentId().equalTo(id)))));
        // 查出子级下所有的子级
        ids.addAll(this.jdbcEngine.queryColumnList(RouteModel.id_alias, String.class, MySqlDynamicEngine.query(RouteModel.class)
                .column(RouteModel.Column::id)
                .where((condition, mainTable) -> condition.and(mainTable.parentIds().like("%/" + id + "/%")))));
        return ids;
    }

    @Override
    public boolean getValidatePathCanUseBySubModuleId(String moduleId, String subModuleId, String path, List<String> excludePaths) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(subModuleId)) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        if (StringUtil.isEmpty(path)) {
            ExceptionUtil.throwFailException("路由地址不能为空");
        }
        if (!path.matches(TableUtils.ROUTE_PATH_REGEX)) {
            ExceptionUtil.throwFailException("路由地址格式不正确");
        }
        return this.jdbcEngine.queryCount(MySqlDynamicEngine.query(RouteModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId)
                        .subModuleId().equalTo(subModuleId)
                        .path().equalTo(path)
                        .path().notInS(excludePaths)))) == 0;
    }

    @Override
    public boolean getValidateAliasCanUseBySubModuleId(String moduleId, String subModuleId, String alias, List<String> excludeAliases) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(subModuleId)) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        return this.jdbcEngine.queryCount(MySqlDynamicEngine.query(RouteModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId)
                        .subModuleId().equalTo(subModuleId)
                        .alias().equalTo(alias)
                        .alias().notInS(excludeAliases)))) == 0;
    }

    @Override
    public RouteGet postRoute(String moduleId, RoutePost record) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(record.getSubModuleId())) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        if (StringUtil.isEmpty(record.getName())) {
            ExceptionUtil.throwFailException("路由名称不能为空");
        }
        if (StringUtil.isEmpty(record.getValue())) {
            ExceptionUtil.throwFailException("路由唯一标识符不能为空");
        }
        if (!this.getValidateValueCanUseBySubModuleId(moduleId, record.getSubModuleId(), record.getValue(), null)) {
            ExceptionUtil.throwFailException("路由唯一标识符不可用");
        }
        if (StringUtil.isEmpty(record.getPath())) {
            ExceptionUtil.throwFailException("路由地址不能为空");
        }
        if (!this.getValidatePathCanUseBySubModuleId(moduleId, record.getSubModuleId(), record.getPath(), null)) {
            ExceptionUtil.throwFailException("路由地址不可用");
        }
        if (!StringUtil.isEmpty(record.getAlias())) {
            if (!this.getValidateAliasCanUseBySubModuleId(moduleId, record.getSubModuleId(), record.getAlias(), null)) {
                ExceptionUtil.throwFailException("路由别名不可用");
            }
        }
        if (StringUtil.isEmpty(record.getCache())) {
            record.setCache(RouteCache.N.name());
        } else if (!EnumMethods.contains(record.getCache(), RouteCache.values())) {
            ExceptionUtil.throwFailException("是否缓存不正确");
        }
        if (StringUtil.isEmpty(record.getStatus())) {
            record.setStatus(Status.NORMAL.name());
        } else if (!EnumMethods.contains(record.getStatus(), Status.values())) {
            ExceptionUtil.throwFailException("状态类型不正确");
        }

        Module module = this.jdbcEngine.queryByPrimaryKey(moduleId, Module.class, MySqlDynamicEngine.query(ModuleModel.class));
        if (module == null) {
            ExceptionUtil.throwFailException("模块不存在");
        }

        SubModule subModule = this.jdbcEngine.queryByPrimaryKey(record.getSubModuleId(), SubModule.class, MySqlDynamicEngine.query(SubModuleModel.class));
        if (subModule == null) {
            ExceptionUtil.throwFailException("子模块不存在");
        }

        String id = subModule.getId() + "-" + record.getValue();

        Route routeInsert = new Route();

        routeInsert.setId(id);
        routeInsert.setModuleId(moduleId);
        routeInsert.setSubModuleId(subModule.getId());
        routeInsert.setSubModuleName(subModule.getName());
        routeInsert.setName(record.getName());
        routeInsert.setValue(record.getValue());
        routeInsert.setDescription(record.getDescription());
        routeInsert.setPath(record.getPath());
        routeInsert.setAlias(record.getAlias());
        routeInsert.setCache(record.getCache());

        if (StringUtil.isEmpty(record.getParentId())) {
            //没有父级路由
            routeInsert.setParentId("");
            routeInsert.setParentIds("");
        } else {
            //有父级路由
            Route parentRoute = this.jdbcEngine.queryOne(Route.class, MySqlDynamicEngine.query(RouteModel.class)
                    .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId).id().equalTo(record.getParentId()))));
            if (parentRoute == null) {
                ExceptionUtil.throwFailException("父级路由不存在");
            }
            routeInsert.setParentId(parentRoute.getId());
            String parentIds = parentRoute.getParentIds();
            if (StringUtil.isEmpty(parentIds)) {
                parentIds = "";
            }
            routeInsert.setParentIds(parentIds + "/" + parentRoute.getId());
        }

        routeInsert.setStatus(record.getStatus());
        routeInsert.setCreateTime(Time.localDateTimeNow());
        routeInsert.setCreateTimeStamp(Time.timeStamp());

        Long index = this.jdbcEngine.queryColumnOne(1, Long.class, MySqlDynamicEngine.query(RouteModel.class)
                .functionColumn(FunctionColumnType.MAX, RouteModel.Column::index));
        routeInsert.setIndex(TableUtils.getRouteIndex(index));

        int count = this.jdbcEngine.insertJavaBeanSelective(routeInsert, MySqlDynamicEngine.insert(RouteModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("新增路由失败");
        }

        //新增路由视图
        String routeViewJson = record.getRouteViewJson();
        if (!StringUtil.isEmpty(routeViewJson)) {
            List<RouteViewPost> routeViewPostList = JSONObject.parseArray(routeViewJson, RouteViewPost.class);
            routeViewPostList.forEach(routeView -> {
                routeView.setSubModuleId(subModule.getId());
                routeView.setRouteId(id);
                routeView.setName(routeView.getValue());
            });
            this.routeViewService.postListRouteView(moduleId, routeViewPostList);
        }
        RouteGet route = this.jdbcEngine.queryByPrimaryKey(id, RouteGet.class, MySqlDynamicEngine.query(RouteModel.class));
        List<RouteView> routeViews = this.jdbcEngine.queryForList(RouteView.class, MySqlDynamicEngine.query(RouteViewModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId)
                        .routeId().equalTo(id)))
                .sort(table -> table.index().asc()));
        route.setRouteViews(routeViews);
        return route;
    }

    @Override
    public void putRouteByRouteId(String moduleId, String routeId, RoutePut record) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeId)) {
            ExceptionUtil.throwFailException("路由ID不能为空");
        }
        Route route = this.jdbcEngine.queryOne(Route.class, MySqlDynamicEngine.query(RouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId).id().equalTo(routeId))));
        if (route == null) {
            ExceptionUtil.throwFailException("路由不存在");
        }

        Route routeUpdate = new Route();
        RouteView routeViewUpdate = null;
        RouteViewTemplate routeViewTemplateUpdate = null;
        AutRoleRouteViewTemplate roleRouteViewTemplateUpdate = null;
        MenuRoute menuRouteUpdate = null;

        if (!StringUtil.isEmpty(record.getName()) && !record.getName().equals(route.getName())) {
            routeUpdate.setName(record.getName());
            routeViewUpdate = new RouteView();
            routeViewUpdate.setRouteName(record.getName());
            routeViewTemplateUpdate = new RouteViewTemplate();
            routeViewTemplateUpdate.setRouteName(record.getName());
            roleRouteViewTemplateUpdate = new AutRoleRouteViewTemplate();
            roleRouteViewTemplateUpdate.setRouteName(record.getName());
            menuRouteUpdate = new MenuRoute();
            menuRouteUpdate.setRouteName(record.getName());
        }
        if (!StringUtil.isEmpty(record.getValue()) && !record.getValue().equals(route.getValue())) {
            if (!getValidateValueCanUseBySubModuleId(moduleId, route.getSubModuleId(), record.getValue(), Collections.singletonList(route.getValue()))) {
                ExceptionUtil.throwFailException("路由唯一标识符不可用");
            }
            routeUpdate.setValue(record.getValue());
            if (routeViewUpdate == null) {
                routeViewUpdate = new RouteView();
            }
            routeViewUpdate.setRouteValue(record.getValue());
            if (routeViewTemplateUpdate == null) {
                routeViewTemplateUpdate = new RouteViewTemplate();
            }
            routeViewTemplateUpdate.setRouteValue(record.getValue());
            if (roleRouteViewTemplateUpdate == null) {
                roleRouteViewTemplateUpdate = new AutRoleRouteViewTemplate();
            }
            roleRouteViewTemplateUpdate.setRouteValue(record.getValue());
            if (menuRouteUpdate == null) {
                menuRouteUpdate = new MenuRoute();
            }
            menuRouteUpdate.setRouteValue(record.getValue());
        }
        routeUpdate.setDescription(record.getDescription());
        if (!StringUtil.isEmpty(record.getPath()) && !record.getPath().equals(route.getPath())) {
            if (!getValidatePathCanUseBySubModuleId(moduleId, route.getSubModuleId(), record.getPath(), Collections.singletonList(route.getPath()))) {
                ExceptionUtil.throwFailException("路由地址不可用");
            }
            routeUpdate.setPath(record.getPath());
            if (routeViewUpdate == null) {
                routeViewUpdate = new RouteView();
            }
            routeViewUpdate.setRoutePath(record.getPath());
            if (routeViewTemplateUpdate == null) {
                routeViewTemplateUpdate = new RouteViewTemplate();
            }
            routeViewTemplateUpdate.setRoutePath(record.getPath());
            if (roleRouteViewTemplateUpdate == null) {
                roleRouteViewTemplateUpdate = new AutRoleRouteViewTemplate();
            }
            roleRouteViewTemplateUpdate.setRoutePath(record.getPath());
            if (menuRouteUpdate == null) {
                menuRouteUpdate = new MenuRoute();
            }
            menuRouteUpdate.setRoutePath(record.getPath());
        }
        if (!StringUtil.isEmpty(record.getAlias()) && !record.getAlias().equals(route.getAlias())) {
            if (!this.getValidateAliasCanUseBySubModuleId(moduleId, route.getSubModuleId(), record.getAlias(), Collections.singletonList(route.getAlias()))) {
                ExceptionUtil.throwFailException("路由别名不可用");
            }
            routeUpdate.setAlias(record.getAlias());
        }
        if (!StringUtil.isEmpty(record.getCache()) && !record.getCache().equals(route.getCache())) {
            if (!EnumMethods.contains(record.getCache(), RouteCache.values())) {
                ExceptionUtil.throwFailException("是否缓存不正确");
            }
            routeUpdate.setCache(record.getCache());
        }
        if (!StringUtil.isEmpty(record.getStatus()) && !record.getStatus().equals(route.getStatus())) {
            if (!EnumMethods.contains(record.getStatus(), Status.values())) {
                ExceptionUtil.throwFailException("状态类型不正确");
            }
            routeUpdate.setStatus(record.getStatus());
        }

        String timeString = Time.localDateTimeNow();
        Long timeStamp = Time.timeStamp();

        routeUpdate.setUpdateTime(timeString);
        routeUpdate.setUpdateTimeStamp(timeStamp);

        //更新路由视图
        String routeViewJson = record.getRouteViewJson();
        if (!StringUtil.isEmpty(routeViewJson)) {
            //查询出已有视图
            List<RouteView> routeViews = this.jdbcEngine.queryForList(RouteView.class, MySqlDynamicEngine.query(RouteViewModel.class)
                    .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId)
                            .routeId().equalTo(routeId))));
            List<RouteViewPut> routeViewPutList = JSONObject.parseArray(routeViewJson, RouteViewPut.class);
            // 要删除的路由视图ID
            List<String> routeViewIdsDelete = new ArrayList<>();
            // 要新增的路由视图
            List<RouteViewPost> routeViewsInsert = new ArrayList<>();
            // 要更新的路由视图
            List<RouteViewPut> routeViewsUpdate = new ArrayList<>();

            for (RouteViewPut routeViewPut : routeViewPutList) {
                if (StringUtil.isEmpty(routeViewPut.getId())) {
                    //要新增的
                    RouteViewPost routeViewInsert = new RouteViewPost();
                    BeanUtils.copyProperties(routeViewPut, routeViewInsert);
                    routeViewInsert.setSubModuleId(route.getSubModuleId());
                    routeViewInsert.setRouteId(route.getId());
                    routeViewInsert.setName(routeViewInsert.getValue());
                    routeViewsInsert.add(routeViewInsert);
                    continue;
                }
                for (RouteView routeView : routeViews) {
                    if (routeView.getId().equals(routeViewPut.getId())) {
                        //要修改的
                        routeViewPut.setUpdateTime(timeString);
                        routeViewPut.setUpdateTimeStamp(timeStamp);
                        routeViewsUpdate.add(routeViewPut);
                        break;
                    }
                }
            }
            if (routeViews.size() > 0) {
                routeViews.forEach(routeView -> {
                    for (RouteViewPut routeViewPut : routeViewPutList) {
                        if (StringUtil.isEmpty(routeViewPut.getId()) || routeView.getId().equals(routeViewPut.getId())) {
                            return;
                        }
                    }
                    routeViewIdsDelete.add(routeView.getId());
                });
            }

            //删除多余路由视图
            if (!StringUtil.isEmpty(routeViewIdsDelete)) {
                this.routeViewService.deleteListRouteViewByRouteViewIds(moduleId, routeViewIdsDelete);
            }
            //批量新增路由视图
            if (routeViewsInsert.size() > 0) {
                this.routeViewService.postListRouteView(moduleId, routeViewsInsert);
            }
            //更新路由视图
            for (RouteViewPut routeViewPut : routeViewsUpdate) {
                this.routeViewService.putRouteViewByRouteViewId(moduleId, routeViewPut.getId(), routeViewPut);
            }
        }

        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(routeId, routeUpdate, MySqlDynamicEngine.update(RouteModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("更新路由失败");
        }
        //更新路由视图数据
        if (routeViewUpdate != null) {
            routeViewUpdate.setUpdateTime(timeString);
            routeViewUpdate.setUpdateTimeStamp(timeStamp);
            this.routeViewService.putListRouteViewByModuleIdAndRouteId(moduleId, routeId, routeViewUpdate);
        }
        //更新路由视图模板数据
        if (routeViewTemplateUpdate != null) {
            routeViewTemplateUpdate.setUpdateTime(timeString);
            routeViewTemplateUpdate.setUpdateTimeStamp(timeStamp);
            this.routeViewTemplateService.putListRouteViewTemplateByRouteId(moduleId, routeId, routeViewTemplateUpdate);
        }
        //更新角色路由视图模板数据
        if (roleRouteViewTemplateUpdate != null) {
            roleRouteViewTemplateUpdate.setUpdateTime(timeString);
            roleRouteViewTemplateUpdate.setUpdateTimeStamp(timeStamp);
            this.roleRouteViewTemplateService.putListRoleRouteViewTemplateByRouteId(moduleId, routeId, roleRouteViewTemplateUpdate);
        }
        //更新菜单路由数据
        if (menuRouteUpdate != null) {
            menuRouteUpdate.setUpdateTime(timeString);
            menuRouteUpdate.setUpdateTimeStamp(timeStamp);
            this.menuRouteService.putListMenuRouteByRouteId(moduleId, routeId, menuRouteUpdate);
        }

    }

    @Override
    public void putListRouteByModuleIdAndSubModuleId(String moduleId, String subModuleId, Route routeUpdate) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(subModuleId)) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        this.jdbcEngine.updateJavaBeanSelective(routeUpdate, MySqlDynamicEngine.update(RouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .subModuleId().equalTo(subModuleId))));
    }

    @Override
    public void putSwitchRouteIndexByRouteId(String moduleId, String sourceRouteId, String targetRouteId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        Route sourceRoute = this.jdbcEngine.queryOne(Route.class, MySqlDynamicEngine.query(RouteModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId).id().equalTo(sourceRouteId))));
        if (sourceRoute == null) {
            ExceptionUtil.throwFailException(40404, "路由不存在");
        }
        Route targetRoute = this.jdbcEngine.queryOne(Route.class, MySqlDynamicEngine.query(RouteModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId).id().equalTo(targetRouteId))));
        if (targetRoute == null) {
            ExceptionUtil.throwFailException(40404, "路由不存在");
        }
        Route routeUpdate = new Route();
        routeUpdate.setIndex(targetRoute.getIndex());
        routeUpdate.setUpdateTime(Time.localDateTimeNow());
        routeUpdate.setUpdateTimeStamp(Time.timeStamp());

        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(sourceRouteId, routeUpdate, MySqlDynamicEngine.update(RouteModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("操作失败");
        }
        routeUpdate.setIndex(sourceRoute.getIndex());
        count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(targetRouteId, routeUpdate, MySqlDynamicEngine.update(RouteModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("操作失败");
        }
    }

    @Override
    public void deleteRouteByRouteId(String moduleId, String routeId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeId)) {
            ExceptionUtil.throwFailException("路由ID不能为空");
        }
        //查询出所有子路由的ID
        Set<String> ids = this.getChildrenIds(routeId);
        ids.add(routeId);
        //删除路由
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(RouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .id().inS(ids))));
        if (count != ids.size()) {
            ExceptionUtil.throwFailException("删除路由失败");
        }
        //删除路由视图数据(会同时删除路由视图模板数据、角色路由视图模板数据)
        this.routeViewService.deleteListRouteViewByRouteIds(moduleId, new ArrayList<>(ids));
        //删除菜单路由数据
        this.menuRouteService.deleteListMenuRouteByRouteIds(moduleId, new ArrayList<>(ids));
    }

    @Override
    public void deleteListRouteBySubModuleId(String moduleId, String subModuleId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(subModuleId)) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        //查询子模块下拥有的路由ID
        List<String> routeIds = this.jdbcEngine.queryColumnList(RouteModel.id_alias, String.class, MySqlDynamicEngine.query(RouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .subModuleId().equalTo(subModuleId))));
        if (routeIds.size() == 0) {
            return;
        }
        Set<String> ids = new HashSet<>();
        for (String routeId : routeIds) {
            ids.addAll(this.getChildrenIds(routeId));
            ids.add(routeId);
        }
        //删除路由
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(RouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .id().inS(ids))));
        if (count != routeIds.size()) {
            ExceptionUtil.throwFailException("删除路由失败");
        }
        //删除路由视图数据(会同时删除路由视图模板数据、角色路由视图模板数据)
        this.routeViewService.deleteListRouteViewByRouteIds(moduleId, new ArrayList<>(ids));
        //删除菜单路由数据
        this.menuRouteService.deleteListMenuRouteByRouteIds(moduleId, new ArrayList<>(ids));
    }

    @Override
    public void deleteListRouteBySubModuleIds(String moduleId, List<String> subModuleIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(subModuleIds)) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        //查询子模块下拥有的路由ID
        List<String> routeIds = this.jdbcEngine.queryColumnList(RouteModel.id_alias, String.class, MySqlDynamicEngine.query(RouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .subModuleId().inS(subModuleIds))));
        if (routeIds.size() == 0) {
            return;
        }
        Set<String> ids = new HashSet<>();
        for (String routeId : routeIds) {
            ids.addAll(this.getChildrenIds(routeId));
            ids.add(routeId);
        }
        //删除路由
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(RouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .id().inS(ids))));
        if (count != routeIds.size()) {
            ExceptionUtil.throwFailException("删除路由失败");
        }
        //删除路由视图数据(会同时删除路由视图模板数据、角色路由视图模板数据)
        this.routeViewService.deleteListRouteViewByRouteIds(moduleId, new ArrayList<>(ids));
        //删除菜单路由数据
        this.menuRouteService.deleteListMenuRouteByRouteIds(moduleId, new ArrayList<>(ids));
    }

    @Override
    public void deleteListRouteByRouteIds(String moduleId, List<String> routeIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeIds)) {
            ExceptionUtil.throwFailException("路由ID不能为空");
        }
        Set<String> ids = new HashSet<>();
        for (String routeId : routeIds) {
            ids.addAll(this.getChildrenIds(routeId));
            ids.add(routeId);
        }
        //删除路由
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(RouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .id().inS(ids))));
        if (count != routeIds.size()) {
            ExceptionUtil.throwFailException("删除路由失败");
        }
        //删除路由视图数据(会同时删除路由视图模板数据、角色路由视图模板数据)
        this.routeViewService.deleteListRouteViewByRouteIds(moduleId, new ArrayList<>(ids));
        //删除菜单路由数据
        this.menuRouteService.deleteListMenuRouteByRouteIds(moduleId, new ArrayList<>(ids));
    }
}
