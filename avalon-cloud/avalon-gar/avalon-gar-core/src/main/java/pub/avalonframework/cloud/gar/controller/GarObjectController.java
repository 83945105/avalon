package pub.avalonframework.cloud.gar.controller;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.avalon.beans.Limit;
import pub.avalon.holygrail.response.utils.DataViewUtil;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalonframework.cloud.gar.api.GarObjectApi;
import pub.avalonframework.cloud.gar.beans.PageListResult;
import pub.avalonframework.cloud.gar.beans.Table;
import pub.avalonframework.cloud.gar.dc.AutObjectGet;
import pub.avalonframework.cloud.gar.entity.AutObject;
import pub.avalonframework.cloud.gar.service.GarObjectService;
import pub.avalonframework.cloud.gar.service.impl.DefaultGarObjectServiceImpl;

import java.util.Collections;

/**
 * @author 白超
 * @date 2018/7/24
 */
@RestController
@RequestMapping(GarObjectApi.ROOT_PATH)
public class GarObjectController implements GarObjectApi, InitializingBean {

    @Autowired(required = false)
    private GarObjectService objectService;

    @Override
    @RequestMapping(value = "/get/tableOptions")
    public DataView getTableOptions() throws Exception {
        Table table = this.objectService.getTableOptions();
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("table", table));
    }

    @Override
    @RequestMapping(value = "/get/objectByObjectId/{objectId}")
    public DataView getObjectByObjectId(@PathVariable String objectId) throws Exception {
        AutObject row = this.objectService.getObjectByObjectId(objectId);
        if (row == null) {
            ExceptionUtil.throwFailException(40404, "对象不存在");
        }
        return DataViewUtil.getModelViewSuccess(row);
    }

    @Override
    @RequestMapping(value = "/get/pageList/object")
    public DataView getPageListObject(AutObjectGet record, Integer currentPage, Integer pageSize) throws Exception {
        PageListResult<Limit, AutObject> pageListResult = this.objectService.getPageListObject(record, currentPage, pageSize);
        return DataViewUtil.getModelViewSuccess(pageListResult.getRows(), pageListResult.getLimit());
    }

    @Override
    @RequestMapping(value = "/put/objectEnabledByObjectId/{objectId}")
    public DataView putObjectEnabledByObjectId(@PathVariable String objectId) throws Exception {
        this.objectService.putObjectEnabledByObjectId(objectId);
        return DataViewUtil.getMessageViewSuccess("启用成功");
    }

    @Override
    @RequestMapping(value = "/put/objectDisabledByObjectId/{objectId}")
    public DataView putObjectDisabledByObjectId(@PathVariable String objectId) throws Exception {
        this.objectService.putObjectDisabledByObjectId(objectId);
        return DataViewUtil.getMessageViewSuccess("禁用成功");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.objectService == null) {
            this.objectService = new DefaultGarObjectServiceImpl();
        }
    }
}
