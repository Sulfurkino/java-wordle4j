package ru.yandex.practicum;

import java.util.*;

public class WordleTip {

    private final Random random = new Random();

    public void tip(GameState game, WordleDictionary wordleDictionary) {

        List<String> tipDictionary = new ArrayList<>(wordleDictionary.getWords());

        List<String> tipGuesses = game.getGuessesList();

        List<String> tipsMade = game.getTipsList();

        //исключаем уже использованные слова и ответ
        tipDictionary.removeAll(tipGuesses);
        tipDictionary.removeAll(tipsMade);
        tipDictionary.remove(game.getAnswer());

        char[] currentGuess = game.getGuessMask();

        String guessString = new String(currentGuess);

        if (guessString.indexOf(WordleAnalyser.EXACT) >= 0) {

            Map<String, char[]> wordMap = new HashMap<>();

            //создаём маску для каждого слова
            for (String word : tipDictionary) {

                char[] charWordSymb = new char[WordleAnalyser.WORD_LENGTH];

                Arrays.fill(charWordSymb, WordleAnalyser.ABSENT);

                for (int i = 0; i < word.length(); i++) {

                    if (word.charAt(i) == game.getAnswer().charAt(i)) {

                        charWordSymb[i] = WordleAnalyser.EXACT;
                    }
                }

                wordMap.put(word, charWordSymb);
            }

            List<String> filteredTips = new ArrayList<>();

            for (Map.Entry<String, char[]> entry : wordMap.entrySet()) {

                boolean matches = true;

                for (int i = 0; i < currentGuess.length; i++) {

                    if (currentGuess[i] == WordleAnalyser.EXACT && entry.getValue()[i] != WordleAnalyser.EXACT) {

                        matches = false;
                        break;
                    }
                }

                if (matches) {
                    filteredTips.add(entry.getKey());
                }
            }

            tipDictionary = filteredTips;
        }

        if (tipDictionary.isEmpty()) {

            throw new IllegalStateException("Tip dictionary is empty");
        }

        String tip = tipDictionary.get(random.nextInt(tipDictionary.size()));

        game.addTip(tip);

        System.out.println("Вот вам подсказка, это слово не загадано - " + tip);
    }
}