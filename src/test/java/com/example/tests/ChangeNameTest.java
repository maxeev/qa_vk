package com.example.tests;

import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.pages.*;

class ChangeNameTest extends MainTest {

    @Test
    void testChangeProfileName() {

        String newName = "Имя";

        PersonalInfoPage personalInfoPage = new FeedPage()
            .profileClick()
            .settingsClick()
            .clickOnPersonalInfoBtn()
            .mainInfoClick()
            .setNewName(newName)
            .saveChangesClick();    

        refresh();
        
        assertEquals(newName, personalInfoPage.getUpdatedName());
    }
}
