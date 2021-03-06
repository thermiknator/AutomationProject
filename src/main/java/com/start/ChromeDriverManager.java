package com.start;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class ChromeDriverManager extends DriverManager {

    private ChromeDriverService chService;

    @Override
    public void startService() {
        String pathname = getPathName();
        if (null == chService) {
            try {
                chService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(pathname))
                        .usingAnyFreePort()
                        .build();
                chService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if (null != chService && chService.isRunning())
            chService.stop();
    }

    @Override
    public void createDriver() {
       // DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
       // capabilities.setCapability(ChromeOptions.CAPABILITY, options);
       // driver = new ChromeDriver(chService, capabilities);
        driver = new ChromeDriver(chService, options);
    }

    private String getPathName(){
        String chromedriverPath = "src/main/resources/Drivers/chromedriver";
        if(System.getProperty("os.name").toLowerCase().contains("win")) {
            chromedriverPath += ".exe";
        }
        return chromedriverPath;
    }

}
