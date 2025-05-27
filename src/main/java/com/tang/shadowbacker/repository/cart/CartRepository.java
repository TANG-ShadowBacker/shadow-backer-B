package com.tang.shadowbacker.repository.cart;

import com.tang.shadowbacker.model.cart.Cart;
import com.tang.shadowbacker.model.user.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, String> {

    // 根据用户查找所有购物车记录
    List<Cart> findByUser(UserAccount user);

    // 如果需要根据创建时间排序
    List<Cart> findByUserOrderByCreatedAtDesc(UserAccount user);
}
