package bst;
/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.
The encoded string should be as compact as possible.
Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

complexity requirement: O(N)

sol:
There is no need to use "#" or "null" in bst which makes it more compact!
The reason is that we can reconstruct bst by only using preorder(/postorder/levelorder) traversal.
However, in the binary tree situation, we need to use preorder(/postorder/levelorder) + inorder to reconstruct the tree. If we want to directly construct BT, we have to use "#" or "null".
*/

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBST {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserialize(q, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private void serialize(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(node.val + ",");
        serialize(node.left, sb);
        serialize(node.right, sb);
    }

    private TreeNode deserialize(Queue<String> q, int lower, int upper) {
        if (q.isEmpty()) {
            return null;
        }
        int val = Integer.parseInt(q.peek());
        if (val < lower || val > upper) {
            return null;
        }
        q.poll();
        TreeNode node = new TreeNode(val);
        node.left = deserialize(q, lower, val);
        node.right = deserialize(q, val, upper);
        return node;
    }
}
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
