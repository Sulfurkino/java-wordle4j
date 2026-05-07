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

    public char[] getGuessMask() {
        return guessMask;
    }

    public List<String> getTipsList() {
        return tipsList;
    }

    public List<String> getGuessesList() {
        return guessesList;
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
