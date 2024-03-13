package co.develhope.interceptor2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class BasicController {

    @GetMapping("")
    public String sayHello() {
        return "Welcome";
    }
}
