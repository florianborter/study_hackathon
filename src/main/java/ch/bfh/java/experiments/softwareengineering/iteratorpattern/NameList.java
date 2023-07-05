package ch.bfh.java.experiments.softwareengineering.iteratorpattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class NameList {
    public List<String> names = Arrays.asList("Beni", "Brötli", "Buschi", "Flo", "Lara");

    public Iterator<String> getIterator() {
        return new NameIterator();
    }

    private class NameIterator implements Iterator<String> {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < names.size();
        }

        @Override
        public String next() {
            return names.get(index++);
        }
    }
}
