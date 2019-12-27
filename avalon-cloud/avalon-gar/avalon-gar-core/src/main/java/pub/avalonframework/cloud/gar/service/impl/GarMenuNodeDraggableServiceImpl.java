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
import pub.avalonframework.cloud.gar.beans.MenuDragParams;
import pub.avalonframework.cloud.gar.dc.MenuTreeNode;
import pub.avalonframework.cloud.gar.entity.AutRoleMenu;
import pub.avalonframework.cloud.gar.entity.Menu;
import pub.avalonframework.cloud.gar.entity.MenuGroup;
import pub.avalonframework.cloud.gar.entity.SubModule;
import pub.avalonframework.cloud.gar.model.*;
import pub.avalonframework.cloud.gar.service.GarMenuNodeDraggableService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 白超
 * @date 2019/1/19
 */
@Service
public class GarMenuNodeDraggableServiceImpl implements GarMenuNodeDraggableService {

    @Autowired
    private SpringJdbcEngine jdbcEngine;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<MenuTreeNode> findBrotherNodesInSortIndex(MenuTreeNode startNode, MenuTreeNode endNode, MenuDragParams params) {
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
        if (StringUtil.isEmpty(params.getDragMenuGroupId())) {
            ExceptionUtil.throwErrorException("拖拽所属菜单组ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropMenuGroupId())) {
            ExceptionUtil.throwErrorException("放置所属菜单组ID不存在");
        }
        return this.jdbcEngine.queryForList(MenuTreeNode.class, MySqlDynamicEngine.query(MenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(params.getModuleId())
                                .subModuleId().equalTo(params.getDragSubModuleId())
                                .menuGroupId().equalTo(params.getDragMenuGroupId())
                                .parentId().equalTo(startNode.getParentId())
                                .index().greaterThan(startNode.getSortIndex())
                                .index().lessThan(endNode.getSortIndex()))));
    }

    @Override
    public MenuTreeNode findDropPreviousBrotherNode(MenuTreeNode drop, MenuDragParams params) {
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
        if (StringUtil.isEmpty(params.getDragMenuGroupId())) {
            ExceptionUtil.throwErrorException("拖拽所属菜单组ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropMenuGroupId())) {
            ExceptionUtil.throwErrorException("放置所属菜单组ID不存在");
        }
        return this.jdbcEngine.queryOne(MenuTreeNode.class, MySqlDynamicEngine.query(MenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(params.getModuleId())
                                .subModuleId().equalTo(params.getDropSubModuleId())
                                .menuGroupId().equalTo(params.getDropMenuGroupId())
                                .parentId().equalTo(drop.getParentId())
                                .index().lessThan(drop.getSortIndex())))
                .sort(table -> table.index().desc())
                .limitOne());
    }

    @Override
    public MenuTreeNode findDragNextBrotherNode(MenuTreeNode drag, MenuDragParams params) {
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
        if (StringUtil.isEmpty(params.getDragMenuGroupId())) {
            ExceptionUtil.throwErrorException("拖拽所属菜单组ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropMenuGroupId())) {
            ExceptionUtil.throwErrorException("放置所属菜单组ID不存在");
        }
        return this.jdbcEngine.queryOne(MenuTreeNode.class, MySqlDynamicEngine.query(MenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(params.getModuleId())
                                .subModuleId().equalTo(params.getDragSubModuleId())
                                .menuGroupId().equalTo(params.getDragMenuGroupId())
                                .parentId().equalTo(drag.getParentId())
                                .index().greaterThan(drag.getSortIndex())))
                .sort(table -> table.index().asc())
                .limitOne());
    }

