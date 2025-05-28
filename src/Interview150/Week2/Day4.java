package Interview150.Week2;

import DataStructures.Tree.TreeNode;

import java.util.*;

public class Day4 {
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.data != q.data) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                current.add(candidates[i]);
                backtrack(candidates, target - candidates[i], i, current, result);
                current.removeLast();
            }
        }
    }

    public static List<List<Integer>> Combinations(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack2(1, n, k, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack2(int start, int n, int k, List<Integer> current, List<List<Integer>> result) {
        if (k == current.size()) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i <= n; i++) {
            if (!current.contains(i)) {
                current.add(i);
                backtrack2(i, n, k, current, result);
                current.removeLast();
            }
        }
    }

    public static int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }


    public static void main(String[] args) {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);
        TreeNode q = new TreeNode(1);
        q.left = new TreeNode(2);
        q.right = new TreeNode(3);
        System.out.println(isSameTree(p, q));
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println(combinationSum(candidates, target));
        System.out.println(Combinations(4, 2));
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(minPathSum(grid)); // Output: 7
    }
}



