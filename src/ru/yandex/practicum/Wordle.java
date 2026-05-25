package ru.yandex.practicum;

import ru.yandex.practicum.exception.LoadException;

import java.util.Scanner;

import static ru.yandex.practicum.WordleDictionaryLoader.load;


public class Wordle {

    public static void main(String[] args) {

        try {

            WordleLogger wordleLogger = new WordleLogger();
            wordleLogger.info("Запуск приложения");

            WordleTip wordleTip = new WordleTip();

            Scanner scanner = new Scanner(System.in);

            WordleDictionary dictionary = load("words_ru.txt");

            GameState game = new GameState(dictionary.getRandomWord(), wordleLogger);

            while (true) {

                System.out.print("Введите слово (exit - выход, enter для подсказки): ");

                String input = Utils.normaliseWord(scanner.nextLine());

                //выход
                if (input.equals("exit")) {

                    wordleLogger.info("Пользователь вышел из игры");

                    break;
                }

                //подсказка
                if (input.isBlank()) {

                    wordleTip.tip(game, dictionary);

                    continue;
                }

                //проверка длины
                if (input.length() != WordleAnalyser.WORD_LENGTH) {

                    System.out.println("Размер слова должен быть 5 букв.");

                    continue;
                }

                //проверка
                if (!dictionary.contains(input)) {

                    System.out.println("К сожалению программа не знает этого слова, попробуйте другое.");

                    continue;
                }

                //анализ
                String analyseResult = WordleAnalyser.analyse(input, game.getAnswer());

                //регистрация попытки
                boolean registered = game.guessRegistration(input, analyseResult);

                if (!registered) {

                    System.out.println("Игра уже завершена.");

                    break;
                }

                //вывод результата анализа
                System.out.println(analyseResult);

                //ставшиеся попытки
                System.out.printf("Осталось %d попыток.%n", GameState.MAX_STEPS - game.getSteps());

                //победа
                if (game.isSolved()) {

                    System.out.println("Поздравляем, вы победили!");

                    break;
                }

                //поражение
                if (game.getSteps() >= GameState.MAX_STEPS) {

                    System.out.println("Игра окончена! Вы проиграли.");

                    System.out.println("Ответ: " + game.getAnswer());

                    break;
                }
            }

            scanner.close();

            wordleLogger.info("Работа приложения завершена");

        } catch (LoadException loadException) {

            System.out.println("Файл не загружен, пожалуйста загрузите файл");
        }
    }
}