package com.qxk.springall.springcloudhystrix.integration;

import com.qxk.springall.springcloudhystrix.model.Coffee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "waiter-service", contextId = "coffee",
        qualifier = "coffeeService",
        fallback = FallbackCoffeeService.class)
// 如果用了Fallback，不要在接口上加@RequestMapping，path可以用在这里
public interface CoffeeService {
    @GetMapping(path = "coffee/", params = "!name")
    List<Coffee> getAll();

    @GetMapping("coffee/{id}")
    Coffee getById(@PathVariable Long id);

    @GetMapping(path = "coffee/", params = "name")
    Coffee getByName(@RequestParam String name);
}
