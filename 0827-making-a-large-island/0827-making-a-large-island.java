class Solution {

    class ptr {
        int id;
        int length;
        ptr(int id) {
            this.length = 0;
            this.id=id;
        }
    }


    public void dfs(int r,int c,int[][] grid,ptr[][] pg,ptr temp){
        grid[r][c]=2;
        temp.length++;
        pg[r][c]=temp;
        int change[][] = new int[][] {{1, 0},{0, 1},{-1, 0},{0, -1}};
        for(int i=0; i<4; i++){
            int row=r+change[i][0];
            int column=c+change[i][1];
            if(row<0||column<0||row>=grid.length||column>=grid[0].length)continue;
            if(grid[row][column]==1)dfs(row,column,grid,pg,temp);
        }
    }
    public int largestIsland(int[][] grid) {
        int id=0;
        ptr[][] pg=new ptr[grid.length][grid[0].length]; 
        for(int i=0; i<grid.length; i++ ){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j]==1){
                    ptr temp=new ptr(id++);
                    dfs(i,j,grid,pg,temp);
                }
            }
        }

        int ret=0;

        for(int i=0; i<grid.length; i++ ){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j]==2)ret=Math.max(ret,pg[i][j].length);
                else if(grid[i][j]==0){
                    int sum=0;
                    HashSet<Integer> set = new HashSet<>();
                    int change[][] = new int[][] {{1, 0},{0, 1},{-1, 0},{0, -1}};
                    for(int k=0; k<4; k++){
                        int r=i+change[k][0];
                        int c=j+change[k][1];
                        if(r<0||c<0||r>=grid.length||c>=grid[0].length)continue;
                        if(grid[r][c]==2){
                            
                            if(!set.contains(pg[r][c].id)){
                                set.add(pg[r][c].id);
                                sum+=pg[r][c].length;
                            }
                        }
                    }
                    ret=Math.max(ret,sum+1);
                }
                
            }
        }

        return ret;
        
    }
}