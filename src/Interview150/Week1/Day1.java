package Interview150.Week1;

import java.util.*;

public class Day1 {

    // GENERIC Questions
    public static int BestTimeBuy() {
        int[] numArr = {7, 1, 5, 3, 6, 4};
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : numArr) {
            minPrice = Math.min(minPrice, price);
            int profit = price - minPrice;
            maxProfit = Math.max(profit, maxProfit);
        }
        return maxProfit;

    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int uniqueIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[uniqueIndex]) {
                uniqueIndex++;
                nums[uniqueIndex] = nums[i];
            }
        }
        return uniqueIndex + 1;
    }

    // Two Pointers

    public static int[] sortedTwoSum() {
        int[] arr = new int[]{3, 2, 4};
        int left = 0, right = arr.length - 1;
        int target = 6;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{};
    }

    public static int[] unsortedTwoSum() {
        int[] arr = {3, 2, 4};
        int target = 6;
        int[][] numsWithIndex = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            numsWithIndex[i][0] = arr[i];
            numsWithIndex[i][1] = i;
        }
        Arrays.sort(numsWithIndex, Comparator.comparingInt(a -> a[0]));
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int sum = numsWithIndex[left][0] + numsWithIndex[right][0];
            if (sum == target) {
                return new int[]{numsWithIndex[left][1], numsWithIndex[right][1]};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }

        return new int[]{};
    }

    public static int[] mapTwoSum() {
        int[] arr = {3, 2, 4};
        int target = 6;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int dif = target - arr[i];
            if (map.containsKey(dif)) {
                return new int[]{map.get(dif), i};
            }
            map.put(arr[i],i);
        }

        return new int[]{};
    }


    // SLIDING Window Problems

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> seen = new HashSet<>();
        int left = 0, maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            while (seen.contains(s.charAt(i))) {
                seen.remove(s.charAt(left));
                left++;
            }
            seen.add(s.charAt(i));
            maxLen = Math.max(maxLen, i - left + 1);

        }
        return maxLen;

    }

    public static int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        int left = 0, sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            while (sum >= target) {
                minLength = Math.min(minLength, i - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static int slidingArraySum(int[] numbs, int k) {
        int firstSum = 0;
        int maxSum = 0;
        for (int i = 0; i < k; i++) {
            firstSum += numbs[i];
        }
        for (int i = k; i < numbs.length; i++) {
            firstSum += numbs[i] - numbs[i - k];
            maxSum = Math.max(firstSum, maxSum);
        }
        return maxSum;

    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortedTwoSum()));
        System.out.println(BestTimeBuy());
        System.out.println(Arrays.toString(unsortedTwoSum()));
        System.out.println(Arrays.toString(mapTwoSum()));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
        System.out.println(minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(slidingArraySum(new int[]{2, 3, 1, 2, 4, 3}, 3));
    }
}
