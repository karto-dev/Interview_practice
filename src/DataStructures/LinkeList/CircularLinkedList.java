package DataStructures.LinkeList;

public class CircularLinkedList {
    Node head;
    Node tail;

    public boolean isCircular() {
        if (head == null) return false;
        Node temp = head;
        while (temp.next != null) {
            if (temp.next == head) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        tail.next = head;
    }

    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        tail.next = head;
    }

    public void insertAtPosition(int data, int position) {
        Node newNode = new Node(data);
        if (position == 1) {
            insertAtBeginning(data); // Reuse the insertAtBeginning method
            return;
        }

        Node current = head;
        for (int i = 1; i < position - 1; i++) {
            current = current.next;
            if (current == head) { // If position is out of bounds
                System.out.println("Position out of bounds.");
                return;
            }
        }

        newNode.next = current.next;
        current.next = newNode;

        // If the new node is inserted at the end
        if (current == tail) {
            tail = newNode;
        }
    }

    public void deleteFromBeginning() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }

        if (head == tail) { // Single node case
            head = null;
            tail = null;
        } else {
            head = head.next;
            tail.next = head; // Update the tail to point to the new head
        }
    }

    public void deleteFromEnd() {
        if(head == null){
            System.out.println(" List is Empty");
            return;
        } if(head == tail){
            head = null;
            tail = null;
        } else {
            Node temp = head;
            while (temp.next.next != tail){
                temp = temp.next;
            }
            temp.next = tail;
            tail.next = head;
        }
    }

    public void deleteAtPosition(int position) {
        if (head == null) { // List is empty
            System.out.println("List is empty!");
            return;
        }
        if (position == 1) { // Deleting the first node
            deleteFromBeginning(); // Reuse the deleteFromBeginning method
            return;
        }
        Node current = head;
        Node previous = null;
        for (int i = 1; i < position && current != tail; i++) {
            previous = current;
            current = current.next;
        }
        if (current == tail && position > 1) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (previous != null) {
            previous.next = current.next; // Remove the node by updating pointers
        }
        // Update the tail if the deleted node was the tail
        if (current == tail) {
            tail = previous;
        }
    }

    public void traverseCircularLinkedList() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }

        Node current = head;
        do {
            System.out.print(current.data + " ");
            current = current.next;
        } while (current != head); // Stop when we reach the head again
        System.out.println();
    }

    public static void main() {
        CircularLinkedList list = new CircularLinkedList();
        // Insertions
        list.insertAtBeginning(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);
        list.insertAtPosition(15, 2); // Insert 15 at position 2
        System.out.println("After Insertions:");
        list.traverseCircularLinkedList();
        // Deletions
        list.deleteFromBeginning();
        list.deleteFromEnd();
        list.deleteAtPosition(2); // Delete the node at position 2
        System.out.println("After Deletions:");
        list.traverseCircularLinkedList();
        System.out.println(list.isCircular());
    }

}
