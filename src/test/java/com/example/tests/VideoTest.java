package com.example.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import com.example.pages.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;

class VideoTest extends MainTest {
    private static final Logger log = LoggerFactory.getLogger(VideoTest.class);

    @Tag("video")
    @DisplayName("Проверка воспроизведения видео")
    @ParameterizedTest
    @ValueSource(strings = {"Бойцовский клуб"})
    void videoTest(String searchValue) {

        log.info("Запуск теста для видео с названием: '{}'", searchValue);
        VideoPage videoPage = new FeedPage()
                .videoClick()
                .videoSearchClick()
                .setSearchValue(searchValue)
                .searchButtonClick();

        log.info("Выполнен поиск видео и открыто первое видео");

        videoPage.verifyVideoSearchResult();

        int offsetToZero = videoPage.firstVideoClick().moveSliderToZero().getSliderValue();

        log.info("Позиция слайдера после сброса: {}%", offsetToZero);

        int targetOffset = 30; // на 30 %
        int oneMoreOffset = videoPage.moveSlider(targetOffset).getSliderValue();

        log.info("Позиция слайдера после перемещения: {}%", oneMoreOffset);

        videoPage.videoPlayClick().videoPauseClick();

        assertAll(
                "Проверка позиций слайдера",
                () -> assertEquals(0, offsetToZero, "Слайдер не сброшен в 0"),
                () -> assertEquals(targetOffset, oneMoreOffset,
                        "Слайдер переместился не на " + targetOffset + "%")
        );

        log.info("Видео успешно воспроизводится и ставится на паузу");
        log.info("Тест успешно завершен для видео с названием: '{}'", searchValue);
    }  
}
