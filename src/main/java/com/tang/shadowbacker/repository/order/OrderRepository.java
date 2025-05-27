package com.tang.shadowbacker.repository.order;

import com.tang.shadowbacker.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
