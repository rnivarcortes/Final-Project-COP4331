package oop.project.library.lexer;

import java.util.HashMap;
import java.util.Map;

public class Lexer {

    public static Map<String, String> lex(String input) {
        if (input.length() == 0) {
            return new HashMap<>();
        }

        Map<String,String> arguments = new HashMap<>();
        String[] values = input.split("\\s+");

        int index = 0;

        for (int i = 0; i < values.length; i++) {

            if (values[i].startsWith("--")) {

               if (values[i].length() < 3) {
                   throw new IllegalArgumentException("No flag name: " + values[i]);
               }

               String flag = values[i].substring(2);
                if (i + 1 < values.length && !values[i + 1].startsWith("--")) {
                    arguments.put(flag, values[++i]);
                } else {
                    throw new IllegalArgumentException("Expected value for flag: " + flag);
                }

            } else if (values[i].startsWith("---")) {
                throw new IllegalArgumentException("Invalid argument format: " + values[i]);
            } else {
                arguments.put(String.valueOf(index++), values[i]);
            }

        }

        return arguments;
    }
}
