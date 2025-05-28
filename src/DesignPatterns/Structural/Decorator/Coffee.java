package DesignPatterns.Structural.Decorator;

public class Coffee implements SimpleCoffee{
    @Override
    public String getName() {
        return "this is a Simple Coffee";
    }

    @Override
    public String getDescription() {
        return "This contains only milk, coffee";
    }
}
