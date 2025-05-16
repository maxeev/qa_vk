package com.example.pages;

import static com.codeborne.selenide.Condition.visible;

import com.example.pages.components.PostRecord;
import com.example.pages.components.SideBar;
import com.example.pages.components.Header;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

public class FeedPage implements CheckPage {

    public static By momentsPath = By.xpath(".//*[@data-l='t,to_moments']");
    public static By hobbiesPath = By.xpath(".//*[@data-l='t,to_hobbies']");
    
    private final SideBar sideBar = new SideBar();
    private final Header header = new Header();

    @Override
    public void checkPage() {
        $(momentsPath).shouldBe(visible.because("Moments path is not visible"));
        $(hobbiesPath).shouldBe(visible.because("Hobbies path is not visible"));
    }

    public PostRecord postClick() {
        this.sideBar.postPathClick();
        return new PostRecord();
    }

    public ProfilePage profileClick() {
        this.sideBar.profilePathClick();
        return new ProfilePage();
    }

    public MessagesPage messagesClick() {
        this.header.clickMessages();
        return new MessagesPage();
    }

    public VideoPage videoClick() {
        this.header.clickVideos();
        return new VideoPage();
    }
}


