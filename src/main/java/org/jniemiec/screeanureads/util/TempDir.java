package org.jniemiec.screeanureads.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TempDir {

    private static TempDir instance = new TempDir();
    private File tempDir;

    public static File get() {
        return instance.tempDir;
    }

    public static void delete() {
        try {
            FileUtils.deleteDirectory(get());
        } catch (IOException e) {
            System.out.println("Cannot delete te temporary folder: " + get().getAbsolutePath());
            e.printStackTrace();
        }
    }

    public TempDir() {
        tempDir = createTempDirectory();
    }

    private File createTempDirectory() {
        Path path = null;
        try {
            path = Files.createTempDirectory("screeanu_reads_tmp_");
        } catch (IOException e) {
            System.out.println("Cannot create tmp directory: " +  path.toAbsolutePath());
            e.printStackTrace();
        }
        System.out.println("Temporary directory path: " +  path.toAbsolutePath());
        return path.toFile();
    }
}
