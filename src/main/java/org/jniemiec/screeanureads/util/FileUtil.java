package org.jniemiec.screeanureads.util;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class FileUtil {

    public static String absolutePathOfImage(String fileName) {
        return fileFromResources("images/" + fileName).getAbsolutePath();
    }

    public static String absolutePathOfFileFromResources(String fileName) {
        return fileFromResources(fileName).getAbsolutePath();
    }

    public static File getTempDirectory() {
        Path path = null;
        try {
            path = Files.createTempDirectory("prefix123");
        } catch (IOException e) {
            System.out.println("Cannot create tmp directory: " +  path.toAbsolutePath());
            e.printStackTrace();
        }
        System.out.println("Temporary directory path: " +  path.toAbsolutePath());
        return path.toFile();
    }

    public static File fileFromResources(String fileName) {
        String path = Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();
        System.out.println(path);
        return new File(path);
    }

    public static File imageFromResources(String fileName) {
        String path = Thread.currentThread().getContextClassLoader().getResource("images/" + fileName).getPath();
        System.out.println(path);
        return new File(path);
    }

    public static boolean isJPGFile(File file) {
        String extension = FilenameUtils.getExtension(file.getName());
        return Arrays.asList(new String[]{"jpg", "JPG"}).contains(extension);
    }

    public static boolean isPNGFile(File file) {
        String extension = FilenameUtils.getExtension(file.getName());
        return Arrays.asList(new String[]{"png", "PNG"}).contains(extension);
    }
}
