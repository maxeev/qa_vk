package com.example.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;


import com.codeborne.selenide.ElementsCollection;

public class ProfilePage extends OkPage {

    private final SelenideElement settingsPath = $x("//*[contains(@data-l, 'settings')]/a");
    private final ElementsCollection postsPath = $$x("//div[@class='feed-w']");

    private final String pageLanguage = $("html").attr("lang");

    private final By feedAction = By.xpath(".//div[@class='feed-action']");
    private final By deleteConfirm = By.xpath(".//*[contains(@class, 'form-actions_yes')]");

    private final By deleteRecordRUPath = By.xpath(".//*[text()='Удалить заметку']");
    private final By deleteRecordENPath = By.xpath(".//*[text()='Delete post']");

    public ProfilePage() {
        checkProfilePage();
    }

    public void checkProfilePage() {
        settingsPath.shouldBe(visible.because("Settings path is not visible"));
    }

    public ProfilePage deletePost() {

        final String RU = "ru";
        final String EN = "en";

        SelenideElement lastPost = this.postsPath.first();

        actions().moveToElement(lastPost.$(feedAction)).perform();
        
        if (this.pageLanguage.equals(RU)) {
            lastPost.$(deleteRecordRUPath).click();
        }
        else if (this.pageLanguage.equals(EN)) {
            lastPost.$(deleteRecordENPath).click();
        }
        else {
            throw new IllegalStateException("Неизвестный язык страницы: " + this.pageLanguage);
        }

        lastPost.$(deleteConfirm).click();
        return new ProfilePage();
    }

    public void verifyPostIsDeleted(String text, String currentTime, String timePlus1) {
        SelenideElement lastPost = this.postsPath.first();

        lastPost.$x(".//*[contains(text(), '" + text + "') and .//time[text()='" + currentTime + "' or text()='" + timePlus1 + "']]")
        .shouldNot(exist);
    }


    public void verifyPostIsPublished(String text) {
        SelenideElement lastPost = this.postsPath.first();
        lastPost.$x(".//*[text()='" + text + "']").shouldBe(visible);
    }

    public void verifyTimePublishedPost(String currentTime, String timePlus1) {
        SelenideElement lastPost = this.postsPath.first();
        lastPost.$x(".//time[text()='" + currentTime + "' or text()='" + timePlus1 + "']").shouldBe(visible);
    }

    public SettingsPage settingsClick() {
        settingsPath.click();
        return new SettingsPage();
    }

}
