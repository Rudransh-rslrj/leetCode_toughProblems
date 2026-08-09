class Solution {
    public String largestNumber(int[] nums) {
        int n =nums.length;
        String [] temp= new String[n];
        for(int i=0; i<n; i++)temp[i]=Integer.toString(nums[i]);
        Arrays.sort(temp, (a, b) -> (b + a).compareTo(a + b));
        if(temp[0].equals("0"))return "0";
        return String.join("",temp);  
    }
}