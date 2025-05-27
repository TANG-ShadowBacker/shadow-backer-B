package com.tang.shadowbacker.service;

import com.tang.shadowbacker.dto.ItemListDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {
    Page<ItemListDTO> getItems(Pageable pageable);
}