package DesignPatterns.Behavioural.State;

public class OnState implements Fanstate{
    @Override
    public void turnOn(Fan fan) {
        System.out.println("Already High");
    }

    @Override
    public void turnOff(Fan fan) {
        System.out.println("Moved to Mid State");
        fan.setFanstate(new MidState());
    }
}
