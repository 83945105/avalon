package pub.avalonframework.cloud.gar.service;

import pub.avalon.beans.Limit;
import pub.avalonframework.cloud.gar.beans.PageListResult;
import pub.avalonframework.cloud.gar.beans.Table;
import pub.avalonframework.cloud.gar.dc.AutObjectGet;
import pub.avalonframework.cloud.gar.entity.AutObject;

/**
 * @author 白超
 * @date 2018/11/14
 */
public interface GarObjectService {

    /**
     * 获取表格配置
     *
     * @return
     * @throws Exception
     */
    Table getTableOptions() throws Exception;

    /**
     * 根据对象ID查询对象
     *
     * @param objectId
     * @return
     * @throws Exception
     */
    AutObject getObjectByObjectId(String objectId) throws Exception;

    /**
     * 分页条件查询对象集合
     *
     * @param record
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageListResult<Limit, AutObject> getPageListObject(AutObjectGet record, Integer currentPage, Integer pageSize) throws Exception;

    /**
     * 启用对象
     *
     * @param objectId
     * @throws Exception
     */
    void putObjectEnabledByObjectId(String objectId) throws Exception;

    /**
     * 禁用对象
     *
     * @param objectId
     * @throws Exception
     */
    void putObjectDisabledByObjectId(String objectId) throws Exception;
}