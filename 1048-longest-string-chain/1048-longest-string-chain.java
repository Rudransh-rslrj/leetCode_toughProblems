class Solution {
    
    public int helper(int index,List<List<Integer>> adj,int dp[] ){
        if(dp[index]!=-1)return dp[index];
        int n=adj.get(index).size();
        if(n==0)return dp[index]=1;
        int max=0;
        int k=0;
        for(int i=0; i<n; i++){
            k=Math.max(helper(adj.get(index).get(i),adj,dp),k);
        }
        return dp[index]=k+1;
    }

    public int longestStrChain(String[] words) {
        HashMap<String,Integer> map = new HashMap<>();
        int n = words.length;
        
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<n; i++){
            map.put(words[i],i);
            adj.add(new ArrayList<>());
        }
        
        for(int i=0; i<n; i++){
            for(int j=0; j<=words[i].length(); j++)
            for(char c='a'; c<='z'; c++){
                String s = words[i].substring(0,j)+c+words[i].substring(j,words[i].length());
                if(map.containsKey(s)){
                    int p = map.get(s);
                    adj.get(i).add(p);
                }
            }
        }
        int dp[]=new int[n];
        for(int i=0; i<n; i++)dp[i]=-1;
        int max=1;
        for(int i=0; i<n; i++)if(dp[i]==-1)max=Math.max(max,helper(i,adj,dp));
        return max;
    }
}