package com.example.pages;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;

public class SettingsPage implements CheckPage{

    private final By personalInfoPath = By.xpath("//*[@data-l='t,profile_form']");    
    
    @Override
    public void checkPage() {
        $(personalInfoPath).shouldBe(visible.because("Personal info path is not visible"));
    }

    public PersonalInfoPage clickOnPersonalInfoBtn() {
        $(personalInfoPath).shouldBe(visible).click();
        return new PersonalInfoPage();
    }
}
