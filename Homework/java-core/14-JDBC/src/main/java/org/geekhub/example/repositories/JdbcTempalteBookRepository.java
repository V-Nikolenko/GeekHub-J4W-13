package org.geekhub.example.repositories;

import org.geekhub.example.models.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;

@Repository
public class JdbcTempalteBookRepository implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTempalteBookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTable() {
        String sqlQuery = """
            CREATE TABLE IF NOT EXISTS books (
                id SERIAL PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                description VARCHAR(255),
                author VARCHAR(255) NOT NULL,
                publishDate TIMESTAMP
            )
            """;

        jdbcTemplate.execute(sqlQuery);
    }

    @Override
    public Book createBook(@NonNull Book book) {
        String sqlQuery = """
            INSERT INTO books (name, description, author, publishDate) VALUES (?, ?, ?, ?)
            """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sqlQuery, new String[]{"id"});
            statement.setString(1, book.name());
            statement.setString(2, book.description());
            statement.setString(3, book.author());
            statement.setTimestamp(4, Timestamp.from(book.publishDate().toInstant()));
            return statement;
        }, keyHolder);

        return new Book(
            keyHolder.getKey().intValue(),
            book.name(),
            book.description(),
            book.author(),
            book.publishDate()
        );
    }

    @Override
    public List<Book> getAllBooks() {
        String sqlQuery = "SELECT * FROM books ORDER BY id ASC";

        return jdbcTemplate.query(sqlQuery, BookMapper::mapToBook);
    }

    @Override
    public Book getBookById(int id) {
        String sqlQuery = "SELECT * FROM books WHERE id = ?";

        return jdbcTemplate.queryForObject(sqlQuery, BookMapper::mapToBook, id);
    }

    @Override
    public void deleteBookById(int id) {
        String sqlQuery = "DELETE FROM books WHERE id = ?";

        jdbcTemplate.update(sqlQuery, id);
    }

    @Override
    public void updateBook(@NonNull Book book) {
        String sqlQuery = """
            UPDATE books
            SET name = ?, description = ?, author = ?, publishDate = ?
            WHERE id = ?
            """;

        jdbcTemplate.update(sqlQuery, book.name(), book.description(), book.author(), book.publishDate(), book.id());
    }

    @Override
    public List<Book> findBooksByName(@NonNull String name) {
        String sqlQuery = "SELECT * FROM books WHERE name = ?";

        return jdbcTemplate.query(sqlQuery, BookMapper::mapToBook, name);
    }

    @Override
    public List<Book> findBooksByAuthor(@NonNull String author) {
        String sqlQuery = "SELECT * FROM books WHERE author = ?";

        return jdbcTemplate.query(sqlQuery, BookMapper::mapToBook, author);
    }

    @Override
    public List<Book> findBooksPublishedInDateRange(@NonNull OffsetDateTime from, @NonNull OffsetDateTime to) {
        String sqlQuery = "SELECT * FROM books WHERE year BETWEEN ? AND ?";

        return jdbcTemplate.query(sqlQuery, BookMapper::mapToBook, from, to);
    }
}
