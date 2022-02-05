package org.jniemiec.screeanureads.analysis;

import org.jniemiec.screeanureads.model.InstaStoryUnitData;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Analyzer {

    private List<InstaStoryUnitData> records;
    private File folder;

    private List<Integer> accountsReached = new ArrayList<>();
    private List<Integer> impressions = new ArrayList<>();
    private List<Integer> linkClicks = new ArrayList<>();
    private List<Integer> stickerTaps = new ArrayList<>();

    public Analyzer(List<InstaStoryUnitData> records, File folder) {
        this.records = records;
        this.folder = folder;
    }

    public void analyze() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n<<<<<<<<< Screeanu Reads 1.0 >>>>>>>>>>>\n");

        System.out.println("**********************************");
        System.out.println("Dane sczytane z kolejnych plików: ");
        records.forEach(record -> {
            System.out.println(record);
            accountsReached.add(record.getAccountsReached());
            impressions.add(record.getImpressions());
            linkClicks.add(record.getLinkClicks());
            stickerTaps.add(record.getStickerTaps());
        });

        System.out.println("**********************************");
        System.out.println("Podsumowanie: ");
        System.out.println("Scieżka do folderu: " + folder.getAbsolutePath());
        System.out.println("Liczba plików w folderze: " + records.size());

        System.out.println("\nPodejrzane pliki (nie sczytano danych): ");
        records.forEach(record -> {
            if(record.isEmpty()) {
                System.out.println("\t" + record.getFile().getName());
            }
        });

        System.out.println("\nPliki z Link Clicks: ");
        records.forEach(record -> {
            if(record.getLinkClicks() != null) {
                System.out.println("\t" + record.getFile().getName() + ": " + record.getLinkClicks());
            }
        });

        System.out.println("\nPliki ze Sticker Taps: ");
        records.forEach(record -> {
            if(record.getStickerTaps() != null) {
                System.out.println("\t" + record.getFile().getName() + ": " + record.getStickerTaps());
            }
        });

        System.out.println("\nZasięg: ");
        System.out.println("\tMax: " + maxValue(accountsReached));
        System.out.println("\tMin: " + minValue(accountsReached));
        System.out.println("\tŚrednia: " + averageValue(accountsReached));

        System.out.println("\nWyświetlenia: ");
        System.out.println("\tMax: " + maxValue(impressions));
        System.out.println("\tMin: " + minValue(impressions));
        System.out.println("\tŚrednia: " + averageValue(impressions));


        AtomicBoolean areThereAnyLinkClicks = new AtomicBoolean(false);
        linkClicks.forEach(record -> {
            if(record != null) {
                areThereAnyLinkClicks.set(true);
            }
        });
        if (areThereAnyLinkClicks.get()) {
            System.out.println("\nKliknięcia linków: ");
            System.out.println("\tSuma: " + sum(linkClicks));
            System.out.println("\tMax: " + maxValue(linkClicks));
            System.out.println("\tMin: " + minValue(linkClicks));
            System.out.println("\tŚrednia: " + averageValue(linkClicks));
        }

        AtomicBoolean areThereAnyStickerTaps = new AtomicBoolean(false);
        stickerTaps.forEach(record -> {
            if(record != null) {
                areThereAnyStickerTaps.set(true);
            }
        });
        if (areThereAnyStickerTaps.get()) {
            System.out.println("\nDotknięcia naklejek: ");
            System.out.println("\tSuma: " + sum(stickerTaps));
            System.out.println("\tMax: " + maxValue(stickerTaps));
            System.out.println("\tMin: " + minValue(stickerTaps));
            System.out.println("\tŚrednia: " + averageValue(stickerTaps));
        }

        System.out.println("<<<<<<<<< Autor: Kuba Niemiec >>>>>>>>>>>\n\n\n\n\n\n\n\n\n\n\n");
    }

    private int minValue(List<Integer> list) {
        ArrayList<Integer> sortedList = new ArrayList<>();
        list.forEach(record -> {
            if(record != null) {
                sortedList.add(record);
            }
        });
        Collections.sort(sortedList);
        return sortedList.get(0);
    }

    private int maxValue(List<Integer> list) {
        ArrayList<Integer> sortedList = new ArrayList<>();
        list.forEach(record -> {
            if(record != null) {
                sortedList.add(record);
            }
        });
        Collections.sort(sortedList);
        return sortedList.get(sortedList.size() - 1);
    }

    private int averageValue(List<Integer> list) {
        int sum = 0;
        int numberOfElements = 0;
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i) != null) {
                sum += list.get(i);
                numberOfElements++;
            }
        }
        return sum/numberOfElements;
    }

    private int sum(List<Integer> list) {
        int sum = 0;
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i) != null) {
                sum += list.get(i);
            }
        }
        return sum;
    }


}
