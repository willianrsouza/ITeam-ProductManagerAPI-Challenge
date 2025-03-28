package br.com.iteam.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class Product {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
    private Integer stock;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public Product(String name, String description, BigDecimal price, Category category, Integer stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stock = stock;
    }
}
