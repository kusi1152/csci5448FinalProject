package org.example;
import java.util.List;

public class WordleGame {

    private static WordleGame instance;

    private final List<String> words;

    private WordleGame() {
        words = List.of("apple", "brave", "crisp", "dream", "eagle", "flame", "ghost", "honey", "ivory", "joker", "knock", "lemon", "magic", "night", "ocean", "pilot", "queen", "river", "stone", "track");
    }

    public static WordleGame getInstance() {
        if (instance == null) {
            instance = new WordleGame();
        }
        return instance;
    }

    public List<String> getWords() {
        return words;
    }
}
