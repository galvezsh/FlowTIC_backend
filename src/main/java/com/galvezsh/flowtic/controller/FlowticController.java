package com.galvezsh.flowtic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class FlowticController {

    @GetMapping("/flowtic")
    public String greating() {
        return "Hello world from TOMCAT!!!";
    }

}
