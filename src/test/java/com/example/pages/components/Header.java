package com.example.pages.components;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

import org.openqa.selenium.By;

import com.example.pages.CheckPage;

public class Header implements CheckPage{
    
    private final By messagesPath = By.xpath(".//*[@data-l='t,messages']");
    private final By discussionsPath = By.xpath(".//*[@data-l='t,discussions']");
    private final By notificationsPath = By.xpath(".//*[@data-l='t,notifications']");
    private final By guestsPath = By.xpath(".//*[@data-l='t,guests']");
    private final By marksPath = By.xpath(".//*[@data-l='t,marks']");
    private final By videoPath = By.xpath(".//*[@data-l='t,video']");
    private final By musicPath = By.xpath(".//*[@data-l='t,music']");

    @Override
    public void checkPage() {
        $(messagesPath).shouldBe(visible.because("Messages path is not visible"));
        $(discussionsPath).shouldBe(visible.because("Discussions path is not visible"));
        $(notificationsPath).shouldBe(visible.because("Notifications path is not visible"));
        $(guestsPath).shouldBe(visible.because("Guests path is not visible"));
        $(marksPath).shouldBe(visible.because("Marks path is not visible"));
        $(videoPath).shouldBe(visible.because("Video path is not visible"));  
        $(musicPath).shouldBe(visible.because("Music path is not visible"));
    }
    
    public void clickMessages() {
        $(messagesPath).shouldBe(visible).click();
    }

    public void clickVideos() {
        $(videoPath).shouldBe(visible).click();
    }
}
