package br.com.iteam.core.domain.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
    private Integer stock;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

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

    public Product(String name, String description, BigDecimal price, Category category, Integer stock) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

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

    public void setCategory(Category category) {
        this.category = category;
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
