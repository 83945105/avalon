package pub.avalonframework.cloud;

import feign.FeignException;
import feign.codec.Decoder;
import feign.optionals.OptionalDecoder;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import pub.avalonframework.security.core.api.service.WebService;
import pub.avalonframework.shiro.service.impl.ShiroWebServiceImpl;
import pub.avalonframework.web.spring.http.response.HttpResultInfo;
import pub.avalonframework.web.spring.http.response.exception.ResponseException;
import pub.avalonframework.web.spring.http.response.feign.codec.ResponseViewDecoder;
import pub.avalonframework.web.spring.http.response.view.ExceptionView;
import pub.avalonframework.web.spring.http.response.view.MessageView;
import pub.avalonframework.web.spring.http.response.view.impl.ExceptionMessageView;

import javax.servlet.http.HttpServletRequest;

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