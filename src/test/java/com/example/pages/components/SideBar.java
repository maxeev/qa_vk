package com.example.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.visible;

public class SideBar {
    public final SelenideElement profilePath = $x("//*[@data-l='t,userPage']");
    public final SelenideElement feedPath = $x("//*[@data-l='t,userMain']");
    public final SelenideElement hobbyPath = $x("//*[@data-l='t,hobby']");
    public final SelenideElement friendsPath = $x("//*[@data-l='t,userFriend']");
    public final SelenideElement photosPath = $x("//*[@data-l='t,userPhotos']");
    public final SelenideElement groupsPath = $x("//*[@data-l='t,userAltGroup']");
    public final SelenideElement gamesPath = $x("//*[@data-l='t,appsShowcaseHD']");
    public final SelenideElement giftsPath = $x("//*[@data-l='t,giftsFront']");

    public final SelenideElement postPath = $x("//button[@data-l=\"t,pf_dropdown\"]");
 
    public SideBar() {
        checkSideBar();
    }

    public void checkSideBar() {
        profilePath.shouldBe(visible.because("Profile path is not visible"));
        feedPath.shouldBe(visible.because("Feed path is not visible"));
        hobbyPath.shouldBe(visible.because("Hobby path is not visible"));
        friendsPath.shouldBe(visible.because("Friends path is not visible"));
        photosPath.shouldBe(visible.because("Photos path is not visible"));
        groupsPath.shouldBe(visible.because("Groups path is not visible"));
        gamesPath.shouldBe(visible.because("Games path is not visible"));
        giftsPath.shouldBe(visible.because("Gifts path is not visible"));
        postPath.shouldBe(visible.because("Post path is not visible"));
    }

    public boolean isDisplayed() {
        return isElementDisplayed(profilePath, "Profile path")
                && isElementDisplayed(feedPath, "Feed path")
                && isElementDisplayed(hobbyPath, "Hobby path")
                && isElementDisplayed(friendsPath, "Friends path")
                && isElementDisplayed(photosPath, "Photos path")
                && isElementDisplayed(groupsPath, "Groups path")
                && isElementDisplayed(gamesPath, "Games path")
                && isElementDisplayed(giftsPath, "Gifts path")
                && isElementDisplayed(postPath, "Post path");
    }

    private boolean isElementDisplayed(SelenideElement element, String elementName) {
        if (!element.isDisplayed()) {
            throw new AssertionError(elementName + " is not displayed!");
        }
        return true;
    }
}
