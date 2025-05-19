package com.tang.shadowbacker.repository.item;

import com.tang.shadowbacker.model.item.MetaItemTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaItemTagRepository extends JpaRepository<MetaItemTag, String> {
    boolean existsByItemIdAndTagId(String itemId, String tagId);
}
