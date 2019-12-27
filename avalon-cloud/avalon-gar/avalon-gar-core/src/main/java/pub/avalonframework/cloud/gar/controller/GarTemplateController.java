package pub.avalonframework.cloud.gar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.avalon.holygrail.function.beans.DropType;
import pub.avalon.holygrail.response.utils.DataViewUtil;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalon.holygrail.utils.StringUtil;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.spring.beans.PageResultForMap;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.api.GarTemplateApi;
import pub.avalonframework.cloud.gar.beans.TemplateDragParams;
import pub.avalonframework.cloud.gar.dc.TemplateGet;
import pub.avalonframework.cloud.gar.dc.TemplateListRow;
import pub.avalonframework.cloud.gar.dc.TemplatePost;
import pub.avalonframework.cloud.gar.dc.TemplatePut;
import pub.avalonframework.cloud.gar.model.TemplateModel;
import pub.avalonframework.cloud.gar.service.GarTemplateRowDraggableService;
import pub.avalonframework.cloud.gar.service.GarTemplateService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author 白超
 * @date 2018/12/6
 */
@RequestMapping(GarTemplateApi.ROOT_PATH)
@RestController
public class GarTemplateController implements GarTemplateApi {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarTemplateService templateService;

    @Autowired
    private GarTemplateRowDraggableService templateRowDraggableService;

    @Autowired
    private HttpServletRequest request;

    @Override
    @RequestMapping(value = "/get/validateValueCanUseBySubModuleId/{value}/{subModuleId}")
    public DataView getValidateValueCanUseBySubModuleId(@PathVariable String value, @PathVariable String subModuleId, String excludeValues, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        boolean canUse = this.templateService.getValidateValueCanUse(moduleId, subModuleId, value, StringUtil.isEmpty(excludeValues) ? null : Arrays.asList(excludeValues.split(",")));
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("canUse", canUse));
    }

    @Override
    @RequestMapping(value = "/get/templateByTemplateId/{templateId}")
    public DataView getTemplateByTemplateId(@PathVariable String templateId, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        Map<String, Object> template = this.jdbcEngine.queryOne(MySqlDynamicEngine.query(TemplateModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(finalModuleId).id().equalTo(templateId))));
        if (template == null) {
            ExceptionUtil.throwFailException(40404, "模板不存在");
        }
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("template", template));
    }

    @Override
    @RequestMapping(value = "/get/list/template")
    public DataView getListTemplate(TemplateGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(TemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd.and(mt.name().like(record.getLikeText()))
                                .or(mt.value().like(record.getLikeText())))
                        .and(mainTable.moduleId().equalTo(finalModuleId)
                                .subModuleId().inS(record.getSubModuleIdSet())
                                .id().notInS(record.getNotInIdSet())
                                .type().equalTo(record.getType())))
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/pageList/template")
    public DataView getPageListTemplate(TemplateGet record, Integer currentPage, Integer pageSize, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        currentPage = (currentPage == null || currentPage < 1) ? 1 : currentPage;
        pageSize = (pageSize == null || pageSize < 1 || pageSize > 500) ? 1 : pageSize;
        PageResultForMap pageResultForMap = this.jdbcEngine.pageQueryList(currentPage, pageSize, MySqlDynamicEngine.query(TemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd.and(mt.name().like(record.getLikeText()))
                                .or(mt.value().like(record.getLikeText())))
                        .and(mainTable.moduleId().equalTo(finalModuleId)
                                .subModuleId().inS(record.getSubModuleIdSet())
                                .id().notInS(record.getNotInIdSet())
                                .type().equalTo(record.getType())))
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(pageResultForMap.getResult(), pageResultForMap.getLimit());
    }

    @Override
    @RequestMapping(value = "/post/template")
    public DataView postTemplate(TemplatePost record, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        TemplateGet template = this.templateService.postTemplate(moduleId, record);
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("template", template));
    }

    @Override
    @RequestMapping(value = "/put/templateByTemplateId/{templateId}")
    public DataView putTemplateByTemplateId(@PathVariable String templateId, TemplatePut record, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.templateService.putTemplateByTemplateId(moduleId, templateId, record);
        return DataViewUtil.getMessageViewSuccess("修改模板成功");
    }

    @Override
    @RequestMapping(value = "/put/switchTemplateIndexByTemplateId/{sourceTemplateId}/{targetTemplateId}")
    public DataView putSwitchTemplateIndexByTemplateId(@PathVariable String sourceTemplateId, @PathVariable String targetTemplateId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.templateService.putSwitchTemplateIndexByTemplateId(moduleId, sourceTemplateId, targetTemplateId);
        return DataViewUtil.getMessageViewSuccess("操作成功");
    }

    @Override
    @RequestMapping(value = "/delete/templateByTemplateId/{templateId}")
    public DataView deleteTemplateByTemplateId(@PathVariable String templateId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.templateService.deleteTemplateByTemplateId(moduleId, templateId);
        return DataViewUtil.getMessageViewSuccess("删除模板成功");
    }

    @Override
    @RequestMapping(value = "/delete/list/templateByTemplateIds/{templateIds}")
    public DataView deleteListTemplateByTemplateIds(@PathVariable String templateIds, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.templateService.deleteListTemplateByTemplateIds(moduleId, Arrays.asList(templateIds.split(",")));
        return DataViewUtil.getMessageViewSuccess("删除模板成功");
    }

    @Override
    @RequestMapping(value = "/put/dragTemplateListRow/{dragTemplateId}/{dropTemplateId}/{dropType}")
    public DataView putDragTemplateListRow(@PathVariable String dragTemplateId, @PathVariable String dropTemplateId, @PathVariable String dropType, TemplateDragParams record, String moduleId) throws Exception {
        if (StringUtil.isEmpty(record.getDragSubModuleId())) {
            ExceptionUtil.throwFailException("拖拽节点所属子模块ID不能为空");
        }
        if (StringUtil.isEmpty(record.getDropSubModuleId())) {
            ExceptionUtil.throwFailException("放置节点所属子模块ID不能为空");
        }
        moduleId = TableUtils.getModuleId(moduleId, request);
        record.setModuleId(moduleId);
        DropType dt = null;
        switch (dropType) {
            case "before":
                dt = DropType.BEFORE;
                break;
            case "after":
                dt = DropType.AFTER;
                break;
            default:
                ExceptionUtil.throwFailException("放置类型不正确");
        }
        TemplateListRow dragRow = this.jdbcEngine.queryByPrimaryKey(dragTemplateId, TemplateListRow.class, MySqlDynamicEngine.query(TemplateModel.class));
        if (dragRow == null) {
            ExceptionUtil.throwFailException("拖拽模板不存在");
        }
        TemplateListRow dropRow = this.jdbcEngine.queryByPrimaryKey(dropTemplateId, TemplateListRow.class, MySqlDynamicEngine.query(TemplateModel.class));
        if (dropRow == null) {
            ExceptionUtil.throwFailException("放置模板不存在");
        }
        this.templateRowDraggableService.draggableRow(dragRow, dropRow, dt, record);
        return DataViewUtil.getMessageViewSuccess("拖拽成功");
    }

}
