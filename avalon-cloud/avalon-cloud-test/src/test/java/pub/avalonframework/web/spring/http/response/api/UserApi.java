package pub.avalonframework.web.spring.http.response.api;

import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import pub.avalonframework.web.spring.http.response.entity.User;
import pub.avalonframework.web.spring.http.response.view.impl.EntityMessageView;

/**
 * @author baichao
 */
// 微服务使用 name 和 path 属性, 非微服务使用 value 和 url 属性
@FeignClient(name = "user-service", path = UserApi.ROOT_PATH)
public interface UserApi {

    String ROOT_PATH = "/hundda/test/user";

    @RequestMapping(value = "/get/user", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestLine("POST " + ROOT_PATH + "/get/user")
    @Headers("Content-Type: " + MediaType.APPLICATION_JSON_UTF8_VALUE)
    EntityMessageView<User> getUser(User user) throws Exception;
}