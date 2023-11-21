package org.geekhub.example;

import org.apache.http.impl.client.HttpClientBuilder;
import org.geekhub.example.entity.FileInfo;

public class Main {
    public static void main(String[] args) {
        var httpClient = HttpClientBuilder.create().build();
        var fileSizeService = new FileSizeService(httpClient);
        var fileContentService = new FileContentService(httpClient);
        var fileService = new FileService(fileSizeService, fileContentService);

        System.out.println(fileService.getAttachmentFile(new FileInfo("name", "link")));
    }
}
