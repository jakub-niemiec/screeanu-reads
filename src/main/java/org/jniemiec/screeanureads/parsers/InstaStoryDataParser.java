package org.jniemiec.screeanureads.parsers;

import org.jniemiec.screeanureads.model.InstaStoryUnitData;

import java.io.File;

public abstract class InstaStoryDataParser implements Parser<InstaStoryUnitData> {

    @Override
    public InstaStoryUnitData parse(String input, File file) {
        InstaStoryUnitData data = new InstaStoryUnitData();
        data.setFile(file);
        data.setAccountsReached(extractAccountsReached(input));
        data.setImpressions(extractImpressions(input));
        data.setLinkClicks(extractLinkClicks(input));
        data.setStickerTaps(extractStickerTaps(input));
        return data;
    }

    protected abstract String getAccountsReachedLabel();

    protected abstract String getImpressionsLabel();

    protected abstract String getLinkClicksLabel();

    protected abstract String getStickerTapsLabel();

    private Integer extractAccountsReached(String input) {

        int accountReachedMatches = ParserUtils.countMatches(input, getAccountsReachedLabel());
        if(accountReachedMatches == 2) {
            return ParserUtils.extractFirstNumberLocatedAfterAString(input, getAccountsReachedLabel());
        } else if(accountReachedMatches == 1) {
            return ParserUtils.extractLastNumberLocatedBeforeAString(input, getAccountsReachedLabel());
        }

        System.out.println("Cannot find '" + getAccountsReachedLabel() + "' value. ");
        return null;
    }

    private Integer extractValueForLabel(String input, String label) {
        int matches = ParserUtils.countMatches(input, label);
        if(matches == 1) {
            return ParserUtils.extractFirstNumberLocatedAfterAString(input, label);
        }
        System.out.println("Cannot find '" + label + "' value. ");
        return null;
    }

    private Integer extractImpressions(String input) {
        return extractValueForLabel(input, getImpressionsLabel());
    }

    private Integer extractLinkClicks(String input) {
        return extractValueForLabel(input, getLinkClicksLabel());
    }

    private Integer extractStickerTaps(String input) {
        return extractValueForLabel(input, getStickerTapsLabel());
    }
}
