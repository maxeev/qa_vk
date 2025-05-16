package com.example.tests;

import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.pages.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ChangeNameTest extends MainTest {
    private static final Logger log = LoggerFactory.getLogger(ChangeNameTest.class);

    @Tag("changedata")
    @DisplayName("Проверка изменения имени пользователя")
    @ParameterizedTest
    @ValueSource(strings = {"Имя"})
    void testChangeProfileName(String newName) {

    log.info("Проверка изменения имени пользователя на '{}'", newName);

        PersonalInfoPage personalInfoPage = new FeedPage()
            .profileClick()
            .settingsClick()
            .clickOnPersonalInfoBtn()
            .mainInfoClick()
            .setNewName(newName)
            .saveChangesClick();
        
        log.info("Имя изменено");

        refresh();

        log.info("Проверка сохранения изменений");
        
        assertEquals(newName, personalInfoPage.getUpdatedName());

        log.info("Изменения успешно сохранены");
    }
}
