class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n=nums.length;
        if(n==1)return new int[]{nums[0]};
        Deque<Integer> dq = new ArrayDeque<>();
        dq.addFirst(0);
        
        int ret[]=new int[n-k+1];
        int j=0;
        if(k==1)ret[j++]=nums[0];

        for(int i=1; i<n; i++){
            if(dq.peekLast()<i-k+1)dq.removeLast();
            while(dq.size()>0&&nums[dq.peekFirst()]<=nums[i])dq.removeFirst();
            dq.addFirst(i);
            if(i>k-2)ret[j++]=nums[dq.peekLast()];
        }
        return ret;
    }
}