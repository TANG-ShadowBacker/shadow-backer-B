package com.tang.shadowbacker.dto;

import com.tang.shadowbacker.model.item.Item;

import java.math.BigDecimal;

public class ItemListDTO {
    private String itemId;
    private String title;
    private BigDecimal price;
    private String thumbnailUrl;
    private String sellerUsername;

    public ItemListDTO(Item item) {
        this.itemId = item.getItemId();
        this.title = item.getTitle();
        this.price = item.getPrice();
        this.thumbnailUrl = item.getThumbnailUrl();
        this.sellerUsername = item.getSeller().getUsername(); // 只暴露用户名
    }

    // Getter 生成（可以用 Lombok @Getter）
    public String getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }
}
