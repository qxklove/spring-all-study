package com.qxk.springall.springcloudconfigclient.controller;

import com.qxk.springall.springcloudconfigclient.controller.request.NewOrderRequest;
import com.qxk.springall.springcloudconfigclient.model.Coffee;
import com.qxk.springall.springcloudconfigclient.model.CoffeeOrder;
import com.qxk.springall.springcloudconfigclient.service.CoffeeOrderService;
import com.qxk.springall.springcloudconfigclient.service.CoffeeService;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {
    @Autowired
    private CoffeeOrderService orderService;
    @Autowired
    private CoffeeService coffeeService;
    private RateLimiter rateLimiter;

    public CoffeeOrderController(RateLimiterRegistry rateLimiterRegistry) {
        rateLimiter = rateLimiterRegistry.rateLimiter("order");
    }

    @GetMapping("/{id}")
    public CoffeeOrder getOrder(@PathVariable("id") Long id) {
        CoffeeOrder order = null;
        try {
            order = rateLimiter.executeSupplier(() -> orderService.get(id));
            log.info("Get Order: {}", order);
        } catch(RequestNotPermitted e) {
            log.warn("Request Not Permitted! {}", e.getMessage());
        }
        return order;
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @io.github.resilience4j.ratelimiter.annotation.RateLimiter(name = "order")
    public CoffeeOrder create(@RequestBody NewOrderRequest newOrder) {
        log.info("Receive new Order {}", newOrder);
        Coffee[] coffeeList = coffeeService.getCoffeeByName(newOrder.getItems())
                .toArray(new Coffee[] {});
        return orderService.createOrder(newOrder.getCustomer(), coffeeList);
    }
}
