package pub.avalonframework.cloud.gar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.avalon.holygrail.response.utils.DataViewUtil;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalon.holygrail.utils.StringUtil;
import pub.avalon.sqlhelper.core.beans.FunctionColumnType;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.spring.beans.PageResultForMap;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.api.GarResourceApi;
import pub.avalonframework.cloud.gar.dc.AutResourceGet;
import pub.avalonframework.cloud.gar.dc.AutResourcePost;
import pub.avalonframework.cloud.gar.dc.AutResourcePut;
import pub.avalonframework.cloud.gar.model.AutResourceModel;
import pub.avalonframework.cloud.gar.service.GarResourceService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资源控制器
 *
 * @author 白超
 * @date 2018/6/13
 */
@RestController
@RequestMapping(GarResourceApi.ROOT_PATH)
public class GarResourceController implements GarResourceApi {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarResourceService resourceService;

    @Autowired
    private HttpServletRequest request;

    @Override
    @RequestMapping(value = "/get/list/resource")
    public DataView getListResource(AutResourceGet record, String moduleId) throws Exception {
        String resourceTableName = TableUtils.getResourceTableName(moduleId, request);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(resourceTableName, AutResourceModel.class)
                .leftJoin(resourceTableName, AutResourceModel.class, "JurResLeft", (on, joinTable, mainTable) -> on
                        .and(joinTable.parentId().equalTo(mainTable.id())
                                .type().inS(record.getChildTypeSet())))
                .leftJoin(resourceTableName, AutResourceModel.class, "JurResParent", (on, joinTable, mainTable) -> on
                        .and(joinTable.id().equalTo(mainTable.parentId())))
                .functionColumn(AutResourceModel.class, "JurResLeft", FunctionColumnType.COUNT, table -> table.id("childCount"))
                .column(AutResourceModel.class, "JurResParent", table -> table.name("parentResourceName"))
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.parentId().inS(record.getParentIdSet()))
                        .and(mainTable.name().like(record.getLikeText()))
                        .and(mainTable.type().inS(record.getTypeSet())))
                .group(AutResourceModel.Group::id)
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/pageList/resource")
    public DataView getPageListResource(AutResourceGet record, Integer currentPage, Integer pageSize, String moduleId) throws Exception {
        String resourceTableName = TableUtils.getResourceTableName(moduleId, request);
        currentPage = (currentPage == null || currentPage < 1) ? 1 : currentPage;
        pageSize = (pageSize == null || pageSize < 1 || pageSize > 500) ? 1 : pageSize;
        PageResultForMap pageResult = this.jdbcEngine.pageQueryList(currentPage, pageSize, MySqlDynamicEngine.query(resourceTableName, AutResourceModel.class)
                .leftJoin(resourceTableName, AutResourceModel.class, "JurResLeft", (on, joinTable, mainTable) -> on
                        .and(joinTable.parentId().equalTo(mainTable.id())
                                .type().inS(record.getChildTypeSet())))
                .leftJoin(resourceTableName, AutResourceModel.class, "JurResParent", (on, joinTable, mainTable) -> on
                        .and(joinTable.id().equalTo(mainTable.parentId())))
                .functionColumn(AutResourceModel.class, "JurResLeft", FunctionColumnType.COUNT, table -> table.id("childCount"))
                .column(AutResourceModel.class, "JurResParent", table -> table.name("parentResourceName"))
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.parentId().inS(record.getParentIdSet()))
                        .and(mainTable.name().like(record.getLikeText()))
                        .and(mainTable.type().inS(record.getTypeSet())))
                .group(AutResourceModel.Group::id)
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(pageResult.getResult(), pageResult.getLimit());
    }

    @Override
    @RequestMapping(value = "/get/list/rootResource")
    public DataView getListRootResource(AutResourceGet record, String moduleId) throws Exception {
        String resourceTableName = TableUtils.getResourceTableName(moduleId, request);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(resourceTableName, AutResourceModel.class)
                .leftJoin(resourceTableName, AutResourceModel.class, "JurResLeft", (on, joinTable, mainTable) -> on
                        .and(joinTable.parentId().equalTo(mainTable.id())
                                .type().inS(record.getChildTypeSet())))
                .functionColumn(AutResourceModel.class, "JurResLeft", FunctionColumnType.COUNT, table -> table.id("childCount"))
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.name().like(record.getLikeText()))
                        .and(mainTable.type().inS(record.getTypeSet())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.parentId().isNull())
                        .or(mainTable.parentId().equalTo("")))
                .group(AutResourceModel.Group::id)
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/pageList/rootResource")
    public DataView getPageListRootResource(AutResourceGet record, Integer currentPage, Integer pageSize, String moduleId) throws Exception {
        String resourceTableName = TableUtils.getResourceTableName(moduleId, request);
        currentPage = (currentPage == null || currentPage < 1) ? 1 : currentPage;
        pageSize = (pageSize == null || pageSize < 1 || pageSize > 500) ? 1 : pageSize;
        PageResultForMap pageResult = this.jdbcEngine.pageQueryList(currentPage, pageSize, MySqlDynamicEngine.query(resourceTableName, AutResourceModel.class)
                .leftJoin(resourceTableName, AutResourceModel.class, "JurResLeft", (on, joinTable, mainTable) -> on
                        .and(joinTable.parentId().equalTo(mainTable.id())
                                .type().inS(record.getChildTypeSet())))
                .functionColumn(AutResourceModel.class, "JurResLeft", FunctionColumnType.COUNT, table -> table.id("childCount"))
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.name().like(record.getLikeText()))
                        .and(mainTable.type().inS(record.getTypeSet())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.parentId().isNull())
                        .or(mainTable.parentId().equalTo("")))
                .group(AutResourceModel.Group::id)
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(pageResult.getResult(), pageResult.getLimit());
    }

    @Override
    @RequestMapping(value = "/get/list/resourceByParentId/{parentId}")
    public DataView getListResourceByParentId(@PathVariable String parentId, AutResourceGet record, String moduleId) throws Exception {
        String resourceTableName = TableUtils.getResourceTableName(moduleId, request);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(resourceTableName, AutResourceModel.class)
                .leftJoin(resourceTableName, AutResourceModel.class, "JurResLeft", (on, joinTable, mainTable) -> on
                        .and(joinTable.parentId().equalTo(mainTable.id())
                                .type().inS(record.getChildTypeSet())))
                .leftJoin(resourceTableName, AutResourceModel.class, "JurResParent", (on, joinTable, mainTable) -> on
                        .and(joinTable.id().equalTo(mainTable.parentId())))
                .functionColumn(AutResourceModel.class, "JurResLeft", FunctionColumnType.COUNT, table -> table.id("childCount"))
                .column(AutResourceModel.class, "JurResParent", table -> table.name("parentResourceName"))
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.parentId().equalTo(parentId))
                        .and(mainTable.name().like(record.getLikeText()))
                        .and(mainTable.type().inS(record.getTypeSet())))
                .group(AutResourceModel.Group::id)
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/list/resourceIdAndNameByResourceIds/{resourceIds}")
    public DataView getListResourceIdAndNameByResourceIds(@PathVariable String resourceIds, String moduleId) throws Exception {
        if (StringUtil.isEmpty(resourceIds)) {
            return DataViewUtil.getModelViewSuccess(new HashMap<>(0));
        }
        String resourceTableName = TableUtils.getResourceTableName(moduleId, request);
        Map<String, String> results = this.jdbcEngine.queryPairColumnInMap(AutResourceModel.id_alias, AutResourceModel.name_alias, MySqlDynamicEngine.query(resourceTableName, AutResourceModel.class)
                .column(table -> table.id().name())
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().inS(Arrays.asList(resourceIds.split(","))))));
        return DataViewUtil.getModelViewSuccess(results);
    }

    @Override
    @RequestMapping(value = "/post/resource")
    public DataView postResource(AutResourcePost record, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        List<AutResourceGet> rows = this.resourceService.postResource(moduleId, record);
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/put/resourceByResourceId/{resourceId}")
    public DataView putResourceByResourceId(@PathVariable String resourceId, AutResourcePut record, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.resourceService.putResourceByResourceId(moduleId, resourceId, record);
        return DataViewUtil.getMessageViewSuccess("修改资源成功");
    }

    @Override
    @RequestMapping(value = "/delete/resourceByResourceId/{resourceId}")
    public DataView deleteResourceByResourceId(@PathVariable String resourceId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.resourceService.deleteResourceByResourceId(moduleId, resourceId);
        return DataViewUtil.getMessageViewSuccess("删除资源成功");
    }

    @Override
    @RequestMapping(value = "/delete/list/resourceByResourceIds/{resourceIds}")
    public DataView deleteListResourceByResourceIds(@PathVariable String resourceIds, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.resourceService.deleteListResourceByResourceIds(moduleId, Arrays.asList(resourceIds.split(",")));
        return DataViewUtil.getMessageViewSuccess("删除资源成功");
    }

}
