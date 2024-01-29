package org.geekhub.example.repositories;

import org.geekhub.example.models.Book;
import org.springframework.lang.NonNull;

import java.time.OffsetDateTime;
import java.util.List;

public interface BookRepository {

    Book createBook(@NonNull Book book);

    List<Book> getAllBooks();

    Book getBookById(int id);

    void deleteBookById(int id);

    void updateBook(@NonNull Book book);

    List<Book> findBooksByName(@NonNull String name);

    List<Book> findBooksByAuthor(@NonNull String author);

    List<Book> findBooksPublishedInDateRange(@NonNull OffsetDateTime from, @NonNull OffsetDateTime to);
}
