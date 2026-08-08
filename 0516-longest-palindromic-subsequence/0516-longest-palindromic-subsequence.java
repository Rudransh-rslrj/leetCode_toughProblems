class Solution {
    public int helper(String s, int l, int h, int dp[][]) {
        if(dp[l][h]!=0)return dp[l][h];
        else if (l>h) return 0;
        else if (l==h) return dp[l][h]=  1;

        else if (s.charAt(l) == s.charAt(h))return dp[l][h]= 2 + helper(s, l + 1, h - 1,dp);
        else return dp[l][h]= Math.max(helper(s, l + 1, h,dp),helper(s, l, h - 1,dp));
    }
    public int longestPalindromeSubseq(String s) {
        int n=s.length();
        int[][] dp=new int[n][n];
        helper(s, 0, n-1,dp);
        return dp[0][n-1];
    }
}