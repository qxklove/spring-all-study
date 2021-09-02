package com.qxk.springall.springcloudeurekaclient.repository;

import com.qxk.springall.springcloudeurekaclient.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
