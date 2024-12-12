package oop.project.library.parser;

import java.text.ParseException;

public class IntegerRangeParser implements Parser<Integer> {

    @Override
    public Integer parse(String value) throws ParseException { //is our integer range parser only for 1-100?
        try {
            int temp = Integer.parseInt(value);
            if (temp > 0 && temp <= 100) {
                return temp;
            }
            throw new IllegalArgumentException("Parser Error: Expected valid integer in the range [0,100]: " + temp);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }
}
