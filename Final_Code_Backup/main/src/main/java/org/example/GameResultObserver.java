package org.example;
public class GameResultObserver implements GameObserver {

    @Override
    public void onGameOver(boolean won, String answer) {
        System.out.println("\n" + "=".repeat(50));
        if (won) {
            System.out.println("GAME OVER - WORD FOUND!");
            System.out.println("Congratulations! The word was: " + answer.toUpperCase());
        } else {
            System.out.println("GAME OVER - WORD NOT FOUND");
            System.out.println("The correct word was: " + answer.toUpperCase());
        }
        System.out.println("=".repeat(50));
    }
}
