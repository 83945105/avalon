package pub.avalonframework.web.spring.http.response.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pub.avalonframework.web.spring.http.response.ResultInfo;
import pub.avalonframework.web.spring.http.response.view.EntityView;
import pub.avalonframework.web.spring.http.response.view.LimitView;
import pub.avalonframework.web.spring.http.response.view.MessageView;
import pub.avalonframework.web.spring.http.response.view.ResponseView;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * The response utils.
 *
 * @author baichao
 */
public final class ResponseUtils {

    private ResponseUtils() {
    }

    private final static ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        // 忽略多余字段
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String toJsonString(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new ObjectToJsonException(e.getMessage(), e);
        }
    }

    public static <T> T jsonToObject(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new JsonToObjectException(e.getMessage(), e);
        }
    }

    public static <T> T jsonToObject(String json, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (IOException e) {
            throw new JsonToObjectException(e.getMessage(), e);
        }
    }

    private final static com.alibaba.fastjson.TypeReference<Map<String, String>> ROOT_TYPE_REFERENCE = new com.alibaba.fastjson.TypeReference<Map<String, String>>() {
    };

    private final static String RESULT_INFO_KEY = "resultInfo";

    private final static String LIMIT_KEY = "limit";

    private final static String ENTITY_KEY = "entity";

    public static ResponseView jsonToResponseView(String json, Type type, Type parameterizedType) {
        if (type instanceof ParameterizedType) {
            type = ((ParameterizedType) type).getRawType();
        }
        ResponseView responseView = instantiateClassToResponseView((Class) type);
        Map<String, String> rootElement = JSONObject.parseObject(json, ROOT_TYPE_REFERENCE);
        if (responseView instanceof MessageView) {
            ((MessageView) responseView).setResultInfo(rootElement.get(RESULT_INFO_KEY));
        }
        if (responseView instanceof LimitView) {
            ((LimitView) responseView).setLimit(rootElement.get(LIMIT_KEY));
        }
        if (responseView instanceof EntityView) {
            ((EntityView) responseView).setEntity(rootElement.get(ENTITY_KEY), parameterizedType);
        }
        return responseView;
    }

    private static ResponseView instantiateClassToResponseView(Class clazz) {
        try {
            Constructor constructor = clazz.getConstructors()[0];
            return (ResponseView) constructor.newInstance(new Object[constructor.getParameterCount()]);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ResponseViewInstantiatedException(e.getMessage(), e);
        }
    }

    public static ResultInfo jsonToResultInfo(String json) {
        return jsonToObject(json, ResultInfo.class);
    }
}