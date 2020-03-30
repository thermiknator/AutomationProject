package com.share;

import com.util.TestcaseProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Date;

public class ShareManager extends TestcaseProperties {
    ShareEnums type;

    public ShareManager(ShareEnums type, WebDriver driver){
        this.type = type;
        this.driver = driver;
    }

    public ShareManager(){

    }

    public void startTest(){
        values = rd.readData(1, "SocMedCreds");
        String username = values.get("username");
        String password = values.get("password");
        String currentWindow = driver.getWindowHandle();
        driver.findElement(By.xpath(propItem.getProperty("ItemTitle"))).click();
        driver.findElement(By.xpath(propItem.getProperty("ItemTitle"))).click();
        clickOnButton(type);
        changeWindow();
        interact(type, username, password);
        driver.close();
        driver.switchTo().window(currentWindow);
        driver.get("http://automationpractice.com/index.php");
    }

    private void changeWindow(){
        for(String windowHandle : driver.getWindowHandles()){
            driver.switchTo().window(windowHandle);
        }
    }

    private void clickOnButton(ShareEnums type){
        String network;

        switch (type){
            case FACEBOOK:
                network = ShareEnums.FACEBOOK.toString().toLowerCase();
            break;
            case TWITTER:
                network = ShareEnums.TWITTER.toString().toLowerCase();
            break;
            case PINTEREST:
                network = ShareEnums.PINTEREST.toString().toLowerCase();
            break;
            default:
                network = ShareEnums.GOOGLE.toString().toLowerCase();
            break;
        }

        String xpathButton = propItem.getProperty("ItemShareButton").replace("${shareEnum}",
                network);
        driver.findElement(By.xpath(xpathButton)).click();
    }

    private void interact (ShareEnums type, String username, String password){
        try {
            switch (type) {
                case FACEBOOK:
                    driver.findElement(By.xpath(propItem.getProperty("FbUsername"))).sendKeys(username);
                    driver.findElement(By.xpath(propItem.getProperty("FbUserPw"))).sendKeys(password);
                    driver.findElement(By.xpath(propItem.getProperty("FbSubmit"))).click();
                    successMessage(type, true);
                    break;
                case TWITTER:
                    driver.findElement(By.xpath(propItem.getProperty("Twitter"))).click();
                    successMessage(type, true);
                    break;
                case PINTEREST:
                    driver.findElement(By.xpath(propItem.getProperty("Pinterest"))).click();
                    successMessage(type, true);
                    break;
                default:
                    successMessage(type, true);
                    break;
            }
        }catch(Exception e){
            successMessage(type, false);
        }
    }

    private void successMessage(ShareEnums type, boolean success){
        String message = "Test of " + type.toString() + " at " + driver.getCurrentUrl() + " at " + new Date().getTime() +
                " is success: " + success;
        System.out.println(message);
    }

}
