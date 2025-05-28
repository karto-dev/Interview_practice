package Interview150.Week3;

import DataStructures.Tree.TreeNode;

public class Day1 {
    private static int minDiff = Integer.MAX_VALUE;

    // binary Search
    public static int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }

    public static int getMinimumDifference(TreeNode root) {
        dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return minDiff;
    }

    private static void dfs(TreeNode node, int lower, int upper) {
        if (node == null)
            return;
        if (lower != Integer.MIN_VALUE)
            minDiff = Math.min(minDiff, Math.abs(node.data - lower));
        if (upper != Integer.MAX_VALUE)
            minDiff = Math.min(minDiff, Math.abs(node.data - upper));
        dfs(node.left, lower, node.data);
        dfs(node.right, node.data, upper);
    }

    public static int[] searchRange(int[] nums, int target) {
        int left = findBound(nums, target, true);  // Find first occurrence
        int right = findBound(nums, target, false); // Find last occurrence
        return new int[]{left, right};
    }

    private static int findBound(int[] nums, int target, boolean isLeft) {
        int low = 0, high = nums.length - 1;
        int bound = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                bound = mid;
                if (isLeft) {
                    high = mid - 1;  // Search left side for first occurrence
                } else {
                    low = mid + 1;  // Search right side for last occurrence
                }
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return bound;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(STR."Minimum Element: \{findMin(nums)}");
        System.out.println(STR."Max Element: \{findPeakElement(nums)}");
        int[] nums1 = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] result = searchRange(nums1, target);
        System.out.println(STR."Range: [\{result[0]}, \{result[1]}]");
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(3);

        System.out.println(STR."Minimum Absolute Difference: \{getMinimumDifference(root)}");
    }
}





