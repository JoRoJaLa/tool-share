package com.jorojala.toolshare.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test {

    @GetMapping("/")
    public String getTest(){
        return "test.html";
    }
}
