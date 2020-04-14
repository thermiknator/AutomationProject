package com.start;

public class DriverManagerFactory {

    public static DriverManager getManager(DriverType type) {

        DriverManager driverManager;

        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case FIREFOX:
                driverManager = new FirefoxDriverManager();
                break;
            case REMOTECHROME:
                driverManager = new RemoteChromeDriverManager();
                break;
            case REMOTEFIREFOX:
                driverManager = new RemoteFirefoxDriverManager();
                break;
            default:
                driverManager = new SafariDriverManager();
                break;
        }
        return driverManager;

    }
}