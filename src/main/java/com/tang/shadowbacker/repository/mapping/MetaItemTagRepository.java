package com.tang.shadowbacker.repository.mapping;

import com.tang.shadowbacker.model.mapping.MetaItemTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaItemTagRepository extends JpaRepository<MetaItemTag, String> {
    boolean existsByItemIdAndTagId(String itemId, String tagId);
}
