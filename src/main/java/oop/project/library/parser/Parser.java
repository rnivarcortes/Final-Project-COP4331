package oop.project.library.parser;

import oop.project.library.custom.ArgumentParser;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    public enum Type {
        BOOLEAN, INTEGER, DOUBLE, STRING, CUSTOM
    }

    private static final Map<Class<?>, ArgumentParser<?>> customParsers = new HashMap<>();

    public static <T> void putCustomParser(Class<T> customType, ArgumentParser<T> parser) {
        customParsers.put(customType, parser);
    }

    public static Object parseArgument(String value, Type type, Class<?> customType) throws IllegalArgumentException {
        switch (type) {
            case BOOLEAN:
                return parseBoolean(value);
            case INTEGER:
                return parseInteger(value);
            case DOUBLE:
                return parseDouble(value);
            case STRING:
                return parseString(value);
            case CUSTOM:
                return parseCustom(value, customType);
            default:
                throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }

    private static boolean parseBoolean(String value) {
        if ("true".equalsIgnoreCase(value)) {
            return true;
        } else if ("false".equalsIgnoreCase(value)) {
            return false;
        } else {
            throw new IllegalArgumentException("Invalid boolean: " + value);
        }
    }

    private static Integer parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid integer: " + value, e);
        }
    }

    private static Double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid double: " + value, e);
        }
    }

    private static String parseString(String value) {
        return value;
    }

    private static Object parseCustom(String value, Class<?> customType) {
        if (customType == null || !customParsers.containsKey(customType)) {
            throw new IllegalArgumentException("No parser registered for custom type: " + customType);
        }
        // Use the registered parser for the specified custom type
        return customParsers.get(customType).parse(value);
    }

}
