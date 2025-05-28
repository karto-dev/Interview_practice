package VIP50;

class PrefixSum {
    private final int[] prefixSum;

    public PrefixSum(int[] nums) {
        prefixSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return prefixSum[right + 1] - prefixSum[left];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        PrefixSum obj = new PrefixSum(nums);
        System.out.println("Range Sum (1,3): " + obj.sumRange(1, 3));
        System.out.println("Range Sum (2,4): " + obj.sumRange(2, 4));
    }
}
