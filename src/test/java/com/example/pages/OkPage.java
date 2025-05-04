package com.example.pages;
import com.example.pages.components.*;

public class OkPage {

    protected Header header;
    protected SideBar sideBar;

    public OkPage() {
        this.header = new Header();
        this.sideBar = new SideBar();
    }

    public boolean checkToolNavigationBars() {
        return header.isDisplayed() && sideBar.isDisplayed();
    }
                                        
}
