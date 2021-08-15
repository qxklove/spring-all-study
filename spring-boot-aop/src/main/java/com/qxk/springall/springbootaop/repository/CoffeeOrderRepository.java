package com.qxk.springall.springbootaop.repository;

import com.qxk.springall.springbootaop.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
