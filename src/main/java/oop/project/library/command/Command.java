package oop.project.library.command;

import oop.project.library.argument.Argument;
import oop.project.library.lexer.Lexer;
import oop.project.library.parser.*;
import oop.project.library.scenarios.Result;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Command {
    private final String name;  //might not be helpful
    List<Argument> arguments;   //this will be positional and named args

public Command(String name, List<Argument> arguments) {
    this.name = name;
    this.arguments = arguments;
}

    public List<Object> parse(String arguments) throws ParseException{  //might be better data structure instead of object
        var lexer = Lexer.lex(arguments);
        var named = lexer.named();
      //  if(!lexer.named().isEmpty()){
      //      throw new ParseException("Named arguments not supported yet", lexer.positional().size());
        // }
        var map = new HashMap<String, Object>();
        int positionalIndex = 0;

       // System.out.println("testing name: "+ name);

        for(Argument argument : this.arguments) {
            if (argument.isPositional()) { // all for positional args


                if (lexer.positional().size() < positionalIndex) { //this is where we handle positional arguments
                    if (argument.isOptional()) {
                        map.put(argument.name(), argument.defaultValue()); //improve design of "" here, default values suggested
                        continue;
                    }
                    throw new ParseException("Too few arguments", lexer.positional().size()); //TODO: Optional
                }
                var raw = lexer.positional().get(positionalIndex++);
                var parsed = argument.parser().parse(raw);
                map.put(argument.name(), parsed);
            }
            else{//these are named args
                if (named.containsKey(argument.name())){
                    if (argument.isOptional()) {
                        map.put(argument.name(), argument.defaultValue());
                        continue;
                    }
            String raw = named.get(argument.name());
            Object parsed = argument.parser().parse(raw);
            map.put(argument.name(), parsed);
                }
                else{
                    throw new ParseException("Unknown argument:" + argument.name(), positionalIndex);
                }
            }
        }
        //just a check for too many args
        if (lexer.positional().size() > positionalIndex){
            throw new ParseException("Too many arguments", lexer.positional().size());
        }
        return List.of(map);
    }


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
new Argument("difficulty", new StringParser(), true, false, null)
    ));
    }
    public static Command difficulty() {
    return new Command("difficulty", List.of(
            new Argument("difficulty", new StringChoiceParser(Choices.class), true, false, null)
    ));
    }
    public static Command echo() {
        return new Command("echo", List.of(
                new Argument("echo", new StringParser(), true, true, "Echo, echo, echo!")
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
                new Argument("date", new localDateParser(), true, false, null)
        ));

    }

public static Map<String, Object> runAll(Command command, String arguments){
    try{
        var args = Lexer.lex(arguments); //this could be wrong
//System.out.println(args);
return switch (command.name){

    case "add" -> {
        if (args.named().size() > 2){
            throw new ParseException("Too many arguments", args.named().size());
        }

        String[] tokens = arguments.split(" ");//only way I could split the space correctly
        int left = Integer.parseInt(tokens[0]);
        int right = Integer.parseInt(tokens[1]);  //assuming arguments are correct should work

        Map<String, Object> result = new HashMap<>();
        result.put("left", left);
        result.put("right", right);
        yield new Result.Success<>(result).value();
    }
    case "sub" -> {

        if (args.named().size() > 2){
            throw new ParseException("Too many arguments", args.named().size());
       }
        String leftRaw = args.named().get("left");
        String rightRaw = args.named().get("right");

        if (leftRaw == null || rightRaw == null) {
            throw new ParseException("both positional arguments were not provided", 0);
        }
        double left = Parser.useParser(new DoubleParser(), leftRaw);
        double right = Parser.useParser(new DoubleParser(), rightRaw);

        if (Double.isInfinite(left) || Double.isInfinite(right)) {
            throw new ParseException("Cant use Infinity", 0);
        }
        if (Double.isNaN(left) || Double.isNaN(right)) {
            throw new ParseException("Cant use NAN ", 0);
        }



        Map<String, Object> result = new HashMap<>();
        result.put("left", left);
        result.put("right", right);


        yield new Result.Success<>(result).value();
   }
   case "fizzbuzz" -> {
        if (args.named().size() > 1){
            throw new ParseException("Too many arguments", args.named().size());
        }

       String numRaw = args.positional().get(0);

        int num = Integer.parseInt(numRaw);
       if (num > 100 || num <= 0) {
           throw new ParseException("out of range", 0);
       }

        Map<String, Object> result = new HashMap<>();
        result.put("number", num);

        yield new Result.Success<>(result).value();
   }
   case "difficulty" -> {
        if (args.named().size() > 1){
            throw new ParseException("Too many arguments", args.named().size());
        }
       String difficultyStr = args.positional().get(0);
       Choices difficulty = null;

       try {
           // Parse the string into an enum
           difficulty = new StringChoiceParser<>(Choices.class).parse(difficultyStr);
       } catch (ParseException e) {
           throw new ParseException("Invalid difficulty choice: " + difficultyStr, 0);
       }

       // Return the parsed enum
       Map<String, Object> result = new HashMap<>();
       result.put("difficulty", difficultyStr);
       yield new Result.Success<>(result).value();
   }
   case "echo" -> {
        if (args.named().size() > 1){
            throw new ParseException("Too many or few arguments", args.named().size());
        }

        String echo;
       if (args.positional().getFirst() == "") {
           Object defaultVal = command.arguments.getFirst().defaultValue();
           echo = defaultVal.toString();
       } else {

           echo = args.positional().get(0);
       }
        Map<String, Object> result = new HashMap<>();
        result.put("message", echo);
        yield new Result.Success<>(result).value();
   }
   case "search" -> {
        if(args.named().size() > 2){
            throw new ParseException("Too many arguments", args.named().size());
        }
String term = args.positional().get(0);
        boolean isInSensitive = false;
if (args.named().size() == 2){

    isInSensitive = args.named().get("case-insensitive").equals("true");//idk
    if (!isInSensitive){ //do we just make it lowercase if it is sensitive?
        term = term.toLowerCase();
    }
}


        Map<String, Object> result = new HashMap<>();
result.put("term", term);
result.put("case-insensitive", isInSensitive);
yield new Result.Success<>(result).value();
   }
   case "weekday" -> {
        if (args.named().size() > 1){
            throw new ParseException("Too many arguments", args.named().size());
        }

LocalDate date = LocalDate.parse(arguments);


        Map<String, Object> result = new HashMap<>();
result.put("date", date);
        yield new Result.Success<>(result).value();
   }

    default -> throw new IllegalStateException("Unexpected value: " + command.name);
};


    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
}

