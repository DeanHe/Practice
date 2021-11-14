package bst.lca;

import bst.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Given a special binary tree which each node is marked with sequence number below:
   1
 2   3
4 5 6 7

return the path between two nodes
 */
public class BinaryTreePathBetweenTwoNodesGoogle {
    public List<Integer> getPath(TreeNode root, TreeNode a, TreeNode b){
        List<Integer> a_list = dfs(a.val);
        List<Integer> b_list = dfs(b.val);
        int i = 0;
        while(i < a_list.size() && i < b_list.size() && a_list.get(i) == b_list.get(i)){
            i++;
        }
        List<Integer> a_part = a_list.subList(i, a_list.size());
        List<Integer> b_part = b_list.subList(i - 1, b_list.size());
        List<Integer> res = new ArrayList<>();
        Collections.reverse(a_part);
        res.addAll(a_part);
        res.addAll(b_part);
        return res;
    }

    private List<Integer> dfs(int val){
        List<Integer> res = new ArrayList<>();
        while(val > 0){
            res.add(val);
            val = val >> 1;
        }
        Collections.reverse(res);
        return res;
    }
}
