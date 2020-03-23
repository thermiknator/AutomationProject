package com.account;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import com.util.ReadData;
import com.loadObjects.LoadObjProp;

import com.util.VerifyMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Register {
    private WebDriver driver;
    private boolean testValidation;
    Map<String, String> values = new LinkedHashMap<>();
    ReadData rd = new ReadData();

    //Constructors
    public Register(ChromeDriver cDriver, boolean testValidation){
        this.driver = cDriver;
        this.testValidation = testValidation;
    }
    public Register(FirefoxDriver fDriver, boolean testValidation){
        this.driver = fDriver;
        this.testValidation = testValidation;
    }
    public Register(){

    }

    //register steps
    public void register() throws InterruptedException {
        signIn(new LoadObjProp().getNavBarObj());
        Thread.sleep(2000);
        authenticate(new LoadObjProp().getAuthPageObj());
        Thread.sleep(2000);
        setDataToForm(new LoadObjProp().getRegPageObj(), testValidation);
    }

    //Handling separate steps
    //Click on sign in
    private void signIn(Properties prop){
        driver.findElement(By.xpath(prop.getProperty("SignIn"))).click();
    }

    //authenticate
    private void authenticate(Properties prop){
        values = rd.readData(3, "Testdata");
        driver.findElement(By.xpath(prop.getProperty("CreateEmail"))).sendKeys(values.get("Email"));
        driver.findElement(By.xpath(prop.getProperty("CreateSubmit"))).click();
    }

    //set Data to Form
    private void setDataToForm(Properties prop, boolean testValidation) throws InterruptedException{
        values = rd.readData(3, "Testdata");

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

        day.selectByValue(values.get("Day"));
        month.selectByValue(values.get("Month"));
        year.selectByValue(values.get("Year"));

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
        if(!testValidation) {
            driver.findElement(By.xpath(prop.getProperty("AddZip"))).sendKeys(values.get("Zip"));
        }else{
            driver.findElement(By.xpath(prop.getProperty("AddZip"))).sendKeys("1234");
            driver.findElement(By.xpath(prop.getProperty("Register"))).click();
            Thread.sleep(1000);
            assert (new VerifyMethods((WebDriver)driver, prop.getProperty("ZipAlert"), "verifyElementIsPresent").isPresent()==true);
        }
        driver.findElement(By.xpath(prop.getProperty("AddInfo"))).sendKeys(values.get("Additional"));
        driver.findElement(By.xpath(prop.getProperty("AddPhone"))).sendKeys(values.get("Phone"));
        driver.findElement(By.xpath(prop.getProperty("AddMobile"))).sendKeys(values.get("Mobile"));
        driver.findElement(By.xpath(prop.getProperty("AddAliasAdd"))).sendKeys(values.get("Alias"));

        //Select Country and State
        Select state = new Select(driver.findElement(By.xpath(prop.getProperty("AddState"))));
        Select country = new Select(driver.findElement(By.xpath(prop.getProperty("AddCountry"))));
        state.selectByValue(values.get("State"));

        //if Flag is true, test validation
        if(!testValidation) {
            country.selectByValue(values.get("Country"));
        }else{
            country.selectByValue("");
            driver.findElement(By.xpath(prop.getProperty("Register"))).click();
            Thread.sleep(1000);
            assert (new VerifyMethods((WebDriver)driver, prop.getProperty("CountryAlert"), "verifyElementIsPresent").isPresent()==true);
        }

        //finish Registration
        Thread.sleep(1000);
        driver.findElement(By.xpath(prop.getProperty("Register"))).click();

        //Verify Registration was successfull
        assert (new VerifyMethods((WebDriver)driver, prop.getProperty("Success"), "verifyElementIsPresent").isPresent()==true);
        System.out.println("Successfully registered");
    }

}
