package com.anothercharles.oauth2.cognito.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping(value ={"/oauth2/code/cognito"})
    public String code(){
        return "<h1>CODE</h1>";
    }
}
