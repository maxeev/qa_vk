package com.example.pages.components;

import static com.codeborne.selenide.Selenide.$;

import com.example.pages.CheckPage;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;

public class SideBar implements CheckPage{
    public final By feedPath = By.xpath(".//*[@data-l='t,userMain']");
    public final By hobbyPath = By.xpath(".//*[@data-l='t,hobby']");
    public final By friendsPath = By.xpath(".//*[@data-l='t,userFriend']");
    public final By photosPath = By.xpath(".//*[@data-l='t,userPhotos']");
    public final By groupsPath = By.xpath(".//*[@data-l='t,userAltGroup']");
    public final By gamesPath = By.xpath(".//*[@data-l='t,appsShowcaseHD']");
    public final By giftsPath = By.xpath(".//*[@data-l='t,giftsFront']");

    public final By postPath = By.xpath("//button[@data-l=\"t,pf_dropdown\"]"); 
    public final By profilePath = By.xpath(".//*[@data-l='t,userPage']");
 
    @Override
    public void checkPage() {
        $(profilePath).shouldBe(visible.because("Profile path is not visible"));
        $(feedPath).shouldBe(visible.because("Feed path is not visible"));
        $(hobbyPath).shouldBe(visible.because("Hobby path is not visible"));
        $(friendsPath).shouldBe(visible.because("Friends path is not visible"));
        $(photosPath).shouldBe(visible.because("Photos path is not visible"));
        $(groupsPath).shouldBe(visible.because("Groups path is not visible"));
        $(gamesPath).shouldBe(visible.because("Games path is not visible"));
        $(giftsPath).shouldBe(visible.because("Gifts path is not visible"));
        $(postPath).shouldBe(visible.because("Post path is not visible"));
    }

    public void profilePathClick() {
        $(profilePath).shouldBe(visible).click();
    }

    public void postPathClick() {
        $(postPath).shouldBe(visible).click();
    }
}
