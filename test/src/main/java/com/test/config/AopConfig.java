package com.test.config;

import org.aspectj.weaver.Advice;
import org.springframework.aop.config.AopConfigUtils;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@AutoConfiguration
//@ConditionalOnProperty(prefix = "spring.aop", name = "auto", havingValue = "true", matchIfMissing = true)
public class AopConfig {
    // @Configuration(proxyBeanMethods = false)
	// @ConditionalOnClass(Advice.class)
	// static class AspectJAutoProxyingConfiguration {

	// 	@Configuration(proxyBeanMethods = false)
	// 	@EnableAspectJAutoProxy(proxyTargetClass = false)
	// 	@ConditionalOnProperty(prefix = "spring.aop", name = "proxy-target-class", havingValue = "false")
	// 	static class JdkDynamicAutoProxyConfiguration {

	// 	}

	// 	@Configuration(proxyBeanMethods = false)
	// 	@EnableAspectJAutoProxy(proxyTargetClass = true)
	// 	@ConditionalOnProperty(prefix = "spring.aop", name = "proxy-target-class", havingValue = "true",
	// 			matchIfMissing = true)
	// 	static class CglibAutoProxyConfiguration {

	// 	}

	// }

	// @Configuration(proxyBeanMethods = false)
	// @ConditionalOnMissingClass("org.aspectj.weaver.Advice")
	// @ConditionalOnProperty(prefix = "spring.aop", name = "proxy-target-class", havingValue = "true",
	// 		matchIfMissing = true)
	// static class ClassProxyingConfiguration {

	// 	@Bean
	// 	static BeanFactoryPostProcessor forceAutoProxyCreatorToUseClassProxying() {
	// 		return (beanFactory) -> {
	// 			if (beanFactory instanceof BeanDefinitionRegistry registry) {
	// 				AopConfigUtils.registerAutoProxyCreatorIfNecessary(registry);
	// 				AopConfigUtils.forceAutoProxyCreatorToUseClassProxying(registry);
	// 			}
	// 		};
	// 	}

	// }
}
