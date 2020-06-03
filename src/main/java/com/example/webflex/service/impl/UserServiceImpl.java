package com.example.webflex.service.impl;

import com.example.webflex.entity.User;
import com.example.webflex.service.UserService;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author junfeng.hu
 * @create 2020-05-27 15:21
 */
@Repository
public class UserServiceImpl implements UserService {

    private final Map<Integer, User> users = new HashMap<>();

    public UserServiceImpl(){
        this.users.put(1,new User("zhangshan", "男", 29));
        this.users.put(2,new User("lisi", "女", 29));
        this.users.put(3,new User("wangwu", "男", 29));
    }

    @Override
    public Mono<User> getUserById(int id) {
        return Mono.justOrEmpty(this.users.get(id));
    }

    @Override
    public Flux<User> getAllUser() {
        return Flux.fromIterable(this.users.values());
    }

    @Override
    public Mono<Void> saveUserInfo(Mono<User> userMono) {
        return userMono.doOnNext(person -> {
            int id = users.size()+1;
            users.put(id, person);
        }).thenEmpty(Mono.empty());
    }
}
