package com.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.server.DriverFactory;

public class VerifyMethods {
    private String xpath;
    private boolean isPresent;
    private WebDriver driver;

    public VerifyMethods(WebDriver driver, String xpath, String method){
        this.driver = driver;
        switch(method){
            case "verifyElementIsPresent":
                isPresent = verifyElementIsPresent(xpath);
                break;
        }
    }


    private boolean verifyElementIsPresent(String xpath){
        boolean isPresent = false;
            if(driver.findElements(By.xpath(xpath)).size() > 0){
                isPresent = true;
            }
        return isPresent;
    };

    public boolean isPresent() {
        return isPresent;
    }
}
