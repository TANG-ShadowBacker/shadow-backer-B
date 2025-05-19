package com.tang.shadowbacker.repository.cart;

import com.tang.shadowbacker.model.cart.CartCart;
import com.tang.shadowbacker.model.user.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartCartRepository extends JpaRepository<CartCart, String> {

    // 根据用户查找所有购物车记录
    List<CartCart> findByUser(UserAccount user);

    // 如果需要根据创建时间排序
    List<CartCart> findByUserOrderByCreatedAtDesc(UserAccount user);
}
