package pub.avalonframework.wechat.official.account.core.filter;

import pub.avalonframework.wechat.official.account.core.response.BaseMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 过滤器链
 *
 * @author baichao
 */
public class FilterChain {

    private List<Filter> filters = new ArrayList<>();

    /**
     * 过滤器链游标,必要时需要重置
     */
    private int index = 0;

    /**
     * 初始化过滤器链游标,让过滤器链从头开始
     */
    public void initIndex() {
        index = 0;
    }

    public FilterChain addFilter(Filter filter) {
        filters.add(filter);
        return this;
    }

    public BaseMessage doFilter(Map<String, String> requestMap, BaseMessage respBaseMessage) {
        if (index == filters.size()) {
            index = 0;
            return null;
        }
        Filter filter = filters.get(index);
        index++;
        return filter.doFilter(requestMap, respBaseMessage, this);
    }
}