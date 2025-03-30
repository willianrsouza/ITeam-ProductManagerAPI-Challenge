package br.com.iteam.core.domain.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class Product {
    private UUID id;

    public Product(UUID id, String name, String description, BigDecimal price, Category category, Integer stock, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
    private Integer stock;
    final OffsetDateTime createdAt;
    final OffsetDateTime updatedAt;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public Integer getStock() {
        return stock;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}
