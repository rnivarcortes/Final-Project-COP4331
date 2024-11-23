package oop.project.library.parser;

public class StringChoiceParser implements Parser<String> {

    @Override
    public String parse(String value) {
        if (value.equals("easy") || value.equals("medium") || value.equals("hard") || value.equals("peaceful")) {
            return value;
        }
        throw new IllegalArgumentException("Expected valid value (easy, medium, hard, or peaceful)");
    }
}
