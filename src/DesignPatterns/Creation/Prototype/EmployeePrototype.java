package DesignPatterns.Creation.Prototype;

import java.util.Objects;

public class EmployeePrototype implements Cloneable {
    private String name;
    private String color;

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePrototype that = (EmployeePrototype) o;
        return Objects.equals(name, that.name) && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public EmployeePrototype clone() {
        try {
            return (EmployeePrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static void main(String[] args) {
        EmployeePrototype emp = new EmployeePrototype();
        emp.setName("Kart");
        emp.setColor("red");
        System.out.println(emp.color);
        System.out.println(emp.name);
        EmployeePrototype employeePrototype = emp.clone();
        System.out.println(employeePrototype.getName());
        System.out.println(emp.equals(employeePrototype));
        System.out.println(emp == employeePrototype);

    }
}
