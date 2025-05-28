package DesignPatterns.Creation.Builder;

public class CarBuilderPattern {
    private String name,color,model;
    public void printList(){
        System.out.println(String.format(" the color of the car is %s and the name is %s and the model is %s",color,name,model));
    }
    public CarBuilderPattern(CarBuilder carBuilder){
        this.name = carBuilder.name;
        this.color = carBuilder.color;
        this.model = carBuilder.model;
    }
    public static class CarBuilder{
        private String name;
        private String color;
        private String model;

        public CarBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public CarBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public CarBuilder setName(String name) {
            this.name = name;
            return this;
        }
        public CarBuilderPattern build(){
            return new CarBuilderPattern(this);
        }


    }

    public static void main() {
        CarBuilderPattern carBuilderPattern = new CarBuilder().setName("BMV").setColor("RED").setModel("ABC").build();
        carBuilderPattern.printList();
    }

}