package bst;
/*
You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.
Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:

'L' means to go from a node to its left child node.
'R' means to go from a node to its right child node.
'U' means to go from a node to its parent node.
Return the step-by-step directions of the shortest path from node s to node t.

Example 1:
Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
Output: "UURL"
Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.

Example 2:
Input: root = [2,1], startValue = 2, destValue = 1
Output: "L"
Explanation: The shortest path is: 2 → 1.


Constraints:
The number of nodes in the tree is n.
2 <= n <= 10^5
1 <= Node.val <= n
All the values in the tree are unique.
1 <= startValue, destValue <= n
startValue != destValue

hint:
1 The shortest path between any two nodes in a tree must pass through their Lowest Common Ancestor (LCA). The path will travel upwards from node s to the LCA and then downwards from the LCA to node t.
2 Find the path strings from root → s, and root → t. Can you use these two strings to prepare the final answer?
3 Remove the longest common prefix of the two path strings to get the path LCA → s, and LCA → t. Each step in the path of LCA → s should be reversed as 'U'.
 */
public class StepByStepDirectionsFromaBinaryTreeNodeToAnother {
    String ss, sd;
    public String getDirections(TreeNode root, int startValue, int destValue) {
        dfs(root, startValue, destValue, new StringBuilder());
        int i = prefix();
        StringBuilder sb = new StringBuilder();
        for(int j = i; j < ss.length(); j++){
            sb.append("U");
        }
        return sb.toString() + sd.substring(i);
    }

    private int prefix(){
        int i = 0;
        while(i < Math.min(ss.length(), sd.length())){
            if(ss.charAt(i) == sd.charAt(i)){
                i++;
            } else {
                break;
            }
        }
        return i;
    }

    private void dfs(TreeNode root, int startValue, int destValue, StringBuilder sb){
        if(root == null){
            return;
        }
        if(root.val == startValue){
            ss = sb.toString();
        } else if(root.val == destValue){
            sd = sb.toString();
        }
        sb.append("L");
        dfs(root.left, startValue, destValue, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.append("R");
        dfs(root.right, startValue, destValue, sb);
        sb.deleteCharAt(sb.length() - 1);
    }
}
