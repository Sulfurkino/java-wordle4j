package ru.yandex.practicum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {

    @Test
    void shouldRegisterGuess() {

        WordleLogger logger = new WordleLogger();

        GameState game = new GameState("карта", logger);

        boolean result = game.guessRegistration("домик", "-----");

        assertTrue(result);

        assertEquals(1, game.getSteps());

        assertEquals(1, game.getGuessesList().size());
    }

    @Test
    void shouldMarkGameSolved() {

        WordleLogger logger = new WordleLogger();

        GameState game = new GameState("карта", logger);

        game.guessRegistration("карта", "+++++");

        assertTrue(game.isSolved());
    }

    @Test
    void shouldStopGameAfterMaxSteps() {

        WordleLogger logger = new WordleLogger();

        GameState game = new GameState("карта", logger);

        for (int i = 0; i < GameState.MAX_STEPS; i++) {

            game.guessRegistration("домик", "-----");
        }

        boolean result = game.guessRegistration("столб", "-----");

        assertFalse(result);
    }

    @Test
    void shouldProtectGuessMask() {

        WordleLogger logger = new WordleLogger();

        GameState game = new GameState("карта", logger);

        char[] mask = game.getGuessMask();

        mask[0] = '+';

        assertEquals('-', game.getGuessMask()[0]);
    }
}