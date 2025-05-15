package com.example.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage implements CheckPage{

    public static By loginxPath = By.xpath(".//*[@id=\"field_email\"]");
    public static By passwordxPath = By.xpath(".//*[@id=\"field_password\"]");
    public static By authorizeButton = By.xpath(".//input[@data-l='t,sign_in']");  
    public static By errMessagexPath = By.xpath("//div[contains(@class, 'input-e login_error')]");

    @Override
    public void checkPage() {
        $(loginxPath).shouldBe(visible.because("Login field is not visible"));
        $(passwordxPath).shouldBe(visible.because("Password field is not visible"));
        $(authorizeButton).shouldBe(visible.because("Authorize button is not visible"));
    }
    
    public FeedPage authorize(String login, String password) {
        checkPage();
        $(loginxPath).setValue(login);
        $(passwordxPath).setValue(password);
        $(authorizeButton).click();
        return new FeedPage();
    }
} 

