package oop.project.library.parser;

import java.text.ParseException;

public class StringChoiceParser<E extends Enum<E>> implements Parser<E> {
    private final Class<E> type;

    public StringChoiceParser(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Parser Error: Cant have empty String");
        }
        this.type = type;
    }

    @Override
    public E parse(String value) throws ParseException {
        if (value == null || value.isBlank()) {
            throw new ParseException("Parser Error: String input cannot be empty", 0);
        }
        try {
            return Enum.valueOf(type, value); //should return enum value, check here if it doesnt work
        } catch (IllegalArgumentException e) {

            throw new ParseException("Parser Error: String choice not available: " + value, 0);
        }
    }
}
