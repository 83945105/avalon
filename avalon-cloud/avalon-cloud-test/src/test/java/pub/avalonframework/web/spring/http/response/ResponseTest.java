package pub.avalonframework.web.spring.http.response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import pub.avalonframework.web.spring.http.response.api.UserApi;
import pub.avalonframework.web.spring.http.response.controller.UserController;
import pub.avalonframework.web.spring.http.response.entity.User;
import pub.avalonframework.web.spring.http.response.view.impl.EntityMessageView;

/**
 * @author baichao
 */
@SpringBootTest(
        classes = {
                UserController.class
        },
        // 启动原生容器, 并使用自定义端口
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@EnableFeignClients(clients = {
        ResponseTest.TestUserApi.class
})
@EnableAutoConfiguration
public class ResponseTest {

    // 使用Spring cloud Feign 方式调用接口
    @Autowired
    private TestUserApi testUserApi;

    // 微服务使用 name 和 path 属性, 非微服务使用 value 和 url 属性
    @FeignClient(value = "user-service", url = "http://localhost:8080" + UserApi.ROOT_PATH)
    interface TestUserApi extends UserApi {
    }

    @Test
    void TestGetUser() throws Exception {
        User userDTO = new User();
        userDTO.setId("1");
        userDTO.setUsername("测试用户");
        userDTO.setAge(18);

        EntityMessageView<User> view = this.testUserApi.getUser(userDTO);
        ResultInfo resultInfo = view.getResultInfo();
        Assertions.assertNotNull(resultInfo);
        Assertions.assertTrue(resultInfo.isSuccess());
        Assertions.assertFalse(resultInfo.isFail());
        Assertions.assertFalse(resultInfo.isError());

        User user = view.getEntity();
        Assertions.assertNotNull(user);
        Assertions.assertNotEquals(userDTO, user);
        Assertions.assertEquals(userDTO.getId(), user.getId());
        Assertions.assertEquals(userDTO.getUsername(), user.getUsername());
        Assertions.assertEquals(userDTO.getAge(), user.getAge());
    }

    @Test
    void TestGetValidationBoolean() throws Exception {
        EntityMessageView<Boolean> view = this.testUserApi.getValidationBoolean();
        ResultInfo resultInfo = view.getResultInfo();
        Assertions.assertNotNull(resultInfo);
        Assertions.assertTrue(resultInfo.isSuccess());
        Assertions.assertFalse(resultInfo.isFail());
        Assertions.assertFalse(resultInfo.isError());
        Assertions.assertTrue(view.getEntity());
    }

    @Test
    void TestGetValidationString() throws Exception {
        EntityMessageView<String> view = testUserApi.getValidationString();
        ResultInfo resultInfo = view.getResultInfo();
        Assertions.assertNotNull(resultInfo);
        Assertions.assertTrue(resultInfo.isSuccess());
        Assertions.assertFalse(resultInfo.isFail());
        Assertions.assertFalse(resultInfo.isError());
        Assertions.assertEquals("OK", view.getEntity());
    }


    @Test
    void TestGetValidationInteger() throws Exception {
        EntityMessageView<Integer> view = testUserApi.getValidationInteger();
        ResultInfo resultInfo = view.getResultInfo();
        Assertions.assertNotNull(resultInfo);
        Assertions.assertTrue(resultInfo.isSuccess());
        Assertions.assertFalse(resultInfo.isFail());
        Assertions.assertFalse(resultInfo.isError());
        Assertions.assertEquals(1, view.getEntity());
    }


    @Test
    void TestGetValidationLong() throws Exception {
        EntityMessageView<Long> view = testUserApi.getValidationLong();
        ResultInfo resultInfo = view.getResultInfo();
        Assertions.assertNotNull(resultInfo);
        Assertions.assertTrue(resultInfo.isSuccess());
        Assertions.assertFalse(resultInfo.isFail());
        Assertions.assertFalse(resultInfo.isError());
        Assertions.assertEquals(1L, view.getEntity());
    }
}