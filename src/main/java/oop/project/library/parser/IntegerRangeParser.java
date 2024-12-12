package oop.project.library.parser;

import java.text.ParseException;

/*
*
* Parses string to integer
* Ensures integer is within range passed in from values min,max
*
*/

public class IntegerRangeParser implements Parser<Integer> {
    private final int min;
    private final int max;

    public IntegerRangeParser(int min, int max) {
        this.min = min;
        this.max = max;
    }
//Checks to make sure value falls between given range
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
