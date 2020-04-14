package com.start;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.firefox.GeckoDriverService.Builder;

import java.io.File;

public class FirefoxDriverManager extends DriverManager{

    private GeckoDriverService gService;

    @Override
    protected void startService() {
        String pathname = getPathName();
        if (null == gService){
            try{
                gService = new GeckoDriverService.Builder()
                        .usingDriverExecutable(new File(pathname))
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
        if(null != gService && gService.isRunning()){
            gService.stop();
        }
    }

    @Override
    protected void createDriver() {
        FirefoxOptions options = new FirefoxOptions()
                .setProfile(new FirefoxProfile());
        driver = new FirefoxDriver(gService, options);
    }

    private String getPathName(){
        String ffdriverPath = "src/main/resources/Drivers/geckodriver";
        if(System.getProperty("os.name").toLowerCase().contains("win")) {
            ffdriverPath += ".exe";
        }
        return ffdriverPath;
    }
}
