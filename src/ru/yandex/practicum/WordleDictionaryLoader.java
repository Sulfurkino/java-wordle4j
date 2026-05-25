package ru.yandex.practicum;

import ru.yandex.practicum.exception.LoadException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class WordleDictionaryLoader {

    public static WordleDictionary load(String filePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            String line;
            List<String> words = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() != 5) {
                    continue;
                }
                line = Utils.normaliseWord(line);
                words.add(line);
            }

            return new WordleDictionary(words);
        } catch (IOException e) {
            throw new LoadException("Ошибка загрузки файла");
        }
    }
}