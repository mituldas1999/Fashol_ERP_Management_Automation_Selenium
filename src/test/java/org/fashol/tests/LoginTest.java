package org.fashol.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
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
import org.fashol.pages.LoginPage;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        // Setup ChromeDriver using WebDriverManager (this will automatically manage chromedriver)
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Initialize the WebDriverWait with a 10-second timeout
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to the login page
        driver.get("https://erp.fashol.com/login");
        loginPage = new LoginPage(driver); // Instantiate LoginPage
    }

    @Test
    public void testValidLogin() {
        // Perform login with valid credentials
        loginPage.login("validuser@example.com", "valid_password");

        // Wait for the login to complete (check URL or any post-login element)
        wait.until(ExpectedConditions.urlContains("dashboard"));

        // Validate successful login
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed, dashboard not found.");
    }

    @Test
    public void testInvalidLogin() {
        // Perform login with invalid credentials
        loginPage.login("invaliduser@example.com", "invalid_password");

        // Wait for error message
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".error-message")));

        // Optionally, validate the error message here if needed
        // String errorMessage = loginPage.getErrorMessage();
        // Assert.assertEquals(errorMessage, "Invalid username or password.");
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }
}
