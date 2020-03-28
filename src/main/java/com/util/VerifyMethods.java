package com.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VerifyMethods {
    private boolean isPresent;
    private int numberOfItems;
    private WebDriver driver;

    public VerifyMethods(WebDriver driver, String xpath, int numberOfItems){
        this.driver = driver;
        numberOfItems = countElements(xpath, numberOfItems);
    }

    public VerifyMethods(WebDriver driver, String xpath, String method){
        this.driver = driver;
        switch(method){
            case "verifyElementIsPresent":
                isPresent = verifyElementIsPresent(xpath);
                break;
        }
    }

    public VerifyMethods(WebDriver driver, String xpath, String method, String attribute){
        this.driver = driver;
        switch(method){
            case "verifyElementHasText":
                isPresent = verifyElementHasText(xpath, attribute);
                break;
            case "verifyElementHasAttribute":
                isPresent = verifyElementHasAttribute(xpath, attribute);
        }
    }


    private boolean verifyElementIsPresent(String xpath){
        boolean isPresent = false;
            if(driver.findElements(By.xpath(xpath)).size() > 0){
                isPresent = true;
            }
        return isPresent;
    }

    private boolean verifyElementHasText(String xpath, String text){
        boolean hasText = false;
        if(driver.findElement(By.xpath(xpath)).getText().contains(text)){
            hasText = true;
            System.out.println("Element has text");
        }
        return hasText;
    }

    private boolean verifyElementHasAttribute(String xpath, String attribute){
        boolean hasAttribute = false;
        if(attribute.contains("#")){
            hasAttribute = checkColor(xpath, attribute);
            System.out.println("Element has attribute");
        }
        return hasAttribute;
    }

    private boolean checkColor(String xpath, String hex){
        boolean hasAttribute = false;
        if(driver.findElement(By.xpath(xpath)).getCssValue("background-color").equals(hex)){
            hasAttribute=true;
        }
        return hasAttribute;
    }

    public boolean isPresent() {
        return isPresent;
    }

    private int countElements(String xpath, int numberOfItems){
        return driver.findElements(By.xpath(xpath)).size();
    }
}
