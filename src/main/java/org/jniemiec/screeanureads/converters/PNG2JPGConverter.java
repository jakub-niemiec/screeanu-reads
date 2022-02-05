package org.jniemiec.screeanureads.converters;

import org.jniemiec.screeanureads.util.FileUtil;
import org.jniemiec.screeanureads.util.TempDir;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PNG2JPGConverter implements ImageFormatConverter {

    @Override
    public File convert(File file) {
        if(FileUtil.isJPGFile(file)){
            System.out.println("File " + file.getName() + " is a jpg file, no changes needed.");
            return file;
        }
        else if(FileUtil.isPNGFile(file)) {
            System.out.println("File " + file.getName() + " is a png file, convertion needed.");
            return convertPNG2JPG2(file);
        }
        System.out.println("This is another type of file, be careful!");
        return file;
    }

    private File convertPNG2JPG(File pngFile) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(pngFile);
        } catch (IOException e) {
            System.out.println("Cannot read from " + pngFile.getName());
            e.printStackTrace();
        }

        File output = new File(TempDir.get(), pngFile.getName() + ".jpg");
        try {
            ImageIO.write(img, "jpg", output);
            System.out.println("Absolut path to the newly created file: " + output.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Cannot write to " + output.getName());
            e.printStackTrace();
        }
        return output;
    }

    private File convertPNG2JPG2(File pngFile) {
        File output = new File(TempDir.get(), "tmp.jpg");
        try {

            BufferedImage image = ImageIO.read(pngFile);
            BufferedImage result = new BufferedImage(
                    image.getWidth(),
                    image.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            result.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
            ImageIO.write(result, "jpg", output);
            System.out.println("Absolut path to the newly created file: " + output.getAbsolutePath());

        }  catch (IOException e) {
            System.out.println("Cannot write to " + output.getName());
            e.printStackTrace();
        }
        return output;
    }

}
