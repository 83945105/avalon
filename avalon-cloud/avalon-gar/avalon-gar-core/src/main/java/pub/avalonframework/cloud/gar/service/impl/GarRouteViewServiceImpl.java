package pub.avalonframework.cloud.gar.service.impl;

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
import pub.avalonframework.cloud.gar.beans.RouteViewProps;
import pub.avalonframework.cloud.gar.dc.RouteViewGet;
import pub.avalonframework.cloud.gar.dc.RouteViewPost;
import pub.avalonframework.cloud.gar.dc.RouteViewPut;
import pub.avalonframework.cloud.gar.entity.*;
import pub.avalonframework.cloud.gar.model.ModuleModel;
import pub.avalonframework.cloud.gar.model.RouteModel;
import pub.avalonframework.cloud.gar.model.RouteViewModel;
import pub.avalonframework.cloud.gar.model.SubModuleModel;
import pub.avalonframework.cloud.gar.service.GarRoleRouteViewTemplateService;
import pub.avalonframework.cloud.gar.service.GarRouteViewService;
import pub.avalonframework.cloud.gar.service.GarRouteViewTemplateService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 白超
 * @date 2018/12/6
 */
@Service
public class GarRouteViewServiceImpl implements GarRouteViewService {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarRouteViewTemplateService routeViewTemplateService;
    @Autowired
    private GarRoleRouteViewTemplateService roleRouteViewTemplateService;

