package oop.project.library.argument;

import oop.project.library.parser.Parser;

public record Argument(
        String name,
        Parser<?> parser,
        boolean optional
) {}







/*public class Argument {

    private final String name;
    private final Parser parser;
    private final boolean optional;

    public Argument(String name, Parser parser, boolean optional) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        this.name = name;
        this.parser = parser;
        this.optional = optional;
    }

    public String name() {
        return name;
    }

    public Parser parser() {
        return parser;
    }

    public boolean optional() {
        return optional;
    }
}*/
