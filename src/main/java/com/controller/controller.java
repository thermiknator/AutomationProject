package com.controller;
import com.start.StartBrowser;
import com.account.Register;
import com.util.ReadData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class controller {
    public static void main(String[] args){
        StartBrowser sb = new StartBrowser();
        WebDriver driver = sb.startBrowser("Chrome");
        driver.get("http://automationpractice.com/index.php");
        Register reg = new Register((ChromeDriver)driver, true);
        try {
            reg.register();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(System.getProperty("user.dir") + "/Testdata.xlsx");
        ReadData rd = new ReadData();
        System.out.println(rd.readData(1, "Testdata"));
    }
}
