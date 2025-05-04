package com.example.tests;

import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.pages.*;

public class PostDeleteTest extends MainTest {

    //@Disabled
    @Tag("post")
    @DisplayName("Проверка публикации и удаления поста")
    @ParameterizedTest
    @ValueSource(strings = {"Тест 1", "Второй тест"})
    public void testPostingRecord(String text) {

        FeedPage feedPage = new FeedPage().postClick()
                .recordClick()
                .enterTextClick(text)
                .shareRecordClick();

        ProfilePage profilePage = feedPage.profileClick();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String currentTime = now.format(formatter);
        String timePlus1 = now.plusMinutes(1).format(formatter);

        refresh();

        assertAll(
            () -> profilePage.verifyPostIsPublished(text),
            () -> profilePage.verifyTimePublishedPost(currentTime, timePlus1)
        );

        ProfilePage newProfilePage = profilePage.deletePost();
        refresh();
        newProfilePage.verifyPostIsDeleted(text, currentTime, timePlus1);

    }
}
