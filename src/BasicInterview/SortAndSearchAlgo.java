package BasicInterview;

import java.util.Arrays;

public class SortAndSearchAlgo {
    public void bubbleSort() {
        int[] arr = new int[]{1, 5, 3, 4, 2};
        int n = arr.length;
        int temp;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public int binarySearchRecursive(int[] arr, int target, int left, int right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            return mid;
        }
        if (arr[mid] > target) {
            return binarySearchRecursive(arr, target, left, mid - 1);
        } else {
            return binarySearchRecursive(arr, target, mid + 1, right);
        }
    }

    public void sort(int[] arr) {
        int n = arr.length;
        // Build a max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        // Extract elements one by one
        for (int i = n - 1; i > 0; i--) {
            // Swap root with the last element
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // Heapify the reduced heap
            heapify(arr, i, 0);
        }
    }

    void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] > arr[largest])
            largest = left;
        if (right < n && arr[right] > arr[largest])
            largest = right;
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    public void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;

            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    void merge(int[] arr, int l, int m, int r) {
        int leftSize = m - l + 1;
        int rightSize = r - m;

        int[] left = new int[leftSize];
        int[] right = new int[rightSize];

        System.arraycopy(arr, l, left, 0, leftSize);

        for (int j = 0; j < rightSize; j++)
            right[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;
        while (i < leftSize && j < rightSize) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < leftSize) {
            arr[k++] = left[i++];
        }

        while (j < rightSize) {
            arr[k++] = right[j++];
        }
    }

    void merge1(int[] arr, int l, int m, int r) {
        int leftSize = m - l + 1;
        int rightSize = r - m;

        int[] left = new int[leftSize];
        int[] right = new int[rightSize];

        System.arraycopy(arr, l, left, 0, leftSize);

        for (int j = 0; j < rightSize; j++)
            right[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;
        while (i < leftSize && j < rightSize) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < leftSize) {
            arr[k++] = left[i++];
        }

        while (j < rightSize) {
            arr[k++] = right[j++];
        }
    }


    public static void reverse(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            // Swap elements
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            // Move pointers
            left++;
            right--;
        }
    }


    public static void main(String[] args) {
        SortAndSearchAlgo sortAndSearchAlgo = new SortAndSearchAlgo();
        sortAndSearchAlgo.bubbleSort();
        int[] arr = new int[]{1, 3, 5, 7, 9, 34, 56};
        int target = 9;
        System.out.println(sortAndSearchAlgo.binarySearch(arr, target));
        System.out.println(sortAndSearchAlgo.binarySearchRecursive(arr, target, 0, arr.length - 1));
        sortAndSearchAlgo.sort(arr);
        System.out.println(STR."Sorted array: \{Arrays.toString(arr)}");
        int[] newarr = {38, 27, 43, 3, 9, 82, 10};
        sortAndSearchAlgo.mergeSort(newarr, 0, newarr.length - 1);
        System.out.println("Sorted array: " + Arrays.toString(arr));
        reverse(newarr);
        System.out.println(Arrays.toString(newarr));
    }
}
