package DataStructures.StackAndQueue;

public class StackImpl {
    private int[] items;
    private int top;
    private int capacity;

    public StackImpl(int capacity) {
        this.capacity = capacity;
        items = new int[capacity];
        top = -1; // Indicates the stack is empty
    }

    public void push(int item) {
        if (top == capacity - 1) {
            throw new StackOverflowError("Stack is full!");
        }
        items[++top] = item;
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }
        return items[top--];
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }
        return items[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    public static void main(String[] args) {
        StackImpl stack = new StackImpl(5);
        stack.push(10);
        stack.push(20);
        stack.push(40);
        System.out.println(stack.pop());  // Output: 20
        System.out.println(stack.peek());
        stack.pop();// Output: 10
        for(int i=0;i<stack.size();i++){
            System.out.println(stack.peek()+"iii");
        }
    }
}
