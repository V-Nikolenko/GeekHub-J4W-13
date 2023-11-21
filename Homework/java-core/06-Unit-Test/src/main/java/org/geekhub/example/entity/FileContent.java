package org.geekhub.example.entity;

public record FileContent(
    FileInfo fileInfo,
    byte[] content
) {
}
