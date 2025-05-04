package com.example.pages;

import static com.codeborne.selenide.Selenide.$x;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;

public class LoginPage{

    private final SelenideElement loginxPath = $x("//*[@id=\"field_email\"]");
    private final SelenideElement passwordxPath = $x("//*[@id=\"field_password\"]");
    private final SelenideElement authorizeButton = $x("//input[@data-l='t,sign_in']");


    public FeedPage authorize(String login, String password) {
        try {
            loginxPath.setValue(login);
            passwordxPath.setValue(password);
            authorizeButton.click();
            return new FeedPage(); 
        } catch (ElementNotFound ex) {
            throw new RuntimeException("Неправильно указан логин и/или пароль", ex);
        }
    }
} 

