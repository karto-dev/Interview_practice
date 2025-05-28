package DesignPatterns.Behavioural.State;

public class Fan {
    private Fanstate fanstate;

    Fan() {
        this.fanstate = new OffState();
    }


    public void setFanstate(Fanstate fanstate) {
        this.fanstate = fanstate;
    }

    public void turnOn() {
        fanstate.turnOn(this);
    }

    public void turnOff() {
        fanstate.turnOff(this);
    }

    public static void main() {
        Fan fan = new Fan();
        fan.turnOn();
        fan.turnOn();
        fan.turnOn();
        fan.turnOff();
        fan.turnOff();
        fan.turnOff();
    }
}
