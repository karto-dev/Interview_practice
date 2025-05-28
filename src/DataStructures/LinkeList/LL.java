package DataStructures.LinkeList;

public class LL {
    static Node head;

    public void insertFront(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }
    public void insertLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;

    }
    public void reverse() {
        Node previous = null;
        Node current = head;
        Node next = null;
        while (current!=null){
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;

    }
    public void printList() {
        if (head == null) {
            System.out.println(" Empty List");
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(STR."\{temp.data}-->");
            temp = temp.next;
        }
        System.out.println("null");

    }
    public void insertAtIndex(int index, int data) {
        Node newNode = new Node(data);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            return;
        }
        Node current = head;
        for (int i = 0; i < index - 1 && current != null; i++) {
            current = current.next;
        }
        if (current == null) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        newNode.next = current.next;
        current.next = newNode;
    }
    public void deleteByIndex(int index) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        if (index == 0) {
            head = head.next;
            return;
        }
        Node current = head;
        for (int i = 0; i < index - 1 && current.next != null; i++) {
            current = current.next;
        }
        if (current.next == null) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        current.next = current.next.next;
    }
    public void deleteFront() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        head = head.next;
    }
    public void deleteLast() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        if (head.next == null) {
            head = null;
            return;
        }
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        current.next = null;
    }
    public static void main() {
        LL linkedList = new LL();
        linkedList.insertFront(100);
        linkedList.insertFront(200);
        linkedList.printList();
        linkedList.insertLast(400);
        linkedList.printList();
        linkedList.insertAtIndex(2, 500);
        linkedList.printList();
        linkedList.deleteByIndex(1);
        linkedList.printList();
        linkedList.reverse();
        linkedList.printList();
        linkedList.deleteFront();
        linkedList.printList();
        linkedList.deleteLast();
        linkedList.printList();
    }

}
