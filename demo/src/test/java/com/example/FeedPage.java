package com.example;

import com.codeborne.selenide.Selenide;

public class FeedPage extends OkPage {

    static final String momentsxPath = "//*[@id=\"hook_Block_AlternativeContent\"]/div/alternative-content-block/div/div[1]/button[1]";
    static final String hobbiesxPath = "//*[@id=\"hook_Block_AlternativeContent\"]/div/alternative-content-block/div/div[1]/button[2]";
    
    public OkPage open() {
        Selenide.open("https://ok.ru/feed");
        return this;
    }
}


