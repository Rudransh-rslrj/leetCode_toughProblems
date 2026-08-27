class Solution {
    public boolean canPartition(int[] nums) {
        int sum=0;
        int n=nums.length;
        for(int k: nums)sum+=k;
        if(sum%2!=0)return false;
        sum=sum/2;
        boolean prev[]=new boolean[sum+1];
        
        prev[0]=true;
        if(nums[0] <= sum)
        prev[nums[0]]=true; 

        for(int i=1; i<n; i++){
            boolean curr[]=new boolean[sum+1];
            for(int j=0; j<=sum; j++){
                boolean notPick=prev[j];
                boolean pick=false;
                if(j>=nums[i])
                pick=prev[j-nums[i]];
                curr[j]=pick||notPick;
            }
            prev=curr;
        } 
        return prev[sum];
    }
}