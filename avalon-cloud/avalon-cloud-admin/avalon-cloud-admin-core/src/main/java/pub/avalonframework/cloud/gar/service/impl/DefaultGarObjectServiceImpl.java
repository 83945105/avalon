package pub.avalonframework.cloud.gar.service.impl;

import pub.avalon.beans.Limit;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalonframework.cloud.gar.beans.PageListResult;
import pub.avalonframework.cloud.gar.beans.Table;
import pub.avalonframework.cloud.gar.dc.AutObjectGet;
import pub.avalonframework.cloud.gar.entity.AutObject;
import pub.avalonframework.cloud.gar.service.GarObjectService;

/**
 * @author 白超
 */
public class DefaultGarObjectServiceImpl implements GarObjectService {

    @Override
    public Table getTableOptions() throws Exception {
        ExceptionUtil.throwFailException("若想使用该功能请实现ObjectService接口");
        return null;
    }

    @Override
    public AutObject getObjectByObjectId(String objectId) throws Exception {
        ExceptionUtil.throwFailException("若想使用该功能请实现ObjectService接口");
        return null;
    }

    @Override
    public PageListResult<Limit, AutObject> getPageListObject(AutObjectGet record, Integer currentPage, Integer pageSize) throws Exception {
        ExceptionUtil.throwFailException("若想使用该功能请实现ObjectService接口");
        return null;
    }

    @Override
    public void putObjectEnabledByObjectId(String objectId) throws Exception {
        ExceptionUtil.throwFailException("若想使用该功能请实现ObjectService接口");
    }

    @Override
    public void putObjectDisabledByObjectId(String objectId) throws Exception {
        ExceptionUtil.throwFailException("若想使用该功能请实现ObjectService接口");
    }

}
