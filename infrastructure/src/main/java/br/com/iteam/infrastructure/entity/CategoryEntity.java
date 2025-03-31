package br.com.iteam.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "CreatedAt", nullable = false, updatable = false, insertable = false)
    private OffsetDateTime createdAt;

    @Column(name = "UpdatedAt", nullable = false, insertable = false)
    private OffsetDateTime updatedAt;
}

