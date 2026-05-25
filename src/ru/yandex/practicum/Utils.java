package ru.yandex.practicum;

public class Utils {
    public static String normaliseWord(String word){
        return word.toLowerCase().replaceAll("ё", "е");
    }
}
