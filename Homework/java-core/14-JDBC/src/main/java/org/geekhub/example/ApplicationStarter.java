package org.geekhub.example;

import org.geekhub.example.models.Book;
import org.geekhub.example.repositories.JdbcTempalteBookRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

import java.time.OffsetDateTime;
import java.util.List;

public class ApplicationStarter {

    private static final String DEV_PROFILE = "dev";
    private static final String PROD_PROFILE = "prod";

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.getEnvironment()
            .getSystemProperties()
            .put(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, DEV_PROFILE);

        context.scan("org.geekhub.example");

        context.refresh();

        runSomeQueries(context);
    }

    private static void runSomeQueries(AnnotationConfigApplicationContext context) {
        JdbcTempalteBookRepository bookRepository = context.getBean(JdbcTempalteBookRepository.class);
        bookRepository.createTable();

        boolean containsAnyBook = bookRepository.getAllBooks().isEmpty();
        System.out.println("Table is empty: " + containsAnyBook);

        Book book = new Book("The Lord of the Rings", "The Lord of the Rings is an epic high fantasy novel written by English author and scholar J. R. R. Tolkien.", "J. R. R. Tolkien", OffsetDateTime.now().withYear(1954));
        Book createdBook = bookRepository.createBook(book);

        List<Book> books = bookRepository.getAllBooks();
        System.out.println("Table contains books: " + books);

        System.out.println("Preparing book for update");
        Book updatedBook = new Book(createdBook.id(), "Blade Runner", "Blade Runner is a 1982 science fiction film directed by Ridley Scott, and written by Hampton Fancher and David Peoples.", "Ridley Scott", OffsetDateTime.now().withYear(2048));
        bookRepository.updateBook(updatedBook);

        Book existingBook = bookRepository.getBookById(createdBook.id());
        System.out.println("Updated book: " + existingBook);

        bookRepository.deleteBookById(createdBook.id());

        System.out.println("Table is empty: " + bookRepository.getAllBooks().isEmpty());
    }
}
