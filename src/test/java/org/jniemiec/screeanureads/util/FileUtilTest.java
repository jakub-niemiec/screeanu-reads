package org.jniemiec.screeanureads.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

public class FileUtilTest {

    @Test
    public void shouldRecognizeJPGFile_jpg() {
        File image = FileUtil.imageFromResources("file-formats/image.jpg");
        assertTrue(FileUtil.isJPGFile(image));
    }

    @Test
    public void shouldRecognizeJPGFile_JPG() {
        File image = FileUtil.imageFromResources("file-formats/image2.JPG");
        assertTrue(FileUtil.isJPGFile(image));
    }

    @Test
    public void shouldNotRecognizeJPGFile_png() {
        File image = FileUtil.imageFromResources("file-formats/image3.png");
        assertFalse(FileUtil.isJPGFile(image));
    }

    @Test
    public void shouldRecognizePNGFile_png() {
        File image = FileUtil.imageFromResources("file-formats/image3.png");
        assertTrue(FileUtil.isPNGFile(image));
    }

    @Test
    public void shouldRecognizePNGFile_PNG() {
        File image = FileUtil.imageFromResources("file-formats/image4.PNG");
        assertTrue(FileUtil.isPNGFile(image));
    }

    @Test
    public void shouldNotRecognizePNGFile_JPG() {
        File image = FileUtil.imageFromResources("file-formats/image2.JPG");
        assertFalse(FileUtil.isPNGFile(image));
    }
}
