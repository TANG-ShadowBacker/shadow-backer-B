package com.tang.shadowbacker.repository.mapping;

import com.tang.shadowbacker.model.mapping.CartCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartCartItemRepository extends JpaRepository<CartCartItem, String> {
}
