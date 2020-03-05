package pub.avalonframework.sqlhelper.jdbc.core;

import pub.avalonframework.database.Limit;

import java.util.List;

/**
 * @author baichao
 */
public final class PageResult<T> {

    private List<T> entity;

    private Limit limit;

    public PageResult(List<T> entity, Limit limit) {
        this.entity = entity;
        this.limit = limit;
    }

    public List<T> getEntity() {
        return entity;
    }

    public Limit getLimit() {
        return limit;
    }
}