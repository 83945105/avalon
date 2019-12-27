package pub.avalonframework.shiro.spring.web;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.lang.Nullable;
import pub.avalonframework.security.core.api.beans.CacheType;
import pub.avalonframework.security.core.api.config.SecurityConfiguration;

/**
 * @author baichao
 */
public class ShiroConditional implements Condition {

    @Override
    public boolean matches(@Nullable ConditionContext context, @Nullable AnnotatedTypeMetadata metadata) {
        if (context == null) {
            return false;
        }
        BeanFactory beanFactory = context.getBeanFactory();
        if (beanFactory == null) {
            return false;
        }
        SecurityConfiguration securityConfiguration = beanFactory.getBean(SecurityConfiguration.class);
        return securityConfiguration.getCacheType() == CacheType.EHCACHE;
    }
}