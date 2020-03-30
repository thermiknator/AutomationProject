package com.search;

import com.util.TestcaseProperties;
import com.util.VerifyMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Map;

public class Search extends TestcaseProperties {
    private int searchHits;
    private String searchTerm;
    private String searchHitsString;

    public Search(){}

    public Search(WebDriver driver){
        this.driver = driver;
    }

    @Test
    public boolean searchTest() throws InterruptedException {
        boolean worksFine = false;
        values = rd.readData(1, "SearchTerms");
        for (Map.Entry<String, String> entry : values.entrySet()){
            searchTerm = entry.getKey();
            driver.findElement(By.xpath(propSearch.getProperty("MainSearchInput"))).sendKeys(searchTerm);
            driver.findElement(By.xpath(propSearch.getProperty("StartSearch"))).click();
            Thread.sleep(1000);
            searchHitsString = driver.findElement(By.xpath(propSearch.getProperty("ItemCount"))).getText();
            worksFine = getNumberOfHits(searchHitsString, entry.getValue());
            if(!entry.getValue().equals("0")) {
                worksFine = new VerifyMethods(driver, propSearch.getProperty("Item"), "verifyElementIsPresent").isPresent();
            }
            if(worksFine){
                System.out.println("Search works fine");
            }else{
                System.out.println("Search does not work fine; url: " + driver.getCurrentUrl() + " search term : " + entry.getKey() + " at date " + new Date().getTime());
            }
            driver.findElement(By.xpath(propSearch.getProperty("MainSearchInput"))).clear();
        }
        return worksFine;
    }

    private boolean getNumberOfHits(String searchHitsString, String expectedNo){
        boolean searchHits = false;
        searchHits = searchHitsString.replace(" items", "").endsWith(expectedNo);
        return searchHits;
    }

}
