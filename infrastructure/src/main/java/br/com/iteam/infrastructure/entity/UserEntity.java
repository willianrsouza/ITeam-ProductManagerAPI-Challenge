package br.com.iteam.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Role", nullable = false)
    private String role;

    @Column(name = "IsActive", nullable = false)
    private Boolean isActive;

    @Column(name = "CreatedAt", nullable = false, updatable = false, insertable = false)
    private OffsetDateTime createdAt;

    @Column(name = "UpdatedAt", nullable = false, insertable = false)
    private OffsetDateTime updatedAt;
}
