package oop.project.library.parser;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/*
*
* Parser interface for parsing string into a specific type
* Functional interface used to implement custom parsers
* hashMap container which stores type and types parser
* access parser in map contained based on specific command
*
*/

@FunctionalInterface
public interface Parser<T> {

    T parse(String value) throws ParseException;

    public static <T> T useParser(Parser<T> parser, String value) throws ParseException {
        return parser.parse(value);
    }

    public static final Map<Class<?>, Parser<?>> customParsers = new HashMap<Class<?>, Parser<?>>();

    public static <T> void putCustomParser(Class<T> customType, Parser<T> parser) {customParsers.put(customType, parser);}

}
