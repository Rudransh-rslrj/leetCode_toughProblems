class Solution {

    public int helper(String s, String p, int l1, int l2,int dp[][]) {
        if(dp[l1][l2]!=0)return dp[l1][l2];
        int n1 = s.length();
        int n2 = p.length();

        if (l2 == n2)
            return (l1 == n1)?1:-1;

        if (l1 == n1) {
            // while (l2 < n2 && p.charAt(l2) == '*')
            //     l2++;
            // return l2 == n2;
            if(l2==n2-1&&p.charAt(l2)=='*')return 1;
            else return -1;
        }

        char c1 = s.charAt(l1);
        char c2 = p.charAt(l2);

        if (c2 == '*') {
            dp[l1][l2+1]=helper(s, p, l1, l2 + 1,dp);   
            dp[l1+1][l2]=helper(s, p, l1 + 1, l2,dp); 
            return (dp[l1][l2+1]==1||dp[l1+1][l2]==1)? 1:-1;    
        }

        if (c2 == '?' || c1 == c2)
            return dp[l1+1][l2+1]=helper(s, p, l1 + 1, l2 + 1,dp);

        return -1;
    }

    public boolean isMatch(String s, String p) {
        StringBuilder sb = new StringBuilder();
        for (char c : p.toCharArray()) {
            if (c == '*' && sb.length() > 0 && sb.charAt(sb.length() - 1) == '*')
                continue;
            sb.append(c);
        }
        int dp[][]=new int[s.length()+1][sb.length()+1];

        dp[0][0]= helper(s, sb.toString(), 0, 0,dp);
        return dp[0][0]==1;
    }
}