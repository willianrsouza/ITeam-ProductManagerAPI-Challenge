package br.com.iteam.core.domain.entity;

import br.com.iteam.core.domain.enums.CategoryName;
import java.time.OffsetDateTime;
import java.util.UUID;

public class Category {
    private UUID id;
    private CategoryName name;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public Category(UUID id) {
        this.id = id;
    }

    public Category(UUID id, CategoryName name, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Category(CategoryName name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public CategoryName getName() {
        return name;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}
