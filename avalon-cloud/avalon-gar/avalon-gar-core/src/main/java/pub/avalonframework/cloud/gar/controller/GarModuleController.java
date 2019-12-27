package pub.avalonframework.cloud.gar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pub.avalon.holygrail.response.utils.DataViewUtil;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalon.holygrail.utils.StringUtil;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.api.GarModuleApi;
import pub.avalonframework.cloud.gar.dc.ModuleGet;
import pub.avalonframework.cloud.gar.dc.ModulePost;
import pub.avalonframework.cloud.gar.dc.ModulePut;
import pub.avalonframework.cloud.gar.entity.Module;
import pub.avalonframework.cloud.gar.model.ModuleModel;
import pub.avalonframework.cloud.gar.service.GarModuleService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 模块接口
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/11
 */
@RestController
@RequestMapping(GarModuleApi.ROOT_PATH)
public class GarModuleController implements GarModuleApi {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarModuleService moduleService;

    @Autowired
    private HttpServletRequest request;

    @Override
    @RequestMapping(value = "/get/moduleByModuleId/{moduleId}")
    public DataView getModuleByModuleId(@PathVariable String moduleId) throws Exception {
        Module module = this.jdbcEngine.queryByPrimaryKey(moduleId, Module.class, MySqlDynamicEngine.query(ModuleModel.class));
        if (module == null) {
            ExceptionUtil.throwFailException(40404, "模块不存在");
        }
        return DataViewUtil.getModelViewSuccess(module);
    }

    @Override
    @RequestMapping(value = "/get/validateModuleIdCanUse/{moduleId}")
    public DataView getValidateModuleIdCanUse(@PathVariable String moduleId, String excludeModuleIds) throws Exception {
        boolean bl = this.moduleService.getValidateModuleIdCanUse(moduleId,
                StringUtil.isEmpty(excludeModuleIds) ? null : new HashSet<>(Arrays.asList(excludeModuleIds.split(","))));
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("canUse", bl));
    }

    @Override
    @RequestMapping(value = "/get/validatePathCanUse/{path}")
    public DataView getValidatePathCanUse(@PathVariable String path, String excludePaths) throws Exception {
        boolean bl = this.moduleService.getValidatePathCanUse(path,
                StringUtil.isEmpty(excludePaths) ? null : new HashSet<>(Arrays.asList(excludePaths.split(","))));
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("canUse", bl));
    }

    @Override
    @RequestMapping(value = "/get/validateServiceIdCanUse/{serviceId}")
    public DataView getValidateServiceIdCanUse(@PathVariable String serviceId, String excludeServiceIds) throws Exception {
        boolean bl = this.moduleService.getValidateServiceIdCanUse(serviceId,
                StringUtil.isEmpty(excludeServiceIds) ? null : new HashSet<>(Arrays.asList(excludeServiceIds.split(","))));
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("canUse", bl));
    }

    @Override
    @RequestMapping(value = "/get/list/module")
    public DataView getListModule(ModuleGet record) throws Exception {
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(ModuleModel.class)
                .sort(table -> table.createTimeStamp().asc()));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/post/module", method = RequestMethod.POST)
    public DataView postModule(ModulePost record) throws Exception {
        this.moduleService.postModule(record);
        return DataViewUtil.getMessageViewSuccess("新建模块成功");
    }

    @Override
    @RequestMapping(value = "/put/moduleByModuleId/{moduleId}")
    public DataView putModuleByModuleId(@PathVariable String moduleId, ModulePut record) throws Exception {
        this.moduleService.putModuleByModuleId(moduleId, record);
        return DataViewUtil.getMessageViewSuccess("修改模块成功");
    }

    @Override
    @RequestMapping(value = "/delete/moduleByModuleId/{moduleId}")
    public DataView deleteModuleByModuleId(@PathVariable String moduleId) throws Exception {
        this.moduleService.deleteModuleByModuleId(moduleId);
        return DataViewUtil.getMessageViewSuccess("删除模块成功");
    }

}
