package com.controller;
import com.account.LoginLogout;
import com.share.ShareEnums;
import com.share.ShareManager;
import com.start.DriverManager;
import com.start.DriverManagerFactory;
import com.start.DriverType;

import org.openqa.selenium.WebDriver;

public class controller {
    public static void main(String[] args){
        DriverManager driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
        WebDriver driver = driverManager.getDriver();
        driver.get("http://automationpractice.com/index.php");

        /*
        //Register new User; set Flag to true and check if validation works
        Register reg = new Register(driver, false);
        try {
            reg.register();
        }catch(InterruptedException e){
            e.printStackTrace();
        }*/

        //Login and check if success, verify validation
        LoginLogout log = new LoginLogout(driver);
        log.login(1, "Testdata");
        log.logout();
        log.loginWithWrongCredentials();
    /*
        //Search
        Search search = new Search(driver);
        try {
            boolean worksFine = search.searchTest();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //Review
        Review review = new Review(driver);
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        LoginLogout login = new LoginLogout(driver);
        login.login(1, "Testdata");
        driver.get("http://automationpractice.com/index.php");
        try {
            review.sendReview();
        }catch(InterruptedException e){
            e.printStackTrace();
        }*/

        //Share Product

        ShareManager sm = new ShareManager(ShareEnums.PINTEREST, driver);
        //sm.startTest();

    }
}
