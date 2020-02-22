package pub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 白超
 */
//@EnableZuulProxy
//@EnableEurekaClient
//@EnableFeignClients
//@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
        "com.microservice.config",
        "com.microservice.controller",
        "pub.avalonframework.cloud.gar.config.shiro",
        "pub.avalonframework.cloud.gar.config.jdbc",
//        "pub.avalonframework.cloud.gar.config.feign",
//        "pub.avalonframework.cloud.gar.config.zuul",
        "pub.avalonframework.cloud.gar.service.impl",
        "pub.avalonframework.cloud.gar.controller"
})
@MapperScan(basePackages = "pub.avalonframework.cloud.**.mapper")
public class GarServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GarServiceApplication.class, args);
    }
}
