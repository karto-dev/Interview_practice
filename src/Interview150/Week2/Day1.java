package Interview150.Week2;

import DataStructures.LinkeList.Node;

import java.util.*;


/*
   2
  3 4
 6 5 7
4 1 8 3
 */

public class Day1 {
    // DP Medium
    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        for (int row = n - 2; row >= 0; row--) {
            for (int col = 0; col < triangle.get(row).size(); col++) {
                int minPath = Math.min(triangle.get(row + 1).get(col), triangle.get(row + 1).get(col + 1));
                triangle.get(row).set(col, triangle.get(row).get(col) + minPath);
            }

        }
        return triangle.get(0).get(0);

    }

    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        int n = nums.length;
        if (n == 0) return result;
        int start = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                if (start == nums[i - 1]) {
                    result.add(String.valueOf(start));
                } else {
                    result.add(start + "->" + nums[i - 1]);
                }
                start = nums[i];
            }
        }
        if (start == nums[n - 1]) {
            result.add(String.valueOf(start));
        } else {
            result.add(start + "->" + nums[n - 1]);
        }
        return result;
    }

    public static Node rotateRight(Node head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        Node temp = head;
        int length = 1;
        while (temp.next != null) {
            temp = temp.next;
            length++;
        }
        k %= length;
        if (k == 0) return head;
        temp.next = head;
        temp = head;
        for (int i = 0; i < length - k - 1; i++) {
            temp = temp.next;
        }
        Node newHead = temp.next;
        temp.next = null;
        return newHead;
    }

    public static String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;

        // Traverse both strings from right to left (least significant bit first)
        while (i >= 0 || j >= 0 || carry > 0) {
            int bitA = (i >= 0) ? a.charAt(i--) - '0' : 0;
            int bitB = (j >= 0) ? b.charAt(j--) - '0' : 0;
            int sum = bitA + bitB + carry;

            result.append(sum % 2); // Append the binary digit
            carry = sum / 2; // Update carry
        }

        return result.reverse().toString();
    }

    public static int removeElement(int[] nums, int val) {
        int k = 0; // Pointer for valid elements
        for (int num : nums) {
            if (num != val) {
                nums[k++] = num; // Place valid elements at the front
            }
        }
        return k;
    }


    public static void main(String[] args) {
        List<List<Integer>> triangle = List.of(
                List.of(2),
                List.of(3, 4),
                List.of(6, 5, 7),
                List.of(4, 1, 8, 3)
        );

        //System.out.println(minimumTotal(triangle));
        int[] nums = {0, 1, 2, 4, 5, 7};
        System.out.println(summaryRanges(nums)); // Output: [0->2, 4->5, 7]
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        int k = 2;
        Node rotatedHead = rotateRight(head, k);
        while (rotatedHead != null) {
            System.out.print(rotatedHead.data + " ");
            rotatedHead = rotatedHead.next;
        }
        // Output: 4 5 1 2 3
        int[] nums2 = {3, 2, 2, 3, 4, 3};
        int val = 3;
        int k2 = removeElement(nums, val);

        // Printing the modified array
        System.out.print("New array: ");
        for (int i = 0; i < k2; i++) {
            System.out.print(nums2[i] + " ");
        }
        System.out.println("\nLength after removal: " + k2);
        String a = "1101";
        String b = "1011";
        System.out.println(addBinary(a, b));
    }


}






