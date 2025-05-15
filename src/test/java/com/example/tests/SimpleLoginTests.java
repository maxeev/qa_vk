package com.example.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;

import com.codeborne.selenide.Configuration;
import com.example.pages.LoginPage;

class SimpleLoginTests {

    public final String loginURL = "https://ok.ru/";

    static {
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 100; //
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--profile-directory=Default");
        options.addArguments("--start-maximized"); 
        Configuration.browserCapabilities = options;
    }

    @BeforeEach
    void setUp() {
        // Открываем страницу входа перед каждым тестом
        open(loginURL);
        webdriver().driver().getWebDriver().manage().window().maximize();
    }

    @Test
    @DisplayName("Тест: Неправильный логин")
    void testUnsuccessfulLogin() {
        // Попытка входа с неверными данными
        $(LoginPage.loginxPath).setValue("invalidUser ");
        $(LoginPage.passwordxPath).setValue("invalidPassword");
        $(LoginPage.authorizeButton).click();

        // Проверка, что отображается сообщение об ошибке
        String expectedErrorMessage = "Неправильно указан логин и/или пароль";
        $x(LoginPage.errMessagexPath).shouldBe(visible).shouldHave(text(expectedErrorMessage));
    }

    @Test
    @DisplayName("Тест: Пустой логин")
    void testEmptyUsername() {
        // Вводим только пароль
        $(LoginPage.passwordxPath).setValue("somePassword");
        $(LoginPage.authorizeButton).click();

        // Проверка, что отображается сообщение об ошибке
        String expectedErrorMessage = "Введите логин";
        $x(LoginPage.errMessagexPath).shouldBe(visible).shouldHave(text(expectedErrorMessage));
    }

    @Test
    @DisplayName("Тест: Пустой пароль")
    void testEmptyPassword() {
        // Вводим только логин
        $(LoginPage.loginxPath).setValue("someUser ");
        $(LoginPage.authorizeButton).click();

        // Проверка, что отображается сообщение об ошибке
        String expectedErrorMessage = "Введите пароль";
        $x(LoginPage.errMessagexPath).shouldBe(visible).shouldHave(text(expectedErrorMessage));
    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }
}
