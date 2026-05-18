package ru.yandex.practicum;

import java.util.List;
import java.util.Random;

/*
этот класс содержит в себе список слов List<String>
    его методы похожи на методы списка, но учитывают особенности игры
    также этот класс может содержать рутинные функции.
 */
public class WordleDictionary {

    private List<String> words;
    private final Random random = new Random();

    public WordleDictionary(List<String> words) {
        this.words = words;
    }

    public List<String> getWords() {
        return words;
    }

    public String getRandomWord() {
        return words.get(random.nextInt(words.size()));
    }

    public boolean contains(String word) {
        return words.contains(word);
    }

}
