package com.example.tests;

import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.example.pages.*;

public class ChangeNameTest extends MainTest {

    @Test
    public void testChangeProfileName() throws InterruptedException {

        String newName = "Имя";

        PersonalInfoPage personalInfoPage = new FeedPage()
            .profileClick()
            .settingsClick()
            .personalInfoClick()
            .mainInfoClick()
            .setNewName(newName)
            .saveChangesClick();    

        refresh();
        
        assertEquals(newName, personalInfoPage.getUpdatedName());
    }
}
