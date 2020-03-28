package com.account;

import com.util.TestcaseProperties;
import com.util.VerifyMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginLogout extends TestcaseProperties {
    private String username;
    private String password;

    public LoginLogout(WebDriver driver){
        this.driver = driver;
    }
    public LoginLogout(){}

    //Login
    public void login(int row, String pathToData){
        values = rd.readData(row, pathToData);
        username = values.get("Email");
        password = values.get("Password");
        navAndSetText(username, password);
        if(new VerifyMethods(driver, propReg.getProperty("Success"), "verifyElementIsPresent").isPresent()){
            System.out.println("Login was successful");
        };
    }

    //Logout
    public void logout(){
        driver.findElement(By.xpath(propNav.getProperty("SignOut"))).click();
        if(new VerifyMethods(driver, propNav.getProperty("SignIn"), "verifyElementIsPresent").isPresent()){
            System.out.println("Logout was successful");
        };
    }

    //Login with wrong credentials
    public void loginWithWrongCredentials(){
        username = "test@test.com";
        password = "test";
        navAndSetText(username, password);
        if(new VerifyMethods(driver, propAuth.getProperty("AlertText"), "verifyElementHasText", "Invalid password.").isPresent() & new VerifyMethods(driver, propAuth.getProperty("Alert"), "verifyElementHasAttribute", "#f3515c").isPresent()){
            System.out.println("Alert was triggered");
        }
    }

    private void navAndSetText(String username, String password){
        try {
            driver.findElement(By.xpath(propNav.getProperty("SignIn"))).click();
        }catch(Exception e){
            e.printStackTrace();
            driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        }
        driver.findElement(By.xpath(propAuth.getProperty("UserEmail"))).sendKeys(username);
        driver.findElement(By.xpath(propAuth.getProperty("UserPW"))).sendKeys(password);
        driver.findElement(By.xpath(propAuth.getProperty("LoginSubmit"))).click();
    }

}
