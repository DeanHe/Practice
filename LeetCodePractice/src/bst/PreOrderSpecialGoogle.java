package bst;

import java.util.ArrayList;
import java.util.List;

/*
Given a node structure, preorder traversal for the tree, can use recursion and stack, must use while loop
 */
public class PreOrderSpecialGoogle {

    public List<Gnode> preOrderTraversal(Gnode root){
        List<Gnode> res = new ArrayList<>();
        Gnode cur = root;
        while(cur != null){
            cur = getLeftMost(res, cur);
            if(cur.right != null){
                cur = cur.right;
                cur = getLeftMost(res, cur);
            } else {
                // backtrack to the parent has right child
                while(cur.parent != null){
                    cur = cur.parent;
                    if(cur.right != null){
                        cur = cur.right;
                        break;
                    }
                }
            }
        }
        return res;
    }

    private Gnode getLeftMost(List<Gnode> res, Gnode root){
        while(root.left != null){
            res.add(root);
            root = root.left;
        }
        return root;
    }

    public class Gnode {
        int val;
        public Gnode left, right, parent;
        public Gnode(int val){
            this.val = val;
        }
    }
}
