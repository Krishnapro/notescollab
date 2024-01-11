package com.notescollab.notescollab.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiRestController {

    @GetMapping("/greeting")
    public String greeting(){
        return "Hello world! Spring Boot Rest API";
    }
}
