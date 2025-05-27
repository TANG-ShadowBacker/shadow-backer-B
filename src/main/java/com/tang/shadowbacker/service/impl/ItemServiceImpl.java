package com.tang.shadowbacker.service.impl;

import com.tang.shadowbacker.dto.ItemListDTO;
import com.tang.shadowbacker.repository.item.ItemRepository;
import com.tang.shadowbacker.service.ItemService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional
    public Page<ItemListDTO> getItems(Pageable pageable) {
        return itemRepository.findAll(pageable).map(ItemListDTO::new);
    }
}