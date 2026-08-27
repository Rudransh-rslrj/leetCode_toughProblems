class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n =matrix.length;
        int dp[][]= new int[n+1][n];
        for(int i=1; i<=n; i++){
            for(int j=0; j<n ; j++){
                int min=Integer.MAX_VALUE;
                for(int k=-1; k<=1;k++){
                    int col=j+k;
                    if(col==n||col<0)continue;
                    min=Math.min(min,dp[i-1][col]);
                }
                min+=matrix[i-1][j];
                dp[i][j]=min;
            }
        }
        int ret=Integer.MAX_VALUE;
        for(int i=0; i<n; i++)ret=Math.min(ret,dp[n][i]);
        return ret;   
    }
}