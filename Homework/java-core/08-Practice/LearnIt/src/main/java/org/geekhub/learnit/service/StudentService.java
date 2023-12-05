package org.geekhub.learnit.service;

import org.geekhub.learnit.model.Student;
import org.geekhub.learnit.repository.StudentRepository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(String name, Map<String, Double> scores) {
        if (Objects.isNull(name)|| name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (scores.isEmpty()) {
            throw new IllegalArgumentException("Scores cannot be empty");
        }

        if (scores.keySet().stream().anyMatch(String::isBlank)) {
            throw new IllegalArgumentException("Subject cannot be empty");
        }

        if (scores.values().stream().anyMatch(score -> score < 0 || score > 100)) {
            throw new IllegalArgumentException("Score cannot be less than 0 or greater than 100");
        }

        Student student = new Student(name, scores);
        studentRepository.addStudent(student);
    }

    public Student getLastCreatedStudent() {
        List<Student> students = studentRepository.getStudents();
        if (students.isEmpty()) {
            throw new IllegalStateException("Student List is empty");
        }

        int index = students.size() - 1;
        return students.get(index);
    }

    public Student getStudent(int index) {
        return studentRepository.getStudent(index);
    }

    public List<Student> getStudents() {
        return studentRepository.getStudents();
    }
}
