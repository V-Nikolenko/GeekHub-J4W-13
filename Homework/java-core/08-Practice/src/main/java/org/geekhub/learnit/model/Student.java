package org.geekhub.learnit.model;

import java.util.Map;

public record Student(String name, Map<String, Double> scores) {

    public double getAverageScore() {
        return scores.values().stream()
            .mapToDouble(Double::doubleValue)
            .average()
            .orElse(0);
    }
}
