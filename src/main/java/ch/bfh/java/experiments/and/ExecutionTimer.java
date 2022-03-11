package ch.bfh.java.experiments.and;

import java.util.function.Supplier;

public class ExecutionTimer<T> {

    public T result = null;
    public long time = 0; // nanoseconds

    public ExecutionTimer(final Supplier<T> code) {
        System.gc(); // important !
        long start = System.nanoTime();
        this.result = code.get();
        long end = System.nanoTime();
        this.time = end - start;
    }

}