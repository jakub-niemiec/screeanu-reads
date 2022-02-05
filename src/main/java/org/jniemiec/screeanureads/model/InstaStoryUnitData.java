package org.jniemiec.screeanureads.model;

import java.io.File;

public class InstaStoryUnitData {

    private File file;
    private String input;
    private Integer accountsReached;
    private Integer impressions;
    private Integer linkClicks;
    private Integer stickerTaps;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Integer getAccountsReached() {
        return accountsReached;
    }

    public void setAccountsReached(Integer accountsReached) {
        this.accountsReached = accountsReached;
    }

    public Integer getImpressions() {
        return impressions;
    }

    public void setImpressions(Integer impressions) {
        this.impressions = impressions;
    }

    public Integer getLinkClicks() {
        return linkClicks;
    }

    public void setLinkClicks(Integer linkClicks) {
        this.linkClicks = linkClicks;
    }

    public Integer getStickerTaps() {
        return stickerTaps;
    }

    public void setStickerTaps(Integer stickerTaps) {
        this.stickerTaps = stickerTaps;
    }

    @Override
    public String toString() {
        return "Data extracted from file " + file.getName() + ": " +
                "\n\taccountsReached=" + formatInteger(accountsReached) +
                "\n\timpressions=" + formatInteger(impressions) +
                "\n\tlinkClicks=" + formatInteger(linkClicks) +
                "\n\tstickerTaps=" + formatInteger(stickerTaps) +
                "\n";
    }

    private String formatInteger(Integer value) {
        if(value == null) {
            return "[NO DATA FOUND]";
        }
        else return value.toString();
    }

    public boolean isEmpty() {
        return (accountsReached == null && impressions == null && linkClicks == null && stickerTaps == null);
    }

    public boolean noImpressionOrAccountsReached() {
        return (accountsReached == null || impressions == null);
    }


}
