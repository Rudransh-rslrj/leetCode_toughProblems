class Solution {
    record pair(int a, int b) {}

    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        long ret = 0;
        pair diff[] = new pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            diff[i] = new pair((nums[i]^k)-nums[i],i);
        }
        Arrays.sort(diff, (a1, a2) -> a2.a - a1.a);
        int i = 0;
        while (i < nums.length - 1 && diff[i + 1].a >= 0) {
            ret += (nums[diff[i].b] ^ k) + (nums[diff[i + 1].b] ^ k);
            i += 2;
        }
        if (i < nums.length-1&&(nums[diff[i].b]^k)+(nums[diff[i + 1].b]^k)> nums[diff[i].b] + nums[diff[i + 1].b]) {
            ret += (nums[diff[i].b] ^ k) + (nums[diff[i + 1].b] ^ k);
            i += 2;
        }
        while (i < nums.length) {
            ret += nums[diff[i].b];
            i++;
        }

        return ret;
    }
}