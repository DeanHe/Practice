package BST;

import java.util.*;

/*
        Given two binary search trees root1 and root2.
        Return a list containing all the integers from both trees sorted in ascending order.

        Example 1:

        Input: root1 = [2,1,4], root2 = [1,0,3]
        Output: [0,1,1,2,3,4]
        Example 2:

        Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
        Output: [-10,0,0,1,2,5,7,10]
        Example 3:

        Input: root1 = [], root2 = [5,1,7,0,2]
        Output: [0,1,2,5,7]
        Example 4:

        Input: root1 = [0,-10,10], root2 = []
        Output: [-10,0,10]
        Example 5:


        Input: root1 = [1,null,8], root2 = [8,1]
        Output: [1,1,8,8]


        Constraints:

        Each tree has at most 5000 nodes.
        Each node's value is between [-10^5, 10^5].
*/
public class AllElementsInTwoBinarySearchTrees {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> res = new ArrayList<>();
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        inOrder(root1, q1);
        inOrder(root2, q2);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (q1.peek() < q2.peek()) {
                res.add(q1.poll());
            } else {
                res.add(q2.poll());
            }
        }
        while (!q1.isEmpty()) {
            res.add(q1.poll());
        }
        while (!q2.isEmpty()) {
            res.add(q2.poll());
        }
        return res;
    }

    private void inOrder(TreeNode root, Queue<Integer> queue) {
        if (root == null) {
            return;
        }
        inOrder(root.left, queue);
        queue.offer(root.val);
        inOrder(root.right, queue);
    }
}
