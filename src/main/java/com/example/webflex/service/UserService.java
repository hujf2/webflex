package com.example.webflex.service;

import com.example.webflex.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author junfeng.hu
 * @create 2020-05-27 15:03
 */
public interface UserService {
    Mono<User> getUserById(int id);
    Flux<User> getAllUser();
    Mono<Void> saveUserInfo(Mono<User> user);
}
