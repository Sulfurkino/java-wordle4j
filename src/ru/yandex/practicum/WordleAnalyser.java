package ru.yandex.practicum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordleAnalyser {

    public static final int WORD_LENGTH = 5;

    public static final char EXACT = '+';
    public static final char PRESENT = '^';
    public static final char ABSENT = '-';

    public static String analyse(String guess, String answer) {

        char[] guessCh = guess.toCharArray();
        char[] answerCh = answer.toCharArray();

        char[] result = new char[WORD_LENGTH];
        Arrays.fill(result, ABSENT);

        boolean[] guessUsed = new boolean[WORD_LENGTH];
        boolean[] answerUsed = new boolean[WORD_LENGTH];


        for (int i = 0; i < WORD_LENGTH; i++) {

            if (guessCh[i] == answerCh[i]) {
                result[i] = EXACT;

                guessUsed[i] = true;
                answerUsed[i] = true;
            }
        }

        for (int i = 0; i < WORD_LENGTH; i++) {

            if (answerUsed[i]) {
                continue;
            }

            for (int j = 0; j < WORD_LENGTH; j++) {

                if (guessUsed[j]) {
                    continue;
                }

                if (answerCh[i] == guessCh[j]) {

                    result[j] = PRESENT;

                    answerUsed[i] = true;
                    guessUsed[j] = true;

                    break;
                }
            }
        }

        return new String(result);
    }
}
