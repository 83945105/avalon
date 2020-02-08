package pub.avalonframework.web.spring.http.response.feign.codec;

import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import pub.avalonframework.web.spring.http.response.utils.ResponseUtils;
import pub.avalonframework.web.spring.http.response.view.ResponseView;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * The feign decoder to response view.
 *
 * @author baichao
 */
public class ResponseViewDecoder implements Decoder {

    private Decoder decoder;

    public ResponseViewDecoder(Decoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public Object decode(Response response, Type type) throws FeignException, IOException {
        if (isParameterizeResponseView(type)) {
            return createResponse(response, type, ((ParameterizedType) type).getActualTypeArguments()[0]);
        }
        if (isResponseView(type)) {
            return createResponse(response, type, null);
        }
        return decoder.decode(response, type);
    }

    private boolean isParameterizeResponseView(Type type) {
        if (type instanceof ParameterizedType) {
            return isResponseView(((ParameterizedType) type).getRawType());
        }
        return false;
    }

    private boolean isResponseView(Type type) {
        if (type instanceof Class) {
            Class c = (Class) type;
            return ResponseView.class.isAssignableFrom(c);
        }
        return false;
    }

    private ResponseView createResponse(Response response, Type type, Type parameterizedType) {
        return ResponseUtils.jsonToResponseView(read(response), type, parameterizedType);
    }

    private String read(Response response) {
        Response.Body body = response.body();
        try (BufferedReader reader = new BufferedReader(body.asReader())) {
            StringBuilder sb = new StringBuilder(128);
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            throw new ReadFeignResponseException(e.getMessage(), e);
        } finally {
            response.close();
        }
    }
}