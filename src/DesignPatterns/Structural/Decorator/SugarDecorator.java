package DesignPatterns.Structural.Decorator;

public class SugarDecorator extends CoffeDecorator{
    SugarDecorator(Coffee coffee){
        this.coffee = coffee;
    }
    public String getName() {
        return super.getName() + " Sugar Added ";
    }

    public String getDescription() {
        return super.getDescription() + " Sugar Added ";
    }

    public static void main(String[] args) {
        SugarDecorator sugarDecorator = new SugarDecorator(new Coffee());
        System.out.println(sugarDecorator.getName() + sugarDecorator.getDescription());
    }

}