    @Override
    public boolean getValidateValueCanUseByRouteId(String moduleId, String routeId, String value, List<String> excludeValues) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeId)) {
            ExceptionUtil.throwFailException("路由ID不能为空");
        }
        return this.jdbcEngine.queryCount(MySqlDynamicEngine.query(RouteViewModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId)
                        .routeId().equalTo(routeId)
                        .value().equalTo(value)
                        .value().notInS(excludeValues)))) == 0;
    }

    @Override
    public RouteViewGet postRouteView(String moduleId, RouteViewPost record) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(record.getSubModuleId())) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        if (StringUtil.isEmpty(record.getRouteId())) {
            ExceptionUtil.throwErrorException("路由ID不能为空");
        }
        if (StringUtil.isEmpty(record.getName())) {
            ExceptionUtil.throwFailException("路由视图名称不能为空");
        }
        if (StringUtil.isEmpty(record.getValue())) {
            ExceptionUtil.throwFailException("路由视图唯一标识符不能为空");
        }
        if (!this.getValidateValueCanUseByRouteId(moduleId, record.getRouteId(), record.getValue(), null)) {
            ExceptionUtil.throwFailException("路由视图唯一标识符不可用");
        }
        if (StringUtil.isEmpty(record.getProps())) {
            record.setProps(RouteViewProps.FALSE.value);
        } else if (!EnumMethods.contains(record.getProps(), RouteViewProps.values())) {
            ExceptionUtil.throwFailException("路由props值不正确");
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

        Route route = this.jdbcEngine.queryByPrimaryKey(record.getRouteId(), Route.class, MySqlDynamicEngine.query(RouteModel.class));
        if (route == null) {
            ExceptionUtil.throwFailException("路由不存在");
        }

        String id = route.getId() + "-" + record.getValue();

        RouteView routeViewInsert = new RouteView();

        routeViewInsert.setId(id);

        routeViewInsert.setModuleId(moduleId);
        routeViewInsert.setSubModuleId(subModule.getId());
        routeViewInsert.setSubModuleName(subModule.getName());

        routeViewInsert.setRouteId(route.getId());
        routeViewInsert.setRouteName(route.getName());
        routeViewInsert.setRouteValue(route.getValue());
        routeViewInsert.setRoutePath(route.getPath());

        routeViewInsert.setName(record.getName());
        routeViewInsert.setValue(record.getValue());
        routeViewInsert.setProps(record.getProps());
        routeViewInsert.setDescription(record.getDescription());

        routeViewInsert.setStatus(record.getStatus());
        routeViewInsert.setCreateTime(Time.localDateTimeNow());
        routeViewInsert.setCreateTimeStamp(Time.timeStamp());

        Long index = this.jdbcEngine.queryColumnOne(1, Long.class, MySqlDynamicEngine.query(RouteViewModel.class)
                .functionColumn(FunctionColumnType.MAX, RouteViewModel.Column::index)
                .where((condition, mainTable) -> condition.and(mainTable.routeId().equalTo(route.getId()))));
        routeViewInsert.setIndex(TableUtils.getRouteViewIndex(index));

        int count = this.jdbcEngine.insertJavaBeanSelective(routeViewInsert, MySqlDynamicEngine.insert(RouteViewModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("新增路由视图失败");
        }
        return this.jdbcEngine.queryByPrimaryKey(id, RouteViewGet.class, MySqlDynamicEngine.query(RouteViewModel.class));
    }

    @Override
    public void postListRouteView(String moduleId, List<RouteViewPost> records) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(records)) {
            ExceptionUtil.throwFailException("没有可以新增的数据");
        }
        Module module = this.jdbcEngine.queryByPrimaryKey(moduleId, Module.class, MySqlDynamicEngine.query(ModuleModel.class));
        if (module == null) {
            ExceptionUtil.throwFailException("模块不存在");
        }
        Long index = 0L;
        List<RouteView> routeViewsInsert = new ArrayList<>();
        RouteView routeViewInsert;
        for (RouteViewPost record : records) {
            if (StringUtil.isEmpty(record.getSubModuleId())) {
                ExceptionUtil.throwFailException("子模块ID不能为空");
            }
            if (StringUtil.isEmpty(record.getRouteId())) {
                ExceptionUtil.throwErrorException("路由ID不能为空");
            }
            if (StringUtil.isEmpty(record.getName())) {
                ExceptionUtil.throwFailException("路由视图名称不能为空");
            }
            if (StringUtil.isEmpty(record.getValue())) {
                ExceptionUtil.throwFailException("路由视图唯一标识符不能为空");
            }
            if (!this.getValidateValueCanUseByRouteId(moduleId, record.getRouteId(), record.getValue(), null)) {
                ExceptionUtil.throwFailException("路由视图唯一标识符不可用");
            }
            if (StringUtil.isEmpty(record.getProps())) {
                record.setProps(RouteViewProps.FALSE.value);
            } else if (!EnumMethods.contains(record.getProps(), RouteViewProps.values())) {
                ExceptionUtil.throwFailException("路由props值不正确");
            }
            if (StringUtil.isEmpty(record.getStatus())) {
                record.setStatus(Status.NORMAL.name());
            } else if (!EnumMethods.contains(record.getStatus(), Status.values())) {
                ExceptionUtil.throwFailException("状态类型不正确");
            }

            SubModule subModule = this.jdbcEngine.queryByPrimaryKey(record.getSubModuleId(), SubModule.class, MySqlDynamicEngine.query(SubModuleModel.class));
            if (subModule == null) {
                ExceptionUtil.throwFailException("子模块不存在");
            }

            Route route = this.jdbcEngine.queryByPrimaryKey(record.getRouteId(), Route.class, MySqlDynamicEngine.query(RouteModel.class));
            if (route == null) {
                ExceptionUtil.throwFailException("路由不存在");
            }

            String id = route.getId() + "-" + record.getValue();

            routeViewInsert = new RouteView();

            routeViewInsert.setId(id);

            routeViewInsert.setModuleId(moduleId);
            routeViewInsert.setSubModuleId(subModule.getId());
            routeViewInsert.setSubModuleName(subModule.getName());

            routeViewInsert.setRouteId(route.getId());
            routeViewInsert.setRouteName(route.getName());
            routeViewInsert.setRouteValue(route.getValue());
            routeViewInsert.setRoutePath(route.getPath());

            routeViewInsert.setName(record.getName());
            routeViewInsert.setValue(record.getValue());
            routeViewInsert.setProps(record.getProps());
            routeViewInsert.setDescription(record.getDescription());

            routeViewInsert.setStatus(record.getStatus());
            routeViewInsert.setCreateTime(Time.localDateTimeNow());
            routeViewInsert.setCreateTimeStamp(Time.timeStamp());

            routeViewInsert.setIndex(TableUtils.getRouteViewIndex(index));

            index = TableUtils.getRouteViewIndex(index);

            routeViewsInsert.add(routeViewInsert);
        }

        int count = this.jdbcEngine.batchInsertJavaBeans(routeViewsInsert, MySqlDynamicEngine.insert(RouteViewModel.class));
        if (count == 0) {
            ExceptionUtil.throwFailException("新增路由视图失败");
        }
    }

    @Override
    public void putRouteViewByRouteViewId(String moduleId, String routeViewId, RouteViewPut record) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeViewId)) {
            ExceptionUtil.throwFailException("路由视图ID不能为空");
        }
        RouteView routeView = this.jdbcEngine.queryOne(RouteView.class, MySqlDynamicEngine.query(RouteViewModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId).id().equalTo(routeViewId))));
        if (routeView == null) {
            ExceptionUtil.throwFailException("路由视图不存在");
        }

        RouteView routeViewUpdate = new RouteView();
        RouteViewTemplate routeViewTemplateUpdate = null;
        AutRoleRouteViewTemplate roleRouteViewTemplateUpdate = null;

        if (!StringUtil.isEmpty(record.getName()) && !record.getName().equals(routeView.getName())) {
            routeViewUpdate.setName(record.getName());
            routeViewTemplateUpdate = new RouteViewTemplate();
            routeViewTemplateUpdate.setRouteViewName(record.getName());
            roleRouteViewTemplateUpdate = new AutRoleRouteViewTemplate();
            roleRouteViewTemplateUpdate.setRouteViewName(record.getName());
        }
        if (!StringUtil.isEmpty(record.getValue()) && !record.getValue().equals(routeView.getValue())) {
            if (!getValidateValueCanUseByRouteId(moduleId, routeView.getRouteId(), record.getValue(), Collections.singletonList(routeView.getValue()))) {
                ExceptionUtil.throwFailException("路由视图唯一标识符不可用");
            }
            routeViewUpdate.setValue(record.getValue());
            if (routeViewTemplateUpdate == null) {
                routeViewTemplateUpdate = new RouteViewTemplate();
            }
            routeViewTemplateUpdate.setRouteViewValue(record.getValue());
            if (roleRouteViewTemplateUpdate == null) {
                roleRouteViewTemplateUpdate = new AutRoleRouteViewTemplate();
            }
            roleRouteViewTemplateUpdate.setRouteViewValue(record.getValue());
        }
        if (!StringUtil.isEmpty(record.getProps()) && !record.getProps().equals(routeView.getProps())) {
            if (!EnumMethods.contains(record.getProps(), RouteViewProps.values())) {
                ExceptionUtil.throwFailException("路由props值不正确");
            }
            routeViewUpdate.setProps(record.getProps());
        }
        routeViewUpdate.setDescription(record.getDescription());
        if (!StringUtil.isEmpty(record.getStatus()) && !record.getStatus().equals(routeView.getStatus())) {
            if (!EnumMethods.contains(record.getStatus(), Status.values())) {
                ExceptionUtil.throwFailException("状态类型不正确");
            }
            routeViewUpdate.setStatus(record.getStatus());
        }

        String timeString = Time.localDateTimeNow();
        Long timeStamp = Time.timeStamp();

        routeViewUpdate.setUpdateTime(timeString);
        routeViewUpdate.setUpdateTimeStamp(timeStamp);

        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(routeViewId, routeViewUpdate, MySqlDynamicEngine.update(RouteViewModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("更新路由视图失败");
        }
        //更新路由视图模板数据
        if (routeViewTemplateUpdate != null) {
            routeViewTemplateUpdate.setUpdateTime(timeString);
            routeViewTemplateUpdate.setUpdateTimeStamp(timeStamp);
            this.routeViewTemplateService.putListRouteViewTemplateByRouteViewId(moduleId, routeViewId, routeViewTemplateUpdate);
        }
        //更新角色路由视图模板数据
        if (roleRouteViewTemplateUpdate != null) {
            roleRouteViewTemplateUpdate.setUpdateTime(timeString);
            roleRouteViewTemplateUpdate.setUpdateTimeStamp(timeStamp);
            this.roleRouteViewTemplateService.putListRoleRouteViewTemplateByRouteViewId(moduleId, routeViewId, roleRouteViewTemplateUpdate);
        }

    }

    @Override
    public void putListRouteViewByModuleIdAndRouteId(String moduleId, String routeId, RouteView routeViewUpdate) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeId)) {
            ExceptionUtil.throwFailException("路由ID不能为空");
        }
        this.jdbcEngine.updateJavaBeanSelective(routeViewUpdate, MySqlDynamicEngine.update(RouteViewModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeId().equalTo(routeId))));
    }

    @Override
    public void putListRouteViewByModuleIdAndRouteIds(String moduleId, List<String> routeIds, RouteView routeViewUpdate) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeIds)) {
            ExceptionUtil.throwFailException("路由ID不能为空");
        }
        this.jdbcEngine.updateJavaBeanSelective(routeViewUpdate, MySqlDynamicEngine.update(RouteViewModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeId().inS(routeIds))));
    }

    @Override
    public void putSwitchRouteViewIndexByRouteViewId(String moduleId, String sourceRouteViewId, String targetRouteViewId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        RouteView sourceRouteView = this.jdbcEngine.queryOne(RouteView.class, MySqlDynamicEngine.query(RouteViewModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId).id().equalTo(sourceRouteViewId))));
        if (sourceRouteView == null) {
            ExceptionUtil.throwFailException(40404, "路由视图不存在");
        }
        RouteView targetRouteView = this.jdbcEngine.queryOne(RouteView.class, MySqlDynamicEngine.query(RouteViewModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .id().equalTo(targetRouteViewId))));
        if (targetRouteView == null) {
            ExceptionUtil.throwFailException(40404, "路由视图不存在");
        }
        RouteView routeViewUpdate = new RouteView();
        routeViewUpdate.setIndex(targetRouteView.getIndex());
        routeViewUpdate.setUpdateTime(Time.localDateTimeNow());
        routeViewUpdate.setUpdateTimeStamp(Time.timeStamp());

        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(sourceRouteViewId, routeViewUpdate, MySqlDynamicEngine.update(RouteViewModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("操作失败");
        }
        routeViewUpdate.setIndex(sourceRouteView.getIndex());
        count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(targetRouteViewId, routeViewUpdate, MySqlDynamicEngine.update(RouteViewModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("操作失败");
        }
    }

    @Override
    public void deleteRouteViewByRouteViewId(String moduleId, String routeViewId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeViewId)) {
            ExceptionUtil.throwFailException("路由视图ID不能为空");
        }
        //删除路由视图
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(RouteViewModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .id().equalTo(routeViewId))));
        if (count != 1) {
            ExceptionUtil.throwFailException("删除路由视图失败");
        }
        //删除路由视图模板数据(会同时删除角色路由视图模板数据)
        this.routeViewTemplateService.deleteListRouteViewTemplateByRouteViewId(moduleId, routeViewId);
    }

    @Override
    public void deleteListRouteViewByRouteId(String moduleId, String routeId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeId)) {
            ExceptionUtil.throwFailException("路由ID不能为空");
        }
        //查询子模块下拥有的路由视图ID
        List<String> routeViewIds = this.jdbcEngine.queryColumnList(RouteViewModel.id_alias, String.class, MySqlDynamicEngine.query(RouteViewModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeId().equalTo(routeId))));
        if (routeViewIds.size() == 0) {
            return;
        }
        //删除路由视图
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(RouteViewModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeId().equalTo(routeId))));
        if (count != routeViewIds.size()) {
            ExceptionUtil.throwFailException("删除路由视图失败");
        }
        //删除路由视图模板数据(会同时删除角色路由视图模板数据)
        this.routeViewTemplateService.deleteListRouteViewTemplateByRouteViewIds(moduleId, routeViewIds);
    }

    @Override
    public void deleteListRouteViewByRouteIds(String moduleId, List<String> routeIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeIds)) {
            ExceptionUtil.throwFailException("路由ID不能为空");
        }
        //查询子模块下拥有的路由视图ID
        List<String> routeViewIds = this.jdbcEngine.queryColumnList(RouteViewModel.id_alias, String.class, MySqlDynamicEngine.query(RouteViewModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeId().inS(routeIds))));
        if (routeViewIds.size() == 0) {
            return;
        }
        //删除路由视图
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(RouteViewModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeId().inS(routeIds))));
        if (count != routeViewIds.size()) {
            ExceptionUtil.throwFailException("删除路由视图失败");
        }
        //删除路由视图模板数据(会同时删除角色路由视图模板数据)
        this.routeViewTemplateService.deleteListRouteViewTemplateByRouteViewIds(moduleId, routeViewIds);
    }

    @Override
    public void deleteListRouteViewByRouteViewIds(String moduleId, List<String> routeViewIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeViewIds)) {
            ExceptionUtil.throwFailException("路由视图ID不能为空");
        }
        //删除路由视图
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(RouteViewModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .id().inS(routeViewIds))));
        if (count == 0) {
            ExceptionUtil.throwFailException("删除路由视图失败");
        }
        //删除路由视图模板数据(会同时删除角色路由视图模板数据)
        this.routeViewTemplateService.deleteListRouteViewTemplateByRouteViewIds(moduleId, routeViewIds);
    }
}
