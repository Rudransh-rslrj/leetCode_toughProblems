class WordDictionary {
    class Trie {
        Trie[] next;
        boolean completed;
        public Trie() {
            this.next = new Trie[26];
            this.completed=false;
        }
        
        public void insert(String word) {
            int i=0;
            Trie temp=this;
            while(i<word.length()){
                int j=word.charAt(i)-'a';
                if(temp.next[j]==null)temp.next[j]=new Trie();
                temp=temp.next[j];
                i++;
            }
            temp.completed=true; 
        }
        
        public boolean search(String word,int index, Trie t) {
            if (index == word.length()) return t.completed;
            if(word.charAt(index)=='.'){
                boolean s=false;
                for(int i =0 ; i<26; i++){
                    if(t.next[i]!=null)s=s||search(word,index+1,t.next[i]);
                }
                return s;
            }
            else{
                
                int j=word.charAt(index)-'a';
                if(t.next[j]==null)return false;
                return search(word,index+1,t.next[j]);

            }  
        }
    }
    Trie tp;


    public WordDictionary() {
        this.tp=new Trie();
        
    }
    
    public void addWord(String word) {
        tp.insert(word);

        
    }
    
    public boolean search(String word) {
        return tp.search(word,0,this.tp);
        
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */