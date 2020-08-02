package com.atnt.training.calcservice.service;

import com.atnt.training.calcservice.dao.CalcDAO;
import com.atnt.training.calcservice.exception.CalcException;
import com.atnt.training.calcservice.model.CaclResponse;
import com.atnt.training.calcservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CalcService {

    @Autowired
    private CalcDAO calcDAO;

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

    public void saveUser(final String name, final String lastName) {
        this.calcDAO.saveUser(name, lastName);
    }

    public User getUser(final int id) {
        return this.calcDAO.getUser(id);
    }
}
