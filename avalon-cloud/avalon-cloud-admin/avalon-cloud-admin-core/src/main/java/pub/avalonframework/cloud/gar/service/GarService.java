package pub.avalonframework.cloud.gar.service;

import java.util.Map;

/**
 * @author 白超
 * @date 2018/11/26
 */
public interface GarService {

    /**
     * 判断是否在线
     *
     * @return
     * @throws Exception
     */
    boolean getIsOnline() throws Exception;

    /**
     * 获取在线信息
     *
     * @return
     * @throws Exception
     */
    Map<String, Object> getOnline() throws Exception;

    /**
     * 退出系统
     *
     * @return
     * @throws Exception
     */
    Map<String, Object> getLogout() throws Exception;
}