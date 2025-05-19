package com.tang.shadowbacker.repository.cart;

import com.tang.shadowbacker.model.cart.CartCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartCartItemRepository extends JpaRepository<CartCartItem, String> {
}
