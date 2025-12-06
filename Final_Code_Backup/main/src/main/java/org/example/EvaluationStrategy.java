package org.example;

import java.util.List;

public interface EvaluationStrategy {
    List<Integer> checkResult(String answer, String input);
}
