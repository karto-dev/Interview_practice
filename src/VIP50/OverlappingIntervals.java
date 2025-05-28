package VIP50;

import java.util.*;

public class OverlappingIntervals {

    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return new int[0][];
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> merged = new ArrayList<>();
        int[] current = intervals[0];
        for (int[] interval : intervals) {
            if (current[1] >= interval[0]) {
                current[1] = Math.max(current[1], interval[1]);
            } else {
                merged.add(current);
                current = interval;
            }
        }
        merged.add(current);
        return merged.toArray(new int[merged.size()][]);
    }

    public static int maxNonOverlappingIntervals(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int end = Integer.MIN_VALUE;
        int count = 0;
        for (int[] interval : intervals) {
            if (interval[0] >= end) {
                count++;
                end = interval[1];
            }
        }

        return count;
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0, n = intervals.length;

        // Add all intervals before the newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i++]);
        }

        // Merge overlapping intervals with newInterval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        result.add(newInterval);

        // Add all remaining intervals
        while (i < n) {
            result.add(intervals[i++]);
        }

        return result.toArray(new int[result.size()][]);
    }


    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] mergedIntervals = merge(intervals);
        System.out.println("Merged Intervals:");
        for (int[] interval : mergedIntervals) {
            System.out.println(Arrays.toString(interval));
        }
        int[][] intervals2 = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        // 1,2 2,3 1,3 3,4
        System.out.println(maxNonOverlappingIntervals(intervals2));
        int[][] intervals3 = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};
        int[][] mergedIntervals2 = insert(intervals3, newInterval);
        System.out.println("Updated Intervals:");
        for (int[] interval : mergedIntervals2) {
            System.out.println(Arrays.toString(interval));
        }
    }
}






