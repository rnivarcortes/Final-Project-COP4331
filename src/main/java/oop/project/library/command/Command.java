package oop.project.library.command;

import oop.project.library.argument.Argument;
import oop.project.library.lexer.Lexer;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public record Command (
        String name,   //might not be helpful
        List<Argument> positional
) {

    public List<Object> parse(String arguments) throws ParseException{  //might be better data structure instead of object
        var lexer = Lexer.lex(arguments);
        if(!lexer.named().isEmpty()){
            throw new ParseException("Named arguments not supported", lexer.positional().size());
        }
        var map = new HashMap<String, Object>();
        int positionalIndex = 0;
        for(Argument argument : positional) {
            if (lexer.positional().size() < positionalIndex) {
                if (argument.optional()){
                    map.put(argument.name(), "");
                    continue;
                }
                throw new ParseException("Too few arguments", lexer.positional().size()); //TODO: Optional
            }
            var raw = lexer.positional().get(positionalIndex++);
            var parsed = argument.parser().parse(raw);
            map.put(argument.name(), parsed);
        }
        if (lexer.positional().size() > positionalIndex){
            throw new ParseException("Too many arguments", lexer.positional().size());
        }
        return List.of(map);
    }
}

