package oop.project.library.parser;

import java.text.ParseException;

public class BooleanParser implements Parser<Boolean> {

    @Override
    public Boolean parse(String value) throws ParseException {
        if ("true".equalsIgnoreCase(value)) {
            return true;
        } else if ("false".equalsIgnoreCase(value)) {
            return false;
        } else {
            throw new IllegalArgumentException("Invalid boolean: " + value);
        }
    }
}
