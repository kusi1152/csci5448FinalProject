package org.example;
import java.util.Scanner;


public class PlayWordleGame extends WordleGameTemplate {

    private String difficultyName;

    public PlayWordleGame(WordFactory wordFactory, EvaluationStrategy evaluationStrategy, Scanner scanner, String difficultyName) {
        this.wordFactory = wordFactory;
        this.evaluationStrategy = evaluationStrategy;
        this.scanner = scanner;
        this.difficultyName = difficultyName;
    }

    @Override
    protected void initializeGame() {
        System.out.println("Difficulty: " + difficultyName);
        targetWord = wordFactory.getWord();
    }
}