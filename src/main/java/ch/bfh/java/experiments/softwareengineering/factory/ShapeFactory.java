package ch.bfh.java.experiments.softwareengineering.factory;

public class ShapeFactory {
    public MyShape createShape(MyShapeType shapeType) {
        return switch (shapeType) {
            case CIRCLE -> new MyCircle();
            case RECTANGLE -> new MyRectangle();
            case SQUARE -> new MySquare();
            case HEXAGON -> new MyHexagon();
            default -> null;
        };
    }
}
