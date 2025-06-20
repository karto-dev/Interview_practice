package VVIP;

import java.util.*;

public class ArrayPointers {
    public static int[] twoSum(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int dif = target - arr[i];
            if (map.containsKey(dif)) {
                return new int[]{map.get(dif), i};
            } else {
                map.put(arr[i], i);
            }
        }
        return new int[]{};
    }

    public static int BestTimeBuyAndSell(int[] arr) {
        int minprice = Integer.MAX_VALUE, maxprofit = 0;
        for (int price : arr) {
            minprice = Math.min(minprice, price);
            int profit = price - minprice;
            maxprofit = Math.max(maxprofit, profit);
        }
        return maxprofit;
    }

    public static int MaximumSubArray(int[] arr, int k) {
        int initialSum = 0, totalSum = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            initialSum += arr[i];
        }
        for (int i = k; i < arr.length; i++) {
            initialSum += arr[i] - arr[i - k];
            totalSum = Math.max(initialSum, totalSum);
        }
        return totalSum;
    }

    public static int[] moveZeroes(int[] nums) {
        int nonZero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[nonZero];
                nums[nonZero] = temp;
                nonZero++;
            }
        }
        return nums;
    }

    public static int[][] mergeInterval(int[][] intervals) {
        List<int[]> mergedArray = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int[] current = intervals[0];
        for (int[] interval : intervals) {
            if (current[1] > interval[0]) {
                current[1] = Math.max(current[1], interval[1]);
            } else {
                mergedArray.add(current);
                current = interval;
            }
        }
        mergedArray.add(current);
        return mergedArray.toArray(new int[mergedArray.size()][]);
    }

    public static int mostWater(int[] arr) {
        int left = 0, right = arr.length - 1;
        int maxWater = 0;
        while (left < right) {
            int height = Math.min(arr[left], arr[right]);
            int width = right - left;
            maxWater = Math.max(maxWater, height * width);
            if (arr[left] < arr[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxWater;

    }

    public static int trappingWater(int[] arr) {
        int left = 0, right = arr.length - 1;
        int leftMax = arr[left];
        int rightMax = arr[right];
        int maxWater = 0;
        while (left < right) {
            if (arr[left] < arr[right]) {
                leftMax = Math.max(leftMax, arr[left]);
                maxWater += leftMax - arr[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, arr[right]);
                maxWater += rightMax - arr[right];
                right--;
            }
        }
        return maxWater;

    }

    public static int removeDuplicates(int[] arr) {
        int idx = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                arr[idx++] = arr[i];
            }
        }
        return idx;
    }

    public static int missingNumber(int[] nums) {
        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = Arrays.stream(nums).sum();
        return expectedSum - actualSum;
    }

    public static int duplicateNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int value = 0;
        for (int val : nums) {
            if (map.containsKey(val)) {
                value = val;
            } else {
                map.put(val, 1);
            }
        }
        return value;
    }

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }
        int suffixProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }

        return answer;

    }

    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n - 1;
        int j = n - 1;
        int i = m - 1;
        while (j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[k] = nums1[i];
                k--;
                i--;
            } else {
                nums1[k] = nums2[j];
                k--;
                j--;
            }
        }
        return nums1;

    }

    public static int[] mergeArrays(int[] ar1, int[] ar2, int[] ar3) {
        int i = 0, j = 0, k = 0;
        int n1 = ar1.length;
        int n2 = ar2.length;
        while (i < n1 && j < n2) {
            if (ar1[i] < ar2[j]) {
                ar3[k++] = ar1[i++];
            } else {
                ar3[k++] = ar2[j++];
            }
        }
        while (i < n1) {
            ar3[k++] = ar1[i++];
        }
        while (j < n2) {
            ar3[k++] = ar2[j++];
        }
        return ar3;
    }

    public static int lcs(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        int m = s1.length();
        int n = s2.length();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public static int lcsRecursive(String s1, String s2, int i, int j) {
        if (i == 0 || j == 0)
            return 0;
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            return 1 + lcsRecursive(s1, s2, i - 1, j - 1);
        }
        return Math.max(lcsRecursive(s1, s2, i - 1, j), lcsRecursive(s1, s2, i, j - 1));
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{3, 2, 4}, 6)));
        System.out.println(BestTimeBuyAndSell(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(MaximumSubArray(new int[]{7, 1, 5, 3, 6, 4}, 3));
        System.out.println(Arrays.toString(moveZeroes(new int[]{3, 0, 1, 12, 0})));
        System.out.println(Arrays.deepToString(mergeInterval(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
        System.out.println(mostWater(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(trappingWater(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        int[] arr2 = {1, 2, 2, 3, 4, 4, 4, 5, 5};
        int newSize = removeDuplicates(new int[]{});
        for (int i = 0; i < newSize; i++) {
            System.out.print(STR."\{arr2[i]}->");
        }
        System.out.println(missingNumber(new int[]{0, 1, 2, 4, 5}));
        System.out.println(duplicateNumber(new int[]{0, 1, 2, 4, 4}));
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3)));
        System.out.println(Arrays.toString(mergeArrays(new int[]{1, 3, 5, 7}, new int[]{2, 4, 6, 8, 10}, new int[9])));
        System.out.println(lcs("abcde", "cde"));
        System.out.println(lcsRecursive("abcde", "cde", 5, 3));

    }
}
