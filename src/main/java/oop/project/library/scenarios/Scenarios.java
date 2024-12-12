package oop.project.library.scenarios;

import oop.project.library.command.Command;
import oop.project.library.lexer.Lexer;
import oop.project.library.parser.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

/* DOCUMENTATION:
 * Scenarios.java is responsible for processing various commands within the library
 *
 * Each command processed by corresponding method which is based on users initial input
 * values following are the commands arguments
 * Arguments can be positional, named, or optional
 * positional arguments are the default
 * named arguments are to be specified with --flag
 * optional arguments are dictated by command type
 *
 * Input specification for commands include these arguments-
 *
 * lex - any number of any arguments
 * add - two positional arguments
 * sub - two named arguments
 * fizzbuzz - one positional argument
 * difficulty - one positional argument
 * echo - one optional argument (treat as positional)
 * search - one positional and 1 optional argument
 * weekday - one positional argument
 *
 */

public class Scenarios {

    public static Result<Map<String, Object>> parse(String command) {
        var split = command.split(" ", 2);
        var base = split[0];
        var arguments = split.length == 2 ? split[1] : "";
        return switch (base) {
            case "lex" -> lex(arguments);
            case "add" -> add(arguments);
            case "sub" -> sub(arguments);
            case "fizzbuzz" -> fizzbuzz(arguments);
            case "difficulty" -> difficulty(arguments);
            case "echo" -> echo(arguments);
            case "search" -> search(arguments);
            case "weekday" -> weekday(arguments);
            default -> throw new AssertionError("Undefined command " + base + ".");
        };
    }
    // Used for testing internal lexer behavior
    private static Result<Map<String, Object>> lex(String arguments) {
        try {
            var lexArguments = Lexer.lex(arguments);
            Map<String, Object> result = new HashMap<>(lexArguments.named());
            return new Result.Success<>(result);
        } catch (Exception e) {
            return new Result.Failure<>("Error lexing arguments: " + e.getMessage());
        }
    }
    //adds together two positional arguments
    private static Result<Map<String, Object>> add(String arguments) {
        try {
            Command add = Command.add(); //this initializes the arguments for the add command
            Map<String, Object> result = Command.runAll(add,arguments);
            return new Result.Success<>(result);
            }catch (Exception e) {
            return new Result.Failure<>(e.getMessage());
        }
    }
    //subtracts two named arguments
    private static Result<Map<String, Object>> sub(String arguments) {
        try {
            Command sub = Command.sub();
            Map<String, Object> result = Command.runAll(sub,arguments);
            return new Result.Success<>(result);
        } catch (Exception e) {
            return new Result.Failure<>(e.getMessage());
        }
    }
//runs fizzbuzz algorithm on a number between the ranger 0-100
    //Returns an error if not between 0-100
    private static Result<Map<String, Object>> fizzbuzz(String arguments) {
        try {
            Command fizz = Command.fizzbuzz();
            Map<String, Object> result = Command.runAll(fizz,arguments);
            return new Result.Success<>(result);
        } catch (Exception e) {
            return new Result.Failure<>("Error in fizzbuzz command: " + e.getMessage());
        }
    }
//Takes positional argument that must be one of the below strings
    //"easy", "normal", "hard", "peaceful"
    private static Result<Map<String, Object>> difficulty(String arguments) {
        try {
            Command diff = Command.difficulty();
            Map<String, Object> result = Command.runAll(diff,arguments);
            return new Result.Success<>(result);
        } catch (Exception e) {
            return new Result.Failure<>("Error in difficulty command: " + e.getMessage());
        }
    }
     /*
     * Echos arguments back to the user
     * Takes and optional argument
     * if argument present will echo provided argument
     * if not the default will display "Echo, echo, echo..."
     */
    private static Result<Map<String, Object>> echo(String arguments) {
        try {
            Command echo = Command.echo();
            Map<String, Object> result = Command.runAll(echo,arguments);
            return new Result.Success<>(result);
        } catch (Exception e) {
            return new Result.Failure<>("Error in echo command: " + e.getMessage());
        }
    }
//Searches for term given by provided arguments
// Takes one positional argument of any string (without spaces) and an optional flag
// Can specify case sensitivity with optional boolean flag --case-insensitive [true or false]
    private static Result<Map<String, Object>> search(String arguments) {
        try {
            Command search = Command.search();
            Map<String, Object> result = Command.runAll(search,arguments);
            return new Result.Success<>(result);
        } catch (Exception e) {
            return new Result.Failure<>("Error in search command: " + e.getMessage());
        }
    }
    //provides weekday for given date
// Takes one positional argument which is an ISO-8601 data
    //Will be stored as a LocalDate
    private static Result<Map<String, Object>> weekday(String arguments) {
        try {
            Parser.putCustomParser(LocalDate.class, value -> {
                try {
                    return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException("Expected valid date format: " + value);
                }
            });
            Command wkdy = Command.weekday();
            Map<String, Object> result = Command.runAll(wkdy,arguments);
            return new Result.Success<>(result);
        } catch (Exception e) {
            return new Result.Failure<>("Error in weekday command: " + e.getMessage());
        }
    }

}
