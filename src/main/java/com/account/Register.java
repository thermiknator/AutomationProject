package com.account;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import com.util.ReadData;
import com.loadObjects.LoadObjProp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Register {
    private WebDriver driver;

    //Constructors
    public Register(ChromeDriver cDriver){
        this.driver = cDriver;
    }
    public Register(FirefoxDriver fDriver){
        this.driver = fDriver;
    }
    public Register(){

    }

    //register steps
    public void register() throws InterruptedException {
        signIn(new LoadObjProp().getNavBarObj());
        Thread.sleep(2000);
        authenticate(new LoadObjProp().getAuthPageObj());
        Thread.sleep(2000);
        setDataToForm(new LoadObjProp().getRegPageObj());
    }

    //Handling separate steps
    //Click on sign in
    private void signIn(Properties prop){
        driver.findElement(By.xpath(prop.getProperty("SignIn"))).click();
    }

    //authenticate
    private void authenticate(Properties prop){
        driver.findElement(By.xpath(prop.getProperty("CreateEmail"))).sendKeys("asdf@asdf.de");
        driver.findElement(By.xpath(prop.getProperty("CreateSubmit"))).click();
    }

    //set Data to Form
    private void setDataToForm(Properties prop){
        Map<String, String> values = new LinkedHashMap<>();
        ReadData rd = new ReadData();
        values = rd.readData(1, "Testdata");

        //choose Gender
        if(values.get("Gender").equals("1")){
            driver.findElement(By.xpath(prop.getProperty("Mr"))).click();
        } else {
            driver.findElement(By.xpath(prop.getProperty("Ms"))).click();
        }

        //Fill in Data
        driver.findElement(By.xpath(prop.getProperty("Firstname"))).sendKeys(values.get("Firstname"));
        driver.findElement(By.xpath(prop.getProperty("Lastname"))).sendKeys(values.get("Lastname"));
        driver.findElement(By.xpath(prop.getProperty("PW"))).sendKeys(values.get("Password"));

        //Define Select fields and choose correct date
        Select day = new Select(driver.findElement(By.xpath(prop.getProperty("DayOfBirth"))));
        Select month = new Select(driver.findElement(By.xpath(prop.getProperty("MonthOfBirth"))));
        Select year = new Select(driver.findElement(By.xpath(prop.getProperty("YearOfBirth"))));

        day.selectByVisibleText(values.get("Day"));
        month.selectByVisibleText(values.get("Month"));
        year.selectByVisibleText(values.get("Year"));

        //Newsletter and Optin
        if(values.get("Newsletter").equals("Y")){
            driver.findElement(By.xpath(prop.getProperty("Newsletter")));
        }

        if(values.get("Optin").equals("Y")){
            driver.findElement(By.xpath(prop.getProperty("Offers")));
        }

        //Fill address text fields
        driver.findElement(By.xpath(prop.getProperty("AddFirstName"))).sendKeys(values.get("AddFirstName"));
        driver.findElement(By.xpath(prop.getProperty("AddLastName"))).sendKeys(values.get("AddLastName"));
        driver.findElement(By.xpath(prop.getProperty("AddCompany"))).sendKeys(values.get("Company"));
        driver.findElement(By.xpath(prop.getProperty("AddAddress"))).sendKeys(values.get("Adress1"));
        driver.findElement(By.xpath(prop.getProperty("AddAddressTwo"))).sendKeys(values.get("Adress2"));
        driver.findElement(By.xpath(prop.getProperty("AddCity"))).sendKeys(values.get("City"));
        driver.findElement(By.xpath(prop.getProperty("AddZip"))).sendKeys(values.get("Zip"));
        driver.findElement(By.xpath(prop.getProperty("AddInfo"))).sendKeys(values.get("Additional"));
        driver.findElement(By.xpath(prop.getProperty("AddPhone"))).sendKeys(values.get("Phone"));
        driver.findElement(By.xpath(prop.getProperty("AddMobile"))).sendKeys(values.get("Mobile"));
        driver.findElement(By.xpath(prop.getProperty("AddAliasAdd"))).sendKeys(values.get("Alias"));

        //Select Country and State
        Select state = new Select(driver.findElement(By.xpath(prop.getProperty("AddState"))));
        Select country = new Select(driver.findElement(By.xpath(prop.getProperty("AddCountry"))));
        state.selectByVisibleText(values.get("State"));
        country.selectByVisibleText(values.get("Country"));

        //finish Registration
        driver.findElement(By.xpath(prop.getProperty("Register")));

    }

}
