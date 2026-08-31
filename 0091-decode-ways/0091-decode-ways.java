class Solution {
    public int numDecodings(String s) {

        int n=s.length();
        if(n==0||s.charAt(0)=='0')return 0;
        if(n==1)return 1;

        for(int i=1; i<n; i++)if(s.charAt(i)=='0')if(s.charAt(i-1)=='0'||s.charAt(i-1)>'2')return 0;
        
        int dp[][]=new int[n+1][2];
        if(s.charAt(n-1)!='0')dp[n-1][0]=1;
      
        

        for(int i=n-2; i>=0; i--){
            if(s.charAt(i+1)=='0'){
                if(i+2==n)dp[i][1]=1;
                else dp[i][1]=dp[i+2][0]+dp[i+2][1];
            }
            else if(s.charAt(i)>'2'||(s.charAt(i)=='2'&&s.charAt(i+1)>'6')||s.charAt(i)=='0')dp[i][0]=dp[i+1][0]+dp[i+1][1];
            else{
                dp[i][0]=dp[i+1][0]+dp[i+1][1];
                if(i+2==n)dp[i][1]=1;
                else if(s.charAt(i+2)!='0') dp[i][1]=dp[i+2][0]+dp[i+2][1];
            }
        }
        return dp[0][0]+dp[0][1];
        
    }
}