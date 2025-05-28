package DesignPatterns.Behavioural.Observer;

import java.util.ArrayList;
import java.util.List;

public class YtChannnel implements Notification{

    private final String name;
    private List<User> subscribers = new ArrayList<>();

    YtChannnel(String name){
        this.name = name;
    }

    public void addSubscribers(User subscriber){
        subscribers.add(subscriber);
    }
    public void removeSubscribers(User subscriber){
        subscribers.remove(subscriber);
    }


    @Override
    public void getNotification(String videoName) {
        System.out.println("This is the Channel and the name is "+this.name);
        for(User user : subscribers){
            user.getNotification(videoName);
        }
        System.out.println("");

    }

    public static void main() {
        User user = new User("Karthik");
        User user2 = new User("Shashank");
        YtChannnel yr = new YtChannnel("My YouTube");
        yr.addSubscribers(user);
        yr.addSubscribers(user2);
        yr.getNotification("The new Channel");
        yr.removeSubscribers(user2);
        yr.getNotification("The second Channel");
    }
}
