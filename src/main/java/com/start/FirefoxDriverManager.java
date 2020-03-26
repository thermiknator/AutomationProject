package com.start;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.firefox.GeckoDriverService.Builder;

import java.io.File;

public class FirefoxDriverManager extends DriverManager{

    private GeckoDriverService gService;

    private FirefoxDriver startFirefoxBrowser(){
        System.setProperty("webdriver.gecko.driver", "src/main/resources/Drivers/geckodriver");
        FirefoxDriver fDriver = new FirefoxDriver();
        return fDriver;
    };

    @Override
    protected void startService() {
        if (null == gService){
            try{
                gService = new GeckoDriverService.Builder()
                        .usingDriverExecutable(new File("src/main/resources/Drivers/geckodriver"))
                        .usingAnyFreePort()
                        .build();
                gService.start();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void stopService() {

    }

    @Override
    protected void createDriver() {
        FirefoxOptions options = new FirefoxOptions()
                .setProfile(new FirefoxProfile());
        driver = new FirefoxDriver(gService, options);
    }
}
