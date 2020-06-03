package com.example.webflex;

import com.example.webflex.handle.UserHandler;
import com.example.webflex.service.UserService;
import com.example.webflex.service.impl.UserServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

/**
 * @author junfeng.hu
 * @create 2020-06-03 11:20
 */
public class Server2 {

    public static void main(String[] args) throws Exception{
        Server2 server = new Server2();
        server.createReactorServer();
        System.out.println("enter to exit");
        System.in.read();
    }


    public RouterFunction<ServerResponse> routingFunction(){
        UserService userService = new UserServiceImpl();
        UserHandler handler = new UserHandler(userService);
        //设置路由
        return RouterFunctions
                .route(POST("/saveuser").and(accept(MediaType.APPLICATION_JSON)), handler::saveUser)
                .andRoute(GET("/user/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::getUserById)
                .andRoute(GET("/users").and(accept(MediaType.APPLICATION_JSON)), handler::getAllUser);
    }



    public void createReactorServer(){
        RouterFunction<ServerResponse> route = routingFunction();
        HttpHandler httpHandler = toHttpHandler(route);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);

        HttpServer httpServer = HttpServer.create();
        httpServer.handle(adapter).bindNow();
    }


}
