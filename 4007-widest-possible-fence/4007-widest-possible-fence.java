class Solution {
    public int maximumWidth(int[] planks) {
        int n= planks.length;
        HashMap<Integer,Integer> heights = new HashMap<>();
        HashMap<Integer, Integer> weidths = new HashMap<>();
        int ans=0;
        for(int i=0; i<n; i++){
            heights.put(planks[i], heights.getOrDefault(planks[i], 0) + 1);
            weidths.put(planks[i], weidths.getOrDefault(planks[i], 0) + 1);
            ans= Math.max(ans,weidths.get(planks[i]));
        }

        List<Integer> keys = new ArrayList<>(heights.keySet());
        Collections.sort(keys);

        int size=keys.size();

        for (int i=0; i<size; i++) {
            for (int j=i; j<size; j++) {
                int key1=keys.get(i);
                int key2=keys.get(j);
                int k=key1+key2;
                if(!weidths.containsKey(k))weidths.put(k,0);
                if(i==j){
                    weidths.put(k,weidths.get(k)+heights.get(key2)/2);
                    ans= Math.max(ans,weidths.get(k));
                }else{ 
                
                    weidths.put(k,weidths.get(k)+Math.min(heights.get(key1),heights.get(key2)));
                    ans= Math.max(ans,weidths.get(k));
                }
                
            }
        }
        return ans;
    }
}