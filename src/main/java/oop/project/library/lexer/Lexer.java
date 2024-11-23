package oop.project.library.lexer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lexer {

    public record Data(
            List<String> positional,
            Map<String, String> named
    ) {}

    public static Data lex(String input) {

        Map<String,String> arguments = new HashMap<>();
        String[] values = input.split("\\s+");

        if (input.isEmpty()) {
            return new Data(List.of(values), arguments);
        }

        int index = 0;

        for (int i = 0; i < values.length; i++) {

            if (values[i].startsWith("--")) {

               if (values[i].length() < 3) {
                   throw new IllegalArgumentException("No flag name: " + values[i]);
               }

               String flag = values[i].substring(2);

                //check name of command
                //dependent of name, save into map based off argument names
                //save the string "--optional""

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

        return new Data(List.of(values), arguments);

    }
}
