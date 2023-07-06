package ch.bfh.java.experiments.softwareengineering.decorator;

public class Main {
    public static void main(String[] args) {
        var whiteRoast = new WhiteRoast();
        var whiteRoastWithMocha = new Mocha(whiteRoast);
        var whiteRoastWithMochaAndCream = new Cream(whiteRoastWithMocha);
        System.out.println(whiteRoastWithMochaAndCream.ingredients());
        System.out.println(whiteRoastWithMochaAndCream.cost());


        Beverage creamMochaDark = new Cream(new Mocha(new DarkRoast()));
        System.out.println(creamMochaDark.ingredients());
        System.out.println(creamMochaDark.cost());


        Beverage creamWhite = new Cream(new WhiteRoast());
        System.out.println(creamWhite.ingredients());
        System.out.println(creamWhite.cost());

    }
}
