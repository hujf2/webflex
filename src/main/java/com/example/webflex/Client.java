package com.example.webflex;

import com.example.webflex.entity.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author junfeng.hu
 * @create 2020-06-03 15:43
 */
public class Client {
    public static void main(String[] args) throws Exception {
        User book = new User();
        book.setName("name667788");
        book.setAge(11);
        book.setGeneder("xxx");

        //        --------------------------work-------------------------------

        WebClient webClient = WebClient.create("http://localhost:2530");

        Mono<Void> createdEmployee = webClient.post()
                .uri("/saveuser")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(book), User.class)
                .retrieve()
                .bodyToMono(Void.class);
        createdEmployee.block();

//        ---------------------work--------------------

//        final User user = new User();
//        user.setName("test@example.org11122");
//        user.setGeneder("xxx");
//        user.setAge(22);
//        final WebClient client = WebClient.create("http://localhost:2530");
//        final Mono<Void> createdUser = client.post()
//                .uri("/saveuser")
//                .accept(MediaType.APPLICATION_JSON)
//                .body(Mono.just(user), User.class)
//                .exchange()
//                .flatMap(response -> response.bodyToMono(Void.class));
//        System.out.println(createdUser.block());

//        --------------------------work-------------------------------

//        String id = "3";
//        User userresult2 = webClient.get().uri("/user/{id}", id)
//                .accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(User.class).block();
//        System.out.println("userresult.getName() = " + userresult2.getName());

//        --------------------------work-------------------------------

//        Flux<User> resulUserFlux =  webClient.get().uri("/users").accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(User.class);
//        resulUserFlux.map(stu -> stu.getName()).buffer().doOnNext(System.out::print).blockFirst();
    }
}
