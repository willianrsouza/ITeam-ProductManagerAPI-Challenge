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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CREATEDAT", nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "UPDATEDAT", nullable = false)
    private OffsetDateTime updatedAt = OffsetDateTime.now();
}
