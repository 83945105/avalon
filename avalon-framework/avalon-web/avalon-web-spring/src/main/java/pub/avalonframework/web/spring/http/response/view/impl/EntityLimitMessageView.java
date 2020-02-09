package pub.avalonframework.web.spring.http.response.view.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import pub.avalonframework.database.Limit;
import pub.avalonframework.web.spring.http.response.ResultInfo;
import pub.avalonframework.web.spring.http.response.utils.ResponseUtils;
import pub.avalonframework.web.spring.http.response.view.EntityView;
import pub.avalonframework.web.spring.http.response.view.LimitView;
import pub.avalonframework.web.spring.http.response.view.MessageView;

import java.lang.reflect.Type;

/**
 * @author baichao
 */
public final class EntityLimitMessageView<T> implements EntityView<T>, LimitView, MessageView {

    private T entity;

    private Limit limit;

    private ResultInfo resultInfo;

    public EntityLimitMessageView(T entity, Limit limit, ResultInfo resultInfo) {
        this.entity = entity;
        this.limit = limit;
        this.resultInfo = resultInfo;
    }

    @Override
    public T getEntity() {
        return entity;
    }

    @Override
    public void setEntity(T entity) {
        this.entity = entity;
    }

    @Override
    public void setEntity(String entityJson, Type type) {
        this.setEntity(ResponseUtils.jsonToObject(entityJson, new TypeReference<T>() {
            @Override
            public Type getType() {
                return type;
            }
        }));
    }

    @Override
    public Limit getLimit() {
        return limit;
    }

    @Override
    public void setLimit(Limit limit) {
        this.limit = limit;
    }

    @Override
    public void setLimit(String limitJson) {
        this.limit = ResponseUtils.jsonToLimit(limitJson);
    }

    @Override
    public ResultInfo getResultInfo() {
        return resultInfo;
    }

    @Override
    public void setResultInfo(ResultInfo resultInfo) {
        this.resultInfo = resultInfo;
    }

    @Override
    public void setResultInfo(String resultInfoJson) {
        this.resultInfo = ResponseUtils.jsonToResultInfo(resultInfoJson);
    }
}