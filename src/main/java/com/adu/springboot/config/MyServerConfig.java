package com.adu.springboot.config;

import com.adu.springboot.filter.MyFilter;
import com.adu.springboot.listener.MyListener;
import com.adu.springboot.servlet.MyServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.Arrays;

@Configuration
public class MyServerConfig implements WebMvcConfigurer {

    /**
     * 注册servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean myServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new MyServlet(), "/myServlet");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }

    /**
     * 注册拦截器
     * @return
     */
    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new MyFilter());
        //要拦截的请求
        filterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/myServlet"));
        return filterFilterRegistrationBean;
    }

    /**
     * 注册监听器
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<MyListener> registrationBean = new ServletListenerRegistrationBean<>(new MyListener());
        return registrationBean;
    }



}
