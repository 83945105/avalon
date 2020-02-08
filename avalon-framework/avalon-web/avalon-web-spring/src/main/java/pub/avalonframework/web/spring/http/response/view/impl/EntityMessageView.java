package pub.avalonframework.web.spring.http.response.view.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import pub.avalonframework.web.spring.http.response.ResultInfo;
import pub.avalonframework.web.spring.http.response.utils.ResponseUtils;
import pub.avalonframework.web.spring.http.response.view.EntityView;
import pub.avalonframework.web.spring.http.response.view.MessageView;

import java.lang.reflect.Type;

/**
 * The entity message view.
 *
 * @author baichao
 */
public class EntityMessageView<T> implements EntityView<T>, MessageView {

    private T entity;

    private ResultInfo resultInfo;

    public EntityMessageView(T entity, ResultInfo resultInfo) {
        this.entity = entity;
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