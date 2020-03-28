package com.util;

import com.loadObjects.LoadObjProp;
import com.util.RandomGenerator;
import com.util.ReadData;
import org.openqa.selenium.WebDriver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public abstract class TestcaseProperties {
    protected Properties propNav = new LoadObjProp().getNavBarObj();
    protected Properties propAuth = new LoadObjProp().getAuthPageObj();
    protected Properties propReg = new LoadObjProp().getRegPageObj();
    protected Properties propSearch = new LoadObjProp().getSearchObj();
    protected WebDriver driver;
    protected String eMailPrefix = new RandomGenerator(3).getRandomString();
    protected Map<String, String> values = new LinkedHashMap<>();
    protected ReadData rd = new ReadData();

}
