package ru.yandex.practicum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordleAnalyserTest {

    @Test
    void shouldReturnAllExactMatches() {

        String result = WordleAnalyser.analyse("карта", "карта");

        assertEquals("+++++", result);
    }

    @Test

    void shouldReturnAllNoMatches(){

        String result = WordleAnalyser.analyse("","");

        assertEquals("-----", result);
    }

    @Test
    void shouldReturnNoMatches() {

        String result = WordleAnalyser.analyse("домик", "карта");

        assertEquals("----^", result);
    }

    @Test
    void shouldReturnMixedMatches() {

        String result = WordleAnalyser.analyse("талон", "карта");

        assertEquals("^+---", result);
    }

    @Test
    void shouldHandleDuplicateLettersCorrectly() {

        String result = WordleAnalyser.analyse("карта", "ккккк");

        assertEquals("+----", result);
    }
}
