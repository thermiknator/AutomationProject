package com.util;

import com.loadObjects.LoadObjProp;
import com.start.DriverManager;
import com.start.DriverManagerFactory;
import com.start.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public abstract class TestcaseProperties {
    protected Properties propNav = new LoadObjProp().getNavBarObj();
    protected Properties propAuth = new LoadObjProp().getAuthPageObj();
    protected Properties propReg = new LoadObjProp().getRegPageObj();
    protected Properties propSearch = new LoadObjProp().getSearchObj();
    protected Properties propItem = new LoadObjProp().getItemObj();
    protected WebDriver driver;
    protected String eMailPrefix = new RandomGenerator(3).getRandomString();
    protected Map<String, String> values = new LinkedHashMap<>();
    protected ReadData rd = new ReadData();
    protected DriverManager driverManager;

    @BeforeClass
    @Parameters("driverType")
    public void startUp(String driverType){
        DriverType type;
        switch (driverType.toLowerCase()){
            case "firefox":
                type = DriverType.FIREFOX;
            break;
            case "ie11":
                type = DriverType.IE;
            break;
            default:
                type = DriverType.CHROME;
            break;
        }
        driverManager = DriverManagerFactory.getManager(type);
        driver = driverManager.getDriver();
    }

    @BeforeMethod
    @AfterMethod
    public void startAndEndMethod(){
        driver.get("http://automationpractice.com/index.php");
    }

    @AfterClass
    public void tearDown(){
        driverManager.quitDriver();
    }

}
