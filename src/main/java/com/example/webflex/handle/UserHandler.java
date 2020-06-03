package com.example.webflex.handle;

import com.example.webflex.entity.User;
import com.example.webflex.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * @author junfeng.hu
 * @create 2020-06-03 11:20
 */
public class UserHandler {
    // 调用just方式只是声明了数据流，必须调用subscribe方法才会触发数据流
    // 否则什么都不会发生
    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }


    public Mono<ServerResponse> getUserById(ServerRequest request){
        int userId = Integer.valueOf(request.pathVariable("id"));
        Mono<User> userMono = this.userService.getUserById(userId);
        return
             userMono.flatMap(person -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(person)));

    }

    public Mono<ServerResponse> getAllUser(ServerRequest request){
        Flux users = this.userService.getAllUser();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(users, User.class);
    }

    public Mono<ServerResponse> saveUser(ServerRequest request){
        Mono userMono = this.userService.saveUserInfo(request.bodyToMono(User.class));

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userMono, User.class);
    }
}
