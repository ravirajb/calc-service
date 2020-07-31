package com.atnt.training.calcservice.controller;

import com.atnt.training.calcservice.exception.CalcException;
import com.atnt.training.calcservice.model.CaclError;
import com.atnt.training.calcservice.model.CaclResponse;
import com.atnt.training.calcservice.service.CalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConfigurationProperties
@EnableConfigurationProperties
public class CalcController {

    @Autowired
    private CalcService calcService;

    @GetMapping(value = "${calc-service.api.version}" + "/add-numbers")
    public ResponseEntity<?> addNumbers(@RequestParam(name = "number1") final String number1,
                                        @RequestParam(name = "number2") final String number2) {
        HttpHeaders responseHeaders = new HttpHeaders();

        // POJO --> Plain Old Java Object
        try {
            final CaclResponse retVal = calcService.addNumbers(number1, number2);
            return new ResponseEntity<>(retVal,
                    responseHeaders,
                    HttpStatus.OK);
        } catch (CalcException ce) {
            return new ResponseEntity<>(new CaclError(ce.getMessage()),
                    responseHeaders,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TODO - Create end point for Subtraction
    // TODO - Create end point for multiplication
    // TODO - Create end point for Division

}
