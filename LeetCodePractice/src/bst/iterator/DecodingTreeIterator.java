package bst.iterator;

import bst.TreeNode;

import java.util.*;

/*
implement a decoding tree iterator
the decoding tree node looks like:
          ()
         / | \
    (A)  (B " ")  (C)
    /|\               \
(G) (H "l") (F)      (D "o")
 |
(E "he")

input: "AGEHHDBD"
print "hello o"

tag: Google
 */
public class DecodingTreeIterator implements Iterator<String> {

    int i;
    String input;
    Stack<DecodingTreeNode> stack;
    Map<String, String> memo = new HashMap<>();

    public DecodingTreeIterator(String input, DecodingTreeNode root) {
        i = 0;
        this.input = input;
        stack = new Stack<>();
        stack.push(root);
    }

    @Override
    public boolean hasNext() {
        if(i < input.length() && memo.containsKey(input.substring(i, i + 1))){
            return true;
        }
        while(!stack.isEmpty()){
            DecodingTreeNode top = stack.pop();
            memo.put(top.id, top.value);
            for(DecodingTreeNode child : top.children){
                stack.push(child);
            }
            if(i < input.length() && memo.containsKey(input.substring(i, i + 1))){
                return true;
            }
        }
        return false;
    }

    @Override
    public String next() {
        String res = memo.get(input.substring(i, i + 1));
        i++;
        return res;
    }
}
/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */

