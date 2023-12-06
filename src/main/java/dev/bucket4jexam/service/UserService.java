package dev.bucket4jexam.service;

import dev.bucket4jexam.controller.dto.UserAddRequest;
import dev.bucket4jexam.domain.User;
import dev.bucket4jexam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public String addUser(UserAddRequest request) {
        User user = new User(request);
        userRepository.save(user);
        return "added";
    }
}
