package BST;

import java.util.HashSet;
import java.util.Set;

/*
        You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.
        If node i has no left child then leftChild[i] will equal -1, similarly for the right child.
        Note that the nodes have no values and that we only use the node numbers in this problem.

        Example 1:
        Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
        Output: true

        Example 2:
        Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
        Output: false

        Example 3:
        Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
        Output: false

        Example 4:
        Input: n = 6, leftChild = [1,-1,-1,4,-1,-1], rightChild = [2,-1,-1,5,-1,-1]
        Output: false

        Constraints:

        1 <= n <= 10^4
        leftChild.length == rightChild.length == n
        -1 <= leftChild[i], rightChild[i] <= n - 1

analysis:
build indeg map, the root should be the only one with indeg 0
dfs from the root, should visit all nodes only once without cycle
*/
public class ValidateBinaryTreeNodes {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] indeg = new int[n];
        for (int i = 0; i < n; i++) {
            if (leftChild[i] != -1) {
                indeg[leftChild[i]]++;
                if (indeg[leftChild[i]] > 1) {
                    return false;
                }
            }
            if (rightChild[i] != -1) {
                indeg[rightChild[i]]++;
                if (indeg[rightChild[i]] > 1) {
                    return false;
                }
            }
        }
        int root = -1;
        for (int i = 0; i < n; i++) {
            if (indeg[i] == 0) {
                if (root == -1) {
                    root = i;
                } else {
                    return false;
                }
            }
        }
        if (root == -1) {
            return false;
        }
        Set<Integer> visited = new HashSet<>();
        dfs(root, leftChild, rightChild, visited);
        return visited.size() == n;
    }

    private void dfs(int cur, int[] leftChild, int[] rightChild, Set<Integer> visited) {
        if (visited.contains(cur)) {
            return;
        }
        visited.add(cur);
        if (leftChild[cur] != -1) {
            dfs(leftChild[cur], leftChild, rightChild, visited);
        }
        if (rightChild[cur] != -1) {
            dfs(rightChild[cur], leftChild, rightChild, visited);
        }
    }
}
