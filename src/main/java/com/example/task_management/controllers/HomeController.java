package com.example.task_management.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/tasks")
    public String tasks() {
        return "tasks";
    }

    @GetMapping("/tags")
    public String tags() {
        return "tags"; 
    }
}