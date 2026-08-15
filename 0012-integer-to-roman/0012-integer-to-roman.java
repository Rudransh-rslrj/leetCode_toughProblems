class Solution {

    public String intToRoman(int num) {
        int numbers[]={1000,900,500,400,100,90,50,40,10,9,5,4,1};
        int i=0;
        String romanNumbers[]={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder sb=new StringBuilder();

        while(num>0){
            if(numbers[i]<=num){
                sb.append(romanNumbers[i]);
                num-=numbers[i];
            }else{
                i++;
            }
        }

        return sb.toString();
    
    }
}