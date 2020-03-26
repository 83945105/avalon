package pub.avalonframework.web.spring.http.response.api;

import feign.Headers;
import feign.RequestLine;
import pub.avalonframework.web.entity.ResourceFile;
import pub.avalonframework.web.spring.http.response.view.impl.EntityMessageView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author baichao
 */
public interface ResourceApi {

    String ROOT_PATH = "/hundda/test/resource";

    @RequestMapping(value = "/upload/list/file")
    @RequestLine("POST " + ROOT_PATH + "/upload/list/file")
    @Headers("Content-Type: multipart/form-data")
    EntityMessageView<List<ResourceFile>> uploadListFile(@RequestParam("files") @RequestPart("files") MultipartFile[] files) throws Exception;
}