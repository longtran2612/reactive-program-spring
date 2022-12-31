package com.example.reactiveprogramspring.service;

import com.example.reactiveprogramspring.model.User;
import com.example.reactiveprogramspring.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 4:31 PM 31-Dec-22
 * Long Tran
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> save(User user) {
        return userRepository.save(user);
    }
    public Flux<User> getAll() {
        return userRepository.findAll();
    }
    public Mono<User> findById(String id){
        return userRepository.findById(id);
    }
}
