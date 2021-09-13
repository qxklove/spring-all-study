package com.qxk.springall.sleuthwaiterservice.repository;

import com.qxk.springall.sleuthwaiterservice.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
