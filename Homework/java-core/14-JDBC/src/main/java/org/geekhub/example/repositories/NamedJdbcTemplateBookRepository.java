package org.geekhub.example.repositories;

import org.geekhub.example.models.Book;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class NamedJdbcTemplateBookRepository implements BookRepository {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public NamedJdbcTemplateBookRepository(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Override
    public Book createBook(Book book) {
        String query = """
                INSERT INTO books (name, description, author, publishDate) VALUES (:name, :description, :author, :publishDate)
                """;

        SqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("name", book.name())
            .addValue("description", book.description())
            .addValue("author", book.author())
            .addValue("publishDate", java.sql.Timestamp.from(book.publishDate().toInstant()));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(query, parameters, keyHolder, new String[] {"id"});

        return new Book(keyHolder.getKey().intValue(), book.name(), book.description(), book.author(), book.publishDate());
    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public Book getBookById(int id) {
        return null;
    }

    @Override
    public void deleteBookById(int id) {

    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public List<Book> findBooksByName(String name) {
        return null;
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return null;
    }

    @Override
    public List<Book> findBooksPublishedInDateRange(OffsetDateTime from, OffsetDateTime to) {
        return null;
    }
}
