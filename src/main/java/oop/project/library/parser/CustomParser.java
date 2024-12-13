package oop.project.library.parser;

import java.text.ParseException;

/*
*
* Allows use of parsers for any specific data type
* Retrieves parser from hashMap and delegates parsing task
* <T> is the type that the parser will return after parsing input string
*
*/

public class CustomParser<T> implements Parser<T> {
    private final Class<?> type;
//constructor
    public CustomParser(Class<?> type) {
        this.type = type;
    }
//parses given string with passed in parser
     @Override
    public T parse(String value) throws ParseException {
         if (type == null || !customParsers.containsKey(type)) {
             throw new IllegalArgumentException("No parser registered for custom type: " + type);
         }
         // Use the registered parser for the specified custom type
         return (T) customParsers.get(type).parse(value);
     }
}
