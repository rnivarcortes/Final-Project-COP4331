package oop.project.library.scenarios;

import oop.project.library.lexer.Lexer;
import oop.project.library.parser.Parser;

import java.util.HashMap;
import java.util.Map;

public class Scenarios {

    public static Result<Map<String, Object>> parse(String command) {
        //Note: Unlike argparse4j, our library will contain a lexer than can
        //support an arbitrary String (instead of requiring a String[] array).
        //We still need to split the base command from the actual arguments
        //string to know which scenario (aka command) we're trying to parse
        //arguments for. This sounds like something a library should handle...
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

    private static Result<Map<String, Object>> lex(String arguments) {
        //Note: For ease of testing, this should use your Lexer implementation
        //directly rather and return those values.
        try {
            Map<String, String> lexArguments = Lexer.lex(arguments);
            Map<String, Object> result = new HashMap<>(lexArguments);
            return new Result.Success<>(result);
        } catch (Exception e) {
            return new Result.Failure<>("Error lexing arguments: " + e.getMessage());
        }
    }

    private static Result<Map<String, Object>> add(String arguments) {
        //Note: For this part of the project, we're focused on lexing/parsing.
        //The implementation of these scenarios isn't going to look like a full
        //command, but rather some weird hodge-podge mix. For example:
        //var args = Lexer.parse(arguments);
        //var left = IntegerParser.parse(args.positional[0]);
        //This is fine - our goal right now is to implement this functionality
        //so we can build up the actual command system in Part 3.
        try {
            var args = Lexer.lex(arguments);
            int left = (Integer) Parser.parseArgument(args.get("0"), Parser.Type.INTEGER, null);
            int right = (Integer) Parser.parseArgument(args.get("1"), Parser.Type.INTEGER, null);
            Map<String, Object> result = new HashMap<>();
            result.put("left", left);
            result.put("right", right);
            return new Result.Success<>(result);
            //Map<String, Object> result = new HashMap<>(args);
            //return new Result.Success<>(result);
        } catch (Exception e) {
            return new Result.Failure<>("Error in add command: " + e.getMessage());
        }
    }

    private static Result<Map<String, Object>> sub(String arguments) {
        try {
            var args = Lexer.lex(arguments);
            double left = (double) Parser.parseArgument(args.get("left"), Parser.Type.DOUBLE, null);
            double  right = (double) Parser.parseArgument(args.get("right"), Parser.Type.DOUBLE, null);
            Map<String, Object> result = new HashMap<>();
            result.put("left", left);
            result.put("right", right);
            return new Result.Success<>(result);
            //Map<String, Object> result = new HashMap<>(args);
            //return new Result.Success<>(result);
        } catch (Exception e) {
            return new Result.Failure<>("Error in sub command: " + e.getMessage());
        }
    }

    private static Result<Map<String, Object>> fizzbuzz(String arguments) {
        //Note: This is the first command your library may not support all the
        //functionality to implement yet. This is fine - parse the number like
        //normal, then check the range manually. The goal is to get a feel for
        //the validation involved even if it's not in the library yet.
        //var number = IntegerParser.parse(lexedArguments.get("number"));
        //if (number < 1 || number > 100) ...
        throw new UnsupportedOperationException("TODO"); //TODO
    }

    private static Result<Map<String, Object>> difficulty(String arguments) {
        throw new UnsupportedOperationException("TODO"); //TODO
    }

    private static Result<Map<String, Object>> echo(String arguments) {
        throw new UnsupportedOperationException("TODO"); //TODO
    }

    private static Result<Map<String, Object>> search(String arguments) {
        throw new UnsupportedOperationException("TODO"); //TODO
    }

    private static Result<Map<String, Object>> weekday(String arguments) {
        throw new UnsupportedOperationException("TODO"); //TODO
    }

}
