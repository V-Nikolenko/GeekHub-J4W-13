package org.geekhub.learnit;

import org.geekhub.learnit.consoleapi.CreateStudentApi;
import org.geekhub.learnit.consoleapi.GetStudentApi;
import org.geekhub.learnit.consoleapi.MainMenu;
import org.geekhub.learnit.repository.StudentRepository;
import org.geekhub.learnit.repository.StudentRepositoryInMemory;
import org.geekhub.learnit.service.StudentService;

import java.util.Scanner;


public class ApplicationStarter {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            StudentRepository studentRepository = new StudentRepositoryInMemory();
            StudentService studentService = new StudentService(studentRepository);
            CreateStudentApi createStudentApi = new CreateStudentApi(scanner, studentService);
            GetStudentApi getStudentApi = new GetStudentApi(scanner, studentService);

            MainMenu mainMenu = new MainMenu(scanner, createStudentApi, getStudentApi);
            mainMenu.printMenu();
        }
    }
}
