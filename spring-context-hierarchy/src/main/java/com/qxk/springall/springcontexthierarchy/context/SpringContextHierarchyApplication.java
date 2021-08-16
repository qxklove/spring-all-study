package com.qxk.springall.springcontexthierarchy.context;

import com.qxk.springall.springcontexthierarchy.foo.FooConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
@SpringBootApplication
public class SpringContextHierarchyApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringContextHierarchyApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ApplicationContext fooContext = new AnnotationConfigApplicationContext(FooConfig.class);
        ClassPathXmlApplicationContext barContext = new ClassPathXmlApplicationContext(
                new String[] {"applicationContext.xml"}, fooContext);//这里指定了fooContext是barContext的父context
        TestBean bean = fooContext.getBean("testBeanX", TestBean.class);
        bean.hello();

        log.info("=============");

        bean = barContext.getBean("testBeanX", TestBean.class);
        bean.hello();

        bean = barContext.getBean("testBeanY", TestBean.class);
        bean.hello();
    }
}
