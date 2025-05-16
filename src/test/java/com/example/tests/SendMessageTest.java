package com.example.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.codeborne.selenide.ex.ElementNotFound;
import com.example.pages.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendMessageTest extends MainTest {
    private static final Logger log = LoggerFactory.getLogger(SendMessageTest.class);
    private static final String FRIEND_NAME = "Максим Киселёв";

    @Tag("message")
    @DisplayName("Проверка отправки сообщения")
    @ParameterizedTest
    @ValueSource(strings = {"привет"})
    public void testSendingMessages(String message) {

        log.info("Начало теста отправки сообщения с текстом: '{}'", message);
        log.info("Получатель: {}", FRIEND_NAME);
        final String datePattern = "HH:mm";

        MessagesPage messagesPage = new FeedPage()
            .messagesClick()
            .setFriendToSearch(FRIEND_NAME)
            .chatClick();

        log.info("Проверка элементов чата");
        assertAll(
            () -> messagesPage.verifyChatTitlePath(),
            () -> messagesPage.verifyChatSendMessagePath()
        );

        log.info("Отправка сообщения");
        MessagesPage newMessagesPage = messagesPage.setMessage(message).sendMessage();
        log.info("Сообщение отправлено");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        String currentTime = now.format(formatter);
        String timePlus1Minute = now.plusMinutes(1).format(formatter);

        assertAll(
            () -> {
                try {
                    log.info("Ожидание появления иконки ожидания отправки");
                    newMessagesPage.waitMessageIcon(message);
                } catch (ElementNotFound e) {
                    log.info("Иконка не появилась -- значит сообщение отправилось");
                }
                log.info("Ожидание исчезновения иконки");
                newMessagesPage.waitIconShouldNotVisible(message);
            },
    
            () -> newMessagesPage.verifyMessageTime(message, currentTime, timePlus1Minute)
        );
        log.info("Сообщение успешно доставлено");
    }

}
