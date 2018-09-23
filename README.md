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
     
     
     