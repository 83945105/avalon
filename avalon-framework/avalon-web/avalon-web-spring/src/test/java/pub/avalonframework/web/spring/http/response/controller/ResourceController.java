package pub.avalonframework.web.spring.http.response.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pub.avalonframework.web.entity.ResourceFile;
import pub.avalonframework.web.spring.http.response.HttpResultInfo;
import pub.avalonframework.web.spring.http.response.api.ResourceApi;
import pub.avalonframework.web.spring.http.response.view.impl.EntityMessageView;
import pub.avalonframework.web.utils.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
@RequestMapping(ResourceApi.ROOT_PATH)
@RestController
public class ResourceController implements ResourceApi {

    @Override
    public EntityMessageView<List<ResourceFile>> uploadListFile(MultipartFile[] files) throws Exception {
        String uploadAddress = "/Users/baichao/develop/idea_projects/avalon/avalon-framework/avalon-web/avalon-web-spring/src/test/resources/upload";
        String relativeSavePath = "/test";
        String absoluteSavePath = uploadAddress + relativeSavePath;
        String fileSaveName = "uploadFile";
        List<ResourceFile> resourceFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            resourceFiles.add(ResourceUtils.uploadFile(file, absoluteSavePath, relativeSavePath, fileSaveName));
        }
        return new EntityMessageView<>(resourceFiles, new HttpResultInfo(HttpStatus.OK));
    }
}
