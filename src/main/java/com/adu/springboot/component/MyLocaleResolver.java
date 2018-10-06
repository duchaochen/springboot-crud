package com.adu.springboot.component;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 国际化
 */
public class MyLocaleResolver implements LocaleResolver {
    /**
     * 解析区域信息
     * @param httpServletRequest
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {

        //获取index.html(l=zh_CN)传过来的信息
        String l = httpServletRequest.getParameter("l");
        Locale locale = Locale.getDefault();
        //StringUtils使用springutils包的类
        if (!StringUtils.isEmpty(l)) {
            String[] arr = l.split("_");
            /**
             * arr[0]:语言代码,arr[1]：国家代码
             * 就可以解析区域信息了
             */
            locale = new Locale(arr[0],arr[1]);
        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, @Nullable HttpServletResponse httpServletResponse, @Nullable Locale locale) {

    }
}
