package com.example.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import com.codeborne.selenide.SelenideElement;
import com.example.pages.components.PostRecord;

public class FeedPage extends OkPage {

    private final SelenideElement momentsPath = $x("//*[@data-l='t,to_moments']");
    private final SelenideElement hobbiesPath = $x("//*[@data-l='t,to_hobbies']");

    public FeedPage() {
        checkFeedPage();
    }

    public void checkFeedPage() {
        momentsPath.shouldBe(visible.because("Moments path is not visible"));
        hobbiesPath.shouldBe(visible.because("Hobbies path is not visible"));
    }

    public PostRecord postClick() {
        this.sideBar.postPath.click();
        return new PostRecord();
    }

    public ProfilePage profileClick() {
        this.sideBar.profilePath.click();
        return new ProfilePage();
    }

}


