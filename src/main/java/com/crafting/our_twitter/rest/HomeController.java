package com.crafting.our_twitter.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/")
public class HomeController {

    @GetMapping(value = "hello")
    public String hello() {
        return "Hello Comrade";
    }
}
