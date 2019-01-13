package com.hibernatetutorial.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> indexSpringBootAPI() {
        LinkedHashMap<String,Object> context = new LinkedHashMap<>();
        context.put("appName","Rest API Dian");
        context.put("Date",new Date().getTime());
        context.put("github","URL GITHUB");
        return ResponseEntity.ok(context);
    }
}