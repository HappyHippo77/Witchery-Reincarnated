package io.github.happyhippo77.witchery2.util;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Quadruple<T> {
    public T first;
    public T second;
    public T third;
    public T fourth;

    public Quadruple(T first, T second, T third, T fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }
}
