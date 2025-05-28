package Interview150.Week2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class Day7 {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;  // Pointer for nums1's last valid element
        int j = n - 1;  // Pointer for nums2's last element
        int k = m + n - 1;  // Pointer for placement in nums1

        while (j >= 0) {  // Merge while nums2 has elements left
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];  // Place the larger element in the correct position
            } else {
                nums1[k--] = nums2[j--];  // Place nums2 element if it's smaller or i is exhausted
            }
        }
    }

    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;
        int insertPos = 2;
        for (int i = 2; i < n; i++) {
            if (nums[i] != nums[insertPos - 2]) {
                nums[insertPos] = nums[i];
                insertPos++;
            }
        }
        return insertPos;
    }

    public static int[][] rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
        return matrix;
    }

    public static int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
        int arrows = 1;
        int prevEnd = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > prevEnd) {
                arrows++;
                prevEnd = points[i][1];
            }
        }
        return arrows;
    }

    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0, result = 0, sign = 1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0'); // Construct number from digits
            } else if (c == '+') {
                result += sign * num; // Apply previous sign
                num = 0;
                sign = 1; // Set sign for next number
            } else if (c == '-') {
                result += sign * num;
                num = 0;
                sign = -1; // Set negative sign for next number
            } else if (c == '(') {
                stack.push(result); // Store current result
                stack.push(sign);  // Store sign before parenthesis
                result = 0;
                sign = 1;
            } else if (c == ')') {
                result += sign * num;
                num = 0;
                result *= stack.pop(); // Apply sign outside parenthesis
                result += stack.pop(); // Add previous result before parenthesis
            }
        }
        return result + (sign * num); // Add last remaining number
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(Arrays.deepToString(rotate(matrix)));
        System.out.println("Rotated Matrix:");
        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        int[] nums = new int[]{1, 1, 1, 2, 2, 3, 3, 3};
        int k = removeDuplicates(nums);
        System.out.println("New length: " + k);
        System.out.println("Modified array: " + java.util.Arrays.toString(java.util.Arrays.copyOf(nums, k)));
        System.out.println("Minimum Arrows Required: " + findMinArrowShots(points));
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        int m = 3, n = 3;
        merge(nums1, m, nums2, n);
        System.out.println("Merged Array: " + java.util.Arrays.toString(nums1));
        String s = "(1+(4+5+2)-3)+(6+8)";
        System.out.println("Result: " + calculate(s));

    }
}




