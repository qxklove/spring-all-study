package com.qxk.springconfig;

import com.qxk.springconfig.bean.ConfigBean;
import com.qxk.springconfig.bean.CustomConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController = @Controller + @ResponseBody，所有返回都以Json字符串的形式返回给客户端，视图就不认识了
 */
@RestController
/**
 * 用这个配置后，就无需再去ConfigBean里指定是组件了（比如@Componet）
 * 但是对于关联自定义配置文件的CustomConfigBean不起作用，还是需要指定其为组件
 */
@EnableConfigurationProperties({ConfigBean.class})
@SpringBootApplication
public class SpringConfigApplication {

    @Autowired
    private ConfigBean configBean;
    @Autowired
    private CustomConfigBean customConfigBean;

    @Value("${qxk.test.env}")
    private String env;

    @GetMapping("/config")
    public String getConfig() {
        return "configBean：" + configBean.getConfig1() + "-" + configBean.getConfig2()
                + "-" + configBean.getConfig() + "-" + ConfigBean.staticConfig;
    }

    @RequestMapping("/customConfig")
    public String getCustomConfig() {
        return "customConfig：" + customConfigBean.getConfig1() + "-" + customConfigBean.getConfig2();
    }

    @RequestMapping("/env")
    public String getEnv() {
        return "env：" + env;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringConfigApplication.class, args);
    }

}
