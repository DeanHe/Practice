import java.util.*;

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
// https://www.lintcode.com/problem/serialize-and-deserialize-binary-tree/description
public class SerializeAndDeserializeBinaryTree {
	/**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        // write your code here
        if(root == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode cur = q.poll();
           if(cur != null){
               sb.append(cur.val + ",");
               q.offer(cur.left);
               q.offer(cur.right);
           } else {
               sb.append("#,");
           }
        }
        return sb.substring(0, sb.length() - 1);
        
    }
    
    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        // write your code here
        if(data == null || data.length() == 0){
            return null;
        }
        String[] array = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(array[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while(!q.isEmpty()){
            TreeNode cur = q.poll();
            if(cur != null){
                if(!array[i].equals("#")){
                    cur.left = new TreeNode(Integer.parseInt(array[i]));
                } else {
                    cur.left = null;
                }
                q.offer(cur.left);
                i++;
                if(!array[i].equals("#")){
                    cur.right = new TreeNode(Integer.parseInt(array[i]));
                } else {
                    cur.right = null;
                }
                q.offer(cur.right);
                i++;
            } 
        }
        return root;
    }
}
