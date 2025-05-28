package DataStructures.StackAndQueue;

public class QueueImpl {
    private int[] items;
    private int front;
    private int rear;
    private int capacity;
    private int size;

    public QueueImpl(int capacity) {
        this.capacity = capacity;
        items = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(int item) {
        if (size == capacity) {
            throw new IllegalStateException("Queue is full!");
        }
        rear = (rear + 1) % capacity; // Circular queue logic
        items[rear] = item;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty!");
        }
        int item = items[front];
        front = (front + 1) % capacity; // Circular queue logic
        size--;
        return item;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty!");
        }
        return items[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        QueueImpl queue = new QueueImpl(5);
        queue.enqueue(10);
        queue.enqueue(20);
        System.out.println(queue.dequeue());  // Output: 10
        System.out.println(queue.peek());     // Output: 20
    }
}

