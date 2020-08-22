package com.crafting.our_twitter.rest;

import com.crafting.our_twitter.service.UserService;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/")
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping(value = "hello")
    public String hello() {
        return "Hello Comrade";
    }

    @GetMapping(value = "login/{username}/{password}")
    public Integer login(@PathVariable(value = "username") String username, @PathVariable(value = "password")String password) {
        return userService.signIn(username, password);
    }
}
