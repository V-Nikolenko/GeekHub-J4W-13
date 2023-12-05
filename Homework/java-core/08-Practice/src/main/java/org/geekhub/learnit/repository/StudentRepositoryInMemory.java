package org.geekhub.learnit.repository;

import org.geekhub.learnit.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryInMemory implements StudentRepository {

    private final List<Student> students = new ArrayList<>();

    @Override
    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public List<Student> getStudents() {
        return students;
    }

    @Override
    public Student getStudent(int index) {
        return students.get(index);
    }
}
