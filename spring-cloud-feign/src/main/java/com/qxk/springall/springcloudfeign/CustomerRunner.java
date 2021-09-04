package com.qxk.springall.springcloudfeign;

import com.qxk.springall.springcloudfeign.integration.CoffeeOrderService;
import com.qxk.springall.springcloudfeign.integration.CoffeeService;
import com.qxk.springall.springcloudfeign.model.Coffee;
import com.qxk.springall.springcloudfeign.model.CoffeeOrder;
import com.qxk.springall.springcloudfeign.model.NewOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class CustomerRunner implements ApplicationRunner {
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService coffeeOrderService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        readMenu();
        Long id = orderCoffee();
        queryOrder(id);
    }

    private void readMenu() {
        List<Coffee> coffees = coffeeService.getAll();
        coffees.forEach(c -> log.info("Coffee: {}", c));
    }

    private Long orderCoffee() {
        NewOrderRequest orderRequest = NewOrderRequest.builder()
                .customer("Li Lei")
                .items(Arrays.asList("capuccino"))
                .build();
        CoffeeOrder order = coffeeOrderService.create(orderRequest);
        log.info("Order ID: {}", order.getId());
        return order.getId();
    }

    private void queryOrder(Long id) {
        CoffeeOrder order = coffeeOrderService.getOrder(id);
        log.info("Order: {}", order);
    }
}
