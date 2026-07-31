class Solution {
    class Trie {
        Trie[] next;
        boolean completed;
        int completedIndex;
        int children;
        public Trie() {
            next = new Trie[26];
            completed=false;
            children = 0;
        }
        
        public void insert(String word,int index) {
            
            int i=0;
            Trie temp=this;
            while(i<word.length()){
                int j=word.charAt(i)-'a';
                if(temp.next[j]==null){
                    temp.next[j]=new Trie();
                    temp.children++;
                }
                
                temp=temp.next[j];
                i++;
            }
            temp.completed=true;
            temp.completedIndex=index;
        }
        
        public void search(Trie t,List<String> ret,char board[][],String[] words, int r, int c,boolean b[][]) {

            if(t.completed){
                ret.add(words[t.completedIndex]);
                t.completed=false;
            }
            int arr[][]={{-1,0,1,0},{0,1,0,-1}};
            for(int i=0; i<4; i++){
                int row=r+arr[0][i];
                int col=c+arr[1][i];
                if(row<0||col<0||row>board.length-1||col>board[0].length-1)continue;
                if(b[row][col])continue;
                Trie p=t.next[(int)(board[row][col]-'a')];
                if(p!=null&&t.children>0){
                    b[row][col]=true;
                    search(p,ret,board,words,row,col,b);
                    b[row][col]=false;

                    if (p.children == 0 && !p.completed) {
                        t.next[board[row][col] - 'a'] = null;
                        t.children--;
                    }
                }
            }
        }
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        Trie t= new Trie();
        int m=board.length;
        int n= board[0].length;
        int k=words.length;
        for(int i=0; i<k; i++)t.insert(words[i],i);
        List<String> ret= new ArrayList<>();
        boolean b[][]=new boolean[m][n];
        for(int i=0; i<m; i++){
            for(int j=0;j<n ; j++){
                //t.search(t,ret,board,words,i,j,b);
                Trie p = t.next[board[i][j] - 'a'];

                if (p != null) {
                    b[i][j] = true;
                    t.search(p, ret, board, words, i, j, b);
                    b[i][j] = false;
                }
                

            }
        }
        return ret;

        
    }
}