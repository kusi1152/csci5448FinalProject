package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassicEvaluationStrategy implements EvaluationStrategy {

    @Override
    public List<Integer> checkResult(String answer, String input) {
        List<Integer> result = new ArrayList<>();
        int length = answer.length();

        Map<Character, Integer> freq = new HashMap<>();
        int[] temp = new int[length];

        for (int i = 0; i < length; i++) {
            if (input.charAt(i) == answer.charAt(i)) {
                temp[i] = 1; // correct position
            } else {
                freq.put(answer.charAt(i), freq.getOrDefault(answer.charAt(i), 0) + 1);
            }
        }

        for (int i = 0; i < length; i++) {
            if (temp[i] == 1) {
                result.add(1);
                continue;
            }

            char ch = input.charAt(i);

            if (freq.getOrDefault(ch, 0) > 0) {
                result.add(0); // letter exists but wrong position
                freq.put(ch, freq.get(ch) - 1);
            } else {
                result.add(-1);
            }
        }

        return result;
    }
}
