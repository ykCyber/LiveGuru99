package com.liveguru99.tests.day02;

import com.liveguru99.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.liveguru99.utilities.Base.*;

public class testCase02 {
    /*
    Verify Sony price on carusel and own Page
     */
    WebDriver driver = WebDriverFactory.getDriver("chrome");

    @BeforeMethod
    public void setUp() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void afterMethod() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void title() {
        driver.get(URL);
        String itemName = "Xperia";
        driver.findElement(By.xpath("//a[contains(text(),'Mobile')]")).click();
        // get price of Item named Xperia
        expectedResult = driver.findElement(By.xpath("//*[@*='" + itemName + "']/..//*[@*='price']")).getText();
        //click item page
        driver.findElement(By.cssSelector("[title='" + itemName + "']")).click();
        Assert.assertTrue(driver.getTitle().contains(itemName));
        actualResult = driver.findElement(By.className("price")).getText();
        System.out.printf("itemName = %S actualReslt %s expectedResult %s" , itemName,actualResult,expectedResult);
        Assert.assertEquals(actualResult, expectedResult, "Veriy price");
    }
}
