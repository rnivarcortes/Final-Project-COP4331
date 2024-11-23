package oop.project.library.parser;

import java.text.ParseException;

public class StringParser implements Parser<String> {

    @Override
    public String parse(String value) throws ParseException {
        try {
            return value;
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }
}
