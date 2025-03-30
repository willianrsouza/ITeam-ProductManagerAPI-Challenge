package br.com.iteam.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    @OneToMany(mappedBy = "categoryEntity")
    private List<ProductEntity> productEntities;

    public CategoryEntity(String name, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
