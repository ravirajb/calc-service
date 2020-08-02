package com.atnt.training.calcservice.controller;

import com.atnt.training.calcservice.exception.CalcException;
import com.atnt.training.calcservice.model.CaclError;
import com.atnt.training.calcservice.model.CaclResponse;
import com.atnt.training.calcservice.model.CalcPayload;
import com.atnt.training.calcservice.model.User;
import com.atnt.training.calcservice.service.CalcService;
import com.atnt.training.calcservice.service.CustomCalcService;
import com.atnt.training.calcservice.service.ICalcService;
import com.atnt.training.calcservice.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        ICalcService calcService = new CustomCalcService();
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

    @PostMapping("${calc-service.api.version}" + "/user/")
    public ResponseEntity<?> saveUser(@RequestBody final User user) {
        HttpHeaders responseHeaders = new HttpHeaders();
        calcService.saveUser(user.getName(), user.getLastName());
        return new ResponseEntity<>(user.getName() + " saved successfully",
                responseHeaders,
                HttpStatus.OK);
    }

    @GetMapping("${calc-service.api.version}" + "/user/{userId}")
    public ResponseEntity<?> saveUser(@PathVariable final int userId) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(calcService.getUser(userId),
                responseHeaders,
                HttpStatus.OK);
    }

    @GetMapping(value = "${calc-service.api.version}" + "/list/{method}")
    public ResponseEntity<?> getOperationDetails(@PathVariable final String method) {
        HttpHeaders responseHeaders = new HttpHeaders();

        if (Constants.OPERATIONS_LIST.containsKey(method)) {
            return new ResponseEntity<>(Constants.OPERATIONS_LIST.get(method),
                    responseHeaders,
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new CaclError("passed method is not found"),
                    responseHeaders,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("${calc-service.api.version}" + "/list/")
    public ResponseEntity<?> getOperationDetails(@RequestBody CalcPayload calcPayload) {
        HttpHeaders responseHeaders = new HttpHeaders();
        if (calcPayload.getMethod() != null &&
                !Constants.OPERATIONS_LIST.containsKey(calcPayload.getMethod())) {
            Constants.OPERATIONS_LIST.put(calcPayload.getMethod(), calcPayload.getDescription());

            return new ResponseEntity<>(calcPayload.getMethod() + "Successfully Listed",
                    responseHeaders,
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new CaclError("Invalid payload or method already listed"),
                    responseHeaders,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "${calc-service.api.version}" + "/list/")
    public ResponseEntity<?> getAllOperationDetails() {
        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<>(Constants.OPERATIONS_LIST,
                responseHeaders,
                HttpStatus.OK);
    }


    // TODO - Create end point for Subtraction
    // TODO - Create end point for multiplication
    // TODO - Create end point for Division

}
