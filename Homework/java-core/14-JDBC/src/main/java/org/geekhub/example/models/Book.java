package org.geekhub.example.models;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;

public record Book(@Nullable Integer id,
                   @NonNull String name,
                   @Nullable String description,
                   @NonNull String author,
                   @NonNull OffsetDateTime publishDate) {


    public Book(@NonNull String name,
                @Nullable String description,
                @NonNull String author,
                @NonNull OffsetDateTime publishDate) {
        this(null, name, description, author, publishDate);
    }
}
