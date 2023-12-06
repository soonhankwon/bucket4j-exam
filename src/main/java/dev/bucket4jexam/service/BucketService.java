package dev.bucket4jexam.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.BandwidthBuilder;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class BucketService {

    // redis 로 대체 가능 : 사용자의 JWT(Key) : Bucket(Value)
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    public Bucket resolveBucket(HttpServletRequest httpServletRequest) {
        return cache.computeIfAbsent(getJwt(httpServletRequest), this::createBucket);
    }

    private String getJwt(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader(AUTHORIZATION);
    }

    private Bucket createBucket(String apiKey) {
        Bandwidth bandwidth = BandwidthBuilder.builder()
                .capacity(3L)
                .refillIntervally(1L, Duration.ofSeconds(1L))
                .build();

        return Bucket.builder()
                .addLimit(bandwidth)
                .build();
    }
}
