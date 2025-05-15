package com.example.tests;

import static com.codeborne.selenide.Selenide.closeWebDriver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import com.codeborne.selenide.Configuration;
import com.example.pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;

import com.example.auth.AuthService;
import com.example.auth.DefaultAuthService;

public abstract class MainTest {

    private final AuthService authService = new DefaultAuthService();

    String login = authService.getLogin(); // теперь логин и пароль задаются через переменные окружения
    String password = authService.getPassword(); 

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
    public void authorize() {
        open(loginURL);
        webdriver().driver().getWebDriver().manage().window().maximize();
        LoginPage loginPage = new LoginPage();
        loginPage.authorize(login, password);
    }
    
    @AfterEach
    public void closeBrowser() {
        closeWebDriver();
    }
}