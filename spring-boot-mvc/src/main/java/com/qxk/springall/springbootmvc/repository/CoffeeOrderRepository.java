package com.qxk.springall.springbootmvc.repository;

import com.qxk.springall.springbootmvc.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
