package DesignPatterns.Behavioural.State;

public class MidState implements Fanstate{
    @Override
    public void turnOn(Fan fan) {
        System.out.println("Moved to High State");
        fan.setFanstate(new OnState());
    }

    @Override
    public void turnOff(Fan fan) {
        System.out.println("Moved to Off State");
        fan.setFanstate(new OffState());
    }
}
