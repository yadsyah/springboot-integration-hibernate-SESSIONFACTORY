package com.hibernatetutorial.demo.controller;

import com.hibernatetutorial.demo.payload.request.ItemProductRequest;
import com.hibernatetutorial.demo.payload.response.global.DataApiResponse;
import com.hibernatetutorial.demo.service.ItemProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ItemProductService itemProductService;

    @PostMapping(value = "/product", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addProductItem(@RequestBody ItemProductRequest itemProductRequest) {
        try {
            DataApiResponse response = itemProductService.saveItemProduct(itemProductRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new DataApiResponse(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/all/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllProductItemPaging(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit) {
        try {
            DataApiResponse response = itemProductService.findAllPaging(page, limit);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new DataApiResponse(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/product/{idItemProduct}")
    public ResponseEntity<?> getOneItemProduct(@PathVariable("idItemProduct")String idItemProduct){
        try{
            DataApiResponse response = itemProductService.getOneItemProduct(idItemProduct);
            return ResponseEntity.ok(response);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(new DataApiResponse(false,e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
