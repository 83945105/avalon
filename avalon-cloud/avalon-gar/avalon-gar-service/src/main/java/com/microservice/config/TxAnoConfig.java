package com.microservice.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 事务
 *
 * @author baichao
 */
@Configuration
public class TxAnoConfig {

    @Value("${web.transaction.pointcut}")
    private String pointcut;
    @Value("${web.transaction.timeout}")
    private Integer timeout;

    /**
     * 必须使用AspectJ方式的AutoProxy,这样才能和DataSourceSwitchAspect保持统一的aop拦截方式，否则不同的拦截方式会导致order失效
     *
     * @return
     */
/*    @Bean
    public AnnotationAwareAspectJAutoProxyCreator txProxy() {
        AnnotationAwareAspectJAutoProxyCreator creator = new AnnotationAwareAspectJAutoProxyCreator();
        creator.setInterceptorNames("txAdvice");
        creator.setIncludePatterns(Collections.singletonList("execution (public com.hundda.ali.invt..service.*.*(..))"));
        creator.setProxyTargetClass(true);
        creator.setOrder(3);
        return creator;
    }*/
    @Bean("advisor")
    @Qualifier("advisor")
    public Advisor advisor(@Qualifier("ds0MasterPlatformTransactionManager") PlatformTransactionManager transactionManager) {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(this.pointcut);

        TransactionInterceptor txAdvice = new TransactionInterceptor();
        txAdvice.setTransactionManager(transactionManager);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        Map<String, TransactionAttribute> txMap = new HashMap<>(1);

        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);

        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredTx.setTimeout(this.timeout);

        txMap.put("*", requiredTx);
        source.setNameMap(txMap);

        txAdvice.setTransactionAttributeSource(source);

        advisor.setPointcut(pointcut);
        advisor.setAdvice(txAdvice);
        return advisor;
    }

    /*@Bean
    public Advisor pointcutAdvisor(TransactionInterceptor txAdvice) {
        AspectJExpressionPointcutAdvisor pointcutAdvisor = new AspectJExpressionPointcutAdvisor();
        pointcutAdvisor.setAdvice(txAdvice);
        pointcutAdvisor.setExpression(pointcut);
        return pointcutAdvisor;
    }

    @Bean("txAdvice")
    TransactionInterceptor getTransactionInterceptor(PlatformTransactionManager tx) {
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        *//*RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);*//*
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED,
                Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        if (timeout != null) {
            requiredTx.setTimeout(timeout);
        }
        Map<String, TransactionAttribute> txMap = new HashMap<>(1);
        txMap.put("*", requiredTx);
        source.setNameMap(txMap);
        return new TransactionInterceptor(tx, source);
    }*/

}
