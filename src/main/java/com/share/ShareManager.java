package com.share;

import com.util.TestcaseProperties;
import org.openqa.selenium.By;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class ShareManager extends TestcaseProperties {

    protected abstract void startTestcase();

    public void startTest(){
        driver.findElement(By.xpath(propItem.getProperty("ItemTitle"))).click();
        driver.findElement(By.xpath(propItem.getProperty("ItemTitle"))).click();
        startTestcase();
    }

}
