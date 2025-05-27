package com.tang.shadowbacker.model.order;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "payment_record")
public class PaymentRecord {

    @Id
    @Column(name = "payment_id", nullable = false, updatable = false, length = 36)
    private String paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "payment_status")
    private String paymentStatus; // 如：已付/失败

    @Column(name = "payment_method")
    private String paymentMethod; // 如：微信/支付宝

    @Column(name = "payment_type")
    private String paymentType;   // 如：定金/尾款/运维

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

    @PrePersist
    protected void onCreate() {
        if (this.paymentId == null) {
            this.paymentId = UUID.randomUUID().toString();
        }
        if (this.paidAt == null) {
            this.paidAt = LocalDateTime.now();
        }
    }
}
