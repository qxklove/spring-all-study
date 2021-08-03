package com.qxk.springall.springbootdatajpa.repository;

import com.qxk.springall.springbootdatajpa.model.CoffeeOrder;

import java.util.List;

public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder, Long> {
    List<CoffeeOrder> findByCustomerOrderById(String customer);
    List<CoffeeOrder> findByItems_Name(String name);
}
