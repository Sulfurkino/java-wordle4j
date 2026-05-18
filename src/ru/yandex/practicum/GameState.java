package ru.yandex.practicum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameState {

    public static final int MAX_STEPS = 6;

    private final String answer;

    private int steps;

    private final char[] guessMask = new char[WordleAnalyser.WORD_LENGTH];

    private final List<String> guessesList = new ArrayList<>();

    private final List<String> tipsList = new ArrayList<>();

    private boolean isSolved;

    public GameState(String answer, WordleLogger wordleLogger) {

        wordleLogger.info("Created new game");

        this.answer = answer;

        Arrays.fill(guessMask, WordleAnalyser.ABSENT);
    }

    public boolean guessRegistration(String guess, String analyseResult) {

        if (isSolved || steps >= MAX_STEPS) {
            return false;
        }

        guessesList.add(guess);
        updateGuessMask(analyseResult);
        steps++;

        if (guess.equals(answer)) {
            isSolved = true;
        }

        return true;
    }

    private void updateGuessMask(String analyseResult) {

        for (int i = 0; i < analyseResult.length(); i++) {

            if (analyseResult.charAt(i) == WordleAnalyser.EXACT) {

                guessMask[i] = WordleAnalyser.EXACT;
            }
        }
    }

    public void addTip(String tip) {
        tipsList.add(tip);
    }

    public char[] getGuessMask() {
        return Arrays.copyOf(guessMask, guessMask.length);
    }

    public List<String> getTipsList() {
        return List.copyOf(tipsList);
    }

    public List<String> getGuessesList() {
        return List.copyOf(guessesList);
    }

    public String getAnswer() {
        return answer;
    }

    public int getSteps() {
        return steps;
    }

    public boolean isSolved() {
        return isSolved;
    }
}
