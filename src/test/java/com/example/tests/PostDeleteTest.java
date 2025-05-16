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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class PostDeleteTest extends MainTest {
    private static final Logger log = LoggerFactory.getLogger(PostDeleteTest.class);

    @Tag("post")
    @DisplayName("Проверка публикации и удаления поста")
    @ParameterizedTest
    @ValueSource(strings = {"Тест 1"})
    void testPostingRecord(String text) {

        log.info("Проверка публикации и удаления поста с текстом '{}'", text);
        
        FeedPage feedPage = new FeedPage().postClick()
                .recordClick()
                .closeAnnoyingElementIfPresent() // добавил это, потому что при попытке создания записи постоянно появляется небольшое окно с текстом "Для кого опубликовать заметку?"
                .enterTextClick(text)
                .shareRecordClick();

        log.info("Пост опубликован на стену");

        ProfilePage profilePage = feedPage.profileClick();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String currentTime = now.format(formatter);
        String timePlus1Minute = now.plusMinutes(1).format(formatter);

        log.info("Проверка наличия поста");
        assertAll( // проверяет публикацию поста
            () -> {
                try {
                    profilePage.verifyPostIsPublished(text);
                } catch (AssertionError e) {
                    throw new AssertionError("проверка публикации поста с текстом '" + text + "' не прошла: " + e.getMessage(), e);
                }
            }
        );

        ProfilePage newProfilePage = profilePage.deletePost();
        refresh();
        newProfilePage.verifyPostIsDeleted(text, currentTime, timePlus1Minute);

        log.info("Пост успешно удален" );
    }
}
