package ru.sbt.mipt.oop;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface ActionIterator<T, E> {
    public void handleFunction(BiFunction<T, E, Void> func);
}
