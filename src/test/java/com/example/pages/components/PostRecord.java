package com.example.pages.components;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.Selenide;
import com.example.pages.CheckPage;
import com.example.pages.FeedPage;

import java.time.Duration;

import org.openqa.selenium.By;

public class PostRecord implements CheckPage{

    private final By postRecordPath = By.xpath(".//*[@data-l=\"t,feed.posting.ui.input\"]");
    private final By enterTextPath = By.xpath(".//div[@role='textbox']");
    private final By shareRecordPath = By.xpath(".//button[@data-l='t,button.submit']");

    @Override
    public void checkPage() {
        $(postRecordPath).shouldBe(visible.because("Post record is not displayed"));
        $(enterTextPath).shouldBe(visible.because("Enter text is not displayed"));
        $(shareRecordPath).shouldBe(visible.because("Share record is not displayed"));
    }

    public PostRecord recordClick() {
        $(postRecordPath).shouldBe(visible).hover().click();
        return this;
    }

    public PostRecord enterTextClick(String text) {
        $(enterTextPath).shouldBe(visible).setValue(text);
        return this;
    }

    public FeedPage shareRecordClick() {
        $(shareRecordPath).shouldBe(visible).click();
        $(".feed-w").shouldBe(visible, Duration.ofSeconds(10));
    
    return new FeedPage();
}

    public PostRecord closeAnnoyingElementIfPresent() {
        Selenide.$(".iblock-cloud_close").shouldBe(visible, Duration.ofSeconds(2)).click();
        return this;
}
}
