package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class WordleGameTemplate {

    protected static final String RED_BG = "\u001B[41m";
    protected static final String YELLOW_BG = "\u001B[43m";
    protected static final String GREEN_BG = "\u001B[42m";
    protected static final String RESET = "\u001B[0m";
    protected static final String WHITE_TEXT = "\u001B[37m";
    protected static final String BLACK_TEXT = "\u001B[30m";

    protected static final int MAX_ATTEMPTS = 6;
    protected static final int WORD_LENGTH = 5;

    // Dependencies that will be injected
    protected WordFactory wordFactory;
    protected EvaluationStrategy evaluationStrategy;
    protected List<GameObserver> observers = new ArrayList<>();
    protected String targetWord;
    protected Scanner scanner;

    // Template Method - defines the game flow
    public final void playGame() {
        initializeGame();
        displayWelcome();

        boolean wordFound = false;
        int attempt = 0;

        while (attempt < MAX_ATTEMPTS && !wordFound) {
            System.out.println("\nAttempt " + (attempt + 1) + "/" + MAX_ATTEMPTS);
            String guess = getUserInput();

            if (!isValidInput(guess)) {
                System.out.println("Invalid input! Please enter a " + WORD_LENGTH + "-letter word.");
                continue;
            }

            List<Integer> result = evaluateGuess(guess);
            displayResult(guess, result);

            if (isCorrectGuess(result)) {
                wordFound = true;
            }

            attempt++;
        }

        notifyObservers(wordFound);
        cleanup();
    }

    protected abstract void initializeGame();

    protected void displayWelcome() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("          WELCOME TO WORDLE GAME!");
        System.out.println("=".repeat(50));
        System.out.println("Guess the " + WORD_LENGTH + "-letter word in " + MAX_ATTEMPTS + " attempts.");
        System.out.println("Color Guide:");
        System.out.print("  " + GREEN_BG + "   " + RESET + " = Correct letter in correct position\n");
        System.out.print("  " + YELLOW_BG + "   " + RESET + " = Correct letter in wrong position\n");
        System.out.print("  " + RED_BG + "   " + RESET + " = Letter not in word\n");
        System.out.println("=".repeat(50) + "\n");
    }

    protected String getUserInput() {
        System.out.print("Enter your guess: ");
        return scanner.nextLine().toLowerCase().trim();
    }

    protected boolean isValidInput(String input) {
        return input != null && input.length() == WORD_LENGTH && input.matches("[a-z]+");
    }

    protected List<Integer> evaluateGuess(String guess) {
        return evaluationStrategy.checkResult(targetWord, guess);
    }

    protected void displayResult(String guess, List<Integer> result) {
        System.out.println("\nYour guess: " + guess.toUpperCase());
        System.out.print("Result:     ");

        // Display colored boxes
        for (int i = 0; i < result.size(); i++) {
            int value = result.get(i);
            String bgColor;

            if (value == 1) {
                bgColor = GREEN_BG;
            } else if (value == 0) {
                bgColor = YELLOW_BG;
            } else {
                bgColor = RED_BG;
            }

            System.out.print(bgColor);
            for (int j = 0; j < 3; j++) {
                System.out.print(" ");
            }
            System.out.print(RESET);
            System.out.print("  ");
        }
        System.out.println();

        System.out.print("            ");
        for (int i = 0; i < guess.length(); i++) {
            int value = result.get(i);
            String bgColor;

            if (value == 1) {
                bgColor = GREEN_BG;
            } else if (value == 0) {
                bgColor = YELLOW_BG;
            } else {
                bgColor = RED_BG;
            }

            System.out.print(bgColor + BLACK_TEXT + " " + guess.toUpperCase().charAt(i) + " " + RESET + "  ");
        }
        System.out.println("\n");
    }

    protected boolean isCorrectGuess(List<Integer> result) {
        for (int value : result) {
            if (value != 1) {
                return false;
            }
        }
        return true;
    }

    protected void notifyObservers(boolean won) {
        for (GameObserver observer : observers) {
            observer.onGameOver(won, targetWord);
        }
    }

    protected void cleanup() {

    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }
}