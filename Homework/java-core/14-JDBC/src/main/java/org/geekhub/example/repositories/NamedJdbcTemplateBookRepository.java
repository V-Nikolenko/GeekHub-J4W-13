package org.geekhub.example.repositories;

import org.geekhub.example.models.Book;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;

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
        namedJdbcTemplate.update(query, parameters, keyHolder, new String[]{"id"});

        return new Book(keyHolder.getKey().intValue(), book.name(), book.description(), book.author(), book.publishDate());
    }

    @Override
    public List<Book> getAllBooks() {
        String query = "SELECT * FROM books";

        return namedJdbcTemplate.query(query, BookMapper::mapToBook);
    }

    @Override
    public Book getBookById(int id) {
        String query = "SELECT * FROM books WHERE id = :id";

        SqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("id", id);
        return namedJdbcTemplate.queryForObject(query, parameters, BookMapper::mapToBook);
    }

    @Override
    public void deleteBookById(int id) {
        String query = "DELETE FROM books WHERE id = :id";

        SqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("id", id);
        namedJdbcTemplate.update(query, parameters);

    }

    @Override
    public void updateBook(@NonNull Book book) {
        String query = """
            UPDATE books SET name = :name, description = :description, author = :author, publishDate = :publishDate WHERE id = :id
            """;

        SqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("id", book.id())
            .addValue("name", book.name())
            .addValue("description", book.description())
            .addValue("author", book.author())
            .addValue("publishDate", java.sql.Timestamp.from(book.publishDate().toInstant()));
        namedJdbcTemplate.update(query, parameters);

    }

    @Override
    public List<Book> findBooksByName(String name) {
        String query = "SELECT * FROM books WHERE name = :name";

        SqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("name", name);
        return namedJdbcTemplate.query(query, parameters, BookMapper::mapToBook);
    }

    @Override
    public List<Book> findBooksByAuthor(@NonNull String author) {
        String query = "SELECT * FROM books WHERE author = :author";

        SqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("author", author);
        return namedJdbcTemplate.query(query, parameters, BookMapper::mapToBook);
    }

    @Override
    public List<Book> findBooksPublishedInDateRange(OffsetDateTime from, OffsetDateTime to) {
        String query = "SELECT * FROM books WHERE publishDate BETWEEN :from AND :to";

        SqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("from", Timestamp.from(from.toInstant()))
            .addValue("to", Timestamp.from(to.toInstant()));
        return namedJdbcTemplate.query(query, parameters, BookMapper::mapToBook);
    }
}
