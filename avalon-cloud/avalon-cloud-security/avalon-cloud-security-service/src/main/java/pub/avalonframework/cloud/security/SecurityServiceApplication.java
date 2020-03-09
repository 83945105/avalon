package pub.avalonframework.cloud.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author baichao
 */
//@EnableZuulProxy
//@EnableEurekaClient
//@EnableFeignClients
//@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
        "pub.avalonframework.cloud.security.config",
        "pub.avalonframework.cloud.security.service.impl",
        "pub.avalonframework.cloud.security.controller"
})
public class SecurityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityServiceApplication.class, args);
    }
}
