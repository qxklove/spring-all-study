package com.qxk.springall.springbootactuator.repository;

import com.qxk.springall.springbootactuator.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
