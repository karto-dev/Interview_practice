package DesignPatterns.Structural.Decorator;

public abstract class CoffeDecorator {
    Coffee coffee;

    public String getName() {
        return coffee.getName();
    }

    public String getDescription() {
        return coffee.getDescription();
    }

}
