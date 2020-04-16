package com.start;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteFirefoxDriverManager extends DriverManager {
    private WebDriver driver;

    @Override
    protected void startService() {

    }

    @Override
    protected void stopService() {

    }

    @Override
    protected void createDriver() {
        FirefoxOptions fOptions = new FirefoxOptions();
        fOptions.setCapability("Platform", Platform.MAC);
        try{
            URL url = new URL(urlRemoteMachine);
            driver = new RemoteWebDriver(url, fOptions);
        }catch (MalformedURLException e){
            e.printStackTrace();
            System.out.println("this URL was not reached: " + urlRemoteMachine);
        }

    }
}
