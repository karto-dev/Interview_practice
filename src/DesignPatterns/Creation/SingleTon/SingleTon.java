package DesignPatterns.Creation.SingleTon;

public class SingleTon {
    private static volatile SingleTon instance;

    public synchronized static SingleTon getInstance() {
        if(instance == null){
            instance = new SingleTon();
        }
        return instance;
    }
    public void callingMethod(){
        System.out.println(" Calling SingleTon");
    }

    public static void main(String[] args) {
        SingleTon.getInstance().callingMethod();
        SingleTon.instance.callingMethod();

    }
}
