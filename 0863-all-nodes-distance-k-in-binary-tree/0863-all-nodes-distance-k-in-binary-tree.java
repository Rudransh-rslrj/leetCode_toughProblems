/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public void helper(TreeNode root, TreeNode parent[]){
        if(root==null)return;
        helper(root.left,parent);
        if(root.left!=null)parent[root.left.val]=root;
        if(root.right!=null)parent[root.right.val]=root;
        helper(root.right,parent);
    }
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        
        Queue<TreeNode> q= new ArrayDeque<>();
        q.add(target);
        int count=0;
        List<Integer> ret = new ArrayList<>();
        if(k==0){
            ret.add(target.val);
            return ret;
        }
        TreeNode parent[]=new TreeNode[501];
        boolean visited[]=new boolean[501];
        visited[target.val]=true;
        helper(root,parent);
        while(!q.isEmpty()){
            count++;
            int n= q.size();
            for(int i=0; i<n; i++){
                TreeNode t= q.poll();

                if(t.left!=null&&!visited[t.left.val]){
                    q.add(t.left);
                    visited[t.left.val]=true;
                     if(count==k)ret.add(t.left.val);
                   
                }
                if(t.right!=null&&!visited[t.right.val]){
                    q.add(t.right);
                    visited[t.right.val]=true;
                    if(count==k)ret.add(t.right.val);
                
                }
                if(parent[t.val]!=null&&!visited[parent[t.val].val]){
                    q.add(parent[t.val]);
                    visited[parent[t.val].val]=true;
                    if(count==k)ret.add(parent[t.val].val);
                
                }
               
            }
            if(count==k)return ret;
        }
        return ret;

        
    }
}