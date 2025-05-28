package VIP50;

import java.util.*;

public class Dp {
    public static int lcs(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][0];
    }

    public static int lcsRecursive(String s1, String s2, int i, int j) {
        if (i == s1.length() || j == s2.length()) {
            return 0;
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            return 1 + lcsRecursive(s1, s2, i + 1, j + 1);
        }
        return Math.max(lcsRecursive(s1, s2, i, j + 1), lcsRecursive(s1, s2, i + 1, j));
    }

    public static int lcsOptimal(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[] cur = new int[m + 1];
        int[] prev = new int[n + 1];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    cur[j] = 1 + prev[j + 1];
                } else {
                    cur[j] = Math.max(prev[j], cur[j + 1]);
                }
            }
            prev = cur.clone();
        }
        return prev[0];
    }

    public static int LIS(int[] nums, int prevIndex, int currIndex) {
        if (currIndex >= nums.length) {
            return 0;
        }
        int exclude = LIS(nums, prevIndex, currIndex + 1);
        int include = 0;
        if (prevIndex == -1 || nums[currIndex] > nums[prevIndex]) {
            include = 1 + LIS(nums, currIndex, currIndex + 1);
        }
        return Math.max(exclude, include);
    }

    public static int LongestIncreasingSubsequence(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxLength = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }

    public static int maxCoins(int[] nums) {
        int n = nums.length;
        int[] balloons = new int[n + 2]; // Add virtual balloons with value 1 at both ends
        balloons[0] = balloons[n + 1] = 1;

        System.arraycopy(nums, 0, balloons, 1, n);

        int[][] dp = new int[n + 2][n + 2]; // Memoization table
        return burst(dp, balloons, 1, n);
    }

    private static int burst(int[][] dp, int[] balloons, int left, int right) {
        if (left > right) return 0;

        if (dp[left][right] != 0) return dp[left][right]; // Return stored result

        int maxCoins = 0;
        for (int i = left; i <= right; i++) { // Try bursting each balloon
            int coins = balloons[left - 1] * balloons[i] * balloons[right + 1]
                    + burst(dp, balloons, left, i - 1) // Left partition
                    + burst(dp, balloons, i + 1, right); // Right partition
            maxCoins = Math.max(maxCoins, coins);
        }

        dp[left][right] = maxCoins; // Memoize result
        return maxCoins;
    }

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int a = 1; a <= amount; a++) {
            for (int coin : coins) {
                if (a >= coin) {
                    dp[a] = Math.min(dp[a], 1 + dp[a - coin]);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') dfs(board, i, 0);
            if (board[i][n - 1] == 'O') dfs(board, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') dfs(board, 0, j);
            if (board[m - 1][j] == 'O') dfs(board, m - 1, j);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                if (board[i][j] == '*') board[i][j] = 'O'; // Restore safe cells
            }
        }
    }

    private static void dfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != 'O') return;
        board[i][j] = '*';
        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
    }

    public static void main(String[] args) {
        System.out.println(lcs("abcde", "ace"));
        System.out.println(lcsOptimal("abcde", "ace"));
        System.out.println(lcsRecursive("abcde", "ace", 0, 0));
        System.out.println(LongestIncreasingSubsequence(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(LIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}, -1, 0));
        int[] nums1 = {3, 1, 5, 8};
        int[] nums2 = {1, 5};
        System.out.println(STR."Max Coins (nums1): \{maxCoins(nums1)}");
        System.out.println(STR."Max Coins (nums2): \{maxCoins(nums2)}");
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(STR."Minimum Coins (Bottom-Up): \{coinChange(coins, amount)}");
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        solve(board);
        for (char[] row : board) {
            System.out.println(java.util.Arrays.toString(row));
        }
    }
}

