package Interview150.Week1;

import DataStructures.LinkeList.Node;

import java.util.HashSet;
import java.util.Stack;


public class Day5 {
    public static Node sortList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node mid = slow.next;
        slow.next = null;
        // Recursively split and merge
        Node left = sortList(head);
        Node right = sortList(mid);
        return merge(left, right);
    }

    private static Node merge(Node left, Node right) {
        Node dummy = new Node(0);
        Node temp = dummy;

        while (left != null && right != null) {
            if (left.data < right.data) {
                temp.next = left;
                left = left.next;
            } else {
                temp.next = right;
                right = right.next;
            }
            temp = temp.next;
        }

        temp.next = (left != null) ? left : right;
        return dummy.next;
    }

    public static void printList(Node head) {
        while (head != null) {
            System.out.print(STR."\{head.data} -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else if (stack.isEmpty() || !isMatchingPair(stack.pop(), c)) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '{' && close == '}') ||
                (open == '[' && close == ']') ||
                (open == '(' && close == ')');

    }

    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        HashSet<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int longestStreak = 0;
        for (int num : numSet) {
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }


    public static void main(String[] args) {
        Node head = new Node(4);
        head.next = new Node(2);
        head.next.next = new Node(1);
        head.next.next.next = new Node(3);
        printList(head);
        head = sortList(head);
        printList(head);
        System.out.println(isValid("{[]}"));
        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }


}

