package oop.project.library.argument;

import oop.project.library.parser.Parser;

/*
 * Creates argument object storing
 * name - Name of argument
 * parser - Parser type for given command
 * isPositional - True if positional, false if named
 * isOptional - True if argument is optional
 * defaultValue - default value of command
*/

public record Argument(
        String name,
        Parser<?> parser,
        boolean isPositional,
        boolean isOptional,
        String defaultValue
) {
    public Argument(String name, Parser<?> parser, boolean isPositional, boolean isOptional, String defaultValue) {
        this.name = name;
        this.parser = parser;
        this.isPositional = isPositional;
        this.isOptional = isOptional;
        this.defaultValue = defaultValue;
    }
}
