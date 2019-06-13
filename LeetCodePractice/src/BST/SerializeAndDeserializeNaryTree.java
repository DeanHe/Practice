package BST;

import java.util.*;

/*Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.
For example, you may serialize the following 3-ary tree



as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note:
N is in the range of [1, 1000]
Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.*/
public class SerializeAndDeserializeNaryTree {
	// Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) return "";
         
        Queue<Node> que = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(root.val)).append(",#,");
        que.add(root);
         
        while (!que.isEmpty()) {
            Node node = que.poll();
            for (Node n : node.children) {
                sb.append(Integer.toString(n.val)).append(",");
                que.add(n);
            }
            sb.append("#,");
        }
         
        return sb.toString();
    }
 
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.length() == 0) return null;
        String[] s = data.split(",");
 
        Queue<Node> que = new LinkedList<>();
        Node root = new Node(Integer.parseInt(s[0]), new ArrayList<Node>());
        que.add(root);
        int i = 1;
         
        while (!que.isEmpty()) {
            Node node = que.poll();
            i++;
            while (!s[i].equals("#")) {
                Node c = new Node(Integer.parseInt(s[i]), new ArrayList<>());
                node.children.add(c);
                que.add(c);
                i++;
            }
        }
         
        return root;
    }
    class Node {
    	public int val;
        public List<Node> children;
     
        public Node() {}
     
        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
