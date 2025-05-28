package DesignPatterns.Behavioural.Observer;

public class User implements Notification{
    private final String name;

    User(String name){
        this.name = name;
    }

    @Override
    public void getNotification(String videoName) {
        System.out.println("The video "+ videoName + " has been added and notified to "+ this.name );
    }
}
