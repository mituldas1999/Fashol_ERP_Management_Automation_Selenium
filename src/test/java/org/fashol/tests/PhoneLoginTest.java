package org.fashol.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import org.fashol.pages.PhoneLoginPage;

public class PhoneLoginTest {

    WebDriver driver;
    PhoneLoginPage phoneLoginPage;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        // Set the system property for ChromeDriver
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");  // Adjust the path

        // Initialize the WebDriver
        driver = new ChromeDriver();

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to the Phone Login page
        driver.get("https://erp.fashol.com/login");  // Replace with the actual URL
        phoneLoginPage = new PhoneLoginPage(driver);  // Instantiate the PhoneLoginPage class
    }

    @Test
    public void testValidPhoneLoginWithRememberMe() {
        // Test for valid phone login with "Remember Me" checked
        phoneLoginPage.loginWithPhone("1234567890", "123456", true);

        // Wait for the post-login element (e.g., dashboard or home page URL)
        wait.until(ExpectedConditions.urlContains("dashboard"));

        // Validate successful login by checking the current URL
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed, dashboard not found.");
    }

    @Test
    public void testInvalidPhoneLogin() {
        // Test for invalid phone login
        phoneLoginPage.loginWithPhone("1234567890", "wrongOtp", false);

        // Wait for error message
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".error-message")));

       }

    @AfterMethod
    public void tearDown() {
        // Close the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }
}
