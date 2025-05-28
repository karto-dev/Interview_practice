package Interview150.Week1;


import DataStructures.LinkeList.Node;
import DataStructures.Tree.TreeNode;


import java.util.*;

public class Day7 {
    public static Node addTwoNumbers(Node l1, Node l2) {
        Node dummy = new Node(0);
        Node current = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.data;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.data;
                l2 = l2.next;
            }
            carry = sum / 10;
            current.next = new Node(sum % 10);
            current = current.next;
        }

        return dummy.next;
    }

    public static int lengthOfLastWord(String s) {
        String[] strings = s.trim().split("\\s+");
        if (strings.length > 0) {
            return strings[strings.length - 1].length();
        } else {
            return 0;
        }
    }

    public static int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i] += 1;
                return digits;
            }
            digits[i] = 0;
        }
        int[] newDigits = new int[n + 1];
        newDigits[0] = 1;
        return newDigits;

    }


    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            TreeNode rightmostNode = null;
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                rightmostNode = node; // Always update to the last node of this level
                if ((node != null ? node.left : null) != null) queue.offer(node.left);
                if ((node != null ? node.right : null) != null) queue.offer(node.right);
            }
            result.add(rightmostNode != null ? rightmostNode.data : 0); // Capture the rightmost node's value
        }
        return result;
    }


    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word = null;
    }

    class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                node.children.putIfAbsent(c, new TrieNode());
                node = node.children.get(c);
            }
            node.word = word; // Store the complete word at the end node
        }
    }


    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        Trie trie = new Trie();

        // Insert words into Trie
        for (String word : words) {
            trie.insert(word);
        }

        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, trie.root, result);
            }
        }

        return result;
    }

    private void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
        char c = board[i][j];
        if (c == '#' || !node.children.containsKey(c)) return; // Visited or no match in Trie

        TrieNode nextNode = node.children.get(c);
        if (nextNode.word != null) { // Found a word
            result.add(nextNode.word);
            nextNode.word = null; // Avoid duplicates
        }

        board[i][j] = '#'; // Mark as visited
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : directions) {
            int newRow = i + dir[0], newCol = j + dir[1];
            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
                dfs(board, newRow, newCol, nextNode, result);
            }
        }
        board[i][j] = c; // Reset for backtracking

        // Optimization: remove nodes if they are empty
        if (nextNode.children.isEmpty()) {
            node.children.remove(c);
        }
    }



    public static void main(String[] args) {
        Node head = new Node(2);
        head.next = new Node(4);
        head.next.next = new Node(3);
        Node head2 = new Node(5);
        head2.next = new Node(6);
        head2.next.next = new Node(4);
        Day5.printList(addTwoNumbers(head, head2));
        System.out.println(lengthOfLastWord("   fly me   to   the moon  "));
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9, 9, 9})));
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);
        System.out.println(rightSideView(root));
        Day7 day7 = new Day7();
        char[][] board = {
                {'o', 'a', 't', 'h'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};
        System.out.println(day7.findWords(board, words)); // Output: [oath, eat]


    }
}
