package com.start;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;
import java.rmi.server.RemoteRef;

public class RemoteChromeDriverManager extends DriverManager {
    private WebDriver driver;

    @Override
    protected void startService() {

    }

    @Override
    protected void stopService() {

    }

    @Override
    protected void createDriver() {
        ChromeOptions chOptions = new ChromeOptions();
        chOptions.setCapability("Platform", Platform.MAC);
        /*DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setBrowserName("chrome");
        capability.setPlatform(Platform.MAC);*/
        try {
            URL url = new URL(urlRemoteMachine);
            driver = new RemoteWebDriver(url, chOptions);
        }catch (MalformedURLException e){
            e.printStackTrace();
            System.out.println("this URL was not reached: " + urlRemoteMachine);
        }
    }
}
