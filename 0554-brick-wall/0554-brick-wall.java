class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int ret=0;
        for(List<Integer> l1 : wall){
            int sum=0;
            for(int i=0; i<l1.size()-1; i++){
                sum+=l1.get(i);
                if(!map.containsKey(sum))map.put(sum,0);
                map.put(sum,map.get(sum)+1);
                ret=Math.max(ret,map.get(sum));
            }
        }
        return wall.size()-ret; 
    }
}