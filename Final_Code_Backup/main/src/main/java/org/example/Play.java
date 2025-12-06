package org.example;
import java.util.List;
import java.util.Scanner;

public class Play {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n" + "=".repeat(50));
        System.out.println("          WORDLE GAME - SELECT DIFFICULTY");
        System.out.println("=".repeat(50));
        System.out.println("1. Easy   - Words from start of list (first 2)");
        System.out.println("2. Random - Any word from the list");
        System.out.println("3. Hard   - Words from end of list (last 5)");
        System.out.println("=".repeat(50));
        System.out.print("Enter your choice (1/2/3): ");

        String choice = scanner.nextLine().trim();

        WordleGame wordleGame = WordleGame.getInstance();
        List<String> words = wordleGame.getWords();

        WordFactory wordFactory;
        String difficultyName;

        switch (choice) {
            case "1":
                wordFactory = new EasyWordFactory(words);
                difficultyName = "EASY (First 2 words from list)";
                break;
            case "3":
                wordFactory = new DifficultWordFactory(words);
                difficultyName = "HARD (Last 5 words from list)";
                break;
            case "2":
            default:
                wordFactory = new RandomWordFactory(words);
                difficultyName = "RANDOM (Any word from list)";
                break;
        }

        EvaluationStrategy evaluationStrategy = new ClassicEvaluationStrategy();
        GameObserver observer = new GameResultObserver();
        WordleGameTemplate game = new PlayWordleGame(wordFactory, evaluationStrategy, scanner, difficultyName);
        game.addObserver(observer);
        game.playGame();
        scanner.close();
    }
}