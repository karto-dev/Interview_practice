package VIP50;

import java.util.*;

public class BackTrack {

    public List<List<Integer>> permutations(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackPerm(nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrackPerm(List<Integer> nums, List<Integer> objects, List<List<Integer>> result) {
        if (nums.size() == objects.size()) {
            result.add(new ArrayList<>(objects));
            return;
        }
        for (int num : nums) {
            if (!objects.contains(num)) {
                objects.add(num);
                backtrackPerm(nums, objects, result);
                objects.removeLast();
            }
        }
    }

    public List<List<Integer>> Subsets(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackSubset(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrackSubset(int start, List<Integer> nums, List<Integer> objects, List<List<Integer>> result) {
        result.add(new ArrayList<>(objects));
        for (int i = start; i < nums.size(); i++) {
            objects.add(nums.get(i));
            backtrackSubset(i + 1, nums, objects, result);
            objects.removeLast();
        }

    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        Set<Integer> cols = new HashSet<>();
        Set<Integer> diag1 = new HashSet<>();
        Set<Integer> diag2 = new HashSet<>();

        backtrack(result, board, 0, n, cols, diag1, diag2);
        return result;
    }

    private void backtrack(List<List<String>> result, char[][] board, int row, int n,
                           Set<Integer> cols, Set<Integer> diag1, Set<Integer> diag2) {
        if (row == n) {
            result.add(constructBoard(board));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (cols.contains(col) || diag1.contains(row - col) || diag2.contains(row + col)) {
                continue;
            }
            board[row][col] = 'Q';
            cols.add(col);
            diag1.add(row - col);
            diag2.add(row + col);
            backtrack(result, board, row + 1, n, cols, diag1, diag2);
            board[row][col] = '.';
            cols.remove(col);
            diag1.remove(row - col);
            diag2.remove(row + col);
        }
    }

    private List<String> constructBoard(char[][] board) {
        List<String> boardConfig = new ArrayList<>();
        for (char[] row : board) {
            boardConfig.add(new String(row));
        }
        return boardConfig;
    }

    public static void main(String[] args) {
        BackTrack backTrack = new BackTrack();
        System.out.println(backTrack.permutations(List.of(1, 2, 3)));
        System.out.println(backTrack.Subsets(List.of(1, 2, 3)));
        List<List<String>> results = backTrack.solveNQueens(4);

        for (List<String> board : results) {
            System.out.println(board);
            System.out.println();
        }
    }
}





