package pub.avalonframework.cloud.gar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pub.avalon.beans.Time;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalon.holygrail.utils.StringUtil;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.beans.TemplateDragParams;
import pub.avalonframework.cloud.gar.dc.TemplateListRow;
import pub.avalonframework.cloud.gar.entity.Template;
import pub.avalonframework.cloud.gar.model.TemplateModel;
import pub.avalonframework.cloud.gar.service.GarTemplateRowDraggableService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 白超
 */
@Service
public class GarTemplateRowDraggableServiceImpl implements GarTemplateRowDraggableService {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Override
    public List<TemplateListRow> findRowsInSortIndex(TemplateListRow startRow, TemplateListRow endRow, TemplateDragParams params) {
        if (startRow == null) {
            ExceptionUtil.throwFailException("拖拽行不存在");
        }
        if (endRow == null) {
            ExceptionUtil.throwFailException("放置行不存在");
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
        return this.jdbcEngine.queryForList(TemplateListRow.class, MySqlDynamicEngine.query(TemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(params.getModuleId())
                                .index().greaterThan(startRow.getSortIndex())
                                .index().lessThan(endRow.getSortIndex()))));
    }

    @Override
    public void updateRowSortIndex(TemplateListRow row, long sortIndex, TemplateDragParams params) {
        if (StringUtil.isEmpty(row.getId())) {
            ExceptionUtil.throwFailException("行ID不能为空");
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
        Template templateUpdate = new Template();
        templateUpdate.setIndex(sortIndex);
        templateUpdate.setUpdateTime(Time.localDateTimeNow());
        templateUpdate.setUpdateTimeStamp(Time.timeStamp());
        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(row.getId(), templateUpdate, MySqlDynamicEngine.update(TemplateModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("更新模板排序失败");
        }
    }

    static final String minusRowsSortIndexSql = "update `" + TemplateModel.tableName + "` " + TemplateModel.tableAlias + " set " + TemplateModel.tableAlias + ".`" + TemplateModel.index + "` = " + TemplateModel.tableAlias + ".`" + TemplateModel.index + "` - ? where " + TemplateModel.tableAlias + ".`" + TemplateModel.moduleId + "` = ? and " + TemplateModel.tableAlias + ".`" + TemplateModel.primaryKeyName + "` in ";

    @Override
    public void minusRowsSortIndex(Collection<TemplateListRow> rows, long minusNum, TemplateDragParams params) {
        if (StringUtil.isEmpty(rows)) {
            ExceptionUtil.throwFailException("rows不能为空");
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
        for (TemplateListRow row : rows) {
            inSql.append(",?");
            args.add(row.getId());
        }
        inSql.replace(0, 1, "(").append(")");
        this.jdbcEngine.update(minusRowsSortIndexSql + inSql.toString(), args.toArray());
    }

    static final String plusRowsSortIndexSql = "update `" + TemplateModel.tableName + "` " + TemplateModel.tableAlias + " set " + TemplateModel.tableAlias + ".`" + TemplateModel.index + "` = " + TemplateModel.tableAlias + ".`" + TemplateModel.index + "` + ? where " + TemplateModel.tableAlias + ".`" + TemplateModel.moduleId + "` = ? and " + TemplateModel.tableAlias + ".`" + TemplateModel.primaryKeyName + "` in ";

    @Override
    public void plusRowsSortIndex(Collection<TemplateListRow> rows, long plusNum, TemplateDragParams params) {
        if (StringUtil.isEmpty(rows)) {
            ExceptionUtil.throwFailException("rows不能为空");
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
        for (TemplateListRow row : rows) {
            inSql.append(",?");
            args.add(row.getId());
        }
        inSql.replace(0, 1, "(").append(")");
        this.jdbcEngine.update(plusRowsSortIndexSql + inSql.toString(), args.toArray());
    }
}
