package ch.bfh.java.experiments.softwareengineering.factory;

public class MyCircle implements MyShape{
    @Override
    public void draw() {
        System.out.println("Drawing a Circle, beep boop");
    }
}
