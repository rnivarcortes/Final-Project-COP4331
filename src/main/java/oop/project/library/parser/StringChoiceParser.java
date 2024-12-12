package oop.project.library.parser;

import java.text.ParseException;
import java.util.List;

/*
*
* Parses string checking if it fits withing the set of valid string choices
* list of choices passed in for containing valid strings
*
*/

public class StringChoiceParser implements Parser<String> {
    private final List<String> choices;

    public StringChoiceParser(List<String> choices) {
        this.choices = choices;
    }
//checks for matching valid string choice
    @Override
    public String parse(String value) throws ParseException {
        if (value == null || value.isBlank()) {
            throw new ParseException("Parser Error: String input cannot be empty", 0);
        }
        if (choices.contains(value)) {
            return value;
        } else {
            throw new ParseException("Parser Error: String choice not available: " + value, 0);

        }
    }
}
