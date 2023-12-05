package org.geekhub.learnit.model;

import java.util.Map;

public record Student(String name, Map<String, Double> scores) {
}
