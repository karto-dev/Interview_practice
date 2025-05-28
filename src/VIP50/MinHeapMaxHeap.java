package VIP50;

import java.util.PriorityQueue;
import java.util.*;


public class MinHeapMaxHeap {
    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        maxHeap.addAll(freqMap.entrySet());
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(maxHeap.poll().getKey());
        }

        return result;
    }

    public static int findLargest(int[] nums, int k) {
        PriorityQueue<Integer> ps = new PriorityQueue<>();
        for (int num : nums) {
            ps.offer(num);
            if (ps.size() > k) {
                ps.poll();
            }
        }
        return ps.peek() == null ? 0 : ps.peek();
    }

    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0] + a[1]));
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            minHeap.offer(new int[]{nums1[i], nums2[0], 0});
        }
        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] pair = minHeap.poll();
            result.add(List.of(pair[0], pair[1]));

            int nextIndex = pair[2] + 1;
            if (nextIndex < nums2.length) {
                minHeap.offer(new int[]{pair[0], nums2[nextIndex], nextIndex});
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(findLargest(new int[]{8, 4, 1, 2, 3, 5, 6, 7}, 2));
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(topKFrequent(nums, k));
        System.out.println(" ");
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        int k1 = 3;
        List<List<Integer>> pairs = kSmallestPairs(nums1, nums2, k1);

        for (List<Integer> pair : pairs) {
            System.out.println(pair);
        }
    }
}
