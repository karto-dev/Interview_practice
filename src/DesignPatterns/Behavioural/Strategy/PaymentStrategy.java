package DesignPatterns.Behavioural.Strategy;

public class PaymentStrategy {
    PaymentMode paymentMode;
    public PaymentStrategy(PaymentMode paymentMode){
        this.paymentMode = paymentMode;
    }

    public void doPayment(int money) {
        System.out.println(paymentMode.pay() + money);
    }

    public static void main() {
        PaymentStrategy paymentStrategy = new PaymentStrategy(new CreditPay("My credit Name "));
        paymentStrategy.doPayment(100);
        PaymentStrategy paymentStrategy2 = new PaymentStrategy(new DebtPay("My Debit Name "));
        paymentStrategy2.doPayment(2100);
    }
}
