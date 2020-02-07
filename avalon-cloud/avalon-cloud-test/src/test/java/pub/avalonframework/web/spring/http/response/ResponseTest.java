package pub.avalonframework.web.spring.http.response;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.test.context.junit4.SpringRunner;
import pub.avalonframework.web.spring.http.response.api.UserApi;
import pub.avalonframework.web.spring.http.response.controller.UserController;
import pub.avalonframework.web.spring.http.response.entity.User;
import pub.avalonframework.web.spring.http.response.view.impl.EntityMessageView;

/**
 * @author baichao
 */
@RunWith(SpringRunner.class)
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
    public void TestGetUser() throws Exception {
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
}