package ch.bfh.java.experiments.softwareengineering.iteratorpattern;

public class Main {

    //Uses Iterator Pattern
    public static void main(String[] args) {
        //The ugly way:
        System.out.println("The ugly way: ");
        var nameList = new NameList();
        var nameIterator = nameList.getIterator();
        while (nameIterator.hasNext()) {
            var element = nameIterator.next();
            System.out.println(element);
        }

        //The beautiful way
        System.out.println("\nThe beautiful way: ");
        var nameList2 = new NameListBeautiful();
        for (String name : nameList2) {
            System.out.println(name);
        }
    }
}
