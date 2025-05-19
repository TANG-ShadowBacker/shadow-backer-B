package com.tang.shadowbacker.model.cart;

import com.tang.shadowbacker.model.item.ItemItem;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "cart_cart_item")
public class CartCartItem {

    @Id
    @Column(name = "cart_item_id", nullable = false, updatable = false, length = 36)
    private String cartItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private CartCart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private ItemItem item;

    @Column(nullable = false)
    private int quantity = 1;

    @Column(name = "added_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime addedAt;

    @PrePersist
    protected void onAdd() {
        if (this.cartItemId == null) {
            this.cartItemId = UUID.randomUUID().toString();
        }
        if (this.addedAt == null) {
            this.addedAt = LocalDateTime.now();
        }
        if (quantity <= 0) {
            quantity = 1;
        }
    }
}
