package pub.avalonframework.web.spring.http.response.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.avalonframework.web.spring.http.response.HttpResultInfo;
import pub.avalonframework.web.spring.http.response.api.UserApi;
import pub.avalonframework.web.spring.http.response.entity.User;
import pub.avalonframework.web.spring.http.response.view.impl.EntityMessageView;

/**
 * @author baichao
 */
@RequestMapping(UserApi.ROOT_PATH)
@RestController
public class UserController implements UserApi {

    @RequestMapping(value = "/get/user", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EntityMessageView<User> getUser(@RequestBody User user) throws Exception {
        return new EntityMessageView<>(user, new HttpResultInfo(HttpStatus.OK));
    }
}