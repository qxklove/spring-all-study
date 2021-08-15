package com.qxk.springall.springbootaop.repository;

import com.qxk.springall.springbootaop.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
