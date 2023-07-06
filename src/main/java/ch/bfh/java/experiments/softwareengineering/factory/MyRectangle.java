package ch.bfh.java.experiments.softwareengineering.factory;

public class MyRectangle implements MyShape{
    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle, beep boop");
    }
}
