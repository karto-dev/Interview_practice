package DesignPatterns.Structural.Adaptor;

public class NewPrinter implements printer{
    private final legacyPrinter legacyPrinterr;

    public NewPrinter(legacyPrinter legacyPrinterr) {
        this.legacyPrinterr = legacyPrinterr;
    }

    @Override
    public void print() {
        System.out.println(" this is new Printer");
        legacyPrinterr.legacyPrinting();
    }

    public static void main(String[] args) {
        legacyPrinter lg = new legacyPrinter();
        NewPrinter n = new NewPrinter(lg);
        n.print();
    }
}
