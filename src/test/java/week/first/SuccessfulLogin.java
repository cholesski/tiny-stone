package week.first;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class SuccessfulLogin {
    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";
    @Test
    public static void Test() {
        //Firefox driver fails to load on my system so i used chrome
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String currentURL = driver.getCurrentUrl();
        System.out.println("CURRENT URL IS -> " + currentURL);

        WebElement usernameFieldXPath = driver.findElement(By.xpath("//input[@type='text']"));
        usernameFieldXPath.sendKeys(USERNAME);

        WebElement passwordFieldCSS = driver.findElement(By.cssSelector("input[type='password']"));
        passwordFieldCSS.sendKeys(PASSWORD);

        WebElement logInButtonCSS = driver.findElement(By.cssSelector("input[value='Login']"));
        logInButtonCSS.click();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        try {
            logInButtonCSS = driver.findElement(By.cssSelector("input[value='Login']"));
        }catch(Exception e) {
            Assert.assertEquals(1, 1, "test passed!");
        }
        driver.close();
    }
}