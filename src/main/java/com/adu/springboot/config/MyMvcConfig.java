package com.adu.springboot.config;

import com.adu.springboot.component.LoginHandlerInterceptor;
import com.adu.springboot.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * 添加视图解析器
             * @param registry
             */
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            /**
             * 添加拦截器
             * @param registry
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(new LoginHandlerInterceptor())
//                        //拦截所有
//                        .addPathPatterns("/**")
//                        //拦截排除
//                        .excludePathPatterns("/index.html","/","/user/login");
            }
        };
    }

    /**
     * 配置加载区域解析器,springmvc会自动调用
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

}
