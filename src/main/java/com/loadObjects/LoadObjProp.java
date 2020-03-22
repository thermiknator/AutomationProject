package com.loadObjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadObjProp {

    private Properties prop = new Properties();
    private FileInputStream fis;
    private String pathToProperties;

    public LoadObjProp(){

    };

    //get all objects of nav bar
    public Properties getNavBarObj(){
        pathToProperties = "naviObj.properties";
        loadProperties(pathToProperties);
        return prop;
    }

    //get all objects of authenticate page
    public Properties getAuthPageObj(){
        pathToProperties = "authObj.properties";
        loadProperties(pathToProperties);
        return prop;
    }

    //get all objects of registration page
    public Properties getRegPageObj(){
        pathToProperties = "regObj.properties";
        loadProperties(pathToProperties);
        return prop;
    }


    //load properties
    private Properties loadProperties(String pathToProperties){
        try{
            try {
                fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/Objects/" + pathToProperties);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                prop.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return prop;
    };
}
