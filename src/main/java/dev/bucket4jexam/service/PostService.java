package dev.bucket4jexam.service;

import dev.bucket4jexam.controller.dto.PostAddRequest;
import dev.bucket4jexam.domain.Post;
import dev.bucket4jexam.domain.User;
import dev.bucket4jexam.repository.PostRepository;
import dev.bucket4jexam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public String addPost(PostAddRequest request) {
        User user = userRepository.findUserByName(request.username())
                .orElseThrow(() -> new IllegalArgumentException("no user"));
        Post post = new Post(request, user);
        postRepository.save(post);
        return "added";
    }
}
