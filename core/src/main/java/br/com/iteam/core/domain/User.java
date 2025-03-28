package br.com.iteam.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class User {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String role = "user";
    private Boolean isActive = true;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
