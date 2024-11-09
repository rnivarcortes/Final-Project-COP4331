package oop.project.library.parser;

public class Parser {

    public enum Type {
        BOOLEAN, INTEGER, DOUBLE, STRING, CUSTOM
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
        return false;
    }

}
