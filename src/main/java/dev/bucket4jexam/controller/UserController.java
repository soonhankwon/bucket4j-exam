package dev.bucket4jexam.controller;

import dev.bucket4jexam.controller.dto.UserAddRequest;
import dev.bucket4jexam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserAddRequest request) {
        String res = userService.addUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}
