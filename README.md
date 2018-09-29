### 引入静态资源方式，类路径的设置

     都是以webjars方式引入,官网：https://www.webjars.org/
     1.引入jquery
    <dependency>
        <groupId>org.webjars</groupId>
        <artifactId>jquery</artifactId>
        <version>3.3.1</version>
    </dependency>
    访问方式：http://localhost:8080/webjars/jquery/3.3.1/jquery.js
    
    2."/**"访问当前项目的任何资源
        1)classpath:/META-INF/resuorces/
        2)classpath:/resuorces/
        3)classpath:/static/
        4)classpath:/public/
        5)"/"：当前项目的路径
     classpath表示类路径，一般时间项目的java文件夹和resuorces文件夹下表示类路径
     类路径下访问时就不需要加类路径文件夹名称了，现在来访问一个asserts/js/bootstrap.min.js文件路径为：http://localhost:8080/asserts/js/bootstrap.min.js
     
     3.欢迎页； 静态资源文件夹下的所有index.html页面；被"/**"映射
        localhost:8080/   找index页面
     4.页面的图片设置：所有的 **/favicon.ico  都是在静态资源文件下找。
        在类路径下添加一个favicon.ico即可。
     
     以上所有的静态资源文件配置都是在类路径下！！！请看第二点
     注意：还可以自定义类路径：
     spring.resources.static-locations=classpath:/hello/,classpath:/adu/
     
### thymeleaf导入

    加入thymeleaf启动器
     <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    
    如果只是访问一个没有逻辑的单独页面比如login页面，在配置类中配置一个视图解析器即可
    @Configuration
    public class MyMvcConfig implements WebMvcConfigurer {
        @Bean
        public WebMvcConfigurer webMvcConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addViewControllers(ViewControllerRegistry registry) {
                    registry.addViewController("/").setViewName("login");
                    registry.addViewController("/index.html").setViewName("login");
                }
            };
        }
    }
    
### 项目访问路径设置

    server.servlet.context-path=/crud
    访问地址就需要http://localhost:8080/crud/,就是启动路径
    
###国际化配置

    1.springboot国际化非常简单，只需要配置国际化文件，例如login页面国际化
    创建3个login.properties，login_en_US.properties，login_zh_CN.properties文件然后打开
    login_en_US.properties文件，选择底部的resources Bundle选项卡，然后直接添加国际化的字段就可以了
    格式为：
        login.username，具体的可以查看i18n文件夹下的内容
    
    值设置好了之后springboot会自动帮我们加载成功，我们只需要在页面写上对应的值即可.
    取国际化值的语法为：#{login.username}
    
    最后我们需要在全局配置文件中指定国际化了路径名
    spring.messages.basename=i18n.login
    以上表示类路径下的i18n文件夹下的login.properties
    
    2.手动切换国际化操作
        写一个MyLocaleResolver实现LocaleResolver的resolveLocale，setLocale的2个方法
        代码：
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
        然后需要在配置类中加载该对象
        代码:
         /**
         * 配置加载区域解析器,springmvc会自动调用
         * @return
         */
        @Bean
        public LocaleResolver localeResolver() {
            return new MyLocaleResolver();
        }
    