package br.com.iteam.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "CategoryId", referencedColumnName = "Id")
    private CategoryEntity category;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "Price", nullable = false)
    private BigDecimal price;

    @Column(name = "Stock", nullable = false)
    private Integer stock;

    @Column(name = "CreatedAt", nullable = false, updatable = false, insertable = false)
    private OffsetDateTime createdAt;

    @Column(name = "UpdatedAt", nullable = false, insertable = false)
    private OffsetDateTime updatedAt;
}
