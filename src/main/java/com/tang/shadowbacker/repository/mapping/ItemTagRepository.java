package com.tang.shadowbacker.repository.mapping;

import com.tang.shadowbacker.model.mapping.ItemTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemTagRepository extends JpaRepository<ItemTag, String> {
    boolean existsByItem_ItemIdAndTag_TagId(String itemId, String tagId);
}
