package com.hibernatetutorial.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;

@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> indexSpringBootAPI() {
        LinkedHashMap<String,Object> context = new LinkedHashMap<>();
        context.put("appName","Rest API Dian");
        context.put("Date",new Date().getTime());
        context.put("github","URL GITHUB");
        return ResponseEntity.ok(context);
    }

    @GetMapping(value = "hello")
    public HashMap<String,String> helloWorldInternational(){
        HashMap<String,String> context = new HashMap<>();
        context.put("locale",LocaleContextHolder.getLocale().toString());
        context.put("good.morning.message",messageSource.getMessage("good.morning.message",null,LocaleContextHolder.getLocale()));
        return context;
    }

}