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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "CATEGORYID", referencedColumnName = "Id", nullable = false)  // A chave estrangeira vai referenciar o UUID
    private CategoryEntity categoryEntity;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "STOCK", nullable = false)
    private Integer stock;

    @Column(name = "CREATEDAT", nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "UPDATEDAT", nullable = false)
    private OffsetDateTime updatedAt = OffsetDateTime.now();
}
