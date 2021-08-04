package com.qxk.springall.springbootmybatis;

import com.qxk.springall.springbootmybatis.mapper.CoffeeMapper;
import com.qxk.springall.springbootmybatis.mapper.CoffeeWithMyBatisGeneratorMapper;
import com.qxk.springall.springbootmybatis.model.Coffee;
import com.qxk.springall.springbootmybatis.model.CoffeeWithMyBatisGenerator;
import com.qxk.springall.springbootmybatis.model.CoffeeWithMyBatisGeneratorExample;
import lombok.extern.slf4j.Slf4j;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@MapperScan("com.qxk.springall.springbootmybatis.mapper")
@SpringBootApplication
public class SpringBootMybatisApplication implements ApplicationRunner {
    @Autowired
    private CoffeeMapper coffeeMapper;
    @Autowired
    private CoffeeWithMyBatisGeneratorMapper coffeeWithMyBatisGeneratorMapper;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        testWithoutMyBatisGenerator();
        //generateArtifacts();
        playWithArtifacts();
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
}
