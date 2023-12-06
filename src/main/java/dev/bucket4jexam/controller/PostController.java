package dev.bucket4jexam.controller;

import dev.bucket4jexam.controller.dto.PostAddRequest;
import dev.bucket4jexam.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<String> addPost(@RequestBody PostAddRequest request) {
        String res = postService.addPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}
