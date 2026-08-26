class Solution {
    boolean helper(String s1, String s2){
        return s1.compareTo(s2)<0;
    }
    public String shortestBeautifulSubstring(String s, int k) {
        int h=0;
        int l=0;
        int n= s.length();
        int count=0;
        int l1=-1;
        int h1=(int)1e9;
        while(h<n){
            if(s.charAt(h)=='1'){
                count++;
                if(count>k){
                    l++;
                    count--;
                    while(s.charAt(l)=='0')l++;
                }
                if(count==k){
                    while(s.charAt(l)=='0')l++;
                    if(h-l==h1-l1&&helper(s.substring(l,h),s.substring(l1,h1))){
                        l1=l;
                        h1=h;
                    }
                    else if(h-l<h1-l1){
                        l1=l;
                        h1=h;
                    }
                }
            }
            h++;
        }
        if(h1==(int)1e9)return "";
        return s.substring(l1,h1+1);
        
    }
}