package org.jniemiec.screeanureads.parsers;

import org.jniemiec.screeanureads.model.InstaStoryUnitData;
import org.jniemiec.screeanureads.model.Labels;

public class ParserSelector {

    public static Parser<InstaStoryUnitData> selectParser(String input) {

        if(ParserUtils.countMatches(input, Labels.EN.ACCOUNTS_REACHED_LABEL) > 0
                || ParserUtils.countMatches(input, "Content") > 0
                || ParserUtils.countMatches(input, "Taps") > 0
                || ParserUtils.countMatches(input, "Navigation") > 0
        )  {
            return new InstaStoryDateParserEN();
        }
        if(ParserUtils.countMatches(input, Labels.EN.ACCOUNTS_REACHED_LABEL) > 0
                || ParserUtils.countMatches(input, "gacja") > 0
                || ParserUtils.countMatches(input, "zawar") > 0
        ) {
            return new InstaStoryDateParserPL();
        }
    return new OtherImageParser();
    }
}
