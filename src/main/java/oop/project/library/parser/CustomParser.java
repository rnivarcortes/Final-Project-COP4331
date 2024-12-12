package oop.project.library.parser;

import java.util.HashMap;
import java.util.Map;

public class CustomParser {
    private static final Map<Class<?>, Parser<?>> customParsers = new HashMap<Class<?>, Parser<?>>();
    public static <T> void putCustomParser(Class<T> customType, Parser<T> parser) {}
    public static Object parseCustom(String value, Class<?> customType) {
        if (customType == null || !customParsers.containsKey(customType)) {
            throw new IllegalArgumentException("No parser registered for custom type: " + customType);
        }
        // Use the registered parser for the specified custom type
        //return customParsers.get(customType).parse(value);
        return null;
    }
}
