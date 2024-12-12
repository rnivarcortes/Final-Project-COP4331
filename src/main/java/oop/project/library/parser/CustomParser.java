package oop.project.library.parser;

import java.text.ParseException;

public class CustomParser<T> implements Parser<T> {
    private final Class<?> type;

    public CustomParser(Class<?> type) {
        this.type = type;
    }

     @Override
    public T parse(String value) throws ParseException {
         if (type == null || !customParsers.containsKey(type)) {
             throw new IllegalArgumentException("No parser registered for custom type: " + type);
         }
         // Use the registered parser for the specified custom type
         return (T) customParsers.get(type).parse(value);
     }
}
