package ru.volkova.libraryprogect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/hello")
public class HelloController {

        @RequestMapping("/say")
        public String sayHello() {
            return "hello";
        }

    }