package com.util;

import java.nio.charset.Charset;
import java.util.Random;

public class RandomGenerator {
    //Fields
    private String randomString;
    private int length;

    //Constructors
    public RandomGenerator(int length){
        this.length = length;
    }

    public RandomGenerator(){
    }

    //Getter and Setter
    public String getRandomString() {
        randomString = generateRandomString();
        return randomString;
    }

    private String generateRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

}
