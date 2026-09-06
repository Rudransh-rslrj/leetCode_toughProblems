/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int ret=0;
    public int[] helper(TreeNode root){
        int s[]=new int[3];
        if(root == null) return new int[]{0, 0, 0};
        s[0]=root.val;
        int l[]=helper(root.left);
        int r[]=helper(root.right);

        if(root.left!=null){  
            if(l[0]==-(int)1e9||l[2]>=root.val)return new int[]{-(int)1e9,0,0};
            s[0]+=l[0];
        }
        
        if(root.right!=null){ 
            if(r[0]==-(int)1e9||root.val>=r[1])return new int[]{-(int)1e9,0,0};
            s[0]+=r[0];
        }

        ret=Math.max(ret,s[0]);
        s[1] = root.left == null ? root.val : l[1];
        s[2] = root.right == null ? root.val : r[2];
        return s ;
    }
    public int maxSumBST(TreeNode root) {
        ret=0;
        helper(root);
        return ret;
        
    }
}