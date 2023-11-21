package org.geekhub.example;

import org.geekhub.example.entity.FileContent;
import org.geekhub.example.entity.FileInfo;
import org.geekhub.example.exception.FileSizeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;

import static java.net.URI.create;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {
    private static final URI TEST_URI = create("testUri");
    private static final FileInfo FILE_INFO = new FileInfo("name", "testUri");

    @Mock
    private FileSizeService fileSizeService;
    @Mock
    private FileContentService fileContentService;

    private FileService fileService;

    @BeforeEach
    void setUp() {
        fileService = new FileService(
            fileSizeService,
            fileContentService
        );
    }

    @Test
    void getAttachmentFile() {
        var bytes = new byte[]{1, 2, 3};

        when(fileSizeService.getExpectedBase64Size(TEST_URI)).thenReturn(10_000L);
        when(fileContentService.getFileContent(TEST_URI)).thenReturn(bytes);

        assertThat(fileService.getAttachmentFile(FILE_INFO))
            .isEqualTo(new FileContent(FILE_INFO, bytes));
    }

    @Test
    void getAttachmentFile_whenInvalidSize() {
        when(fileSizeService.getExpectedBase64Size(TEST_URI)).thenReturn(30_000_000L);

        assertThatCode(() -> fileService.getAttachmentFile(FILE_INFO))
            .isInstanceOf(FileSizeException.class)
            .hasMessage("File size exceeds the maximum allowed limit of 20 MB: 30000000");
    }
}
