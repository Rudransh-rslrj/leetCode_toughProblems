/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {


    public StringBuilder helper(TreeNode root){
        if(root == null) return new StringBuilder();
        Queue<TreeNode> q= new ArrayDeque<>();
        q.add(root);
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(root.val)+',');

        while(!q.isEmpty()){
            int n= q.size();
            for(int i=0; i<n; i++){
                TreeNode t= q.poll();
                if(t.left!=null){
                    q.add(t.left);
                    sb.append(Integer.toString(t.left.val)+',');   
                }
                else sb.append("*,");
               
                if(t.right!=null){
                    q.add(t.right);
                    sb.append(Integer.toString(t.right.val)+',');
                }
                else sb.append("*,");
            }
        }
        return sb;

    }
    public String serialize(TreeNode root) {
        
        StringBuilder sb = helper(root);
        return sb.toString();
        
    }

    public TreeNode deserialize(String data) {
        if(data.length()==0)return null;
        String edges[]=data.split(",");
        int v3=Integer.parseInt(edges[0]);
        TreeNode root = new TreeNode(v3);
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        int i=1;
        while(!q.isEmpty()&&i<edges.length){
            TreeNode temp=q.poll();
            String a=edges[i++]; 
            if(!a.equals("*")){
                int v1=Integer.parseInt(a);
                TreeNode temp1=new TreeNode(v1);
                temp.left=temp1;
                q.add(temp1);
            }
            String b=edges[i++];
            if(!b.equals("*")){
                int v2=Integer.parseInt(b);
                TreeNode temp2=new TreeNode(v2);
                temp.right=temp2;
                q.add(temp2);
            }
            
        }
        return root;
        
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));