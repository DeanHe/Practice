package bst;
/*

You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

Example
Example 1:

Input: "-4(2(3)(1))(6(5))"
Output: {-4,2,6,3,1,5}
Explanation:
The output is look like this:
      -4
     /  \
    2    6
   / \   /
  3   1 5
Example 2:

Input: "1(-1)"
Output: {1,-1}
Explanation:
The output is look like this:
     1
    /
  -1
Notice
There will only be '(', ')', '-' and '0' ~ '9' in the input string.
An empty tree is represented by "" instead of "()".

 */
public class ConstructBinaryTreeFromString {
    /**
     * @param s: a string
     * @return: a root of this tree
     */
    public TreeNode str2tree(String s) {
        if(s == null && s.length() == 0){
            return null;
        }
        if(s.charAt(0) == '(' && s.charAt(s.length() - 1) == ')'){
            s = s.substring(1, s.length() - 1);
        }
        int i = s.indexOf('(');
        if(i == -1){
            i = s.length();
        }
        TreeNode root = new TreeNode(Integer.parseInt(s.substring(0, i)));
        int cnt = 0;
        int j = i;
        while(j < s.length()){
            if(s.charAt(j) == '('){
                cnt++;
            } else if(s.charAt(j) == ')'){
                cnt--;
                if(cnt == 0){
                    j++;
                    break;
                }
            }
            j++;
        }
        root.left = str2tree(s.substring(i, j));
        root.right = str2tree(s.substring(j));
        return root;
    }
}
