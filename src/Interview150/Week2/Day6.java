package Interview150.Week2;

import java.util.*;

public class Day6 {
    static class Node {
        int val;
        Node next, random;

        Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static Node copyRandomList(Node head) {
        if (head == null) return null;
        // Step 1: Clone nodes and place them next to originals
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }
        // Step 2: Assign random pointers to copied nodes
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }
        // Step 3: Separate original and copied lists
        Node dummy = new Node(0);
        Node copyCurr = dummy;
        curr = head;
        while (curr != null) {
            copyCurr.next = curr.next;
            copyCurr = copyCurr.next;
            curr.next = curr.next.next;
            curr = curr.next;
        }
        return dummy.next; // Head of deep copied list
    }

    public static int maxProfit(int k, int[] prices) {
        if (prices.length == 0 || k == 0)
            return 0;
        int n = prices.length;
        if (k >= n / 2) {
            return maxProfitUnlimitedTransactions(prices);
        }
        int[][] dp = new int[k + 1][n];
        for (int t = 1; t <= k; t++) {
            int maxDiff = -prices[0];
            for (int d = 1; d < n; d++) {
                dp[t][d] = Math.max(dp[t][d - 1], prices[d] + maxDiff);
                maxDiff = Math.max(maxDiff, dp[t - 1][d] - prices[d]);
            }
        }
        return dp[k][n - 1];
    }

    private static int maxProfitUnlimitedTransactions(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || words.length == 0) return result;

        int wordLength = words[0].length();
        int totalLength = wordLength * words.length;

        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        // Iterate over possible starting positions
        for (int i = 0; i < wordLength; i++) {
            int left = i, right = i;
            Map<String, Integer> seenWords = new HashMap<>();

            while (right + wordLength <= s.length()) {
                String word = s.substring(right, right + wordLength);
                right += wordLength;

                if (wordCount.containsKey(word)) {
                    seenWords.put(word, seenWords.getOrDefault(word, 0) + 1);

                    while (seenWords.get(word) > wordCount.get(word)) {
                        String leftWord = s.substring(left, left + wordLength);
                        seenWords.put(leftWord, seenWords.get(leftWord) - 1);
                        left += wordLength;
                    }

                    if (right - left == totalLength) {
                        result.add(left);
                    }
                } else {
                    seenWords.clear();
                    left = right;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Example Usage
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.random = head.next.next; // 1.random -> 3
        head.next.random = head; // 2.random -> 1
        int[] prices = {3, 2, 6, 5, 0, 3};
        int k = 2;

        System.out.println("Max Profit with " + k + " transactions: " + maxProfit(k, prices));
        Node copiedHead = copyRandomList(head);
        System.out.println("Deep copy created!");
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        System.out.println(findSubstring(s, words)); // Output: [0, 9]
    }
}










