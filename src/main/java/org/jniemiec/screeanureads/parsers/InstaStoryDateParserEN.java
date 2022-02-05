package org.jniemiec.screeanureads.parsers;

import static org.jniemiec.screeanureads.model.Labels.EN.*;

public class InstaStoryDateParserEN extends InstaStoryDataParser {

    @Override
    public String getAccountsReachedLabel() {
        return ACCOUNTS_REACHED_LABEL;
    }

    @Override
    public String getImpressionsLabel() {
        return IMPRESSIONS_LABEL;
    }

    @Override
    protected String getLinkClicksLabel() {
        return LINK_CLICKS_LABEL;
    }

    @Override
    protected String getStickerTapsLabel() {
        return STICKER_TAPS_LABEL;
    }
}
