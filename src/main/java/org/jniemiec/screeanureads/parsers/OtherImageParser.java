package org.jniemiec.screeanureads.parsers;

import org.jniemiec.screeanureads.model.InstaStoryUnitData;

import java.io.File;

public class OtherImageParser implements Parser<InstaStoryUnitData> {
    @Override
    public InstaStoryUnitData parse(String input, File file) {
        InstaStoryUnitData instaStoryUnitData = new InstaStoryUnitData();
        instaStoryUnitData.setFile(file);
        return instaStoryUnitData;
    }
}
