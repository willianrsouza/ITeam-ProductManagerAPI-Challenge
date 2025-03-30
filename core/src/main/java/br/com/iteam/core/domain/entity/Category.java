package br.com.iteam.core.domain.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

public class Category {
    private UUID id;
    private String name;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public Category(UUID id, String name, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}
