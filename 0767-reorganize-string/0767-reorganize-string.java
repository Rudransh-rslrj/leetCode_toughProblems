class Solution {
    public String reorganizeString(String s) {
        HashMap<Character,Integer> map=new HashMap<>();
        for(int i=0; i<s.length(); i++){
            char c= s.charAt(i);
            if(!map.containsKey(c))map.put(c,0);
            map.put(c,map.get(c)+1);
        } 
        PriorityQueue<Character> pq= new PriorityQueue<>((a, b)->{
            return map.get(b)-map.get(a);
        });
        for(char key: map.keySet())pq.add(key);

        StringBuilder ret = new StringBuilder();
        while(!pq.isEmpty()){
            if(pq.size()==1){
                if(map.get(pq.peek())>1)return "";
                ret.append(pq.poll());
                break;
            }
            char c1=pq.poll();
            char c2=pq.poll();
            ret.append(c1);
            ret.append(c2);
            map.put(c1,map.get(c1)-1);
            map.put(c2,map.get(c2)-1);
            if(map.get(c1)!=0)pq.add(c1);
            if(map.get(c2)!=0)pq.add(c2);
            
        }
        if(pq.isEmpty())return ret.toString();
        return "";  
    }
}