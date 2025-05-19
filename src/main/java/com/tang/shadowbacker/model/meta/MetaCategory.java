package com.tang.shadowbacker.model.meta;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "meta_category")
public class MetaCategory {

    @Id
    @Column(name = "category_id", nullable = false, updatable = false, length = 36)
    private String categoryId;

    @Column
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @PrePersist
    protected void onCreate() {
        if (this.categoryId == null) {
            this.categoryId = UUID.randomUUID().toString();
        }
    }
}
