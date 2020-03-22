package com.account;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import com.util.ReadData;
import com.loadObjects.LoadObjProp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Register {
    private WebDriver driver;

    //Constructors
    public Register(ChromeDriver cDriver){
        this.driver = cDriver;
    }
    public Register(FirefoxDriver fDriver){
        this.driver = fDriver;
    }
    public Register(){

    }

    //register methods
    public void register() throws InterruptedException {
        signIn(new LoadObjProp().getNavBarObj());
        Thread.sleep(2000);
        authenticate(new LoadObjProp().getAuthPageObj());
        Thread.sleep(2000);
        setDataToForm(new LoadObjProp().getRegPageObj());
    }

    //Handling separate steps
    //Click on sign in
    private void signIn(Properties prop){
        driver.findElement(By.xpath(prop.getProperty("SignIn"))).click();
    }

    //authenticate
    private void authenticate(Properties prop){
        driver.findElement(By.xpath(prop.getProperty("CreateEmail"))).sendKeys("asdf@asdf.de");
        driver.findElement(By.xpath(prop.getProperty("CreateSubmit"))).click();
    }

    //set Data to Form
    private void setDataToForm(Properties prop){
        Map<String, String> values = new LinkedHashMap<>();
        ReadData rd = new ReadData();
        values = rd.readData(1, "Testdata");
    }

}
