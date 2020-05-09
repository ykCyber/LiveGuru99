package com.liveguru99.tests.day01;

import com.liveguru99.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.liveguru99.utilities.Base.*;

public class testCase01 {
    /*
    Verify homapage title
    Verify mobilepage title
    Verify sort by name is OK
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
    }

    @Test
    public void title() {
        driver.get(URL);

        WebElement demoSiteString = driver.findElement(By.tagName("h2"));
        actualResult = demoSiteString.getText();
        expectedResult = "This is demo";
        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertTrue(demoSiteString.isDisplayed());

    }

    @Test
    public void test() {
        driver.get(URL);
        Actions actions = new Actions(driver);
        WebElement mobileLink = driver.findElement(By.xpath("//a[contains(text(),'Mobile')]"));
        actions.moveToElement(mobileLink).click().build().perform();
        actualResult = driver.getTitle();
        expectedResult = "Mobile";
        Assert.assertEquals(actualResult, expectedResult, "Verify title of mobile page");
        Select select = new Select(driver.findElement(By.xpath("//*[*='Sort By']")));
        select.selectByValue("Name");

    }
}
