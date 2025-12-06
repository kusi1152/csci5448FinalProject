package org.example;

import java.util.List;
import java.util.Random;

public class RandomWordFactory implements WordFactory {

    private final List<String> words;
    private final Random random = new Random();

    public RandomWordFactory(List<String> words) {
        this.words = words;
    }

    @Override
    public String getWord() {
        return words.get(random.nextInt(words.size()));
    }
}

