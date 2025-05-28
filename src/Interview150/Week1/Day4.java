package Interview150.Week1;

import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class Day4 {
    public static boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> table = new HashMap<>();
        for (char c : magazine.toCharArray()) {
            table.put(c, table.getOrDefault(c, 0) + 1);
        }
        for (char c : ransomNote.toCharArray()) {
            if (!table.containsKey(c) || table.get(c) <= 0) {
                return false;
            }
            table.put(c, table.get(c) - 1);
        }
        return true;
    }

    public static boolean myRansomNote(String ransomNote, String magazine) {
        Map<Character, Integer> ransomMap = new HashMap<>();
        Map<Character, Integer> magaZineMap = new HashMap<>();
        for (char c : ransomNote.toCharArray()) {
            ransomMap.put(c, ransomMap.getOrDefault(c, 0) + 1);
        }
        for (char c : magazine.toCharArray()) {
            magaZineMap.put(c, magaZineMap.getOrDefault(c, 0) + 1);
        }
        for (char c : ransomNote.toCharArray()) {
            if (ransomMap.get(c) > magaZineMap.get(c) &&
                    !Objects.equals(magaZineMap.get(c), ransomMap.get(c))) {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0, right = cols - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;
            for (int i = right; i >= left; i--) {
                result.add(matrix[bottom][i]);
            }
            bottom--;
            for (int i = bottom; i >= top; i--) {
                result.add(matrix[i][left]);
            }
            left++;
        }
        return result;
    }

    public static int subArraySum(int[] numbs) {
        int currMax = 0;
        int realMax = Integer.MIN_VALUE;
        for (int num : numbs) {
            currMax = Math.max(num, currMax + num);
            realMax = Math.max(currMax, realMax);
        }
        return realMax;
    }

    public static int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> innerMap : map.entrySet()) {
            if (innerMap.getValue() == 1) {
                return innerMap.getKey();
            }
        }
        return 0;
    }

    public static boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (backtrack(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean backtrack(char[][] board, String word, int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }

        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != word.charAt(index)) {
            return false;
        }

        char temp = board[i][j];
        board[i][j] = '#'; // Mark as visited

        boolean found = backtrack(board, word, i + 1, j, index + 1) || // Down
                backtrack(board, word, i - 1, j, index + 1) || // Up
                backtrack(board, word, i, j + 1, index + 1) || // Right
                backtrack(board, word, i, j - 1, index + 1);   // Left

        board[i][j] = temp;

        return found;
    }

    public static List<Integer> spiralOrderPractice(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null) {
            return result;
        }
        int left = 0, top = 0;
        int bottom = matrix.length - 1;
        int right = matrix[0].length - 1;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;
            for (int i = right; i >= left; i--) {
                result.add(matrix[bottom][i]);
            }
            bottom--;
            for (int i = bottom; i >= top; i--) {
                result.add(matrix[i][left]);
            }
            left++;
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(canConstruct("bg", "efjbdfbdgfjhhaiigfhbaejahgfbbgbjagbddfgdiaigdadhcfcj"));
        System.out.println(myRansomNote("bg", "efjbdfbdgfjhhaiigfhbaejahgfbbgbjagbddfgdiaigdadhcfcj"));
        System.out.println(spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        System.out.println(spiralOrderPractice(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        System.out.println(subArraySum(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(singleNumber(new int[]{2, 2, 1}));
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        System.out.println(exist(board, word));

    }
}






