package oop.project.library.lexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lexer {

    public record Data(
            List<String> positional,
            Map<String, String> named
    ) {}

    public static Data lex(String input) {

        List<String> positional = new ArrayList<>();
        Map<String,String> named = new HashMap<>();
        String[] values = input.split("\\s+");

        if (input.isEmpty()) {
            return new Data(List.of(), Map.of()); //return empty
        }
        int index = 0;
        for (int i = 0; i < values.length; i++) {
String value = values[i];
            if (values[i].startsWith("--")) {

               if (values[i].length() < 3) {
                   throw new IllegalArgumentException("No flag name: " + values[i]);
               }

               String flag = values[i].substring(2);//gets flag name
                //check name of command
                //dependent of name, save into map based off argument names
                //save the string "--optional""
                if (i + 1 < values.length && !values[i + 1].startsWith("--")) {
                    named.put(flag, values[++i]);
                } else {
                    throw new IllegalArgumentException("Expected value for flag: " + flag);
                }

            } else if (values[i].startsWith("---")) {
                throw new IllegalArgumentException("Invalid argument format: " + values[i]);
            } else {
                named.put(String.valueOf(index++), values[i]);
            }
positional.add(values[i]);
        }
        return new Data(positional, named);

    }
}
