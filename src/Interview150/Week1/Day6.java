package Interview150.Week1;

import DataStructures.LinkeList.Node;

import java.util.*;


public class Day6 {

    public static int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int prev2 = 0;
        int prev1 = nums[0];
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(prev1, nums[i] + prev2);
            prev2 = prev1;
            prev1 = max;
        }
        return max;
    }

    public static Node reverseBetween(Node head, int left, int right) {
        if (head == null || left == right) return head;
        Node dummy = new Node(0);
        dummy.next = head;
        Node prev = dummy;
        // Move `prev` to the node before `left`
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }
        // Reverse nodes between left and right
        Node curr = prev.next;
        Node next;
        for (int i = 0; i < right - left; i++) {
            next = curr.next;
            curr.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }
        return dummy.next;
    }

    public static String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder reversed = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]).append(" ");
        }
        return reversed.toString().trim();
    }

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int count = 0;
        int rows = grid.length, cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') { // Found an island
                    count++;
                    dfs(grid, i, j); // Mark all connected land as visited
                }
            }
        }

        return count;


    }

    private static void dfs(char[][] grid, int i, int j) {
        // Boundary check + stop at water ('0')
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0'; // Mark current cell as visited
        // Explore all four directions
        dfs(grid, i + 1, j); // Down
        dfs(grid, i - 1, j); // Up
        dfs(grid, i, j + 1); // Right
        dfs(grid, i, j - 1); // Left
    }

    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Object[] arr = map.values().toArray();
        System.out.println(map);
        Arrays.sort(arr);
        int element = (int) arr[map.size() - 1];
        int key = 0;
        for (Map.Entry<Integer, Integer> imap : map.entrySet()) {
            if (imap.getValue() == element) {
                key = imap.getKey();
            }
        }
        System.out.println(Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey());

        return key;
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{2, 7, 9, 3, 1}));
        char[][] grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head = reverseBetween(head, 2, 4);
        Day5.printList(head);
        System.out.println(reverseWords(" Hello World "));
        System.out.println(numIslands(grid));
        System.out.println(majorityElement(new int[]{1,2,3,4,4,2,4,5,3,2}));
    }
}
