package com.qxk.springall.springcloudconfigclient.repository;

import com.qxk.springall.springcloudconfigclient.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
