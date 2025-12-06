package org.example;
import java.util.List;
import java.util.Random;

public class EasyWordFactory implements WordFactory {

    private final List<String> words;
    private final Random random = new Random();

    public EasyWordFactory(List<String> words) {
        this.words = words;
    }

    @Override
    public String getWord() {

        int count = Math.min(2, words.size());
        return words.get(random.nextInt(count));
    }
}
