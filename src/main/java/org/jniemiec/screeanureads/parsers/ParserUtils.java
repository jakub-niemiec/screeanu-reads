package org.jniemiec.screeanureads.parsers;

import org.apache.commons.lang3.StringUtils;

public class ParserUtils {

    public static int countMatches(String input, String substring) {
        return StringUtils.countMatches(input, substring);
    }

    public static Integer extractFirstNumberLocatedAfterAString(String input, String string) {

        Integer firstOccurenceOfAString = firstOccurenceOfAString(input, string);
        if (firstOccurenceOfAString == null) {
            return null;
        }


        Integer firstDigit = findTheFirstDigitAfterAnIndexAndWhitespace(input, firstOccurenceOfAString + string.length());
        if (firstDigit == null) {
            System.out.println("There is no digit after the first occurrence of string '" + string + "' in '" + input + "'.");
            return null;
        }

        Integer lastCharOfASequenceThatIsDigitOrDelimiter = findTheLastCharOfANumericSequence(input, firstDigit);

        String numberWithDelimiters = input.substring(firstDigit, lastCharOfASequenceThatIsDigitOrDelimiter + 1);
        System.out.println("Number with delimiters: '" + numberWithDelimiters + "'.");

        return covertToInteger(numberWithDelimiters);
    }

    public static Integer extractLastNumberLocatedBeforeAString(String input, String string) {

        Integer firstOccurenceOfAString = firstOccurenceOfAString(input, string);
        if (firstOccurenceOfAString == null) {
            return null;
        }


        Integer lastDigit = findTheLastDigitBeforeAnIndexAndWhitespace(input, firstOccurenceOfAString);
        if (lastDigit == null) {
            System.out.println("There is no digit before the first occurrence of string '" + string + "' in '" + input + "'.");
            return null;
        }

        Integer firstCharOfASequenceThatIsDigitOrDelimiter = findTheFirstCharOfANumericSequence(input, lastDigit);

        String numberWithDelimiters = input.substring(firstCharOfASequenceThatIsDigitOrDelimiter, lastDigit + 1);
        System.out.println("Number with delimiters: '" + numberWithDelimiters + "'.");

        return covertToInteger(numberWithDelimiters);
    }

    public static Integer covertToInteger(String numberWithDelimiters) {
        String numberOnly = numberWithDelimiters.replaceAll("[^0-9]", "");
        System.out.println("Number only: '" + numberOnly + "'.");
        return Integer.parseInt(numberOnly);
    }

    public static Integer firstOccurenceOfAString(String input, String string) {
        int firstOccurenceOfAString = input.indexOf(string);
        if (firstOccurenceOfAString == -1) {
            System.out.println("String '" + string + "' is not a substring of '" + input + "'.");
            return null;
        }
        return firstOccurenceOfAString;
    }

    public static Integer findTheFirstDigitAfterAnIndexAndWhitespace(String input, int index) {
        boolean isWhitespaceInBetween = false;
        for (int idx = index; idx < input.length(); idx++) {
            char ch = input.charAt(idx);
            if (isDigit(ch)) {
                return idx;
            } else if(isWhitespace(ch)) {
                isWhitespaceInBetween = true;
            } else if(!isWhitespace(ch) && isWhitespaceInBetween) {
                // String "input" without white spaces has finished and we had a whitespace,
                // so now we expect a digit - if it is not a digit, we should return null
                return null;
            }
        }
        return null;
    }

    public static Integer findTheLastDigitBeforeAnIndexAndWhitespace(String input, int index) {
        boolean isWhitespaceInBetween = false;
        for (int idx = index - 1; idx >= 0; idx--) {
            char ch = input.charAt(idx);
            if (isDigit(ch)) {
                return idx;
            } else if(isWhitespace(ch)) {
                isWhitespaceInBetween = true;
            } else if(!isWhitespace(ch) && isWhitespaceInBetween) {
                // String "input" without white spaces has finished and we had a whitespace,
                // so now we expect a digit - if it is not a digit, we should return null
                return null;
            }
        }
        return null;
    }

    public static Integer findTheLastCharOfANumericSequence(String input, int idxOfTheFirstDigitInASequence) {

        char firstDigitInASequence = input.charAt(idxOfTheFirstDigitInASequence);
        if (!isDigit(firstDigitInASequence)) {
            throw new RuntimeException("Wrong usage of the function. Chat at [" +
                    idxOfTheFirstDigitInASequence + "] is not a digit. It's '" + firstDigitInASequence + "'.");
        }

        for (int idx = idxOfTheFirstDigitInASequence + 1; idx < input.length(); idx++) {
            char ch = input.charAt(idx);
            if (!isDigitOrDelimiter(ch)) {
                if (isDigit(input.charAt(idx - 1))) {
                    return idx - 1;
                }
                if (isDelimiter(input.charAt(idx - 1))) {
                    return idx - 2;
                }
            } else if (isDelimiter(ch)) {
                if (isDelimiter(input.charAt(idx - 1))) {
                    return idx - 2;
                }
            }
        }
        char ch = input.charAt(input.length() - 1);
        // now ch must be digit or delimiter
        if (isDigit(ch)) {
            return input.length() - 1;
        }
        return input.length() - 2;
    }

    public static Integer findTheFirstCharOfANumericSequence(String input, int idxOfTheLastDigitInASequence) {

        char lastDigitInASequence = input.charAt(idxOfTheLastDigitInASequence);
        if (!isDigit(lastDigitInASequence)) {
            throw new RuntimeException("Wrong usage of the function. Chat at [" +
                    idxOfTheLastDigitInASequence + "] is not a digit. It's '" + lastDigitInASequence + "'.");
        }

        for (int idx = idxOfTheLastDigitInASequence - 1; idx >= 0; idx--) {
            char ch = input.charAt(idx);
            if (!isDigitOrDelimiter(ch)) {
                if (isDigit(input.charAt(idx + 1))) {
                    return idx + 1;
                }
                if (isDelimiter(input.charAt(idx + 1))) {
                    return idx + 2;
                }
            } else if (isDelimiter(ch)) {
                if (isDelimiter(input.charAt(idx + 1))) {
                    return idx + 2;
                }
            }
        }

        char ch = input.charAt(0);
        // now ch must be digit or delimiter
        if (isDigit(ch)) {
            return 0;
        }
        return 1;
    }

    public static boolean isWhitespace(char ch) {
        return Character.isWhitespace(ch);
    }

    public static boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    public static boolean isDelimiter(char ch) {
        return (',' == ch || ' ' == ch);
    }

    public static boolean isDigitOrDelimiter(char ch) {
        return isDigit(ch) || isDelimiter(ch);
    }
}
