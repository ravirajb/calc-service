package com.atnt.training.calcservice.service;

import com.atnt.training.calcservice.exception.CalcException;
import com.atnt.training.calcservice.model.CaclResponse;
import org.springframework.stereotype.Service;

@Service
public class CalcService implements ICalcService {
    public CaclResponse addNumbers(final String number1,
                                   final String number2) {
        try {
            Float number1FloatValue = Float.parseFloat(number1);
            Float number2FloatValue = Float.parseFloat(number2);
            return new CaclResponse(number1FloatValue + number2FloatValue);
        } catch (NumberFormatException nfe) {
            throw new CalcException("Invalid input. Please pass only numbers for addition");
        }
    }
}
