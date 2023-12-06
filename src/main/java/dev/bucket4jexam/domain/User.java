package dev.bucket4jexam.domain;

import dev.bucket4jexam.controller.dto.UserAddRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Entity(name = "`user`")
public class User {

    @Id
    private String id;

    private String name;

    public User(UserAddRequest request) {
        this.id = UUID.randomUUID().toString();
        this.name = request.name();
    }
}
