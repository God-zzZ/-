package com.atguigu.scw.webui.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import feign.Retryer;
@Configuration
public class AppWebConfig implements WebMvcConfigurer {
	//取消feign的重试机制：
	@Bean
    Retryer feignRetryer() {
        return Retryer.NEVER_RETRY;
    }
	//取消ribbon的重试机制：
	/*@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
	    HttpComponentsClientHttpRequestFactory httpRequestFactory =  new HttpComponentsClientHttpRequestFactory();
	    httpRequestFactory.setReadTimeout(5000);
	    httpRequestFactory.setConnectTimeout(5000);
	    return new RestTemplate(httpRequestFactory);
	}*/
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("user/login");
	}
}
