package Interview150.Week2;

import DataStructures.Tree.TreeNode;

public class Day5 {

    public static int trailingZeroes(int n) {
        int count = 0;
        while (n >= 5) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return constructBST(nums, 0, nums.length - 1);

    }

    private static TreeNode constructBST(int[] nums, int left, int right) {
        if (left > right) return null; // Base case

        int mid = left + (right - left) / 2; // Middle element for balanced BST
        TreeNode root = new TreeNode(nums[mid]);

        root.left = constructBST(nums, left, mid - 1); // Left subtree
        root.right = constructBST(nums, mid + 1, right); // Right subtree

        return root;
    }

    public static void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(STR."\{root.data} ->");
        printInOrder(root.right);
    }

    public static void main(String[] args) {
        System.out.println(trailingZeroes(5));
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode root = sortedArrayToBST(nums);
        printInOrder(root);
        System.out.print("null");

    }
}
