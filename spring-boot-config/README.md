# Spring Boot 配置

## 1.定制Banner
在src/main/resources目录下新建banner.txt文件，然后将自己的图案黏贴进去即可。
ASCII图案可通过网站 <http://www.network-science.de/ascii/> 一键生成。  
banner也可以关闭，在入口类的main方法中：

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DemoApplication.class);
        app.setBannerMode(Mode.OFF);
        app.run(args);
    }


## 2.全局配置文件
用`@Value("${属性名}")`来加载配置文件中的属性值

    @Value("${qxk.config1}")
    private String config1;

通过注解`@ConfigurationProperties(prefix="qxk")`指明了属性的通用前缀，通用前缀加属性名和配置文件的属性名一一对应。

    @ConfigurationProperties(prefix = "qxk")
    public class ConfigBean {
        private String config1;
        private String config2;
        // get,set略
    }

属性间的引用:config1 = ${config2} - ${config3}

## 3.自定义配置文件
用注解`@PropertySource("classpath:custom.properties")`指明了使用哪个配置文件

    @ConfigurationProperties(prefix = "qxk.custom")
    @PropertySource("classpath:custom.properties")
    public class CustomConfigBean {
        private String config1;
        private String config2;
        // get,set略
    }

## 4.通过命令行设置属性值
在运行Spring Boot jar文件时，可以使用命令`java -jar xxx.jar --配置名=配置值`来设置配置。  
如果不想项目的配置被命令行修改，可以在入口类的main方法中：

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setAddCommandLineProperties(false);
        app.run(args);
    }

## 5.使用xml配置
可以在入口类里通过注解`@ImportResource({"classpath:some-application.xml"})`来引入xml配置文件。

## 6.Profile配置
Profile用来针对不同的环境下使用不同的配置文件。  
多环境配置文件必须以application-{profile}.properties的格式命，其中{profile}为环境标识。  
比如定义两个配置文件：application-dev.properties，application-prod.properties，
然后在application.properties文件中通过`spring.profiles.active`属性来设置用哪个配置，其值对应{profile}值。

## 7.补充
@Value()只能给普通变量注入值，不能直接给静态变量赋值，若要给静态变量设置配置值，
可以使用set()方法，在上面加上@Value，注意**set方法不要加static修饰**， 还需要在类上加入@Component注解，如下所示：

    public static String staticConfig;

    @Value("${qxk.staticConfig}")
    public void setStaticConfig(String staticConfig) {
        StaticConfigBean.staticConfig = staticConfig;
    }

## 参考
<https://mrbird.cc/Spring-Boot%20basic%20config.html>