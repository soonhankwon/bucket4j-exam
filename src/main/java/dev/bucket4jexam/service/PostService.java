package dev.bucket4jexam.service;

import dev.bucket4jexam.controller.dto.PostAddRequest;
import dev.bucket4jexam.domain.Post;
import dev.bucket4jexam.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public String addPost(PostAddRequest request) {
        Post post = new Post(request);
        postRepository.save(post);
        return "added";
    }
}