    @Override
    public MenuTreeNode findDropNextBrotherNode(MenuTreeNode drop, MenuDragParams params) {
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
        if (StringUtil.isEmpty(params.getDragMenuGroupId())) {
            ExceptionUtil.throwErrorException("拖拽所属菜单组ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropMenuGroupId())) {
            ExceptionUtil.throwErrorException("放置所属菜单组ID不存在");
        }
        return this.jdbcEngine.queryOne(MenuTreeNode.class, MySqlDynamicEngine.query(MenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(params.getModuleId())
                                .subModuleId().equalTo(params.getDropSubModuleId())
                                .menuGroupId().equalTo(params.getDropMenuGroupId())
                                .parentId().equalTo(drop.getParentId())
                                .index().greaterThan(drop.getSortIndex())))
                .sort(table -> table.index().asc())
                .limitOne());
    }

    @Override
    public MenuTreeNode findParentNode(MenuTreeNode node) {
        if (StringUtil.isEmpty(node.getParentId())) {
            return null;
        }
        return this.jdbcEngine.queryByPrimaryKey(node.getParentId(), MenuTreeNode.class, MySqlDynamicEngine.query(MenuModel.class));
    }

    @Override
    public long getSortIndexInterval() {
        return TableUtils.MENU_INDEX_INTERVAL;
    }

