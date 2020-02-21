package pub.avalonframework.cloud;

import feign.FeignException;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.optionals.OptionalDecoder;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import pub.avalonframework.security.core.api.service.WebService;
import pub.avalonframework.shiro.service.impl.ShiroWebServiceImpl;
import pub.avalonframework.web.spring.api.config.CorsConfiguration;
import pub.avalonframework.web.spring.api.config.WebSpringConfiguration;
import pub.avalonframework.web.spring.http.response.HttpResultInfo;
import pub.avalonframework.web.spring.http.response.exception.ResponseException;
import pub.avalonframework.web.spring.http.response.feign.codec.ResponseViewDecoder;
import pub.avalonframework.web.spring.http.response.view.ExceptionView;
import pub.avalonframework.web.spring.http.response.view.MessageView;
import pub.avalonframework.web.spring.http.response.view.impl.ExceptionMessageView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Web spring boot configuration.
 *
 * @author baichao
 */
@Configuration
public class AvalonCloudConfiguration {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    @ConditionalOnMissingBean
    public Decoder feignDecoder() {
        return new OptionalDecoder(new ResponseViewDecoder(new ResponseEntityDecoder(new SpringDecoder(this.messageConverters))));
    }

    /**
     * 用于微服务之间使用Feign互相调用传递Headers
     *
     * @return
     */
    @Bean
    public RequestInterceptor headerInterceptor() {
        return template -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            if (attributes == null) {
                return;
            }
            HttpServletRequest request = attributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    String values = request.getHeader(name);
                    template.header(name, values);
                }
            }
        };
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Bean
    public FormContentFilter httpPutFormContentFilter() {
        return new FormContentFilter();
    }

    @Configuration
    protected static class WeiqueWebMvcConfigurationSupport extends WebMvcConfigurationSupport {

        @Value("${spring.mvc.static-path-pattern:/**}")
        private String staticPathPattern;
        @Value("${spring.resources.static-locations:classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/}")
        private String staticLocations;

        @Autowired
        private WebSpringConfiguration webSpringConfiguration;

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            CorsConfiguration cors = webSpringConfiguration.getCors();
            if (cors.getEnabled()) {
                //设置允许跨域的路径
                registry.addMapping(cors.getMapping())
                        //设置允许跨域请求的域名
                        .allowedOrigins(cors.getAllowedOrigins().toArray(new String[0]))
                        //是否允许证书 不再默认开启
                        .allowCredentials(cors.getAllowCredentials())
                        //设置允许的方法
                        .allowedMethods(cors.getAllowedMethods().toArray(new String[0]))
                        //跨域允许时间
                        .maxAge(cors.getMaxAge());
            }
        }

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            ResourceHandlerRegistration registration = registry.addResourceHandler(staticPathPattern);
            String sign = ",";
            for (String location : staticLocations.split(sign)) {
                registration.addResourceLocations(location);
            }
            super.addResourceHandlers(registry);
        }
    }

    @Configuration
    @ControllerAdvice
    protected static class GlobalExceptionHandler {

        @ExceptionHandler(ResponseException.class)
        @ResponseBody
        ExceptionView responseException(HttpServletRequest request, ResponseException ex) throws Exception {
            ExceptionView exceptionView = ex.transformToExceptionView();
            if (exceptionView instanceof MessageView && ((MessageView) exceptionView).getResultInfo().isError()) {
                ex.printStackTrace();
            }
            return exceptionView;
        }

        @ExceptionHandler(UnauthenticatedException.class)
        @ResponseBody
        ExceptionView unauthenticatedException(HttpServletRequest request, UnauthenticatedException ex) throws Exception {
            return new ExceptionMessageView(new HttpResultInfo(HttpStatus.PROXY_AUTHENTICATION_REQUIRED));
        }

        @ExceptionHandler(UnauthorizedException.class)
        @ResponseBody
        ExceptionView unauthorizedException(HttpServletRequest request, UnauthorizedException ex) throws Exception {
            return new ExceptionMessageView(new HttpResultInfo(HttpStatus.UNAUTHORIZED));
        }

        @ExceptionHandler(FeignException.class)
        @ResponseBody
        ExceptionView feignException(HttpServletRequest request, FeignException ex) throws Exception {
            ex.printStackTrace();
            return new ExceptionMessageView(new HttpResultInfo(HttpStatus.BAD_REQUEST));
        }

        @ExceptionHandler(Exception.class)
        @ResponseBody
        ExceptionView exceptionHandler(HttpServletRequest request, Exception ex) throws Exception {
            ex.printStackTrace();
            return new ExceptionMessageView(new HttpResultInfo(HttpStatus.SERVICE_UNAVAILABLE));
        }
    }

    @Bean
    @ConditionalOnMissingBean
    public WebService webService() {
        return new ShiroWebServiceImpl();
    }
}