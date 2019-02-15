package com.hibernatetutorial.demo.controller;

import com.hibernatetutorial.demo.payload.request.GTE.SimulasiGTERequest;
import com.hibernatetutorial.demo.payload.response.GTE.SimulasiGTEResponse;
import com.hibernatetutorial.demo.payload.response.global.DataApiResponse;
import com.hibernatetutorial.demo.service.GadaiTabEmasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Api(value = "Simulasi GTE")
public class GteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GteController.class);

    @Autowired
    private GadaiTabEmasService gadaiTabEmasService;

    @ApiOperation(value = "Simulasi Gadai Tabungan Emas", response = SimulasiGTEResponse.class)
    @PostMapping(value = "/simulasi/gte", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> simulasiGTE(@Valid @RequestBody SimulasiGTERequest simulasiGTERequest) {
        DataApiResponse apiResponse = gadaiTabEmasService.simulasiKreditEmas(simulasiGTERequest);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse);
        }
        return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
