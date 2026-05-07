package ru.yandex.practicum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//класс для анализа слов


public class WordleAnalyser {
    public static String analyseWord(String guess, String answer, WordleGame wordleGame) {
        char[] guessCh = guess.toCharArray();
        char[] answerCh = answer.toCharArray();

        char[] result = new char[5];
        // all [-] by default
        Arrays.fill(result, '-');

        boolean[] guessUsed = new boolean[5];
        boolean[] answerUsed = new boolean[5];

        for (int i = 0; i < 5; i++) {
            if (guessCh[i] == answerCh[i]){
                result[i] = '+';
                guessUsed[i] = true;
                answerUsed[i] = true;
                //добавляем угаданную букву в трафарет, который используем в Tip
                wordleGame.getGuessMask()[i] = '+';
            }
        }
        // answer - table, guess - worla;
        // [ , ^ , , +, ]
        // [ , , , true, ]
        // [ , , , true, ]
        for (int i = 0; i < 5; i++) {
          if (answerUsed[i]){
              continue;
          }
            for (int j = 0; j < 5; j++) {
               if (answerCh[i] == guessCh[j]){
                   result[j] = '^';
                   answerUsed[i] = true;
                   guessUsed[j] = true;
                   break;
               }
            }
        }

        //set current guess in wordlegame
        wordleGame.setGuess(new String(result));

        //проверяем пройдена ли игра
        if (wordleGame.getGuess().replace("+", "").isEmpty()){
            wordleGame.setSolved(true);
        }

        return new String(result);
    }
}
