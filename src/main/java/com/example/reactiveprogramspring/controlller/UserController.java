package com.example.reactiveprogramspring.controlller;

import com.example.reactiveprogramspring.model.User;
import com.example.reactiveprogramspring.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * 4:07 PM 31-Dec-22
 * Long Tran
 */
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public Mono<User> createUser() {
        return userService.save(User.builder().name("longgu").build());
    }

    @GetMapping("/all")
    public Flux<User> getAllUser() {
        return userService.getAll().log().delayElements(Duration.ofSeconds(1));
    }

    @GetMapping("/{id}")
    public Mono<User> getById(@PathVariable String id) {
        return userService.findById(id).delayElement(Duration.ofSeconds(5));
    }
}