class Solution {
    public String removeKdigits(String num, int k) {
        if(k>num.length())return "0";
        int n= num.length();
        StringBuilder sb = new StringBuilder();
        sb.append(num.charAt(0));
        for(int i=1; i<n; i++){
            while(sb.length()>0&&sb.charAt(sb.length()-1)>num.charAt(i)&&k>0){
                k--;
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(num.charAt(i));
        }
        String t=sb.toString();
        if(k>0){
            t=t.substring(0,t.length()-k);
        }
        if(t.length()==0)return "0";
        int j=0;
        while(j<t.length()&&t.charAt(j)=='0')j++;
        t=t.substring(j,t.length());
        if(t.length()==0)return "0";
        else return t;
    }
}