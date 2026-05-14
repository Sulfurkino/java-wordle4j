package ru.yandex.practicum;

import java.util.Scanner;

import static ru.yandex.practicum.WordleDictionaryLoader.load;

/*
в главном классе нам нужно:
    -создать лог-файл (он должен передаваться во все классы)
    -создать загрузчик словарей WordleDictionaryLoader
    -загрузить словарь WordleDictionary с помощью класса WordleDictionaryLoader
    затем создать игру WordleGame и передать ей словарь
    вызвать игровой метод в котором в цикле опрашивать пользователя и передавать информацию в игру
    вывести состояние игры и конечный результат
 */
public class Wordle {

    public static void main(String[] args) {
        try {
            WordleLogger wordleLogger = new WordleLogger();
            wordleLogger.info("Запуск приложения");

            WordleTip wordleTip = new WordleTip();
            WordleAnalyser analyser = new WordleAnalyser();
            Scanner scanner = new Scanner(System.in);
            WordleDictionary dictionary = load("words_ru.txt");

            WordleGame game = new WordleGame(dictionary, dictionary.getRandomWord(), wordleLogger);

            while (true) {
                System.out.print("Введите слово (exit - выход, enter для подсказки): ");
                String input = scanner.nextLine();

                //валидация длины слова
                if (input.length() != 5) {
                    System.out.println("Размер слова должен быть 5 букв, введите слово еще раз - ");
                    continue;
                }

                if (input.equalsIgnoreCase("exit")) {
                    wordleLogger.info("Пользователь вышел из игры");
                    break;
                }

                //move
                System.out.println(analyser.analyseWord(input, game.getAnswer(), game));




//                System.out.println("Осталось " + (6 - game.getSteps()) + "попыток.");
                // tip

                if (input.isEmpty()) {
                    wordleTip.tip(game, dictionary);
                }


                //end

                //end win
                //new game
                //end lose
                //new game
            }

            scanner.close();
            wordleLogger.info("Работа приложения завершена");

        } catch (LoadException loadException){
            System.out.println("Файл не загружен, пожалуйста загрузите файл");
        }

    }
}