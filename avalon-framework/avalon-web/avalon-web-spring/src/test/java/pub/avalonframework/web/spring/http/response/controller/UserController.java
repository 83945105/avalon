package pub.avalonframework.web.spring.http.response.controller;

import org.springframework.http.HttpStatus;
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

    public EntityMessageView<User> getUser(User user) throws Exception {
        return new EntityMessageView<>(user, new HttpResultInfo(HttpStatus.OK));
    }

    @Override
    public EntityMessageView<Boolean> getValidationBoolean() throws Exception {
        return new EntityMessageView<>(true, new HttpResultInfo(HttpStatus.OK));
    }

    @Override
    public EntityMessageView<String> getValidationString() throws Exception {
        return new EntityMessageView<>("OK", new HttpResultInfo(HttpStatus.OK));
    }

    @Override
    public EntityMessageView<Integer> getValidationInteger() throws Exception {
        return new EntityMessageView<>(1, new HttpResultInfo(HttpStatus.OK));
    }

    @Override
    public EntityMessageView<Long> getValidationLong() throws Exception {
        return new EntityMessageView<>(1L, new HttpResultInfo(HttpStatus.OK));
    }
}