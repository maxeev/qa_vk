package com.example;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainTest {

    static {
        Configuration.browser = "chrome";
        Configuration.headless = false;
    }

    @BeforeEach
    public void setUp() {
        // Открываем страницу входа перед каждым тестом
        open(LoginPage.LOGIN_URL);
    }

    @Test
    public void testUnsuccessfulLogin() {
        // Попытка входа с неверными данными
        LoginPage.login("invalidUser ", "invalidPassword");

        // Проверка, что отображается сообщение об ошибке
        String expectedErrorMessage = "Неправильно указан логин и/или пароль";
        $x(LoginPage.ERROR_MESSAGE_XPATH).shouldHave(visible).shouldHave(text(expectedErrorMessage));
    }

    @Test
    public void testEmptyUsername() {
        // Вводим только пароль
        $x(LoginPage.PASSWORD_XPATH).setValue("somePassword");
        $x(LoginPage.AUTH_BUTTON_XPATH).click();

        // Проверка, что отображается сообщение об ошибке
        String expectedErrorMessage = "Введите логин";
        $x(LoginPage.ERROR_MESSAGE_XPATH).shouldHave(visible).shouldHave(text(expectedErrorMessage));
    }

    @Test
    public void testEmptyPassword() {
        // Вводим только логин
        $x(LoginPage.LOGIN_XPATH).setValue("someUser ");
        $x(LoginPage.AUTH_BUTTON_XPATH).click();

        // Проверка, что отображается сообщение об ошибке
        String expectedErrorMessage = "Введите пароль";
        $x(LoginPage.ERROR_MESSAGE_XPATH).shouldHave(visible).shouldHave(text(expectedErrorMessage));
    }

    @Test
    public void testSuccessfulLogin() {
        Dotenv dotenv = Dotenv.load();
        open(LoginPage.LOGIN_URL);
        
        // Вводим верные логин и пароль
        $x(LoginPage.LOGIN_XPATH).setValue("technopol39");
        $x(LoginPage.PASSWORD_XPATH).setValue("technopolisPassword");
        $x(LoginPage.AUTH_BUTTON_XPATH).click();

        // Проверяем, что мы поали на страницу FeedPage
        $x(FeedPage.HOBBIES_XPATH).shouldBe(visible);
        $x(FeedPage.MOMENTS_XPATH).shouldBe(visible);
}

    @AfterEach
    public void closeBrowser() {
        closeWebDriver();
    }
}