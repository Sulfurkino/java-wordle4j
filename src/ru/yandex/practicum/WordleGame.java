package ru.yandex.practicum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
в этом классе хранится словарь и состояние игры
    текущий шаг
    всё что пользователь вводил
    правильный ответ

в этом классе нужны методы, которые
    проанализируют совпадение слова с ответом - (в другом классе отдельно)
    предложат слово-подсказку с учётом всего, что вводил пользователь ранее

не забудьте про специальные типы исключений для игровых и неигровых ошибок
 */
public class WordleGame {

    private String answer;
    private int steps;
    private WordleDictionary dictionary;
    private static final int MAX_STEPS = 6;

    //трафарет используемый в tip
    private String guess;
    private char[] guessMask = new char[5];

    //список всех попыток пользователя
    List<String> guessesList = new ArrayList<>();

    //список советов которые были даны пользователю
    List<String> tipsList = new ArrayList<>();

    //состояние игры - пройдена или нет, в конструкторе по умолчанию false
    private boolean isSolved;


    public WordleGame(WordleDictionary dictionary, String answer, WordleLogger wordleLogger) {
        wordleLogger.info("created new game");
        this.dictionary = dictionary;
        this.answer = answer;
        steps = 0;
        isSolved = false;
        Arrays.fill(guessMask, '-');
    }

    public boolean guessRegistration(String guess, String tip){
        if (isSolved || steps == MAX_STEPS){
            return false;
        }

        guessesList.add(guess);
        tipsList.add(tip);

        steps ++;

        //проверяем пройдена ли игра


//        game.setSteps(game.getSteps() + 1);
//        game.getGuessesList().add(input);
//
//        if (!isSolved() && steps == 6) {
//            System.out.println("Игра окончена! Вы проиграли");
//            System.out.println("Ответ: " + answer);
//
//            wordleLogger.info("Игра завершена поражением :(");
//
//            //new game
//            game = new WordleGame(dictionary, dictionary.getRandomWord(), wordleLogger);
//        }
//        if (game.isSolved()) {
//            System.out.println("Поздравляем, вы победили! Угаданное слово - " + game.getAnswer());
//            wordleLogger.info("Игра завершена победой :)");
//
//            //new game
//            game = new WordleGame(dictionary, dictionary.getRandomWord(), wordleLogger);
//        }




        //добавить tip в game.getTipsList()(использованные подсказки)
        game.getTipsList().add(tip);

    }

    public char[] getGuessMask() {
        return guessMask;
    }
    //7
    public List<String> getTipsList() {
        List<String> tipsListCopy = tipsList;
        return tipsListCopy;
    }

    public List<String> getGuessesList() {
        List<String> guessListCopy = guessesList;
        return guessListCopy ;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public WordleDictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(WordleDictionary dictionary) {
        this.dictionary = dictionary;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }
}
