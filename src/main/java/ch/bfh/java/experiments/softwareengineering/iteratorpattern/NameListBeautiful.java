package ch.bfh.java.experiments.softwareengineering.iteratorpattern;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class NameListBeautiful implements Iterable<String> {
    public List<String> beautifulNames = Arrays.asList("Handsome Beni", "Handsome Brötli", "Handsome Buschi", "Handsome Flo", "Beautiful Lara");

    @NotNull
    @Override
    public Iterator<String> iterator() {
        return new BeautifulNameIterator();
    }

    private class BeautifulNameIterator implements Iterator<String> {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < beautifulNames.size();
        }

        @Override
        public String next() {
            return beautifulNames.get(index++);
        }
    }
}
