package DesignPatterns.Structural.Composite;

public class FileRecord implements FileData {
    private final String name;

    public FileRecord(String name) {
        this.name = name;
    }

    @Override
    public void getDetails() {
        System.out.println(STR." The name of the file is \{name}");
    }
}
