package BST;
/*Design an algorithm and write code to serialize and deserialize a binary tree. Writing the tree to a file is called 'serialization' and reading back from the file to reconstruct the exact same binary tree is 'deserialization'.

Example
Example 1:

Input:
Binary tree {3,9,20,#,#,15,7},  denote the following structure:
	  3
	 / \
	9  20
	  /  \
	 15   7
Example 2:

Input:
Binary tree {1,2,3},  denote the following structure:
   1
  / \
 2   3
Our data serialization use BFS traversal. This is just for when you got Wrong Answer and want to debug the input.

You can use other method to do serializaiton and deserialization.

Notice
There is no limit of how you deserialize or serialize a binary tree, LintCode will take your output of serialize as the input of deserialize, it won't check the result of serialize.
*/
import java.util.*;

public class SerializeAndDeserializeBinaryTree {

	/**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
	// pre-order
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
