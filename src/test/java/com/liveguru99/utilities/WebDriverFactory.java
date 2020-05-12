package com.liveguru99.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class WebDriverFactory {


    public static WebDriver getDriver(String browserType) {
        String projectPath = System.getProperty("user.dir");

        WebDriver driver = null;
        switch (browserType.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                //download related crx file of Extention
                // show path to addExtentions
                //import related classes
                //delete the last line driver = new ChromeDriver(); otherwise browser will open as default without extensions

                String relativePath = "drivers\\chropath_6_1_4_0.crx";
                String filePath = (projectPath + "\\" + relativePath);
                System.out.println("filePath = " + filePath);
                options.addExtensions(new File(filePath));
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                driver = new ChromeDriver(capabilities);


                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "opera":
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
                break;
        }

        return driver;
    }
}