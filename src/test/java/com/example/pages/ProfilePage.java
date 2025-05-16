package com.example.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.actions;

import java.util.Map;

import java.time.Duration;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;

public class ProfilePage implements CheckPage {

    private final By settingsPath = By.xpath("//*[contains(@data-l, 'settings')]/a");
    private final ElementsCollection postsPath = $$x(".//div[@class='feed-w']");

    private final String pageLanguage = $("html").attr("lang");

    private final By feedAction = By.xpath(".//div[@class='feed-action']");
    private final By deleteConfirm = By.xpath(".//*[contains(@class, 'form-actions_yes')]");

    private final By deleteRecordRUPath = By.xpath(".//*[text()='Удалить заметку']");
    private final By deleteRecordENPath = By.xpath(".//*[text()='Delete post']");

    private static final String MESSAGE_TIME_XPATH_TEMPLATE = ".//*[contains(text(), '%s') and .//time[text()='%s' or text()='%s']]";

    private final Map<String, By> deletePaths = Map.of(
    "ru", deleteRecordRUPath,
    "en", deleteRecordENPath
    );

    @Override
    public void checkPage() {
        $(settingsPath).shouldBe(visible.because("Settings path is not visible"));
    }

    public ProfilePage deletePost() {
        SelenideElement lastPost = this.postsPath.first()
            .shouldBe(visible, Duration.ofSeconds(10));
        
        actions()
            .moveToElement(lastPost)
            .pause(500)
            .moveToElement(lastPost.$(feedAction).shouldBe(visible))
            .pause(500)
            .perform();
        
        By deletePath = deletePaths.get(this.pageLanguage.toLowerCase());
        if (deletePath == null) {
            throw new IllegalStateException("Неизвестный язык страницы: " + this.pageLanguage);
        }
        
        lastPost.$(deletePath)
            .shouldBe(interactable, Duration.ofSeconds(5))
            .click();
        
        $(deleteConfirm)
            .shouldBe(visible, Duration.ofSeconds(5))
            .shouldBe(interactable, Duration.ofSeconds(5))
            .click();
        
        $(deleteConfirm).shouldBe(hidden, Duration.ofSeconds(5));
        
        return new ProfilePage();
    }

    public void verifyPostIsDeleted(String text, String currentTime, String timePlus1Minute) {
        SelenideElement lastPost = this.postsPath.first();
        String fullXpath = String.format(
                MESSAGE_TIME_XPATH_TEMPLATE,
                text,
                currentTime,
                timePlus1Minute
        );
        lastPost.$x(fullXpath).shouldNot(exist);
    }

    public void verifyPostIsPublished(String text) {
        SelenideElement post = $x(String.format("//div[contains(@class, 'feed-w')]//*[contains(text(), '%s')]", text))
            .shouldBe(visible, Duration.ofSeconds(10));
        
        if (!post.isDisplayed()) {
            Selenide.executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
            post.shouldBe(visible, Duration.ofSeconds(5));
        }
    }

    public SettingsPage settingsClick() {
        $(settingsPath)
            .shouldBe(visible).click();
            return new SettingsPage();
    }

}
