package com.qxk.springall.springcloudsleuth.repository;

import com.qxk.springall.springcloudsleuth.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
