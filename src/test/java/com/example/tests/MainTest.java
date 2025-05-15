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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MainTest {
    private static final Logger log = LoggerFactory.getLogger(MainTest.class);

    public final String loginURL = "https://ok.ru/";

    static {
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 100; 
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--profile-directory=Default");
        options.addArguments("--start-maximized"); 
        Configuration.browserCapabilities = options;
    }

    @BeforeEach
    void setUp() {
        log.info("Открываем страницу входа перед каждым тестом");
        open(loginURL);
        webdriver().driver().getWebDriver().manage().window().maximize();
    }

    @Test
    @DisplayName("Тест: Неправильный логин")
    void testUnsuccessfulLogin() {
        log.info("Попытка входа с неверными данными");
        $(LoginPage.loginxPath).setValue("invalidUser ");
        $(LoginPage.passwordxPath).setValue("invalidPassword");
        $(LoginPage.authorizeButton).click();

        log.info("Проверка, что отображается нужное сообщение об ошибке");
        String expectedErrorMessage = "Неправильно указан логин и/или пароль";
        $(LoginPage.errMessagexPath).shouldBe(visible).shouldHave(text(expectedErrorMessage));
    }

    @Test
    @DisplayName("Тест: Пустой логин")
    void testEmptyUsername() {
        log.info("Вводим только пароль");
        $(LoginPage.passwordxPath).setValue("somePassword");
        $(LoginPage.authorizeButton).click();

        log.info("Проверка, что отображается нужное сообщение об ошибке");
        String expectedErrorMessage = "Введите логин";
        $(LoginPage.errMessagexPath).shouldBe(visible).shouldHave(text(expectedErrorMessage));
    }

    @Test
    @DisplayName("Тест: Пустой пароль")
    void testEmptyPassword() {
        log.info("Проверка, вводим только логин");
        $(LoginPage.loginxPath).setValue("someUser ");
        $(LoginPage.authorizeButton).click();

        log.info("Проверка, что отображается нужное сообщение об ошибке");
        String expectedErrorMessage = "Введите пароль";
        $(LoginPage.errMessagexPath).shouldBe(visible).shouldHave(text(expectedErrorMessage));
    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }
}
