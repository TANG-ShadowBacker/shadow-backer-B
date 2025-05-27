package com.tang.shadowbacker.model.item;

import com.tang.shadowbacker.model.user.UserAccount;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "service_assignment")
public class ServiceAssignment {

    @Id
    @Column(name = "service_assignment_id", nullable = false, updatable = false, length = 36)
    private String serviceAssignmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private UserAccount seller;

    @Column(name = "time_available")
    private String timeAvailable;

    @PrePersist
    protected void onCreate() {
        if (this.serviceAssignmentId == null) {
            this.serviceAssignmentId = UUID.randomUUID().toString();
        }
    }
}
