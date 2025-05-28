package DesignPatterns.Structural.Composite;

import java.util.ArrayList;
import java.util.List;

public class Folder implements FileData {
    private final String folderName;
    public Folder(String name){
        this.folderName = name;
    }
    private final List<FileRecord> arrayList = new ArrayList<>();

    public void addFile(FileRecord fileRecord){
        arrayList.add(fileRecord);
    }
    @Override
    public void getDetails() {
        System.out.println(" the Folder name is "+ folderName);
        for(FileRecord fileData : arrayList){
            fileData.getDetails();
        }
    }

    public static void main(String[] args) {
        FileRecord fileRecord = new FileRecord("abcFile");
        FileRecord fileRecord1 = new FileRecord("def");
        Folder folder = new Folder("Books");
        folder.addFile(fileRecord);
        folder.addFile(fileRecord1);
        folder.getDetails();

    }
}

