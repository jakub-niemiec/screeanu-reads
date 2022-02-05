package org.jniemiec.screeanureads;

import org.jniemiec.screeanureads.analysis.Analyzer;
import org.jniemiec.screeanureads.model.InstaStoryUnitData;
import org.jniemiec.screeanureads.parsers.Parser;
import org.jniemiec.screeanureads.parsers.ParserSelector;
import org.jniemiec.screeanureads.readers.TextFromImageReaderImpl;
import org.jniemiec.screeanureads.util.TempDir;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        LocalDateTime start = LocalDateTime.now();

        try {
            // String pathToDirectory= "C:\\Users\\James\\Documents\\OneDrive\\Screeanu Reads\\Screeny do testów\\InstaStories_EN\\1";
            String pathToDirectory = "C:\\Users\\James\\Desktop\\FINAL\\NISHKA\\2";
            // String pathToDirectory = "C:\\Users\\James\\Desktop\\FINAL\\ANNAMENASFUN\\1";
            // String pathToDirectory = "C:\\Users\\James\\Desktop\\insta-images";


            File folder = new File(pathToDirectory);
            File[] files = folder.listFiles();
            List<InstaStoryUnitData> records = extractDataFromFiles(files);
            new Analyzer(records, folder).analyze();

            displayTimeStatistics(start);

        } finally {
            System.out.println("Deleting temporary folder " + TempDir.get().getAbsolutePath());
            TempDir.delete();
        }
    }

    private static void displayTimeStatistics(LocalDateTime start) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime end = LocalDateTime.now();
        System.out.println("\nTime statistics: ");
        System.out.println("\tStart: " + dtf.format(start));
        System.out.println("\tEnd: " +  dtf.format(end));
        System.out.println("\tDuration: " + Duration.between(start, end));
    }

    public static List<InstaStoryUnitData> extractDataFromFiles(File[] files) {


        return Arrays.asList(files)
                .parallelStream()// use to enable multi-threading
                // .stream() // use to disable multi-threading
                .map(Main::extractDataFromFile)
                .collect(Collectors.toList());
    }

    public static InstaStoryUnitData extractDataFromFile(File file) {
        if (file.isDirectory()) {
            throw new RuntimeException("Subdirectory in the folder " + file.getName() + " - not allowed!");
        }
        System.out.println("\nFile: " + file.getName());
        String text = new TextFromImageReaderImpl().read(file);
        Parser<InstaStoryUnitData> parser = (Parser<InstaStoryUnitData>) ParserSelector.selectParser(text);
        InstaStoryUnitData data = parser.parse(text, file);

        System.out.println("==========TEXT===================");
        System.out.println("\nFile: " + file.getName());
        System.out.print(text);

        System.out.println("==========DATA===================");
        System.out.println("\nFile: " + file.getName());
        System.out.print(data);

        return data;
    }

}
