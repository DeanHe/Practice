package bst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given the root of a binary tree, return all duplicate subtrees.
For each kind of duplicate subtrees, you only need to return the root node of any one of them.
Two trees are duplicate if they have the same structure with the same node values.

Example 1:
Input: root = [1,2,3,4,null,2,4,null,null,4]
Output: [[2,4],[4]]

Example 2:
Input: root = [2,1,1]
Output: [[1]]

Example 3:
Input: root = [2,2,2,3,null,3,null]
Output: [[2,3],[3]]

Constraints:
The number of the nodes in the tree will be in the range [1, 10^4]
-200 <= Node.val <= 200

Analysis:
TC O(N)
 */
public class FindDuplicateSubtrees {
    int id = 1;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> serialToId = new HashMap<>();
        Map<Integer, Integer> idToCnt = new HashMap<>();
        List<TreeNode> res = new ArrayList<>();
        postOrder(root, serialToId, idToCnt, res);
        return res;
    }

    private int postOrder(TreeNode root, Map<String, Integer> serialToId, Map<Integer, Integer> idToCnt, List<TreeNode> res) {
        if (root == null) {
            return 0;
        }
        int leftId = postOrder(root.left, serialToId, idToCnt, res);
        int rightId = postOrder(root.right, serialToId, idToCnt, res);
        String serial = leftId + "," + root.val + "," + rightId;
        int serialId = serialToId.getOrDefault(serial, id);
        if (serialId == id) {
            id++;
        }
        serialToId.put(serial, serialId);
        idToCnt.put(serialId, idToCnt.getOrDefault(serialId, 0) + 1);
        if (idToCnt.get(serialId) == 2) {
            res.add(root);
        }
        return serialId;
    }

}
