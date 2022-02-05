package org.jniemiec.screeanureads.readers;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.jniemiec.screeanureads.util.FileUtil;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TextFromImageImplTest {

    private String output;

    @Given("Image path is image.jpg$")
    public void image_path_is_image_jpg() {
        File file = FileUtil.imageFromResources("image.jpg");
        output = new TextFromImageReaderImpl().read(file);
    }

    @Then("^Expected output String is (.*)$")
    public void verify(String expectedOutput) {
        System.out.println("Expected output: " + expectedOutput);
        System.out.println("Output: " + output);
        assertTrue(expectedOutput.equals(output.trim()));
    }
}
