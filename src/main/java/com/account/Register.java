package com.account;

import java.util.Properties;

import com.loadObjects.LoadObjProp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Register {
    private WebDriver driver;

    public Register(ChromeDriver cDriver){
        this.driver = cDriver;
    }
    public Register(FirefoxDriver fDriver){
        this.driver = fDriver;
    }

    public Register(){

    }

    public void register() throws InterruptedException {
        signIn(new LoadObjProp().getNavBarObj());
        System.out.println("sign in called");
        Thread.sleep(2000);
        authenticate(new LoadObjProp().getAuthPageObj());
    }

    private void signIn(Properties propNav){
        System.out.println("in method sign in before");
        driver.findElement(By.xpath(propNav.getProperty("SignIn"))).click();
        System.out.println("in method sign in after");
    }

    private void authenticate(Properties prop){
        driver.findElement(By.xpath(prop.getProperty("CreateEmail"))).sendKeys("asdf@asdf.de");
        driver.findElement(By.xpath(prop.getProperty("CreateSubmit"))).click();
    }

}
