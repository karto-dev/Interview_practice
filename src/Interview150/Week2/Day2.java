package Interview150.Week2;

import DataStructures.Tree.TreeNode;

import java.util.*;


public class Day2 {

    public static int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] moves = new int[n * n];
        boolean leftToRight = true;
        int index = 0;

        // Convert 2D board to 1D array representation
        for (int i = n - 1; i >= 0; i--) {
            if (leftToRight) {
                for (int j = 0; j < n; j++) {
                    moves[index++] = board[i][j];
                }
            } else {
                for (int j = n - 1; j >= 0; j--) {
                    moves[index++] = board[i][j];
                }
            }
            leftToRight = !leftToRight;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0}); // position, steps
        boolean[] visited = new boolean[n * n];

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int pos = curr[0];
            int steps = curr[1];

            if (pos == n * n - 1) {
                return steps;
            }

            for (int dice = 1; dice <= 6; dice++) {
                int nextPos = pos + dice;
                if (nextPos >= n * n) break;

                if (moves[nextPos] != -1) {
                    nextPos = moves[nextPos] - 1;
                }

                if (!visited[nextPos]) {
                    visited[nextPos] = true;
                    queue.offer(new int[]{nextPos, steps + 1});
                }
            }
        }

        return -1;
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] nums, List<Integer> tempList, List<List<Integer>> result) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        for (int num : nums) {
            if (tempList.contains(num)) continue;
            tempList.add(num);
            backtrack(nums, tempList, result);
            tempList.removeLast();
        }

    }

    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    sum += node.data;
                }

                if (node != null && node.left != null) queue.offer(node.left);
                if ((node != null ? node.right : null) != null) queue.offer(node.right);
            }

            result.add(sum / size);
        }

        return result;
    }

    public static boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }

    public static boolean isHappy(int n) {
        HashSet<Integer> seen = new HashSet<>();

        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }

        return n == 1;
    }

    private static int getNext(int num) {
        int sum = 0;
        while (num > 0) {
            int digit = num % 10;
            sum += digit * digit;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] board = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}
        };
        System.out.println(snakesAndLadders(board)); // Output: Least number of dice rolls
        int[] nums = {1, 2, 3};
        System.out.println(permute(nums));
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(averageOfLevels(root));
        System.out.println(isSubsequence("ace", "abcde"));
        System.out.println(isHappy(19)); // Output: true
        System.out.println(isHappy(2));
    }
}

