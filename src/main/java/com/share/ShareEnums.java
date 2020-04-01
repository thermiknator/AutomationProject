package com.share;

import org.testng.annotations.DataProvider;

public enum ShareEnums {
    TWITTER,
    PINTEREST,
    GOOGLE,
    FACEBOOK;

    @DataProvider(name = "data-provider")
    public static Object[][] getEnums(){
        return new Object[][]{ {1, "SocMedCreds", TWITTER}, {1, "SocMedCreds", PINTEREST}, {1, "SocMedCreds", GOOGLE}, {1, "SocMedCreds", FACEBOOK} };
    }
}
