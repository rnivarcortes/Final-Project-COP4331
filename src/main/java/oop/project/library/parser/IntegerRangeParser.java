package oop.project.library.parser;

import java.text.ParseException;

public class IntegerRangeParser implements Parser<Integer> {
    private final int min;
    private final int max;

    public IntegerRangeParser(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Integer parse(String value) throws ParseException {
        try {
            int temp = Integer.parseInt(value);
            if (temp > min && temp <= max) {
                return temp;
            }
            throw new IllegalArgumentException("Parser Error: Expected valid integer in the range [0,100]: " + temp);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }
}
