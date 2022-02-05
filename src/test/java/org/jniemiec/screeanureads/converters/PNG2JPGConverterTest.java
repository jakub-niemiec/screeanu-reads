package org.jniemiec.screeanureads.converters;

import org.jniemiec.screeanureads.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PNG2JPGConverterTest {

    @Test
    public void shouldConvertPNGToJPGFile() {
        ImageFormatConverter converter = new PNG2JPGConverter();
        File pngImage = FileUtil.imageFromResources("file-formats/image3.png");
        File jpgImage = converter.convert(pngImage);
        assertTrue(FileUtil.isJPGFile(jpgImage));
    }
}
