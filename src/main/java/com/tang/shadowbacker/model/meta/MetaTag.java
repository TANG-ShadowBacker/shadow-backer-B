package com.tang.shadowbacker.model.meta;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "meta_tag", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class MetaTag {

    @Id
    @Column(name = "tag_id", nullable = false, updatable = false, length = 36)
    private String tagId;

    @Column(nullable = false)
    private String name;

    @PrePersist
    protected void onCreate() {
        if (this.tagId == null) {
            this.tagId = UUID.randomUUID().toString();
        }
    }
}
