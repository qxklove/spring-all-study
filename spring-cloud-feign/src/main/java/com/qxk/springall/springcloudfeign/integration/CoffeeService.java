package com.qxk.springall.springcloudfeign.integration;

import com.qxk.springall.springcloudfeign.model.Coffee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "waiter-service", contextId = "coffee")
// 不要在接口上加@RequestMapping
public interface CoffeeService {
    @GetMapping(path = "/coffee/", params = "!name")
    List<Coffee> getAll();

    @GetMapping("/coffee/{id}")
    Coffee getById(@PathVariable Long id);

    @GetMapping(path = "/coffee/", params = "name")
    Coffee getByName(@RequestParam String name);
}
