package org.jniemiec.screeanureads.readers;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.jniemiec.screeanureads.converters.ImageFormatConverter;
import org.jniemiec.screeanureads.converters.PNG2JPGConverter;
import org.jniemiec.screeanureads.util.FileUtil;

import java.io.File;

// use one instance per thread/file
public class TextFromImageReaderImpl implements TextFromImageReader {

    private Tesseract tesseract;
    private ImageFormatConverter converter;

    public TextFromImageReaderImpl() {
        tesseract = new Tesseract();
        tesseract.setDatapath(getOSAgnosticPathToTesseractData());
        converter = new PNG2JPGConverter();
    }

    private String getOSAgnosticPathToTesseractData() {
        String tesseractDatapath = FileUtil.absolutePathOfFileFromResources("tessdata");
        System.out.println("Tesseract data path: " + tesseractDatapath);
        return tesseractDatapath;
    }

    @Override
    public String read(File file) {
        try {
            File convertedFile = converter.convert(file);
            System.out.println("Converted file path: " + convertedFile);
            return tesseract.doOCR(convertedFile); // use tesseract. instead if not multithreading
        } catch (TesseractException e) {
            System.out.println("Problems experienced with file " + file.getAbsolutePath());
            e.printStackTrace();
        }
        return null;
    }
}
