package DesignPatterns.Creation.Factory;

public class DrawingHelper {

    public Draw drawWhat(String name){
        return switch (name) {
            case "CIRCLE" -> new Circle();
            case "TRIANGLE" -> new Triangle();
            default -> null;
        };
    }

    public static void main() {
        DrawingHelper drawingHelper = new DrawingHelper();
        drawingHelper.drawWhat("CIRCLE").draw();
        drawingHelper.drawWhat("TRIANGLE").draw();
    }
}
