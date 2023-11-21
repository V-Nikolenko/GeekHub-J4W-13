package org.geekhub.example;

import org.geekhub.example.entity.FileContent;
import org.geekhub.example.entity.FileInfo;
import org.geekhub.example.exception.FileSizeException;

import java.net.URI;

public class FileService {
    private final FileSizeService fileSizeService;
    private final FileContentService fileContentService;

    public FileService(
        FileSizeService fileSizeService,
        FileContentService fileContentService
    ) {
        this.fileSizeService = fileSizeService;
        this.fileContentService = fileContentService;
    }

    public FileContent getAttachmentFile(FileInfo fileInfo) {
        var uri = URI.create(fileInfo.link());
        var fileSize = fileSizeService.getExpectedBase64Size(uri);
        if (fileSize > 20_000_000) {
            throw new FileSizeException("File size exceeds the maximum allowed limit of 20 MB: " + fileSize);
        }

        var fileContent = fileContentService.getFileContent(uri);

        return new FileContent(fileInfo, fileContent);
    }
}
