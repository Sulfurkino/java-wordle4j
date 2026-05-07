package ru.yandex.practicum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/*
этот класс содержит в себе всю рутину по работе с файлами словарей и с кодировками
    ему нужны методы по загрузке списка слов из файла по имени файла
    на выходе должен быть класс WordleDictionary
 */

//1 статичный метод который принимает путь к файлу и возвращает WordleDictionary
//new WordleDictionary (передаем список слов) + СЧИТЫВАТЬ ЗАГРУЖАТЬ В ДИКШН


public class WordleDictionaryLoader {

    public static WordleDictionary load(String filePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            String line;
            List<String> words = new ArrayList<>();
            //filtration e-e
            //filtration 5letter
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() != 5) {
                    continue;
                }
                line = line.toLowerCase().replaceAll("ё", "е");
                words.add(line);
            }

            return new WordleDictionary(words);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}