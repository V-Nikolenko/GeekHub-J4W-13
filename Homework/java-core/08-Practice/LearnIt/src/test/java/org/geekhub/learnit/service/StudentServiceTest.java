package org.geekhub.learnit.service;

import org.geekhub.learnit.model.Student;
import org.geekhub.learnit.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyMap;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentRepository);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " "})
    void createStudent_shouldThrowIllegalArgumentException_whenNameIsInvalid(String name) {
        Executable executable = () -> studentService.createStudent(name, emptyMap());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Name cannot be empty", exception.getMessage());
    }

    @Test
    void getLastCreatedStudent_shouldReturnLastUser_always() {
        Student expectedStudent = new Student("name3", emptyMap());
        List<Student> students = List.of(
            new Student("name1", Collections.emptyMap()),
            new Student("name2", Collections.emptyMap()),
            expectedStudent
        );
        when(studentRepository.getStudents()).thenReturn(students);

        Student lastCreatedStudent = studentService.getLastCreatedStudent();

        assertEquals(expectedStudent, lastCreatedStudent);
    }

    @Test
    void getLastCreatedStudent_shouldThrowIllegalStateException_whenStudentListIsEmpty() {
        when(studentRepository.getStudents()).thenReturn(Collections.emptyList());

        Executable executable = () -> studentService.getLastCreatedStudent();

        assertThrows(IllegalStateException.class, executable);
    }


    @Test
    void getStudent() {}

    @Test
    void getStudents() {
    }
}
