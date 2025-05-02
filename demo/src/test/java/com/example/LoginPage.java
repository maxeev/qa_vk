package com.example;

import io.github.cdimascio.dotenv.Dotenv;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage{

    static final String loginxPath = "//*[@id=\"field_email\"]";
    static final String passwordxPath = "//*[@id=\"field_password\"]";
    static final String authorizeDatal = ".//*[@value='Войти в Одноклассники']";
    static final String ERROR_MESSAGE_XPATH = "//div[contains(@class, 'input-e login_error')]";
    static final String loginURL = "https://ok.ru/";
} 