package com.example.pages;

import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

public class FeedPage implements CheckPage {

    public static By momentsPath = By.xpath(".//*[@data-l='t,to_moments']");
    public static By hobbiesPath = By.xpath(".//*[@data-l='t,to_hobbies']");

    @Override
    public void checkPage() {
        $(momentsPath).shouldBe(visible.because("Moments path is not visible"));
        $(hobbiesPath).shouldBe(visible.because("Hobbies path is not visible"));
    }

}


