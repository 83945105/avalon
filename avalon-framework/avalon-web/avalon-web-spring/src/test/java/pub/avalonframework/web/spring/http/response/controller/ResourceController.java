package pub.avalonframework.web.spring.http.response.controller;

import pub.avalonframework.web.entity.ResourceFile;
import pub.avalonframework.web.spring.http.response.HttpResultInfo;
import pub.avalonframework.web.spring.http.response.api.ResourceApi;
import pub.avalonframework.web.spring.http.response.view.impl.EntityMessageView;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 */
@RequestMapping(ResourceApi.ROOT_PATH)
@RestController
public class ResourceController implements ResourceApi {

    @Override
    public EntityMessageView<List<ResourceFile>> uploadListFile(MultipartFile[] files) throws Exception {
        return new EntityMessageView<>(Collections.emptyList(), new HttpResultInfo(HttpStatus.OK));
    }
}
