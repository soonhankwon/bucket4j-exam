package dev.bucket4jexam.controller;

import dev.bucket4jexam.controller.dto.PostAddRequest;
import dev.bucket4jexam.service.BucketService;
import dev.bucket4jexam.service.PostService;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final BucketService bucketService;

    @PostMapping
    public ResponseEntity<String> addPost(@RequestBody PostAddRequest request,
                                          HttpServletRequest httpServletRequest) {
        Bucket bucket = bucketService.resolveBucket(httpServletRequest);

        if(bucket.tryConsume(1)) {
            log.info("Valid Response & Current Bucket's Token = {}", bucket.getAvailableTokens());
            String res = postService.addPost(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }
        // 사용자의 1초에 3번을 초과한 요청 : 429 응답
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Traffic Overload");
    }
}
