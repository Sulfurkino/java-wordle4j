package ru.yandex.practicum;

import java.util.*;

public class WordleTip {
    Random random = new Random();
    //    Алгоритм сравнения слова в классе анализатор, тут идет подбор подсказки

    //    Далее из подходящих слов выбираются те, в которых нужные буквы находятся на нужных местах
    //            (если игроку удалось угадать хоть одну такую букву).
    //    При всех заданных условиях становится понятно,
    //    что первое слово в игре можно выбрать любое, если игрок запросил подсказку сразу.
    //    В идеале компьютер должен пройти всю игру сам, реагируя только на нажатия Enter.
    public void tip(WordleGame game, WordleDictionary wordleDictionary) {

        List<String> tipDictionary = new ArrayList<>(wordleDictionary.getWords());
        List<String> tipGuesses = game.getGuessesList();
        List<String> tipsMade = game.getTipsList();

        //исключить два списка слов из списка словаря tipDictionary и ответ
        tipDictionary.removeAll(tipGuesses);
        tipDictionary.removeAll(tipsMade);
        tipDictionary.remove(game.getAnswer());
        // теперь не посоветуются те, что уже были в игре и сам ответ

        //получаем guess string
        char[] currentGuess = game.getGuessMask();
        String guessString = currentGuess.toString();
        //[+,-,-,-,+] - маска currentGuess


        //Получить список tipDictionary, сделать из него словарь.
        // Ключ слово, значение - его символьная строка
        // те из значений которые совпадают с currentGuess оставить в tipDictionary с ключами,
        // остальное исключить


        if (guessString.contains("+")) {
            //узнаем какие буквы нам нужны на месте +

            //переводим правильный ответ string в список
            List<Character> answerChList = game.getAnswer().chars()
                    .mapToObj(c -> (char) c)
                    .toList(); // список букв где все верны  - [t+, a+, b+, l+, e+]



            //переводим весь оставшийся словарь в таблицу, где ключ это слово, а значение это список
            // где '-' это не совпадающая буква с маской currentGuess, а '+' совпадающая
            Map<String,  char[]> wordMap = new TreeMap<>();

            //вычисляем символьное значение каждого слова и добавляем в таблицу
            for (String word : tipDictionary){
                List<Character> charWord = word.chars()
                        .mapToObj(c -> (char) c)
                        .toList();//список букв

                char [] charWordSymb = new char[5];
                Arrays.fill(charWordSymb, '-');//список символов

                //отмечаем в списке символов +;
                for (int i = 0; i < charWord.size(); i++) {
                    if (charWord.get(i) == answerChList.get(i)){
                        charWordSymb[i] = '+';
                    }
                }
                //помещаем в таблицу слово и по значению его символьный список с + и -
                wordMap.put(word, charWordSymb);
            }
            // Теперь есть таблица со словом и его символьным значением.
            // Фильтруем таблицу wordMap
            // используем для этого currentGuess, который является комбинацией всех
            // отгаданных букв пользователя

            for (Map.Entry<String, char[]> entry : wordMap.entrySet()){
                for (int i = 0; i < currentGuess.length; i++) {
                    if (currentGuess[i] != entry.getValue()[i]){
                        //удаляем все слова которые не имеют общих + с currentGuess
                        tipDictionary.remove(entry.getKey());
                    }
                }
            }

        }

        //вывод tip
        String tip = tipDictionary.get(random.nextInt(tipDictionary.size()-1));
        System.out.println("Вот вам подсказка, это слово не загадано - " + tip);

        //добавить tip в game.getTipsList()(использованные подсказки)
        game.getTipsList().add(tip);
    }

}
