package Interview150.Week2;

import java.util.Arrays;
import java.util.Comparator;

public class Day3 {
    public int searchInsert(int[] nums, int target) {
        int right = nums.length - 1;
        int left = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int binarySearch(int[] nums, int target) {
        int right = nums.length - 1;
        int[][] numsWithIndex = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            numsWithIndex[i][0] = nums[i];
            numsWithIndex[i][1] = i;
        }
        Arrays.sort(numsWithIndex, Comparator.comparingInt(a -> a[0]));
        int left = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                right--;
            } else {
                left++;
            }
        }
        return 0;
    }

    public static int maxSubArraySumCircular(int[] nums) {
        int maxSum = kadaneMax(nums);
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        int minSum = kadaneMin(nums);
        if (maxSum < 0) {
            return maxSum;
        }
        return Math.max(maxSum, totalSum - minSum);
    }

    private static int kadaneMax(int[] nums) {
        int maxEndingHere = nums[0], maxSoFar = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    private static int kadaneMin(int[] nums) {
        int minEndingHere = nums[0], minSoFar = nums[0];
        for (int i = 1; i < nums.length; i++) {
            minEndingHere = Math.min(nums[i], minEndingHere + nums[i]);
            minSoFar = Math.min(minSoFar, minEndingHere);
        }
        return minSoFar;
    }

    public int findPeakElement(int[] nums) {
        int maxElement = Integer.MIN_VALUE;
        for (int num : nums) {
            maxElement = Math.max(maxElement, num);
        }
        return binarySearch(nums, maxElement);
    }

    public int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE, sell1 = 0;
        int buy2 = Integer.MIN_VALUE, sell2 = 0;

        for (int price : prices) {
            buy1 = Math.max(buy1, -price); // Buy first stock at lowest price
            sell1 = Math.max(sell1, buy1 + price); // Sell first stock for max profit
            buy2 = Math.max(buy2, sell1 - price); // Buy second stock using profit from first transaction
            sell2 = Math.max(sell2, buy2 + price); // Sell second stock for max profit
        }

        return sell2;
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // Initialize with an unreachable high value
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }


    public static void main(String[] args) {
        Day3 day3 = new Day3();
        int[] nums = {5, -3, 5};
        System.out.println(STR."Maximum Circular Subarray Sum: \{maxSubArraySumCircular(nums)}"); // Output: 10
        System.out.println(day3.searchInsert(new int[]{1, 2, 3, 4, 5}, 3));
        System.out.println(day3.findPeakElement(new int[]{1, 2, 3, 1}));
        System.out.println(day3.maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        System.out.println(day3.coinChange(new int[]{1, 2, 5}, 11));
    }
}




