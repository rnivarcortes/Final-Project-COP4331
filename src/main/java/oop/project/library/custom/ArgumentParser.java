package oop.project.library.custom;

@FunctionalInterface
public interface ArgumentParser<T> {
    T parse(String value) throws IllegalArgumentException;
}
