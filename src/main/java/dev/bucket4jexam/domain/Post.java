package dev.bucket4jexam.domain;

import dev.bucket4jexam.controller.dto.PostAddRequest;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Post(PostAddRequest request, User user) {
        this.content = request.content();
        this.user = user;
    }
}
