package com.socializing;

import com.util.TestcaseProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.Date;

public class Review extends TestcaseProperties {

    public Review(){}

    public Review(WebDriver driver){
        this.driver = driver;
    }

    @Test
    public void sendReview() throws InterruptedException {
        driver.findElement(By.xpath(propItem.getProperty("ItemTitle"))).click();
        driver.findElement(By.xpath(propItem.getProperty("ItemTitle"))).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(propItem.getProperty("ItemReviewButton"))).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(propItem.getProperty("ItemRating"))).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(propItem.getProperty("ItemReviewTitle"))).sendKeys("Test title");
        Thread.sleep(1000);
        driver.findElement(By.xpath(propItem.getProperty("ItemReviewText"))).sendKeys("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit");
        Thread.sleep(1000);
        driver.findElement(By.xpath(propItem.getProperty("ItemReviewSubmit"))).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(propItem.getProperty("ItemCloseReview"))).click();
        Thread.sleep(1000);
        if(driver.findElements(By.xpath(propItem.getProperty("ItemCloseReview"))).size()==0){
            System.out.println("Review successful");
        }else{
            System.out.println("REview was not successful at url : " + driver.getCurrentUrl() + " at " + new Date().getTime());
        }
    }
}
