package pub.avalonframework.web.spring.http.response;

import feign.Feign;
import feign.Retryer;
import feign.form.spring.SpringFormEncoder;
import feign.jackson.JacksonEncoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;
import pub.avalonframework.web.entity.ResourceFile;
import pub.avalonframework.web.spring.http.response.api.ResourceApi;
import pub.avalonframework.web.spring.http.response.controller.ResourceController;
import pub.avalonframework.web.spring.http.response.feign.codec.ResponseViewDecoder;
import pub.avalonframework.web.spring.http.response.view.impl.EntityMessageView;
import pub.avalonframework.web.utils.ResourceUtils;

import java.io.File;
import java.util.List;

/**
 * @author baichao
 */
@SpringBootTest(
        classes = {
                ResourceController.class
        },
        // 启动原生容器, 并使用自定义端口
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@EnableAutoConfiguration
@Disabled
public class ResourceUtilsTest {

    // 使用原生Feign方式调用接口
    private static ResourceApi resourceApiByFeign;

    @BeforeAll
    public static void before() {
        resourceApiByFeign = Feign.builder()
                .encoder(new SpringFormEncoder(new JacksonEncoder()))
                .decoder(new ResponseViewDecoder(null))
                .retryer(new Retryer.Default(5000, 5000, 1))
                .target(ResourceApi.class, "http://localhost:8080");
    }

    @Test
    public void TestUploadFile() throws Exception {
        File file = new File("/Users/baichao/develop/idea_projects/avalon/avalon-framework/avalon-web/avalon-web-spring/src/test/resources/useForUploadTextFile.txt");
        MultipartFile multipartFile = ResourceUtils.buildMultipartFile("files", file);
        EntityMessageView<List<ResourceFile>> view = resourceApiByFeign.uploadListFile(new MultipartFile[]{multipartFile});
        Assertions.assertTrue(view.getResultInfo().isSuccess());
    }
}