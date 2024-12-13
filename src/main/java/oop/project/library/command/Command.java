package oop.project.library.command;

import oop.project.library.argument.Argument;
import oop.project.library.lexer.Lexer;
import oop.project.library.parser.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/*
 * Separates user input into relevant data for our library to use
 * Fills argument object and inserts arguments to a list of arguments.
 * handles commands, delegates information to arguments and parser class
 */

public class Command {
    private final String name;   //name of command
    List<Argument> arguments;   //List of all arguments for the given command

public Command(String name, List<Argument> arguments) {
    this.name = name;
    this.arguments = arguments;
}
    //parses given argument string into argument object
    public static Map<String, Object> parse(String argumentsLexing, List<Argument> arguments) throws ParseException{  //might be better data structure instead of object
        var lexer = Lexer.lex(argumentsLexing);
        var named = lexer.named();
        var positional = lexer.positional();

        var map = new HashMap<String, Object>();
        int positionalIndex = 0;

        for(Argument argument : arguments) {
            if (argument.isPositional()) { //Handle positional args
                if (lexer.positional().size() <= positionalIndex) {
                    throw new ParseException("Too few arguments", lexer.positional().size());
                }
                var raw = lexer.positional().get(positionalIndex++);
                var parsed = argument.parser().parse(raw);
                map.put(argument.name(), parsed);
            }
            else { //Handle named args
                if (named.containsKey(argument.name())){
                    String raw = named.get(argument.name());
                    Object parsed = argument.parser().parse(raw);
                    map.put(argument.name(), parsed);
                } else {
                    if (positionalIndex < positional.size()) {
                        if (!Objects.equals(positional.get(positionalIndex), "")) {
                            map.put(argument.name(), argument.parser().parse(positional.get(positionalIndex)));
                        } else {
                            map.put(argument.name(), argument.parser().parse(argument.defaultValue()));
                        }
                    } else {
                        map.put(argument.name(), argument.parser().parse(argument.defaultValue()));
                    }
                }
                positionalIndex++;
            }
        }
        //Error checking for too many args
        if (lexer.positional().size() > positionalIndex){
            throw new ParseException("Too many arguments", lexer.positional().size());
        }
        return map;
    }

//Populates argument object based on what command is called
    public static Command add(){
        return new Command("add", List.of(
                new Argument("left", new IntegerParser(), true, false, null),
                new Argument("right", new IntegerParser(), true, false, null)
        ));
    }
    public static Command sub(){
        return new Command("sub", List.of(
                new Argument("left", new DoubleParser(), true, false, null),
                new Argument("right", new DoubleParser(), true, false, null)
        ));
    }
    public static Command fizzbuzz() {
        return new Command("fizzbuzz", List.of(
            new Argument("number", new IntegerRangeParser(1, 100), true, false, null)
        ));
    }
    public static Command difficulty() {
        return new Command("difficulty", List.of(
                new Argument("difficulty", new StringChoiceParser(List.of("easy", "normal", "hard", "peaceful")), true, false, null)
        ));
    }
    public static Command echo() {
        return new Command("echo", List.of(
                new Argument("message", new StringParser(), false, true, "Echo, echo, echo!")
        ));
    }

    public static Command search() {
        return new Command("search", List.of(
                new Argument("term", new StringParser(), true, false, null),
                new Argument("case-insensitive", new BooleanParser(), false, true, "false")
        ));
    }

    public static Command weekday() {
        return new Command("weekday", List.of(
                //new Argument("date", new localDateParser(), true, false, null)
                new Argument("date", new CustomParser<>(java.time.LocalDate.class), true, false, null)
        ));

    }

    public static Map<String, Object> runAll(Command command, String arguments){
        try {
            return (parse(arguments, command.arguments));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

