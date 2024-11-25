package oop.project.library.argument;

import oop.project.library.parser.Parser;

public record Argument(
        String name,
        Parser<?> parser,
        boolean isPositional, //tue of yes, false if named
        boolean isOptional,
        Object defaultValue
) {
    public Argument(String name, Parser<?> parser, boolean isPositional, boolean isOptional, Object defaultValue) {
        this.name = name;
        this.parser = parser;
        this.isPositional = isPositional;
        this.isOptional = isOptional;
        this.defaultValue = defaultValue;
        //this.defaultValue = determineDefaultValue(name, defaultValue); //should allow for defaults to be stored
    }


}


/*
* public Argument {
        // Apply conditional default values if not explicitly provided
        if ("echo".equals(name) && defaultValue == null) {
            defaultValue = "Echo, echo, echo!";  // Set a default for "echo"
        }
        // You can add more conditions for other arguments, like "fizzbuzz", etc.
        // Example:
        if ("fizzbuzz".equals(name) && defaultValue == null) {
            defaultValue = "FizzBuzz Default";
        }
    }


    public String name() {
        return name;
    }
    public Parser<?> parser() {
        return parser;
    }
    public boolean isPositional() {
        return isPositional;
    }
    public boolean isOptional() {
        return isOptional;
    }
    public Object defaultValue() {

        return defaultValue;
    }
* */


//------------------------------------------

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
