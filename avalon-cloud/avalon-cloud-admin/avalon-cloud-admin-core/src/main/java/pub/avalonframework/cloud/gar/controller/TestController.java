package pub.avalonframework.cloud.gar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.avalonframework.cloud.gar.mapper.GarMenuMapper;
import pub.avalonframework.web.spring.http.response.HttpResultInfo;
import pub.avalonframework.web.spring.http.response.view.impl.DefaultMessageView;

/**
 * @author baichao
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private GarMenuMapper garMenuMapper;

    @RequestMapping(value = "/test")
    public DefaultMessageView test() throws Exception {
        garMenuMapper.selectByPrimaryKey("1");
        return new DefaultMessageView(new HttpResultInfo(HttpStatus.OK));
    }
}