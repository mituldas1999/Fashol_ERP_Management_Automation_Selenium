package org.fashol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PhoneLoginPage {

    private WebDriver driver;

    // Locators for phone login page elements
    private By phoneNumberField = By.xpath("(//input[@type='number'])[1]");  // Phone number field
    private By sendOtpButton = By.xpath("//button[normalize-space(text())='Send OTP']");  // Send OTP button
    private By otpField = By.xpath("(//input[@type='number'])[2]");  // OTP field
    private By submitButton = By.xpath("//div[contains(@class,'_input_1yq4e_71 flex')]");  // Submit button
    // Locator for "Remember Me" checkbox
    private By rememberMeCheckbox = By.xpath("//*[@id=\"remember\"]");  // Adjust the locator as per the actual element

    // Constructor
    public PhoneLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to enter phone number
    public void enterPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    // Method to click "Send OTP" button
    public void clickSendOtpButton() {
        driver.findElement(sendOtpButton).click();
    }

    // Method to enter OTP
    public void enterOtp(String otp) {
        driver.findElement(otpField).sendKeys(otp);
    }

    // Method to click the submit button
    public void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }

    // Method to click "Remember Me" checkbox (select or deselect based on the current state)
    public void toggleRememberMe() {
        if (!driver.findElement(rememberMeCheckbox).isSelected()) {
            driver.findElement(rememberMeCheckbox).click();
        }
    }

    // Combined method to perform full phone login
    public void loginWithPhone(String phoneNumber, String otp, boolean rememberMe) {
        enterPhoneNumber(phoneNumber);
        clickSendOtpButton();
        enterOtp(otp);
        if (rememberMe) {
            toggleRememberMe();  // Select "Remember Me" if true
        }
        clickSubmitButton();
    }
}
