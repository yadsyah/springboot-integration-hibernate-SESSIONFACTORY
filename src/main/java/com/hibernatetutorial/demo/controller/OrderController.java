package com.hibernatetutorial.demo.controller;

import com.hibernatetutorial.demo.payload.request.OrderRequest;
import com.hibernatetutorial.demo.payload.response.global.DataApiResponse;
import com.hibernatetutorial.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/order",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest){
        try{
            DataApiResponse dataApiResponse = orderService.orderProduct(orderRequest);
            return ResponseEntity.ok(dataApiResponse);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(new DataApiResponse(false,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/order/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllOrder(){
        try{
            DataApiResponse apiResponse = orderService.getAllOrderProduct();
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(new DataApiResponse(false,e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
