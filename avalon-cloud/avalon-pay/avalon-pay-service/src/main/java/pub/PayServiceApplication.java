package pub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 白超
 */
//@EnableZuulProxy
//@EnableEurekaClient
//@EnableFeignClients
//@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
        "com.global.config",
        "com.global.controller",
        "pub.avalon.global.config.message",
        "pub.avalon.pay.config",
        "pub.avalon.pay.service.impl",
        "pub.avalon.pay.controller"
})
public class PayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayServiceApplication.class, args);
    }
}
