package com.tang.shadowbacker.repository.meta;

import com.tang.shadowbacker.model.meta.MetaTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaTagRepository extends JpaRepository<MetaTag, String> {
    boolean existsByName(String name);
}
