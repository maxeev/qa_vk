package com.example.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.shadowCss;
import static com.codeborne.selenide.Selenide.*;

public class VideoPage implements CheckPage {

    private final By videoSearchPath = By.xpath("//input[@type='search']");
    private final By videoSearchResult = By.xpath("//div[contains(@class, 'video-search-result')]");
    private final By searchButton = By.xpath("//button[contains(@data-l, 'searchCtx')]");

    private final ElementsCollection searchResultsItems = $$x("//div[@data-logger='SimpleLogger']");
    private final By videoPlay = By.xpath("//div[@id='VideoAutoplayPlayerE']");
    private final By videoPreview = By.xpath(".//div[@data-module='VideoPreviewAutoplay']");

    private final SelenideElement playIcon = $(shadowCss("svg[data-testid='play-icon']", ".shadow-root-container"));
    private final SelenideElement pauseIcon = $(shadowCss("svg[data-testid='pause-icon']", ".shadow-root-container"));

    private final By loadingSpinner = By.xpath("//clipPath[contains(@id, 'ico_spinner')]");
    private final SelenideElement slider = $(shadowCss("div[role='slider']", ".shadow-root-container"));

    private final SelenideElement sliderHandle = $(shadowCss(".handleWrap .handle", ".shadow-root-container"));

    @Override
    public void checkPage() {
        $(videoSearchPath).shouldBe(visible.because("videoSearchPath is not visible"));
        $(searchButton).shouldBe(visible.because("searchButton is not visible"));
    }

    public VideoPage videoSearchClick() {
        $(videoSearchPath).shouldBe(visible, Duration.ofSeconds(2)).click();
        return this;
    }

    public VideoPage setSearchValue(String searchValue) {
        $(videoSearchPath).shouldBe(visible).click();
        $(videoSearchPath).shouldBe(visible).setValue(searchValue);
        return this;
    }

    public VideoPage searchButtonClick() {
        $(searchButton).shouldBe(visible).click();
        $(loadingSpinner).should(disappear, Duration.ofSeconds(10));
        $(videoSearchResult).should(appear, Duration.ofSeconds(10));
        return this;
    }

    public void verifyVideoSearchResult() {
        $(videoSearchResult).shouldBe(visible).should(appear, Duration.ofSeconds(5));
    }

    public VideoPage firstVideoClick() {
        SelenideElement firstSearchResult = this.searchResultsItems.first();
        firstSearchResult.$(videoPreview).click();
        $(videoPlay).shouldBe(visible, Duration.ofSeconds(5)).click();
        playIcon.shouldBe(exist, Duration.ofSeconds(10)); 
        return this;
    }

    public VideoPage moveSliderToZero() {
        int sliderWidth = slider.getSize().getWidth();
        double currentPos = this.getSliderValueDouble();

        int offsetToZero = (int) ((double) currentPos * (double) sliderWidth / 100.0);

        actions()
                .moveToElement(sliderHandle)
                .clickAndHold()
                .moveByOffset(-offsetToZero, 0)
                .release()
                .perform();

        return this;
    }

    public int getSliderValue() {
        return Integer.parseInt(slider.getAttribute("aria-valuenow"));
    }

    public double getSliderValueDouble() {
        return Double.parseDouble(slider.getAttribute("aria-valuenow"));
    }

    public VideoPage moveSlider(int targetPercent) {

       int  sliderWidth = slider.getSize().getWidth();
       int offset = (int)(sliderWidth * targetPercent * 0.01);

        actions()
                .dragAndDropBy(sliderHandle, offset, 0)
                .click()
                .perform();

        return this;
    }

    public VideoPage videoPlayClick() {
        $(videoPlay).shouldBe(exist, Duration.ofSeconds(5)).click();
        this.pauseIcon.shouldBe(exist, Duration.ofSeconds(5));
        this.playIcon.shouldNotBe(visible, Duration.ofSeconds(5));
        return this;
    }

    public VideoPage videoPauseClick() {
        $(videoPlay).shouldBe(exist, Duration.ofSeconds(5)).click();
        this.playIcon.shouldBe(exist, Duration.ofSeconds(5));
        this.pauseIcon.shouldNotBe(visible, Duration.ofSeconds(5));
        return this;
    }
}
