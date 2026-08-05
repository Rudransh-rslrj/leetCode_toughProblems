class Solution {
    public String getHint(String secret, String guess) {
        int n=secret.length();
        int bulls=0;
        int cows=0;
        HashMap<Character,Integer> set1 = new HashMap<>();
        for(int i=0; i<n; i++){
            char a=secret.charAt(i);
            char b=guess.charAt(i);
            if(a==b){
                bulls+=1;
                continue;
            }
            if(!set1.containsKey(a))set1.put(a,0);
            set1.put(a,set1.get(a)+1);
        }
        for(int i=0; i<n; i++){
            char a=secret.charAt(i);
            char b=guess.charAt(i);
            if(a==b){
                continue;
            }
            if (!set1.containsKey(b)||set1.get(b)<=0) {
                continue;
            }
            set1.put(b,set1.get(b)-1);
            cows++;
        }
        return  String.valueOf(bulls)+"A"+ String.valueOf(cows)+"B";
    }
}