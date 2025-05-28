package VIP50;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SlidingWindow {
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

    public static String minWindow(String s, String t) {
        if (t.length() > s.length()) return "";
        HashMap<Character, Integer> tMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }
        int required = tMap.size(), formed = 0;
        int left = 0, right = 0, minLength = Integer.MAX_VALUE, minLeft = 0;
        HashMap<Character, Integer> windowCounts = new HashMap<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);
            if (tMap.containsKey(c) && windowCounts.get(c).intValue() == tMap.get(c).intValue()) {
                formed++;
            }
            while (left <= right && formed == required) {
                c = s.charAt(left);
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minLeft = left;
                }
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (tMap.containsKey(c) && windowCounts.get(c) < tMap.get(c)) {
                    formed--;
                }
                left++;
            }
            right++;
        }
        return minLength == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLength);
    }

    public static double findMaxAverage(int[] nums, int k) {
        return (double) slidingArraySum(nums, k) / k;
    }

    public static void main(String[] args) {
        System.out.println(STR."Minimum window substring: \{minWindow("ADOBECODEBANC", "ABC")}");
        System.out.println(STR."Minimum window substring: \{minWindow("a", "a")}");
        System.out.println(STR."Minimum window substring: \{minWindow("a", "aa")}");
        int[] nums = {1, 12, -5, -6, 50, 3};
        int k = 4;
        System.out.println(STR."Maximum Average Subarea: \{findMaxAverage(nums, k)}");
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }
}

