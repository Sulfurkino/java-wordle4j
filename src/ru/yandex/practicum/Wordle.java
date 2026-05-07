package ru.yandex.practicum;

import java.util.Scanner;

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
        WordleLogger wordleLogger = new WordleLogger();
        wordleLogger.info("Запуск приложения");
        WordleDictionaryLoader loader = new WordleDictionaryLoader();

        WordleDictionary dictionary = loader.load("words_ru.txt");
        if (dictionary == null) {
            wordleLogger.error("Словарь не загружен");
            return;
        }

        WordleTip wordleTip = new WordleTip();
        WordleAnalyser analyser = new WordleAnalyser();
        Scanner scanner = new Scanner(System.in);

        WordleGame game = new WordleGame(dictionary, dictionary.getRandomWord(), wordleLogger);

        while (true) {
            System.out.print("Введите слово (exit - выход, enter для подсказки): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                wordleLogger.info("Пользователь вышел из игры");
                break;
            }

            //move
            System.out.println(analyser.analyseWord(input, game.getAnswer(), game));

            game.setSteps(game.getSteps() + 1);
            game.getGuessesList().add(input);
            System.out.println("Осталось " + (6 - game.getSteps()) + "попыток.");
            // tip

            if (input == null){
                wordleTip.tip(game, dictionary);
            }


            //end

            if (!game.isSolved() && game.getSteps() == 6) {
                System.out.println("Игра окончена! Вы проиграли");
                System.out.println("Ответ: " + game.getAnswer());

                wordleLogger.info("Игра завершена поражением :(");

                //new game
                game = new WordleGame(dictionary, dictionary.getRandomWord(), wordleLogger);
            }
            if (game.isSolved()){
                System.out.println("Поздравляем, вы победили! Угаданное слово - " + game.getAnswer());
                wordleLogger.info("Игра завершена победой :)");

                //new game
                game = new WordleGame(dictionary, dictionary.getRandomWord(), wordleLogger);
            }
        }

        scanner.close();
        wordleLogger.info("Работа приложения завершена");
    }
}