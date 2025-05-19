package com.tang.shadowbacker.repository.order;

import com.tang.shadowbacker.model.order.OrderOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderOrderRepository extends JpaRepository<OrderOrder, String> {
}
