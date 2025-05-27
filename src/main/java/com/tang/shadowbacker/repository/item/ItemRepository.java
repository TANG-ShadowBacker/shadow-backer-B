package com.tang.shadowbacker.repository.item;

import com.tang.shadowbacker.model.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {
}
