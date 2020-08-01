package com.atnt.training.calcservice.service;

import com.atnt.training.calcservice.model.CaclResponse;
import org.springframework.stereotype.Service;

@Service
public class CustomCalcService implements ICalcService {
    @Override
    public CaclResponse addNumbers(String number1, String number2) {
        System.out.println("In CustomCalcService");
        return new CaclResponse(8.0f);
    }
}
