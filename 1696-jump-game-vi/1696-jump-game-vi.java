class Solution {
    public int maxResult(int[] nums, int k) {
        int n= nums.length;
        int dp[]= new int[n];
        for(int i=0;i<n; i++)dp[i]=Integer.MIN_VALUE;
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(0);

        dp[0]=nums[0];
        for(int i=0; i<n-1; i++){
            while(!dq.isEmpty()&&dq.peekFirst()<i-k+1)dq.pollFirst();
            while(!dq.isEmpty()&&dp[dq.peekLast()]<=dp[i])dq.pollLast();
            dq.addLast(i);
            dp[i+1]=dp[dq.peekFirst()]+nums[i+1];
        }
        return dp[n-1]; 
    }
}