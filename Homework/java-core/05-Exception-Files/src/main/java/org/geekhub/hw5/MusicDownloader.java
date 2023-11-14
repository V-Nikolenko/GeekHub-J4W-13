package org.geekhub.hw5;

import static org.geekhub.hw5.FileUtils.deleteDirectories;

public class MusicDownloader {

    public static void main(String[] args) {
        //TODO-1 Replace path-to-playlist.txt with the correct path to playlist.txt
        String pathToPlaylist = "path-to-playlist.txt";
        String pathToLogFile = "log.txt";
        String mainDirectory = "library";
        int maxFileSize = 10485760;

        ContentValidator contentValidator = new ContentValidator(maxFileSize);
        Downloader downloader = new Downloader(contentValidator, mainDirectory, pathToLogFile);

        downloader.download(pathToPlaylist);

        deleteDirectories(mainDirectory);
    }
}
