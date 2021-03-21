package bst;

import java.util.*;

/*Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each NNode has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.
For example, you may serialize the following 3-ary tree



as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note:
N is in the range of [1, 1000]
Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

analysis:
based on pre order traversal
*/
public class SerializeAndDeserializeNaryTree {
	// Encodes a tree to a single string.
    public String serialize(NNode root) {
        if (root == null) {
        	  return null;
        }
        List<String> list = new LinkedList<>();
        serializeHelper(root, list);
        return String.join(",", list);
    }
    
    private void serializeHelper(NNode root, List<String> list){
    	if(root == null){
    		return;
    	}
    	list.add(String.valueOf(root.val));
    	list.add(String.valueOf(root.children.size()));
    	for(NNode child : root.children){
    		serializeHelper(child, list);
    	}
    }
 
    // Decodes your encoded data to tree.
    public NNode deserialize(String data) {
    	if(data == null || data.length() == 0){
    		return null;
    	}
        String[] arr = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(arr));
        return deserializeHelper(queue);
    }
    
    private NNode deserializeHelper(Queue<String> queue){
    	NNode root = new NNode();
    	root.val = Integer.valueOf(queue.poll());
    	root.children = new ArrayList<>();
    	int size = Integer.valueOf(queue.poll());
    	for(int i = 0; i < size; i++){
    		root.children.add(deserializeHelper(queue));
    	}
    	return root;
    }
}
