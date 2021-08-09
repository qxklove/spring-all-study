package com.qxk.springall.springbootredis.repository;

import com.qxk.springall.springbootredis.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