    @Override
    public long findDropChildMaxSortIndex(MenuTreeNode drop, MenuDragParams params) {
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
        if (StringUtil.isEmpty(params.getDragMenuGroupId())) {
            ExceptionUtil.throwErrorException("拖拽所属菜单组ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropMenuGroupId())) {
            ExceptionUtil.throwErrorException("放置所属菜单组ID不存在");
        }
        Long maxIndex = this.jdbcEngine.queryColumnOne("maxIndex", Long.class, MySqlDynamicEngine.query(MenuModel.class)
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
    public void updateNodeSortIndex(MenuTreeNode node, long sortIndex, MenuDragParams params) {
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
        if (StringUtil.isEmpty(params.getDragMenuGroupId())) {
            ExceptionUtil.throwErrorException("拖拽所属菜单组ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropMenuGroupId())) {
            ExceptionUtil.throwErrorException("放置所属菜单组ID不存在");
        }
        Menu menuUpdate = new Menu();
        menuUpdate.setIndex(sortIndex);
        menuUpdate.setUpdateTime(Time.localDateTimeNow());
        menuUpdate.setUpdateTimeStamp(Time.timeStamp());
        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(node.getId(), menuUpdate, MySqlDynamicEngine.update(MenuModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("更新路由排序失败");
        }
    }

    static final String updateDropBrotherNodesSortIndexToNextSql = "update `" + MenuModel.tableName + "` " + MenuModel.tableAlias + " set " + MenuModel.tableAlias + ".`" + MenuModel.index + "` = " + MenuModel.tableAlias + ".`" + MenuModel.index + "` + " + TableUtils.MENU_INDEX_INTERVAL + " where " + MenuModel.tableAlias + ".`" + MenuModel.moduleId + "` = ? and " + MenuModel.tableAlias + ".`" + MenuModel.subModuleId + "` = ? and " + MenuModel.tableAlias + ".`" + MenuModel.menuGroupId + "` = ? and " + MenuModel.tableAlias + ".`" + MenuModel.parentId + "` = ? and " + MenuModel.tableAlias + ".`" + MenuModel.moduleId + "` = ?";

    @Override
    public void updateDropBrotherNodesSortIndexToNext(MenuTreeNode drop, MenuDragParams params) {
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
        if (StringUtil.isEmpty(params.getDragMenuGroupId())) {
            ExceptionUtil.throwErrorException("拖拽所属菜单组ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropMenuGroupId())) {
            ExceptionUtil.throwErrorException("放置所属菜单组ID不存在");
        }
        this.jdbcTemplate.update(updateDropBrotherNodesSortIndexToNextSql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(params.getModuleId(), params.getDropSubModuleId(), params.getDropMenuGroupId(), drop.getParentId(), params.getModuleId())));
    }

    static final String minusNodesSortIndexSql = "update `" + MenuModel.tableName + "` " + MenuModel.tableAlias + " set " + MenuModel.tableAlias + ".`" + MenuModel.index + "` = " + MenuModel.tableAlias + ".`" + MenuModel.index + "` - ? where " + MenuModel.tableAlias + ".`" + MenuModel.moduleId + "` = ? and " + MenuModel.tableAlias + ".`" + MenuModel.primaryKeyName + "` in ";

    @Override
    public void minusNodesSortIndex(Collection<MenuTreeNode> nodes, long minusNum, MenuDragParams params) {
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
        if (StringUtil.isEmpty(params.getDragMenuGroupId())) {
            ExceptionUtil.throwErrorException("拖拽所属菜单组ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropMenuGroupId())) {
            ExceptionUtil.throwErrorException("放置所属菜单组ID不存在");
        }
        StringBuilder inSql = new StringBuilder();
        List<Object> args = new ArrayList<>();
        args.add(minusNum);
        args.add(params.getModuleId());
        for (MenuTreeNode node : nodes) {
            inSql.append(",?");
            args.add(node.getId());
        }
        inSql.replace(0, 1, "(").append(")");
        this.jdbcTemplate.update(minusNodesSortIndexSql + inSql.toString(),
                new CollectionArgumentPreparedStatementSetter(args));
    }

    static final String plusNodesSortIndexSql = "update `" + MenuModel.tableName + "` " + MenuModel.tableAlias + " set " + MenuModel.tableAlias + ".`" + MenuModel.index + "` = " + MenuModel.tableAlias + ".`" + MenuModel.index + "` + ? where " + MenuModel.tableAlias + ".`" + MenuModel.moduleId + "` = ? and " + MenuModel.tableAlias + ".`" + MenuModel.primaryKeyName + "` in ";

    @Override
    public void plusNodesSortIndex(Collection<MenuTreeNode> nodes, long plusNum, MenuDragParams params) {
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
        if (StringUtil.isEmpty(params.getDragMenuGroupId())) {
            ExceptionUtil.throwErrorException("拖拽所属菜单组ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropMenuGroupId())) {
            ExceptionUtil.throwErrorException("放置所属菜单组ID不存在");
        }
        StringBuilder inSql = new StringBuilder();
        List<Object> args = new ArrayList<>();
        args.add(plusNum);
        args.add(params.getModuleId());
        for (MenuTreeNode node : nodes) {
            inSql.append(",?");
            args.add(node.getId());
        }
        inSql.replace(0, 1, "(").append(")");
        this.jdbcTemplate.update(plusNodesSortIndexSql + inSql.toString(),
                new CollectionArgumentPreparedStatementSetter(args));
    }

    static final String minusGreaterThanNodeSortIndexBrotherNodesSortIndexSql = "update `" + MenuModel.tableName + "` " + MenuModel.tableAlias + " set " + MenuModel.tableAlias + ".`" + MenuModel.index + "` = " + MenuModel.tableAlias + ".`" + MenuModel.index + "` - ? where " + MenuModel.tableAlias + ".`" + MenuModel.moduleId + "` = ? and " + MenuModel.tableAlias + ".`" + MenuModel.subModuleId + "` = ? and " + MenuModel.tableAlias + ".`" + MenuModel.menuGroupId + "` = ? and " + MenuModel.tableAlias + ".`" + MenuModel.parentId + "` = ? and " + MenuModel.tableAlias + ".`" + MenuModel.index + "` > ?";

    @Override
    public void minusGreaterThanNodeSortIndexBrotherNodesSortIndex(MenuTreeNode node, long minusNum, MenuDragParams params) {
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
        if (StringUtil.isEmpty(params.getDragMenuGroupId())) {
            ExceptionUtil.throwErrorException("拖拽所属菜单组ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropMenuGroupId())) {
            ExceptionUtil.throwErrorException("放置所属菜单组ID不存在");
        }
        this.jdbcTemplate.update(minusGreaterThanNodeSortIndexBrotherNodesSortIndexSql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(minusNum, params.getModuleId(), params.getDragSubModuleId(), params.getDragMenuGroupId(), node.getParentId(), node.getSortIndex())));

    }

    static final String plusGreaterThanNodeSortIndexBrotherNodesSortIndexSql = "update `" + MenuModel.tableName + "` " + MenuModel.tableAlias + " set " + MenuModel.tableAlias + ".`" + MenuModel.index + "` = " + MenuModel.tableAlias + ".`" + MenuModel.index + "` + ? where " + MenuModel.tableAlias + ".`" + MenuModel.moduleId + "` = ? and " + MenuModel.tableAlias + ".`" + MenuModel.subModuleId + "` = ? and " + MenuModel.tableAlias + ".`" + MenuModel.menuGroupId + "` = ? and " + MenuModel.tableAlias + ".`" + MenuModel.parentId + "` = ? and " + MenuModel.tableAlias + ".`" + MenuModel.index + "` > ?";

    @Override
    public void plusGreaterThanNodeSortIndexBrotherNodesSortIndex(MenuTreeNode node, long plusNum, MenuDragParams params) {
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
        if (StringUtil.isEmpty(params.getDragMenuGroupId())) {
            ExceptionUtil.throwErrorException("拖拽所属菜单组ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropMenuGroupId())) {
            ExceptionUtil.throwErrorException("放置所属菜单组ID不存在");
        }
        this.jdbcTemplate.update(plusGreaterThanNodeSortIndexBrotherNodesSortIndexSql,
                new CollectionArgumentPreparedStatementSetter(Arrays.asList(plusNum, params.getModuleId(), params.getDropSubModuleId(), params.getDropMenuGroupId(), node.getParentId(), node.getSortIndex())));
    }

    @Override
    public void changeDragNodeParent(MenuTreeNode drag, @Nullable MenuTreeNode newParentNode, MenuDragParams params) {
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
        if (StringUtil.isEmpty(params.getDragMenuGroupId())) {
            ExceptionUtil.throwErrorException("拖拽所属菜单组ID不存在");
        }
        if (StringUtil.isEmpty(params.getDropMenuGroupId())) {
            ExceptionUtil.throwErrorException("放置所属菜单组ID不存在");
        }
        String id = drag.getId();
        Menu menu = this.jdbcEngine.queryByPrimaryKey(id, Menu.class, MySqlDynamicEngine.query(MenuModel.class));
        if (menu == null) {
            ExceptionUtil.throwFailException("路由不存在");
        }
        if (!params.getModuleId().equals(menu.getModuleId())) {
            ExceptionUtil.throwFailException("不支持改变所属模块");
        }
        String parentId = newParentNode == null ? "" : newParentNode.getId();
        String timeString = Time.localDateTimeNow();
        long timeStamp = Time.timeStamp();
        Menu menuUpdate = new Menu();
        menuUpdate.setUpdateTime(timeString);
        menuUpdate.setUpdateTimeStamp(timeStamp);
        Menu childMenuUpdate = new Menu();
        childMenuUpdate.setUpdateTime(timeString);
        childMenuUpdate.setUpdateTimeStamp(timeStamp);
        Menu childrenMenuUpdate = new Menu();
        childrenMenuUpdate.setUpdateTime(timeString);
        childrenMenuUpdate.setUpdateTimeStamp(timeStamp);
        if (!params.getDropSubModuleId().equals(menu.getSubModuleId())) {
            // 改变了所属子模块
            SubModule subModule = this.jdbcEngine.queryByPrimaryKey(params.getDropSubModuleId(), SubModule.class, MySqlDynamicEngine.query(SubModuleModel.class));
            if (subModule == null) {
                ExceptionUtil.throwFailException("子模块不存在");
            }
            menuUpdate.setSubModuleId(subModule.getId());
            menuUpdate.setSubModuleName(subModule.getName());
            childMenuUpdate.setSubModuleId(subModule.getId());
            childMenuUpdate.setSubModuleName(subModule.getName());
            childrenMenuUpdate.setSubModuleId(subModule.getId());
            childrenMenuUpdate.setSubModuleName(subModule.getName());
        }
        AutRoleMenu roleMenuUpdate = null;
        if (!params.getDropMenuGroupId().equals(menu.getMenuGroupId())) {
            //改变了所属菜单组
            MenuGroup menuGroup = this.jdbcEngine.queryByPrimaryKey(params.getDropMenuGroupId(), MenuGroup.class, MySqlDynamicEngine.query(MenuGroupModel.class));
            if (menuGroup == null) {
                ExceptionUtil.throwErrorException("菜单组不存在");
            }
            menuUpdate.setMenuGroupId(menuGroup.getId());
            childMenuUpdate.setMenuGroupId(menuGroup.getId());
            childrenMenuUpdate.setMenuGroupId(menuGroup.getId());
            roleMenuUpdate = new AutRoleMenu();
            roleMenuUpdate.setUpdateTime(timeString);
            roleMenuUpdate.setUpdateTimeStamp(timeStamp);
            roleMenuUpdate.setMenuGroupId(menuGroup.getId());
        }
        if ("".equals(parentId)) {
            //改为根节点
            //更新本节点
            menuUpdate.setParentId("");
            menuUpdate.setParentIds("");
            int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(id, menuUpdate, MySqlDynamicEngine.update(MenuModel.class));
            if (count != 1) {
                ExceptionUtil.throwFailException("更新路由失败");
            }
            //将所有直属子级的parentIds字段更新
            childMenuUpdate.setParentIds("/" + id);
            this.jdbcEngine.updateJavaBeanSelective(childMenuUpdate, MySqlDynamicEngine.update(MenuModel.class)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.moduleId().equalTo(menu.getModuleId())
                                    .subModuleId().equalTo(menu.getSubModuleId())
                                    .menuGroupId().equalTo(menu.getMenuGroupId())
                                    .parentId().equalTo(id))));
            //将所有非直属子级的parentIds字段更新
            //由于mysql不支持replace内使用正则表达式,因此只能查出来一条条修改
            List<Menu> childrenMenuList = this.jdbcEngine.queryForList(Menu.class, MySqlDynamicEngine.query(MenuModel.class)
                    .column(table -> table.id().parentIds())
                    .where((condition, mainTable) -> condition
                            .and(mainTable.moduleId().equalTo(menu.getModuleId())
                                    .subModuleId().equalTo(menu.getSubModuleId())
                                    .menuGroupId().equalTo(menu.getMenuGroupId())
                                    .parentIds().like("%/" + id + "/%"))));
            for (Menu childrenMenu : childrenMenuList) {
                childrenMenuUpdate.setParentIds("/" + id + (childrenMenu.getParentIds().split(id))[1]);
                count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(childrenMenu.getId(), childrenMenuUpdate, MySqlDynamicEngine.update(MenuModel.class));
                if (count != 1) {
                    ExceptionUtil.throwFailException("更新路由失败");
                }
            }
            if (!params.getDropSubModuleId().equals(menu.getSubModuleId()) || roleMenuUpdate != null) {
                List<Menu> childMenuList = this.jdbcEngine.queryForList(Menu.class, MySqlDynamicEngine.query(MenuModel.class)
                        .column(MenuModel.Column::id)
                        .where((condition, mainTable) -> condition
                                .and(mainTable.moduleId().equalTo(menu.getModuleId())
                                        .parentId().equalTo(id))));
                childMenuList.addAll(childrenMenuList);
                Set<String> ids = childMenuList.stream().map(Menu::getId).collect(Collectors.toSet());
                ids.add(id);
                if (roleMenuUpdate != null) {
                    //更新菜单路由
                    String roleMenuTableName = TableUtils.getRoleMenuTableName(menu.getModuleId());
                    this.jdbcEngine.updateJavaBeanSelective(roleMenuUpdate, MySqlDynamicEngine.update(roleMenuTableName, AutRoleMenuModel.class)
                            .where((condition, mainTable) -> condition
                                    .and(mainTable.moduleId().equalTo(menu.getModuleId())
                                            .menuId().inS(ids))));
                }
                if (!params.getDropSubModuleId().equals(menu.getSubModuleId())) {
                    //删除菜单路由
                    this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuRouteModel.class)
                            .where((condition, mainTable) -> condition
                                    .and(mainTable.moduleId().equalTo(menu.getModuleId())
                                            .menuId().inS(ids))));
                }
            }
            return;
        }

        Menu parentMenu = this.jdbcEngine.queryByPrimaryKey(parentId, Menu.class, MySqlDynamicEngine.query(MenuModel.class)
                .column(table -> table.id().parentId().parentIds()));
        if (parentMenu == null) {
            ExceptionUtil.throwFailException("目标路由不存在");
        }
        //更新本节点
        menuUpdate.setParentId(parentMenu.getId());
        menuUpdate.setParentIds(parentMenu.getParentIds() + "/" + parentMenu.getId());
        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(id, menuUpdate, MySqlDynamicEngine.update(MenuModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("更新路由失败");
        }
        //将所有直属子级的parentIds字段更新
        childMenuUpdate.setParentIds(menuUpdate.getParentIds() + "/" + id);
        this.jdbcEngine.updateJavaBeanSelective(childMenuUpdate, MySqlDynamicEngine.update(MenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(menu.getModuleId())
                                .subModuleId().equalTo(menu.getSubModuleId())
                                .menuGroupId().equalTo(menu.getMenuGroupId())
                                .parentId().equalTo(id))));
        //将所有非直属子级的parentIds字段更新
        //由于mysql不支持replace内使用正则表达式,因此只能查出来一条条修改
        List<Menu> childrenMenuList = this.jdbcEngine.queryForList(Menu.class, MySqlDynamicEngine.query(MenuModel.class)
                .column(table -> table.id().parentIds())
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(menu.getModuleId())
                                .subModuleId().equalTo(menu.getSubModuleId())
                                .menuGroupId().equalTo(menu.getMenuGroupId())
                                .parentIds().like("%/" + id + "/%"))));
        for (Menu childrenMenu : childrenMenuList) {
            childrenMenuUpdate.setParentIds(menuUpdate.getParentIds() + "/" + id + (childrenMenu.getParentIds().split(id))[1]);
            count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(childrenMenu.getId(), childrenMenuUpdate, MySqlDynamicEngine.update(MenuModel.class));
            if (count != 1) {
                ExceptionUtil.throwFailException("更新路由失败");
            }
        }
        if (!params.getDropSubModuleId().equals(menu.getSubModuleId()) || roleMenuUpdate != null) {
            List<Menu> childMenuList = this.jdbcEngine.queryForList(Menu.class, MySqlDynamicEngine.query(MenuModel.class)
                    .column(MenuModel.Column::id)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.moduleId().equalTo(menu.getModuleId())
                                    .parentId().equalTo(id))));
            childMenuList.addAll(childrenMenuList);
            Set<String> ids = childMenuList.stream().map(Menu::getId).collect(Collectors.toSet());
            ids.add(id);
            if (roleMenuUpdate != null) {
                //更新菜单路由
                String roleMenuTableName = TableUtils.getRoleMenuTableName(menu.getModuleId());
                this.jdbcEngine.updateJavaBeanSelective(roleMenuUpdate, MySqlDynamicEngine.update(roleMenuTableName, AutRoleMenuModel.class)
                        .where((condition, mainTable) -> condition
                                .and(mainTable.moduleId().equalTo(menu.getModuleId())
                                        .menuId().inS(ids))));
            }
            if (!params.getDropSubModuleId().equals(menu.getSubModuleId())) {
                //删除菜单路由
                this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuRouteModel.class)
                        .where((condition, mainTable) -> condition
                                .and(mainTable.moduleId().equalTo(menu.getModuleId())
                                        .menuId().inS(ids))));
            }
        }
    }

}
