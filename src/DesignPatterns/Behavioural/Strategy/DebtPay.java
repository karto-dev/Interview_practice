package DesignPatterns.Behavioural.Strategy;

public class DebtPay implements PaymentMode {
    private String name;

    public DebtPay(String name) {
        this.name = name;
    }

    @Override
    public String pay() {
        return " doing payment through Debit Card "+ name;
    }
}
