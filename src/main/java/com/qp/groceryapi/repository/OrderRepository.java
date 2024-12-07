package com.qp.groceryapi.repository;

import com.qp.groceryapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
