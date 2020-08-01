package com.atnt.training.calcservice.utils;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static Map<String, String> OPERATIONS_LIST =
            new HashMap<>() {{
                put("add-numbers", "Adds two numbers and returns a response.");
                put("subtract-numbers", "Subtract number2 from number1 and return a response.");
                put("multiply-numbers", "Multiply number1 with number2 and returns a response.");
                put("divide-numbers", "divide number1 with number2 and returns the quotient.");
            }};
}
