package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // 'templates/home.html'을 의미하거나, 다른 경로로 리디렉션할 수 있습니다.
    }
}