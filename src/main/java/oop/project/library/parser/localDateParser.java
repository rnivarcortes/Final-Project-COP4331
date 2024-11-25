package oop.project.library.parser;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class localDateParser implements Parser<LocalDate>{
    @Override
    public LocalDate parse (String value) throws ParseException{
        try {
            return LocalDate.parse(value); // This automatically handles ISO-8601 format
        } catch (DateTimeParseException e) {
            throw new ParseException("Parser Error:Not a real date" + value, 0);
        }
    }
}
