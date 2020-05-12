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
import org.testng.collections.Lists;

import java.util.*;
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
        // find all products listed in page take them in to a list
        List<WebElement> elements = driver.findElements(By.xpath("//h2/a"));
        //create a
        List<String> firstList = new ArrayList<String>();
        for (WebElement element : elements) {
            System.out.println(element.getText());
            firstList.add(element.getText());
        }

        Assert.assertEquals(actualResult, expectedResult, "Verify title of mobile page");
        Select select = new Select(driver.findElement(By.xpath("(//select)[1]")));
        select.selectByVisibleText("Name");

        List<String> newList = Lists.newArrayList();
        elements = driver.findElements(By.xpath("//h2/a"));
        for (WebElement element : elements) {
            newList.add(element.getText());
        }
        Iterator<String> iterator = newList.iterator();
        Collections.sort(firstList);
        for (String s : firstList) {
            Assert.assertEquals(iterator.next(), s, "Verify list is sorted");
        }
        String expectedPrice = driver.findElement(By.xpath("//a[@title='Xperia']/..//*[@class='price']")).getText();
        System.out.println(expectedPrice);
        actions.moveToElement(driver.findElement(By.partialLinkText("xperia"))).click().build().perform();


        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String actualPrice = driver.findElement(By.cssSelector(".price")).getText();
        Assert.assertEquals(actualResult, expectedPrice);

    }
}
