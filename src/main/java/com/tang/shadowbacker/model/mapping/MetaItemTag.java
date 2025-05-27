package com.tang.shadowbacker.model.mapping;

import com.tang.shadowbacker.model.item.Item;
import com.tang.shadowbacker.model.meta.MetaTag;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "meta_item_tag", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"item_id", "tag_id"})
})
public class MetaItemTag {

    @Id
    @Column(name = "meta_item_id", nullable = false, updatable = false, length = 36)
    private String metaItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private MetaTag tag;

    @PrePersist
    protected void onCreate() {
        if (this.metaItemId == null) {
            this.metaItemId = UUID.randomUUID().toString();
        }
    }
}
