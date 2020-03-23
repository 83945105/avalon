package pub.avalonframework.web.spring.http.response;

import feign.Feign;
import feign.Retryer;
import feign.jackson.JacksonEncoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import pub.avalonframework.web.spring.http.response.api.UserApi;
import pub.avalonframework.web.spring.http.response.controller.UserController;
import pub.avalonframework.web.spring.http.response.entity.User;
import pub.avalonframework.web.spring.http.response.feign.codec.ResponseViewDecoder;
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
@EnableAutoConfiguration
public class ResponseTest {

    // 使用原生Feign方式调用接口
    private static UserApi userApiByFeign;

    @BeforeAll
    public static void before() {
        userApiByFeign = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new ResponseViewDecoder(null))
                .retryer(new Retryer.Default(5000, 5000, 1))
                .target(UserApi.class, "http://localhost:8080");
    }

    @Test
    public void TestGetUser() throws Exception {
        User userDTO = new User();
        userDTO.setId("1");
        userDTO.setUsername("测试用户");
        userDTO.setAge(18);

        EntityMessageView<User> view = userApiByFeign.getUser(userDTO);
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
        EntityMessageView<Boolean> view = userApiByFeign.getValidationBoolean();
        ResultInfo resultInfo = view.getResultInfo();
        Assertions.assertNotNull(resultInfo);
        Assertions.assertTrue(resultInfo.isSuccess());
        Assertions.assertFalse(resultInfo.isFail());
        Assertions.assertFalse(resultInfo.isError());
        Assertions.assertTrue(view.getEntity());
    }

    @Test
    void TestGetValidationString() throws Exception {
        EntityMessageView<String> view = userApiByFeign.getValidationString();
        ResultInfo resultInfo = view.getResultInfo();
        Assertions.assertNotNull(resultInfo);
        Assertions.assertTrue(resultInfo.isSuccess());
        Assertions.assertFalse(resultInfo.isFail());
        Assertions.assertFalse(resultInfo.isError());
        Assertions.assertEquals("OK", view.getEntity());
    }


    @Test
    void TestGetValidationInteger() throws Exception {
        EntityMessageView<Integer> view = userApiByFeign.getValidationInteger();
        ResultInfo resultInfo = view.getResultInfo();
        Assertions.assertNotNull(resultInfo);
        Assertions.assertTrue(resultInfo.isSuccess());
        Assertions.assertFalse(resultInfo.isFail());
        Assertions.assertFalse(resultInfo.isError());
        Assertions.assertEquals(1, view.getEntity());
    }


    @Test
    void TestGetValidationLong() throws Exception {
        EntityMessageView<Long> view = userApiByFeign.getValidationLong();
        ResultInfo resultInfo = view.getResultInfo();
        Assertions.assertNotNull(resultInfo);
        Assertions.assertTrue(resultInfo.isSuccess());
        Assertions.assertFalse(resultInfo.isFail());
        Assertions.assertFalse(resultInfo.isError());
        Assertions.assertEquals(1L, view.getEntity());
    }
}