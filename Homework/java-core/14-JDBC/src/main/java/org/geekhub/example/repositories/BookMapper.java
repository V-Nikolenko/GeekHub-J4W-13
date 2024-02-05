package org.geekhub.example.repositories;

import org.geekhub.example.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class BookMapper {

    public static Book mapToBook(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        String author = resultSet.getString("author");
        OffsetDateTime publishDate = resultSet.getTimestamp("publishDate").toInstant().atOffset(ZoneOffset.UTC);
        return new Book(id, name, description, author, publishDate);
    }
}
