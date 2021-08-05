package com.qxk.springall.springbootmybatis;

import com.github.pagehelper.PageInfo;
import com.qxk.springall.springbootmybatis.mapper.CoffeeMapper;
import com.qxk.springall.springbootmybatis.mapper.CoffeeWithMyBatisGeneratorMapper;
import com.qxk.springall.springbootmybatis.model.Coffee;
import com.qxk.springall.springbootmybatis.model.CoffeeWithMyBatisGenerator;
import com.qxk.springall.springbootmybatis.model.CoffeeWithMyBatisGeneratorExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@MapperScan("com.qxk.springall.springbootmybatis.mapper")
@SpringBootApplication
public class SpringBootMybatisApplication implements ApplicationRunner {
    @Resource
    private CoffeeMapper coffeeMapper;
    @Resource
    private CoffeeWithMyBatisGeneratorMapper coffeeWithMyBatisGeneratorMapper;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        testWithoutMyBatisGenerator();
        //myBatisGenerator生成代码
//        generateArtifacts();
//        playWithArtifacts();
        testPageHelper();
    }

    private void testWithoutMyBatisGenerator() {
        Coffee c = Coffee.builder().name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0)).build();
        int count = coffeeMapper.save(c);
        log.info("Save {} Coffee: {}", count, c);

        c = Coffee.builder().name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 25.0)).build();
        count = coffeeMapper.save(c);
        log.info("Save {} Coffee: {}", count, c);

        c = coffeeMapper.findById(c.getId());
        log.info("Find Coffee: {}", c);
    }

    private void generateArtifacts() throws Exception {
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(
                this.getClass().getResourceAsStream("/generatorConfig.xml"));
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    private void playWithArtifacts() {
        CoffeeWithMyBatisGenerator espresso = new CoffeeWithMyBatisGenerator()
                .withName("espresso")
                .withPrice(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .withCreateTime(new Date())
                .withUpdateTime(new Date());
        coffeeWithMyBatisGeneratorMapper.insert(espresso);

        CoffeeWithMyBatisGenerator latte = new CoffeeWithMyBatisGenerator()
                .withName("latte")
                .withPrice(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .withCreateTime(new Date())
                .withUpdateTime(new Date());
        coffeeWithMyBatisGeneratorMapper.insert(latte);

        CoffeeWithMyBatisGenerator s = coffeeWithMyBatisGeneratorMapper.selectByPrimaryKey(1L);
        log.info("CoffeeWithMyBatisGenerator {}", s);

        CoffeeWithMyBatisGeneratorExample example = new CoffeeWithMyBatisGeneratorExample();
        example.createCriteria().andNameEqualTo("latte");
        List<CoffeeWithMyBatisGenerator> list = coffeeWithMyBatisGeneratorMapper.selectByExample(example);
        list.forEach(e -> log.info("selectByExample: {}", e));
    }

    private void testPageHelper() {
        coffeeMapper.findAllWithRowBounds(new RowBounds(1, 3))
                .forEach(c -> log.info("Page(1) Coffee {}", c));
        coffeeMapper.findAllWithRowBounds(new RowBounds(2, 3))
                .forEach(c -> log.info("Page(2) Coffee {}", c));

        log.info("===================");

        coffeeMapper.findAllWithRowBounds(new RowBounds(1, 0))
                .forEach(c -> log.info("Page(1) Coffee {}", c));

        log.info("===================");

        coffeeMapper.findAllWithParam(1, 3)
                .forEach(c -> log.info("Page(1) Coffee {}", c));
        List<Coffee> list = coffeeMapper.findAllWithParam(2, 3);
        PageInfo page = new PageInfo(list);
        log.info("PageInfo: {}", page);
    }
}
