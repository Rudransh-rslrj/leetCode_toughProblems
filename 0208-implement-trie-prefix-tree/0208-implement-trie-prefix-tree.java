class Trie {
    Trie[] next;
    boolean completed;
    public Trie() {
        next = new Trie[26];
        completed=false;
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
    
    public boolean search(String word) {
        int i=0;
        Trie temp=this;
        while(i<word.length()){
            int j=word.charAt(i)-'a';
            if(temp.next[j]==null)return false;
            temp=temp.next[j];
            i++;
        }
        return temp.completed; 
        
    }
    
    public boolean startsWith(String prefix) {
        int i=0;
        Trie temp=this;
        while(i<prefix.length()){
            int j=prefix.charAt(i)-'a';
            if(temp.next[j]==null)return false;
            temp=temp.next[j];
            i++;
        }
        return true;  
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */