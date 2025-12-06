package org.example;
import java.util.List;
import java.util.Random;

public class DifficultWordFactory implements WordFactory {

    private final List<String> words;
    private final Random random = new Random();

    public DifficultWordFactory(List<String> words) {
        this.words = words;
    }

    @Override
    public String getWord() {

        int count = Math.min(5, words.size());
        int startIndex = words.size() - count;

        return words.get(startIndex + random.nextInt(count));
    }
}

