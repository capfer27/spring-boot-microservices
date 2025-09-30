package com.promath.accounts.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    @RequestMapping(method = RequestMethod.GET, value = "/sayHello")
    public String sayHello() {
        return "Hello, world!";
    }
}
