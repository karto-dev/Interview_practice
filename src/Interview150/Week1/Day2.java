package Interview150.Week1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.*;

public class Day2 {
    public static int[] rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        int[] part1 = Arrays.copyOfRange(nums, n - k, n);
        int[] part2 = Arrays.copyOfRange(nums, 0, n - k);
        System.arraycopy(part1, 0, nums, 0, k);
        System.arraycopy(part2, 0, nums, k, n - k);
        return nums;
    }

    public static String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }
        return prefix;
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

    public static boolean isAnagram(String s, String t) {
        Map<Character, Integer> s1 = new HashMap<>();
        Map<Character, Integer> t1 = new HashMap<>();
        for (char c : s.toCharArray()) {
            s1.put(c, s1.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            t1.put(c, t1.getOrDefault(c, 0) + 1);
        }
        return s1.equals(t1);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagramGroups = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            anagramGroups.computeIfAbsent(new String(charArray),k->new ArrayList<>()).add(str);
        }
        return new ArrayList<>(anagramGroups.values());
    }

    public static int findKthLargest(int[] nums, int k) {
        nums = Arrays.stream(nums).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
        return nums[k-1];
    }


    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(Arrays.toString(rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3)));
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println(isAnagram("nagaram", "anagram"));
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(findKthLargest(new int[]{3,2,5,6,1,2},2));

    }

}




