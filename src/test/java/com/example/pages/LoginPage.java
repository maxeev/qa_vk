package com.example.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import com.codeborne.selenide.SelenideElement;

public class LoginPage implements CheckPage{

    public static SelenideElement loginxPath = $x(".//*[@id=\"field_email\"]");
    public static SelenideElement passwordxPath = $x(".//*[@id=\"field_password\"]");
    public static SelenideElement authorizeButton = $x(".//input[@data-l='t,sign_in']"); 
    public static String errMessagexPath = "//div[contains(@class, 'input-e login_error')]";

    @Override
    public void checkPage() {
        loginxPath.shouldBe(visible.because("Login field is not visible"));
        passwordxPath.shouldBe(visible.because("Password field is not visible"));
        authorizeButton.shouldBe(visible.because("Authorize button is not visible"));
    }
    public FeedPage authorize(String login, String password) {
        checkPage();
        loginxPath.setValue(login);
        passwordxPath.setValue(password);
        authorizeButton.click();
        return new FeedPage();
    }
} 

