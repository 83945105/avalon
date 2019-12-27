package pub.avalonframework.cloud.gar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import pub.avalon.beans.Time;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalon.holygrail.utils.StringUtil;
import pub.avalon.sqlhelper.core.beans.FunctionColumnType;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.spring.core.CollectionArgumentPreparedStatementSetter;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.beans.RouteDragParams;
import pub.avalonframework.cloud.gar.dc.RouteTreeNode;
import pub.avalonframework.cloud.gar.entity.Route;
import pub.avalonframework.cloud.gar.entity.RouteView;
import pub.avalonframework.cloud.gar.entity.SubModule;
import pub.avalonframework.cloud.gar.model.MenuRouteModel;
import pub.avalonframework.cloud.gar.model.RouteModel;
import pub.avalonframework.cloud.gar.model.RouteViewModel;
import pub.avalonframework.cloud.gar.model.SubModuleModel;
import pub.avalonframework.cloud.gar.service.GarRouteNodeDraggableService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 白超
 * @date 2019/1/19
 */
@Service
public class GarRouteNodeDraggableServiceImpl implements GarRouteNodeDraggableService {

    @Autowired
    private SpringJdbcEngine jdbcEngine;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RouteTreeNode> findBrotherNodesInSortIndex(RouteTreeNode startNode, RouteTreeNode endNode, RouteDragParams params) {
        if (startNode.getParentId() == null) {
            ExceptionUtil.throwFailException("父节点key不能为空");
        }
        if (StringUtil.isEmpty(params)) {
            ExceptionUtil.throwFailException("参数不存在");
        }
        if (StringUtil.isEmpty(params.getModuleId())) {
            ExceptionUtil.throwFailException("模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDragSubModuleId())) {
            ExceptionUtil.throwFailException("拖拽所属子模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropSubModuleId())) {
            ExceptionUtil.throwFailException("放置所属子模块ID不存在");
        }
        return this.jdbcEngine.queryForList(RouteTreeNode.class, MySqlDynamicEngine.query(RouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(params.getModuleId())
                                .subModuleId().equalTo(params.getDragSubModuleId())
                                .parentId().equalTo(startNode.getParentId())
                                .index().greaterThan(startNode.getSortIndex())
                                .index().lessThan(endNode.getSortIndex()))));
    }

    @Override
    public RouteTreeNode findDropPreviousBrotherNode(RouteTreeNode drop, RouteDragParams params) {
        if (StringUtil.isEmpty(drop.getId())) {
            ExceptionUtil.throwFailException("放置节点ID不能为空");
        }
        if (drop.getParentId() == null) {
            ExceptionUtil.throwFailException("放置节点父ID不能为空");
        }
        if (StringUtil.isEmpty(params)) {
            ExceptionUtil.throwFailException("参数不存在");
        }
        if (StringUtil.isEmpty(params.getModuleId())) {
            ExceptionUtil.throwFailException("模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDragSubModuleId())) {
            ExceptionUtil.throwFailException("拖拽所属子模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropSubModuleId())) {
            ExceptionUtil.throwFailException("放置所属子模块ID不存在");
        }
        return this.jdbcEngine.queryOne(RouteTreeNode.class, MySqlDynamicEngine.query(RouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(params.getModuleId())
                                .subModuleId().equalTo(params.getDropSubModuleId())
                                .parentId().equalTo(drop.getParentId())
                                .index().lessThan(drop.getSortIndex())))
                .sort(table -> table.index().desc())
                .limitOne());
    }

    @Override
    public RouteTreeNode findDragNextBrotherNode(RouteTreeNode drag, RouteDragParams params) {
        if (StringUtil.isEmpty(drag.getId())) {
            ExceptionUtil.throwFailException("拖拽节点ID不能为空");
        }
        if (drag.getParentId() == null) {
            ExceptionUtil.throwFailException("拖拽节点父ID不能为空");
        }
        if (StringUtil.isEmpty(params)) {
            ExceptionUtil.throwFailException("参数不存在");
        }
        if (StringUtil.isEmpty(params.getModuleId())) {
            ExceptionUtil.throwFailException("模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDragSubModuleId())) {
            ExceptionUtil.throwFailException("拖拽所属子模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropSubModuleId())) {
            ExceptionUtil.throwFailException("放置所属子模块ID不存在");
        }
        return this.jdbcEngine.queryOne(RouteTreeNode.class, MySqlDynamicEngine.query(RouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(params.getModuleId())
                                .subModuleId().equalTo(params.getDragSubModuleId())
                                .parentId().equalTo(drag.getParentId())
                                .index().greaterThan(drag.getSortIndex())))
                .sort(table -> table.index().asc())
                .limitOne());
    }

    @Override
    public RouteTreeNode findDropNextBrotherNode(RouteTreeNode drop, RouteDragParams params) {
        if (StringUtil.isEmpty(drop.getId())) {
            ExceptionUtil.throwFailException("放置节点ID不能为空");
        }
        if (drop.getParentId() == null) {
            ExceptionUtil.throwFailException("放置节点父ID不能为空");
        }
        if (StringUtil.isEmpty(params)) {
            ExceptionUtil.throwFailException("参数不存在");
        }
        if (StringUtil.isEmpty(params.getModuleId())) {
            ExceptionUtil.throwFailException("模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDragSubModuleId())) {
            ExceptionUtil.throwFailException("拖拽所属子模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropSubModuleId())) {
            ExceptionUtil.throwFailException("放置所属子模块ID不存在");
        }
        return this.jdbcEngine.queryOne(RouteTreeNode.class, MySqlDynamicEngine.query(RouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(params.getModuleId())
                                .subModuleId().equalTo(params.getDropSubModuleId())
                                .parentId().equalTo(drop.getParentId())
                                .index().greaterThan(drop.getSortIndex())))
                .sort(table -> table.index().asc())
                .limitOne());
    }

    @Override
    public RouteTreeNode findParentNode(RouteTreeNode node) {
        if (StringUtil.isEmpty(node.getParentId())) {
            return null;
        }
        return this.jdbcEngine.queryByPrimaryKey(node.getParentId(), RouteTreeNode.class, MySqlDynamicEngine.query(RouteModel.class));
    }

    @Override
    public long getSortIndexInterval() {
        return TableUtils.ROUTE_INDEX_INTERVAL;
    }

    @Override
    public long findDropChildMaxSortIndex(RouteTreeNode drop, RouteDragParams params) {
        if (StringUtil.isEmpty(drop.getId())) {
            ExceptionUtil.throwFailException("放置节点ID不能为空");
        }
        if (StringUtil.isEmpty(params)) {
            ExceptionUtil.throwFailException("参数不存在");
        }
        if (StringUtil.isEmpty(params.getModuleId())) {
            ExceptionUtil.throwFailException("模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDragSubModuleId())) {
            ExceptionUtil.throwFailException("拖拽所属子模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropSubModuleId())) {
            ExceptionUtil.throwFailException("放置所属子模块ID不存在");
        }
        Long maxIndex = this.jdbcEngine.queryColumnOne("maxIndex", Long.class, MySqlDynamicEngine.query(RouteModel.class)
                .functionColumn(FunctionColumnType.MAX, table -> table.index("maxIndex"))
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(params.getModuleId())
                                .parentId().equalTo(drop.getId()))));
        if (maxIndex == null) {
            return 0;
        }
        return maxIndex;
    }

    @Override
    public void updateNodeSortIndex(RouteTreeNode node, long sortIndex, RouteDragParams params) {
        if (StringUtil.isEmpty(node.getId())) {
            ExceptionUtil.throwFailException("节点ID不能为空");
        }
        if (StringUtil.isEmpty(params)) {
            ExceptionUtil.throwFailException("参数不存在");
        }
        if (StringUtil.isEmpty(params.getModuleId())) {
            ExceptionUtil.throwFailException("模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDragSubModuleId())) {
            ExceptionUtil.throwFailException("拖拽所属子模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropSubModuleId())) {
            ExceptionUtil.throwFailException("放置所属子模块ID不存在");
        }
        Route routeUpdate = new Route();
        routeUpdate.setIndex(sortIndex);
        routeUpdate.setUpdateTime(Time.localDateTimeNow());
        routeUpdate.setUpdateTimeStamp(Time.timeStamp());
        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(node.getId(), routeUpdate, MySqlDynamicEngine.update(RouteModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("更新路由排序失败");
        }
    }

    static final String updateDropBrotherNodesSortIndexToNextSql = "update `" + RouteModel.tableName + "` " + RouteModel.tableAlias + " set " + RouteModel.tableAlias + ".`" + RouteModel.index + "` = " + RouteModel.tableAlias + ".`" + RouteModel.index + "` + " + TableUtils.ROUTE_INDEX_INTERVAL + " where " + RouteModel.tableAlias + ".`" + RouteModel.moduleId + "` = ? and " + RouteModel.tableAlias + ".`" + RouteModel.subModuleId + "` = ? and " + RouteModel.tableAlias + ".`" + RouteModel.parentId + "` = ? and " + RouteModel.tableAlias + ".`" + RouteModel.moduleId + "` = ?";

    @Override
    public void updateDropBrotherNodesSortIndexToNext(RouteTreeNode drop, RouteDragParams params) {
        if (drop.getParentId() == null) {
            ExceptionUtil.throwFailException("放置节点父ID不能为空");
        }
        if (StringUtil.isEmpty(params)) {
            ExceptionUtil.throwFailException("参数不存在");
        }
        if (StringUtil.isEmpty(params.getModuleId())) {
            ExceptionUtil.throwFailException("模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDragSubModuleId())) {
            ExceptionUtil.throwFailException("拖拽所属子模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropSubModuleId())) {
            ExceptionUtil.throwFailException("放置所属子模块ID不存在");
        }
        this.jdbcTemplate.update(updateDropBrotherNodesSortIndexToNextSql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(params.getModuleId(), params.getDropSubModuleId(), drop.getParentId(), params.getModuleId())));
    }

    static final String minusNodesSortIndexSql = "update `" + RouteModel.tableName + "` " + RouteModel.tableAlias + " set " + RouteModel.tableAlias + ".`" + RouteModel.index + "` = " + RouteModel.tableAlias + ".`" + RouteModel.index + "` - ? where " + RouteModel.tableAlias + ".`" + RouteModel.moduleId + "` = ? and " + RouteModel.tableAlias + ".`" + RouteModel.primaryKeyName + "` in ";

    @Override
    public void minusNodesSortIndex(Collection<RouteTreeNode> nodes, long minusNum, RouteDragParams params) {
        if (StringUtil.isEmpty(nodes)) {
            ExceptionUtil.throwFailException("nodes不能为空");
        }
        if (StringUtil.isEmpty(params)) {
            ExceptionUtil.throwFailException("参数不存在");
        }
        if (StringUtil.isEmpty(params.getModuleId())) {
            ExceptionUtil.throwFailException("模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDragSubModuleId())) {
            ExceptionUtil.throwFailException("拖拽所属子模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropSubModuleId())) {
            ExceptionUtil.throwFailException("放置所属子模块ID不存在");
        }
        StringBuilder inSql = new StringBuilder();
        List<Object> args = new ArrayList<>();
        args.add(minusNum);
        args.add(params.getModuleId());
        for (RouteTreeNode node : nodes) {
            inSql.append(",?");
            args.add(node.getId());
        }
        inSql.replace(0, 1, "(").append(")");
        this.jdbcTemplate.update(minusNodesSortIndexSql + inSql.toString(),
                new CollectionArgumentPreparedStatementSetter(args));
    }

    static final String plusNodesSortIndexSql = "update `" + RouteModel.tableName + "` " + RouteModel.tableAlias + " set " + RouteModel.tableAlias + ".`" + RouteModel.index + "` = " + RouteModel.tableAlias + ".`" + RouteModel.index + "` + ? where " + RouteModel.tableAlias + ".`" + RouteModel.moduleId + "` = ? and " + RouteModel.tableAlias + ".`" + RouteModel.primaryKeyName + "` in ";

    @Override
    public void plusNodesSortIndex(Collection<RouteTreeNode> nodes, long plusNum, RouteDragParams params) {
        if (StringUtil.isEmpty(nodes)) {
            ExceptionUtil.throwFailException("nodes不能为空");
        }
        if (StringUtil.isEmpty(params)) {
            ExceptionUtil.throwFailException("参数不存在");
        }
        if (StringUtil.isEmpty(params.getModuleId())) {
            ExceptionUtil.throwFailException("模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDragSubModuleId())) {
            ExceptionUtil.throwFailException("拖拽所属子模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropSubModuleId())) {
            ExceptionUtil.throwFailException("放置所属子模块ID不存在");
        }
        StringBuilder inSql = new StringBuilder();
        List<Object> args = new ArrayList<>();
        args.add(plusNum);
        args.add(params.getModuleId());
        for (RouteTreeNode node : nodes) {
            inSql.append(",?");
            args.add(node.getId());
        }
        inSql.replace(0, 1, "(").append(")");
        this.jdbcTemplate.update(plusNodesSortIndexSql + inSql.toString(),
                new CollectionArgumentPreparedStatementSetter(args));
    }

    static final String minusGreaterThanNodeSortIndexBrotherNodesSortIndexSql = "update `" + RouteModel.tableName + "` " + RouteModel.tableAlias + " set " + RouteModel.tableAlias + ".`" + RouteModel.index + "` = " + RouteModel.tableAlias + ".`" + RouteModel.index + "` - ? where " + RouteModel.tableAlias + ".`" + RouteModel.moduleId + "` = ? and " + RouteModel.tableAlias + ".`" + RouteModel.subModuleId + "` = ? and " + RouteModel.tableAlias + ".`" + RouteModel.parentId + "` = ? and " + RouteModel.tableAlias + ".`" + RouteModel.index + "` > ?";

    @Override
    public void minusGreaterThanNodeSortIndexBrotherNodesSortIndex(RouteTreeNode node, long minusNum, RouteDragParams params) {
        if (node.getParentId() == null) {
            ExceptionUtil.throwFailException("节点父ID不能为空");
        }
        if (StringUtil.isEmpty(params)) {
            ExceptionUtil.throwFailException("参数不存在");
        }
        if (StringUtil.isEmpty(params.getModuleId())) {
            ExceptionUtil.throwFailException("模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDragSubModuleId())) {
            ExceptionUtil.throwFailException("拖拽所属子模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropSubModuleId())) {
            ExceptionUtil.throwFailException("放置所属子模块ID不存在");
        }
        this.jdbcTemplate.update(minusGreaterThanNodeSortIndexBrotherNodesSortIndexSql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(minusNum, params.getModuleId(), params.getDragSubModuleId(), node.getParentId(), node.getSortIndex())));

    }

    static final String plusGreaterThanNodeSortIndexBrotherNodesSortIndexSql = "update `" + RouteModel.tableName + "` " + RouteModel.tableAlias + " set " + RouteModel.tableAlias + ".`" + RouteModel.index + "` = " + RouteModel.tableAlias + ".`" + RouteModel.index + "` + ? where " + RouteModel.tableAlias + ".`" + RouteModel.moduleId + "` = ? and " + RouteModel.tableAlias + ".`" + RouteModel.subModuleId + "` = ? and " + RouteModel.tableAlias + ".`" + RouteModel.parentId + "` = ? and " + RouteModel.tableAlias + ".`" + RouteModel.index + "` > ?";

    @Override
    public void plusGreaterThanNodeSortIndexBrotherNodesSortIndex(RouteTreeNode node, long plusNum, RouteDragParams params) {
        if (node.getParentId() == null) {
            ExceptionUtil.throwFailException("节点父ID不能为空");
        }
        if (StringUtil.isEmpty(params)) {
            ExceptionUtil.throwFailException("参数不存在");
        }
        if (StringUtil.isEmpty(params.getModuleId())) {
            ExceptionUtil.throwFailException("模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDragSubModuleId())) {
            ExceptionUtil.throwFailException("拖拽所属子模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropSubModuleId())) {
            ExceptionUtil.throwFailException("放置所属子模块ID不存在");
        }
        this.jdbcTemplate.update(plusGreaterThanNodeSortIndexBrotherNodesSortIndexSql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(plusNum, params.getModuleId(), params.getDropSubModuleId(), node.getParentId(), node.getSortIndex())));

    }

    @Override
    public void changeDragNodeParent(RouteTreeNode drag, @Nullable RouteTreeNode newParentNode, RouteDragParams params) {
        if (StringUtil.isEmpty(drag.getId())) {
            ExceptionUtil.throwErrorException("拖拽节点ID不能为空");
        }
        if (StringUtil.isEmpty(params)) {
            ExceptionUtil.throwFailException("参数不存在");
        }
        if (StringUtil.isEmpty(params.getModuleId())) {
            ExceptionUtil.throwFailException("模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDragSubModuleId())) {
            ExceptionUtil.throwFailException("拖拽所属子模块ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropSubModuleId())) {
            ExceptionUtil.throwFailException("放置所属子模块ID不存在");
        }
        String id = drag.getId();
        Route route = this.jdbcEngine.queryByPrimaryKey(id, Route.class, MySqlDynamicEngine.query(RouteModel.class));
        if (route == null) {
            ExceptionUtil.throwFailException("路由不存在");
        }
        if (!params.getModuleId().equals(route.getModuleId())) {
            ExceptionUtil.throwFailException("不支持改变所属模块");
        }
        String parentId = newParentNode == null ? "" : newParentNode.getId();
        String timeString = Time.localDateTimeNow();
        long timeStamp = Time.timeStamp();
        Route routeUpdate = new Route();
        routeUpdate.setUpdateTime(timeString);
        routeUpdate.setUpdateTimeStamp(timeStamp);
        Route childRouteUpdate = new Route();
        childRouteUpdate.setUpdateTime(timeString);
        childRouteUpdate.setUpdateTimeStamp(timeStamp);
        Route childrenRouteUpdate = new Route();
        childrenRouteUpdate.setUpdateTime(timeString);
        childrenRouteUpdate.setUpdateTimeStamp(timeStamp);
        RouteView routeViewUpdate = null;
        if (!params.getDropSubModuleId().equals(route.getSubModuleId())) {
            // 改变了所属子模块
            SubModule subModule = this.jdbcEngine.queryByPrimaryKey(params.getDropSubModuleId(), SubModule.class, MySqlDynamicEngine.query(SubModuleModel.class));
            if (subModule == null) {
                ExceptionUtil.throwFailException("子模块不存在");
            }
            routeUpdate.setSubModuleId(subModule.getId());
            routeUpdate.setSubModuleName(subModule.getName());
            childRouteUpdate.setSubModuleId(subModule.getId());
            childRouteUpdate.setSubModuleName(subModule.getName());
            childrenRouteUpdate.setSubModuleId(subModule.getId());
            childrenRouteUpdate.setSubModuleName(subModule.getName());
            routeViewUpdate = new RouteView();
            routeViewUpdate.setUpdateTime(timeString);
            routeViewUpdate.setUpdateTimeStamp(timeStamp);
            routeViewUpdate.setSubModuleId(subModule.getId());
            routeViewUpdate.setSubModuleName(subModule.getName());
        }
        if ("".equals(parentId)) {
            //改为根节点
            //更新本节点
            routeUpdate.setParentId("");
            routeUpdate.setParentIds("");
            int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(id, routeUpdate, MySqlDynamicEngine.update(RouteModel.class));
            if (count != 1) {
                ExceptionUtil.throwFailException("更新路由失败");
            }
            //将所有直属子级的parentIds字段更新
            childRouteUpdate.setParentIds("/" + id);
            this.jdbcEngine.updateJavaBeanSelective(childRouteUpdate, MySqlDynamicEngine.update(RouteModel.class)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.moduleId().equalTo(route.getModuleId())
                                    .subModuleId().equalTo(route.getSubModuleId())
                                    .parentId().equalTo(id))));
            //将所有非直属子级的parentIds字段更新
            //由于mysql不支持replace内使用正则表达式,因此只能查出来一条条修改
            List<Route> childrenRouteList = this.jdbcEngine.queryForList(Route.class, MySqlDynamicEngine.query(RouteModel.class)
                    .column(table -> table.id().parentIds())
                    .where((condition, mainTable) -> condition
                            .and(mainTable.moduleId().equalTo(route.getModuleId())
                                    .subModuleId().equalTo(route.getSubModuleId())
                                    .parentIds().like("%/" + id + "/%"))));
            for (Route childrenRoute : childrenRouteList) {
                childrenRouteUpdate.setParentIds("/" + id + (childrenRoute.getParentIds().split(id))[1]);
                count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(childrenRoute.getId(), childrenRouteUpdate, MySqlDynamicEngine.update(RouteModel.class));
                if (count != 1) {
                    ExceptionUtil.throwFailException("更新路由失败");
                }
            }
            if (routeViewUpdate != null) {
                List<Route> childRouteList = this.jdbcEngine.queryForList(Route.class, MySqlDynamicEngine.query(RouteModel.class)
                        .column(RouteModel.Column::id)
                        .where((condition, mainTable) -> condition
                                .and(mainTable.moduleId().equalTo(route.getModuleId())
                                        .parentId().equalTo(id))));
                childRouteList.addAll(childrenRouteList);
                Set<String> ids = childRouteList.stream().map(Route::getId).collect(Collectors.toSet());
                ids.add(id);
                //更新路由视图
                this.jdbcEngine.updateJavaBeanSelective(routeViewUpdate, MySqlDynamicEngine.update(RouteViewModel.class)
                        .where((condition, mainTable) -> condition
                                .and(mainTable.moduleId().equalTo(route.getModuleId())
                                        .routeId().inS(ids))));
                //删除菜单路由
                this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuRouteModel.class)
                        .where((condition, mainTable) -> condition
                                .and(mainTable.moduleId().equalTo(route.getModuleId())
                                        .routeId().inS(ids))));
            }
            return;
        }
        Route parentRoute = this.jdbcEngine.queryByPrimaryKey(parentId, Route.class, MySqlDynamicEngine.query(RouteModel.class)
                .column(table -> table.id().parentId().parentIds()));
        if (parentRoute == null) {
            ExceptionUtil.throwFailException("目标路由不存在");
        }
        //更新本节点
        routeUpdate.setParentId(parentRoute.getId());
        routeUpdate.setParentIds(parentRoute.getParentIds() + "/" + parentRoute.getId());
        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(id, routeUpdate, MySqlDynamicEngine.update(RouteModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("更新路由失败");
        }
        //将所有直属子级的parentIds字段更新
        childRouteUpdate.setParentIds(routeUpdate.getParentIds() + "/" + id);
        this.jdbcEngine.updateJavaBeanSelective(childRouteUpdate, MySqlDynamicEngine.update(RouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(route.getModuleId())
                                .subModuleId().equalTo(route.getSubModuleId())
                                .parentId().equalTo(id))));
        //将所有非直属子级的parentIds字段更新
        //由于mysql不支持replace内使用正则表达式,因此只能查出来一条条修改
        List<Route> childrenRouteList = this.jdbcEngine.queryForList(Route.class, MySqlDynamicEngine.query(RouteModel.class)
                .column(table -> table.id().parentIds())
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(route.getModuleId())
                                .subModuleId().equalTo(route.getSubModuleId())
                                .parentIds().like("%/" + id + "/%"))));
        for (Route childrenRoute : childrenRouteList) {
            childrenRouteUpdate.setParentIds(routeUpdate.getParentIds() + "/" + id + (childrenRoute.getParentIds().split(id))[1]);
            count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(childrenRoute.getId(), childrenRouteUpdate, MySqlDynamicEngine.update(RouteModel.class));
            if (count != 1) {
                ExceptionUtil.throwFailException("更新路由失败");
            }
        }
        if (routeViewUpdate != null) {
            List<Route> childRouteList = this.jdbcEngine.queryForList(Route.class, MySqlDynamicEngine.query(RouteModel.class)
                    .column(RouteModel.Column::id)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.moduleId().equalTo(route.getModuleId())
                                    .parentId().equalTo(id))));
            childRouteList.addAll(childrenRouteList);
            Set<String> ids = childRouteList.stream().map(Route::getId).collect(Collectors.toSet());
            ids.add(id);
            //更新路由视图
            this.jdbcEngine.updateJavaBeanSelective(routeViewUpdate, MySqlDynamicEngine.update(RouteViewModel.class)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.moduleId().equalTo(route.getModuleId())
                                    .routeId().inS(ids))));
            //删除菜单路由
            this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuRouteModel.class)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.moduleId().equalTo(route.getModuleId())
                                    .routeId().inS(ids))));
        }
    }

}
