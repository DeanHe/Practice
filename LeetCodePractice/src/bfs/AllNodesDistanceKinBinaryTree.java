package bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
/*We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.

 

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.
*/

public class AllNodesDistanceKinBinaryTree {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        int dist = 0;
        List<Integer> res = new ArrayList<>();
        if (k == 0) {
            res.add(target.val);
            return res;
        }
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        dfs(root, null, graph);
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.offer(target);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode cur = queue.poll();
                visited.add(cur);
                List<TreeNode> neighbors = graph.get(cur);
                for (TreeNode nb : neighbors) {
                    if (!visited.contains(nb)) {
                        queue.offer(nb);
                    }
                }
            }
            dist++;
            if (dist == k) {
                for (TreeNode n : queue) {
                    res.add(n.val);
                }
                return res;
            }
        }
        return res;
    }

    void dfs(TreeNode cur, TreeNode parent, Map<TreeNode, List<TreeNode>> graph) {
        if (cur == null) {
            return;
        }
        if (!graph.containsKey(cur)) {
            graph.put(cur, new ArrayList<>());
        }
        if (parent != null) {
            graph.get(cur).add(parent);
        }
        if (cur.left != null) {
            graph.get(cur).add(cur.left);
        }
        if (cur.right != null) {
            graph.get(cur).add(cur.right);
        }
        dfs(cur.left, cur, graph);
        dfs(cur.right, cur, graph);
    }
}
