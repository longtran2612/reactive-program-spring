package com.example.reactiveprogramspring.controlller;

import com.example.reactiveprogramspring.model.User;
import com.example.reactiveprogramspring.service.UserService;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

/**
 * 4:07 PM 31-Dec-22
 * Long Tran
 */
@RestController
public class UserController {

    private final UserService userService;

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public UserController(UserService userService, ReactiveMongoTemplate reactiveMongoTemplate) {
        this.userService = userService;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @PostMapping("/save")
    public Mono<User> createUser() {
        return userService.save(User.builder().name("longgu").build());
    }

    @GetMapping("/all")
    public Flux<User> getAllUser() {
        return userService.getAll().log().delayElements(Duration.ofSeconds(1));
    }

    @GetMapping("/all2")
    public Flux<?> getAllUser2() {
        return Flux.fromIterable(List.of(User.builder().name("longgu").build(), User.builder().name("longgu2").build())).map(user->{
            user.setName(user.getName().toUpperCase());
            return user.getName();
        }).filter(user->user.contains("2"));
    }
    @GetMapping("/all4")
    public Flux<?> getAllUser4() {
        return userService.getAll().concatMap(user->{
            user.setName(user.getName().toUpperCase());
            return Mono.just(user.getName());
        });
    }

    @GetMapping("/all5")
    public Flux<?> getAllUser5() {
        return userService.getAll().flatMap(user->{
            user.setName(user.getName().toUpperCase());
            return Mono.just(user.getName());
        }).flatMap(user->{
            var arr = user.split("");
            return Mono.just(arr);
        });
    }
    @GetMapping("/all6")
    public Flux<?> getAllUser6() {
        return reactiveMongoTemplate.findOne(Query.query(new Criteria()), User.class).flatMapMany(user->{
            user.setName(user.getName().toUpperCase());
            return Mono.just(user.getName());
        }).flatMap(user->{
            var arr = user.split("");
            return Mono.just(arr);
        });
    }

    @GetMapping("/all7")
    public Mono<?> getAllUser7() {
        return reactiveMongoTemplate.findOne(Query.query(new Criteria()), User.class).transform(userFlux-> userFlux.flatMap(user->{
            user.setName(user.getName().toUpperCase());
            return Mono.just(user.getName());
        }).flatMap(user->{
            var arr = user.split("");
            return Mono.just(arr);
        }));
    }
    @GetMapping("/all8")
    public Mono<?> getAllUser8() {
        return reactiveMongoTemplate.findOne(Query.query(Criteria.where("_id").is("12323")), User.class).defaultIfEmpty(User.builder().name("Dèault").build());
    }

    @GetMapping("/all9")
    public Mono<?> getAllUser9() {
        var user1 = Flux.just(User.builder().name("longgu").build(), User.builder().name("longgu2").build()).delayElements(Duration.ofSeconds(1));
        var user2 = Flux.just(User.builder().name("longgu3").build(), User.builder().name("longgu4").build()).delayElements(Duration.ofSeconds(2));

        var user3 = Mono.just(User.builder().name("longgu5").build());


        return Flux.concat(user1, user2,user3).collectList().log();


    }
    @GetMapping("/all10")
    public Mono<?> getAllUser10() {
        var user1 = Flux.just(User.builder().name("longgu").build(), User.builder().name("longgu2").build()).delayElements(Duration.ofSeconds(1));
        var user2 = Flux.just(User.builder().name("longgu3").build(), User.builder().name("longgu4").build()).delayElements(Duration.ofSeconds(2));
        var user3 = Mono.just(User.builder().name("longgu5").build());
        return Flux.merge(user1, user2,user3).collectList().log();

    }
    @GetMapping("/all11")
    public Mono<?> getAllUser11() {
        var user1 = Flux.just(User.builder().name("longgu").build(), User.builder().name("longgu2").build()).delayElements(Duration.ofSeconds(1));
        var user2 = Flux.just(User.builder().name("longgu3").build(), User.builder().name("longgu4").build()).delayElements(Duration.ofSeconds(2));
        var user3 = Mono.just(User.builder().name("longgu5").build());
        return Flux.zip(user1, user2,user3).flatMap(objects -> {
            return Mono.just(objects.getT1().getName() + objects.getT2().getName() + objects.getT3().getName());
        }).collectList().log();

    }

    @GetMapping("/all3")
    public Mono<?> getAllUser3() {
        return Mono.just(User.builder().name("longgu").build()).flatMap(user -> {
            user.setName(user.getName().toUpperCase());
            return Mono.just(user.getName());
        });
    }

    @GetMapping("/{id}")
    public Mono<User> getById(@PathVariable String id) {
        return userService.findById(id).delayElement(Duration.ofSeconds(5));
    }
}