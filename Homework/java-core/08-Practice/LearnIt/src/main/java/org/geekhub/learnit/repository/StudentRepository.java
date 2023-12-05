package org.geekhub.learnit.repository;

import org.geekhub.learnit.model.Student;

import java.util.List;

public interface StudentRepository {

    void addStudent(Student student);

    List<Student> getStudents();

    Student getStudent(int index);

}
