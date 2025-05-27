package com.tang.shadowbacker.repository.mapping;

import com.tang.shadowbacker.model.mapping.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, String> {
}
