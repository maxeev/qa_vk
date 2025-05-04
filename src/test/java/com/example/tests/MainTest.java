package com.example.tests;

import static com.codeborne.selenide.Selenide.closeWebDriver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import com.codeborne.selenide.Configuration;
import com.example.pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public abstract class MainTest {

    private final String login = "technopol39";
    private final String password = "technopolisPassword";

    private static final String CHROME_USER_DATA_DIR = "C:/Users/Пользователь/AppData/Local/Google/Chrome/User Data" + System.currentTimeMillis();

    public final String loginURL = "https://ok.ru/";

    static {
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 100; //
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=" + CHROME_USER_DATA_DIR);
        options.addArguments("--profile-directory=Default");
        options.addArguments("--start-maximized"); 
        Configuration.browserCapabilities = options;
    }

    @BeforeEach
    public void authorize() {
        open(loginURL);

        LoginPage loginPage = new LoginPage();
        loginPage.authorize(login, password);
    }
    
    @AfterEach
    public void closeBrowser() {
        closeWebDriver();
    }
}