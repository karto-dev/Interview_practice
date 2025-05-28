package DataStructures.StackAndQueue;

import java.util.Stack;

class MyQueue {
    private Stack<Integer> pushStack;
    private Stack<Integer> popStack;

    public MyQueue() {
        pushStack = new Stack<>();
        popStack = new Stack<>();
    }

    public void push(int x) {
        pushStack.push(x);
    }

    public int pop() {
        shiftStacks();
        return popStack.pop();
    }

    public int peek() {
        shiftStacks();
        return popStack.peek();
    }

    public boolean empty() {
        return pushStack.isEmpty() && popStack.isEmpty();
    }

    private void shiftStacks() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());
        System.out.println(queue.pop());
        System.out.println(queue.empty());
    }
}