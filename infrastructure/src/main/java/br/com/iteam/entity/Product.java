package br.com.iteam.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.rmi.server.UID;
import java.time.OffsetDateTime;

@Entity
@Table(name = "Products")
public class Product {

    @Column(name = "Id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UID id;

    @Column(name = "CategoryId", nullable = false)
    private Integer categoryId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "Price", nullable = false)
    private BigDecimal price;

    @Column(name = "Stock", nullable = false)
    private Integer stock;

    @Column(name = "CreatedAt", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "UpdatedAt", nullable = false)
    private OffsetDateTime updatedAt;
}
