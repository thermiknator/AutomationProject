package com.start;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class StartBrowser {
    WebDriver driver;
    //Default Contructor
    public StartBrowser() {

    }

    public WebDriver startBrowser(String browser){
        switch(browser){
            case "Chrome":
                driver = startChromeBrowser();
                break;
            case "Firefox":
                driver = startFirefoxBrowser();
                break;
        };
        return driver;
    };

    private ChromeDriver startChromeBrowser(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/Drivers/chromedriver");
        ChromeDriver cDriver = new ChromeDriver();
        return cDriver;
    };

    private FirefoxDriver startFirefoxBrowser(){
        System.setProperty("webdriver.gecko.driver", "src/main/resources/Drivers/geckodriver");
        FirefoxDriver fDriver = new FirefoxDriver();
        return fDriver;
    };

}
