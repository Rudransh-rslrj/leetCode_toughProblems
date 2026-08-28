// class Solution {
//     public String lexPalindromicPermutation(String s, String target) {
//         HashMap<Character,Integer> map= new HashMap<>();
//         int n = s.length();
//         for(int i=0; i<n; i++){
//             char c = s.charAt(i);
//             if(!map.containsKey(c))map.put(c,0);
//             map.put(c,map.get(c)+1);
//         }
//         int count=0;
//         char odd='-';
//         for(char k:map.keySet()){
//             if(map.get(k)%2==1){
//                 count++;
//                 odd=k;
//                 if(count>1)return "";
//             }
//         }
//         if(odd!='-')map.put(odd,map.get(odd)-1);
//         char[] arr = new char[n];
//         int t=0;
//         for(char k:map.keySet()){
//             char c=k;
//             for(int i=0; i<map.get(k)/2; i++){
//                 arr[t++]=c;
//             }
//         }
//         if(odd!='-')arr[t++]=odd;
        
//         for(int i = 0; i < n/2; i++){
//             arr[n-1-i] = arr[i];
//         }

//         for(int i=0; i<n; i++){

//         }
       
//         System.out.println(arr);
//         return new String(arr);


        
//     }
// }


class Solution {
    public String lexPalindromicPermutation(String s, String target) {

        HashMap<Character, Integer> map = new HashMap<>();
        int n = s.length();

        // Frequency count
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Check odd frequency
        int count = 0;
        char odd = '-';

        for (char k : map.keySet()) {
            if (map.get(k) % 2 == 1) {
                count++;
                odd = k;

                if (count > 1)
                    return "";
            }
        }

        // Number of characters in left half
        int m = n / 2;

        // Frequency of left half
        int[] half = new int[26];

        for (char k : map.keySet()) {
            half[k - 'a'] = map.get(k) / 2;
        }

        /*
         * First try to construct a palindrome whose left half
         * is exactly target's left half.
         */
        int[] temp = half.clone();
        char[] left = new char[m];

        boolean possible = true;

        for (int i = 0; i < m; i++) {
            int c = target.charAt(i) - 'a';

            if (temp[c] == 0) {
                possible = false;
                break;
            }

            left[i] = target.charAt(i);
            temp[c]--;
        }

        // If exact left half is possible, construct palindrome
        if (possible) {
            char[] arr = new char[n];

            for (int i = 0; i < m; i++)
                arr[i] = left[i];

            // Middle character
            if (odd != '-')
                arr[m] = odd;

            // Mirror
            for (int i = 0; i < m; i++)
                arr[n - 1 - i] = arr[i];

            String candidate = new String(arr);

            // It must be STRICTLY greater than target
            if (candidate.compareTo(target) > 0)
                return candidate;
        }

        /*
         * Exact target-left-half either:
         * 1. cannot be formed, OR
         * 2. forms a palindrome <= target.
         *
         * Now find the smallest left half strictly greater
         * than target's left half.
         */

        for (int pivot = m - 1; pivot >= 0; pivot--) {

            int[] cnt = half.clone();
            boolean prefixPossible = true;

            // Match target[0 ... pivot-1]
            for (int i = 0; i < pivot; i++) {
                int c = target.charAt(i) - 'a';

                if (cnt[c] == 0) {
                    prefixPossible = false;
                    break;
                }

                cnt[c]--;
            }

            if (!prefixPossible)
                continue;

            // At pivot, choose smallest character > target[pivot]
            int targetChar = target.charAt(pivot) - 'a';
            int bigger = -1;

            for (int c = targetChar + 1; c < 26; c++) {
                if (cnt[c] > 0) {
                    bigger = c;
                    break;
                }
            }

            if (bigger == -1)
                continue;

            // Construct answer
            char[] arr = new char[n];

            // Prefix same as target
            for (int i = 0; i < pivot; i++)
                arr[i] = target.charAt(i);

            // Bigger character at pivot
            arr[pivot] = (char) ('a' + bigger);
            cnt[bigger]--;

            // Fill remaining left half with smallest chars
            int pos = pivot + 1;

            for (int c = 0; c < 26; c++) {
                while (cnt[c] > 0) {
                    arr[pos++] = (char) ('a' + c);
                    cnt[c]--;
                }
            }

            // Middle
            if (odd != '-')
                arr[m] = odd;

            // Mirror left half
            for (int i = 0; i < m; i++)
                arr[n - 1 - i] = arr[i];

            return new String(arr);
        }

        return "";
    }
}