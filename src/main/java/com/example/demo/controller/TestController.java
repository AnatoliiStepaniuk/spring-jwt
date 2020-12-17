package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path = "/test")
public class TestController {

    @GetMapping(path = "/byEmail")
    @PreAuthorize("'anatolii.stepaniuk@gmail.com' == authentication.principal.claims['email']")
    public String byEmail(){
        return "ok";
    }

    @GetMapping(path = "/byScope")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public String byScope(){
        return "ok";
    }

    @GetMapping(path = "/byAuthority")
    @PreAuthorize("hasAuthority('USER')")
    public String byAuthority(Authentication authentication, Principal principal){
        return "ok";
    }
}
