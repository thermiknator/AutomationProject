package com.share;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class FacebookTest extends ShareManager {

    public FacebookTest(WebDriver driver){ this.driver = driver; }
    @Test
    @Override
    protected void startTestcase() {
        values = rd.readData(1, "SocMedCreds");
        String username = values.get("username");
        String password = values.get("password");
        String xpathButton = propItem.getProperty("ItemShareButton").replace("${shareEnum}", ShareEnums.FACEBOOK.toString().toLowerCase());
        String currentWindow = driver.getWindowHandle();
        driver.findElement(By.xpath(xpathButton)).click();
        for(String windowHandle : driver.getWindowHandles()){
            driver.switchTo().window(windowHandle);
        }
        driver.findElement(By.xpath(propItem.getProperty("FbUsername"))).sendKeys(username);
        driver.findElement(By.xpath(propItem.getProperty("FbUserPw"))).sendKeys(password);
        driver.findElement(By.xpath(propItem.getProperty("FbSubmit"))).click();
        driver.close();
        driver.switchTo().window(currentWindow);
        driver.get("http://automationpractice.com/index.php");
    }
}
