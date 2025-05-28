package DesignPatterns.Behavioural.Strategy;

public class CreditPay implements PaymentMode {
    private String name;

    public CreditPay(String myCreditName) {
        this.name = myCreditName;
    }

    @Override
    public String pay() {
       return " doing payment through Credit Card "+ name;
    }
}
