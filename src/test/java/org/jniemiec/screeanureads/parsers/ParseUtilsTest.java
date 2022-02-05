package org.jniemiec.screeanureads.parsers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParseUtilsTest {

    // ParserUtils.findTheFirstDigitAfterAnIndex

    @Test
    public void shouldFindTheFirstDigitAfterAnIndex_1() {
        Integer idx = ParserUtils.findTheFirstDigitAfterAnIndexAndWhitespace("asdasd2", 1);
        assertEquals(6, idx);
    }

    @Test
    public void shouldFindTheFirstDigitAfterAnIndex_0() {
        Integer idx = ParserUtils.findTheFirstDigitAfterAnIndexAndWhitespace("asdasd2", 0);
        assertEquals(6, idx);
    }

    @Test
    public void shouldNotFindTheFirstDigitAfterAnIndex() {
        Integer idx = ParserUtils.findTheFirstDigitAfterAnIndexAndWhitespace("asdasd", 0);
        assertNull(idx);
    }

    // ParserUtils.findFindTheLastDigitBeforeAnIndex

    @Test
    public void shouldFindTheLastDigitBeforeAnIndex_1() {
        Integer idx = ParserUtils.findTheLastDigitBeforeAnIndexAndWhitespace("as34dasd2", 5);
        assertEquals(3, idx);
    }

    @Test
    public void shouldNotFindTheLastDigitBeforeAnIndex() {
        Integer idx = ParserUtils.findTheLastDigitBeforeAnIndexAndWhitespace("asdasd2", 5);
        assertNull(idx);
    }

    @Test
    public void shouldNotFindTheLastDigitBeforeAnIndex_0() {
        Integer idx = ParserUtils.findTheLastDigitBeforeAnIndexAndWhitespace("asdasd2", 0);
        assertNull(idx);
    }

    // ParserUtils.findTheLastCharOfASequenceThatIsDigitOrDelimiter

    @Test
    public void shouldFindTheLastCharOfANumericSequence_simpleScenario() {
        Integer idx = ParserUtils.findTheLastCharOfANumericSequence("123aaa", 0);
        assertEquals(2, idx);
    }

    @Test
    public void shouldFindTheLastCharOfANumericSequence_delimiterInside() {
        Integer idx = ParserUtils.findTheLastCharOfANumericSequence("12,3aaa", 0);
        assertEquals(3, idx);
    }

    @Test
    public void shouldFindTheLastCharOfANumericSequence_delimiter2Inside() {
        Integer idx = ParserUtils.findTheLastCharOfANumericSequence("1 23aaa", 0);
        assertEquals(3, idx);
    }

    @Test
    public void shouldFindTheLastCharOfANumericSequence_numberIsTheLastString() {
        Integer idx = ParserUtils.findTheLastCharOfANumericSequence("123,567", 0);
        assertEquals(6, idx);
    }

    // ParserUtils.findTheFirstCharOfASequenceThatIsDigitOrDelimiter

    @Test
    public void shouldFindTheFirstCharOfANumericSequence_numberIsTheFirstString() {
        Integer idx = ParserUtils.findTheFirstCharOfANumericSequence("123aaa", 2);
        assertEquals(0, idx);
    }

    @Test
    public void shouldFindTheFirstCharOfANumericSequence_delimiterInside() {
        Integer idx = ParserUtils.findTheFirstCharOfANumericSequence("asd 45,67", 5);
        assertEquals(4, idx);
    }

    @Test
    public void shouldFindTheFirstCharOfANumericSequence_delimiter2Inside() {
        Integer idx = ParserUtils.findTheFirstCharOfANumericSequence("asd 45 67", 8);
        assertEquals(4, idx);
    }


    // ParserUtils.extractFirstNumberLocatedAfterAString

    @Test
    public void shouldExtractFirstNumberLocatedAfterAString_noDelimiters() {
        Integer number = ParserUtils.extractFirstNumberLocatedAfterAString("ad  123 x", "ad");
        assertEquals(123, number.intValue());
    }

    @Test
    public void shouldExtractFirstNumberLocatedAfterAString_withDelimiters() {
        Integer number = ParserUtils.extractFirstNumberLocatedAfterAString("ad s1,23 x", " s");
        assertEquals(123, number.intValue());
    }

    @Test
    public void shouldExtractFirstNumberLocatedAfterAString_withDelimiters2() {
        Integer number = ParserUtils.extractFirstNumberLocatedAfterAString("ad s1,2 3 x", "d s");
        assertEquals(123, number.intValue());
    }

    @Test
    public void shouldExtractFirstNumberLocatedAfterAString_withDelimitersTwoSpaces() {
        Integer number = ParserUtils.extractFirstNumberLocatedAfterAString("ads1,2  3 x", "ad");
        assertEquals(12, number.intValue());
    }

    @Test
    public void shouldExtractFirstNumberLocatedAfterAString_numberIsTheLastString() {
        Integer number = ParserUtils.extractFirstNumberLocatedAfterAString("ad xv896,44,33", "x");
        assertEquals(8964433, number.intValue());
    }

    @Test
    public void shouldExtractFirstNumberLocatedAfterAString_onlyNumber() {
        Integer number = ParserUtils.extractFirstNumberLocatedAfterAString("22", "");
        assertEquals(22, number.intValue());
    }

    @Test
    public void shouldNotExtractFirstNumberLocatedAfterAString() {
        Integer number = ParserUtils.extractFirstNumberLocatedAfterAString("a rf", "a");
        assertNull(number);
    }

    // ParserUtils.extractLastNumberLocatedBeforeAString

    @Test
    public void shouldExtractLastNumberLocatedBeforeAString_noDelimiters() {
        Integer number = ParserUtils.extractLastNumberLocatedBeforeAString("as23456 fff", "fff");
        assertEquals(23456, number.intValue());
    }

    @Test
    public void shouldExtractLastNumberLocatedBeforeAString_withDelimiters() {
        Integer number = ParserUtils.extractLastNumberLocatedBeforeAString("3,7fff", "fff");
        assertEquals(37, number.intValue());
    }

    @Test
    public void shouldExtractLastNumberLocatedBeforeAString_withDelimitersTwoCommas() {
        Integer number = ParserUtils.extractLastNumberLocatedBeforeAString("3,,7fff", "fff");
        assertEquals(7, number.intValue());
    }

    @Test
    public void shouldExtractLastNumberLocatedBeforeAString_withDelimiters2() {
        Integer number = ParserUtils.extractLastNumberLocatedBeforeAString("3 7 8fff", "fff");
        assertEquals(378, number.intValue());
    }

    @Test
    public void shouldNotExtractLastNumberLocatedBeforeAString() {
        Integer number = ParserUtils.extractLastNumberLocatedBeforeAString("fff", "fff");
        assertNull(number);
    }
}
