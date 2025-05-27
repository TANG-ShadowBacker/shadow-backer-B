package com.tang.shadowbacker.controller;

import com.tang.shadowbacker.dto.ItemListDTO;
import com.tang.shadowbacker.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public Page<ItemListDTO> getAllItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size
    ) {
        return itemService.getItems(PageRequest.of(page, size, Sort.by("createdAt").descending()));
    }
}
