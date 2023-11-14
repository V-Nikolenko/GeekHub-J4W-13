package org.geekhub.hw5;

import org.geekhub.hw5.exception.FileException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileUtilsTest {

    @Test
    void testReadAllLines() throws IOException {
        String content = "Line 1\nLine 2\nLine 3";
        Path tempFile = Files.createTempFile("testReadAllLines", ".txt");
        Files.write(tempFile, content.getBytes());

        List<String> lines = FileUtils.readAllLines(tempFile.toString());

        assertEquals(List.of("Line 1", "Line 2", "Line 3"), lines);
    }

    @Test
    void testReadAllLinesNonexistentFile() {
        assertThrows(FileException.class, () -> FileUtils.readAllLines("nonexistent.txt"));
    }

    @Test
    void testCreateDirectories() throws IOException {
        Path tempDir = Files.createTempDirectory("testCreateDirectories");
        Path nestedDir = tempDir.resolve("nested");

        FileUtils.createDirectories(nestedDir);

        assertTrue(Files.exists(nestedDir));
    }

    @Test
    void testWriteToFile() throws IOException {
        String content = "Hello, World!";
        Path tempFile = Files.createTempFile("testWriteToFile", ".txt");

        FileUtils.writeToFile(tempFile, content.getBytes());

        assertEquals(content, Files.readString(tempFile));
    }

    @Test
    void testCopyToFile() throws IOException {
        String content = "Copy this content!";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes());
        Path tempFile = Files.createTempFile("testCopyToFile", ".txt");

        FileUtils.copyToFile(inputStream, tempFile);

        inputStream.close();

        assertEquals(content, Files.readString(tempFile));
    }

    @Test
    void testCreateFileIfNotExists() throws IOException {
        Path tempFile = Files.createTempFile("testCreateFileIfNotExists", ".txt");

        FileUtils.createFileIfNotExists(tempFile);

        assertTrue(Files.exists(tempFile));
    }

    @Test
    void testDeleteDirectories() throws IOException {
        Path tempDir = Files.createTempDirectory("testDeleteDirectories");
        Path nestedDir = tempDir.resolve("nested");
        Path nestedFile = nestedDir.resolve("file.txt");

        Files.createDirectories(nestedDir);
        Files.createFile(nestedFile);

        FileUtils.deleteDirectories(tempDir.toString());

        assertFalse(Files.exists(tempDir));
    }

    @Test
    void testDeleteDirectoriesIOException() {
        assertThrows(FileException.class, () -> FileUtils.deleteDirectories("/testDirectory"));
    }

    @Test
    void testDeleteIfExists() throws IOException {
        Path tempFile = Files.createTempFile("testDeleteIfExists", ".txt");

        FileUtils.deleteIfExists(tempFile);

        assertFalse(Files.exists(tempFile));
    }

    @Test
    void testDeleteIfExistsNonexistentFile() {
        Path nonexistentFile = Path.of("nonexistent.txt");

        assertDoesNotThrow(() -> FileUtils.deleteIfExists(nonexistentFile));
    }
}
