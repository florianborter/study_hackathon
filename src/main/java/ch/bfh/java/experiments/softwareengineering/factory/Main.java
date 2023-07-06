package ch.bfh.java.experiments.softwareengineering.factory;

public class Main {
    public static void main(String[] args) {
        var shapeFactory = new ShapeFactory();

        var circle = shapeFactory.createShape(MyShapeType.CIRCLE);
        var rectangle = shapeFactory.createShape(MyShapeType.RECTANGLE);
        var square = shapeFactory.createShape(MyShapeType.SQUARE);
        var hexagon = shapeFactory.createShape(MyShapeType.HEXAGON);
        var other = shapeFactory.createShape(MyShapeType.OTHER);

        circle.draw();
        rectangle.draw();
        square.draw();
        hexagon.draw();
        System.out.println(other == null);
    }
}
