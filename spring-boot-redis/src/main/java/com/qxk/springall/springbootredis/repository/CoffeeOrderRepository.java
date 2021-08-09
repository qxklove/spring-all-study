package com.qxk.springall.springbootredis.repository;

import com.qxk.springall.springbootredis.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
