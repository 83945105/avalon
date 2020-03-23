package pub.avalonframework.web.spring.http.response.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pub.avalonframework.database.Limit;
import pub.avalonframework.web.spring.http.response.ResponseType;
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

    @SuppressWarnings("unchecked")
    public static <T> T jsonToObject(String json, Class<T> clazz) {
        if (clazz == String.class) {
            return (T) json;
        }
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new JsonToObjectException(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T jsonToObject(String json, TypeReference<T> typeReference) {
        if (typeReference.getType() == String.class) {
            return (T) json;
        }
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
        ResponseView responseView = instantiateClassToResponseView((Class<?>) type);
        Map<String, String> rootElement = JSONObject.parseObject(json, ROOT_TYPE_REFERENCE);
        if (responseView instanceof MessageView) {
            ((MessageView) responseView).setResultInfo(rootElement.get(RESULT_INFO_KEY));
        }
        if (responseView instanceof LimitView) {
            ((LimitView) responseView).setLimit(rootElement.get(LIMIT_KEY));
        }
        if (responseView instanceof EntityView) {
            ((EntityView<?>) responseView).setEntity(rootElement.get(ENTITY_KEY), parameterizedType);
        }
        return responseView;
    }

    private static ResponseView instantiateClassToResponseView(Class<?> clazz) {
        try {
            Constructor<?> constructor = clazz.getConstructors()[0];
            return (ResponseView) constructor.newInstance(new Object[constructor.getParameterCount()]);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ResponseViewInstantiatedException(e.getMessage(), e);
        }
    }

    public static Limit jsonToLimit(String json) {
        return jsonToObject(json, ResponseLimit.class);
    }

    private static final class ResponseLimit implements Limit {

        private Long total;

        private Long currentPage;

        private Long pageSize;

        private Long pageCount;

        private Long limit;

        private Long offset;

        @Override
        public Long getTotal() {
            return total;
        }

        @Override
        public void setTotal(Long total) {
            this.total = total;
        }

        @Override
        public Long getCurrentPage() {
            return currentPage;
        }

        @Override
        public void setCurrentPage(Long currentPage) {
            this.currentPage = currentPage;
        }

        @Override
        public Long getPageSize() {
            return pageSize;
        }

        @Override
        public void setPageSize(Long pageSize) {
            this.pageSize = pageSize;
        }

        @Override
        public Long getPageCount() {
            return pageCount;
        }

        public void setPageCount(Long pageCount) {
            this.pageCount = pageCount;
        }

        @Override
        public Long getLimit() {
            return limit;
        }

        public void setLimit(Long limit) {
            this.limit = limit;
        }

        @Override
        public Long getOffset() {
            return offset;
        }

        public void setOffset(Long offset) {
            this.offset = offset;
        }
    }

    public static ResultInfo jsonToResultInfo(String json) {
        return jsonToObject(json, ResponseResultInfo.class);
    }

    private static final class ResponseResultInfo implements ResultInfo {

        private int code;

        private String message;

        private boolean success;

        private boolean fail;

        private boolean error;

        private boolean proxyAuthenticationRequired;

        private boolean unauthorized;

        private ResponseType responseType;

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public void setCode(int code) {
            this.code = code;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        @Override
        public boolean isFail() {
            return fail;
        }

        public void setFail(boolean fail) {
            this.fail = fail;
        }

        @Override
        public boolean isError() {
            return error;
        }

        public void setError(boolean error) {
            this.error = error;
        }

        @Override
        public boolean isProxyAuthenticationRequired() {
            return proxyAuthenticationRequired;
        }

        public void setProxyAuthenticationRequired(boolean proxyAuthenticationRequired) {
            this.proxyAuthenticationRequired = proxyAuthenticationRequired;
        }

        @Override
        public boolean isUnauthorized() {
            return unauthorized;
        }

        public void setUnauthorized(boolean unauthorized) {
            this.unauthorized = unauthorized;
        }

        @Override
        public ResponseType getResponseType() {
            return responseType;
        }

        public void setResponseType(ResponseType responseType) {
            this.responseType = responseType;
        }
    }
}