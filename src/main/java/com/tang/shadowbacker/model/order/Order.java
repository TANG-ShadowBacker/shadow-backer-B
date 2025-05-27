package com.tang.shadowbacker.model.order;

import com.tang.shadowbacker.model.item.Item;
import com.tang.shadowbacker.model.user.UserAccount;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "order_order")
public class Order {

    @Id
    @Column(name = "order_id", nullable = false, updatable = false, length = 36)
    private String orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false)
    private UserAccount buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "order_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime orderTime;

    private String status;

    @PrePersist
    protected void onCreate() {
        if (this.orderId == null) {
            this.orderId = UUID.randomUUID().toString();
        }
        if (this.orderTime == null) {
            this.orderTime = LocalDateTime.now();
        }
    }
}
