package stack.parenthese;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.

Return all the possible results. You may return the answer in any order.

Example 1:

Input: s = "()())()"
Output: ["(())()","()()()"]
Example 2:

Input: s = "(a)())()"
Output: ["(a())()","(a)()()"]
Example 3:

Input: s = ")("
Output: [""]


Constraints:

1 <= s.length <= 25
s consists of lowercase English letters and parentheses '(' and ')'.
There will be at most 20 parentheses in s.

hint:
1
Since we don't know which of the brackets can possibly be removed, we try out all the options!
2
We can use recursion to try out all possibilities for the given expression. For each of the brackets, we have 2 options:
We keep the bracket and add it to the expression that we are building on the fly during recursion.
OR, we can discard the bracket and move on.
3
The one thing all these valid expressions have in common is that they will all be of the same length
i.e. as compared to the original expression, all of these expressions will have the same number of characters removed.
Can we somehow find the number of misplaced parentheses and use it in our solution?
4
The one thing all these valid expressions have in common is that they will all be of the same length i.e. as compared to the original expression, all of these expressions will have the same number of characters removed. Can we somehow find the number of misplaced parentheses and use it in our solution?
5
For every left parenthesis, we should have a corresponding right parenthesis. We can make use of two counters which keep track of misplaced left and right parenthesis and in one iteration we can find out these two values.
0 1 2 3 4 5 6 7
( ) ) ) ( ( ( )
i = 0, left = 1, right = 0
i = 1, left = 0, right = 0
i = 2, left = 0, right = 1
i = 3, left = 0, right = 2
i = 4, left = 1, right = 2
i = 5, left = 2, right = 2
i = 6, left = 3, right = 2
i = 7, left = 2, right = 2
We have 2 misplaced left and 2 misplaced right parentheses.
6
We found out that the exact number of left and right parenthesis that has to be removed to get a valid expression. So, e.g. in a 1000 parentheses string, if there are 2 misplaced left and 2 misplaced right parentheses, after we are done discarding 2 left and 2 right parentheses, we will have only one option per remaining character in the expression i.e. to consider them. We can't discard them.

analysis:
maintain variable removeLeft, removeRight, open
TC: O(2^N)
*/
public class RemoveInvalidParentheses {
    Set<String> set;
    String s;

    public List<String> removeInvalidParentheses(String s) {
        this.s = s;
        set = new HashSet<>();
        int removeLeft = 0, removeRight = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                removeLeft++;
            } else if (c == ')') {
                if (removeLeft > 0) {
                    removeLeft--;
                } else {
                    removeRight++;
                }
            }
        }
        dfs(new StringBuilder(), 0, removeLeft, removeRight, 0);
        List<String> res = new ArrayList<>(set);
        return res;
    }

    private void dfs(StringBuilder sb, int pos, int removeLeft, int removeRight, int open) {
        if (removeLeft < 0 || removeRight < 0 || open < 0) {
            return;
        }
        if (pos == s.length()) {
            if (removeLeft == 0 && removeRight == 0 && open == 0) {
                set.add(sb.toString());
            }
            return;
        }
        int sbLen = sb.length();
        char c = s.charAt(pos);
        if (c == '(') {
            dfs(sb, pos + 1, removeLeft - 1, removeRight, open); //remove
            dfs(sb.append(c), pos + 1, removeLeft, removeRight, open + 1); // use
        } else if (c == ')') {
            dfs(sb, pos + 1, removeLeft, removeRight - 1, open); //remove
            dfs(sb.append(c), pos + 1, removeLeft, removeRight, open - 1); // use
        } else {
            dfs(sb.append(c), pos + 1, removeLeft, removeRight, open);
        }
        sb.setLength(sbLen); // backtrack
    }

    /* bfs */
    public List<String> removeInvalidParenthesesBFS(String s) {
        List<String> res = new ArrayList<>();
        if(s == null){
            return res;
        }
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.offer(s);
        visited.add(s);
        boolean found = false;
        while(!queue.isEmpty()){
            String cur = queue.poll();
            if(isValid(cur)){
                res.add(cur);
                found = true;
            }
            if(found){
                continue;
            }
            // generate all possible states
            for(int i = 0; i < cur.length(); i++){
                // if s[i] is letter
                if(cur.charAt(i) == '(' || cur.charAt(i) == ')'){
                    String temp = cur.substring(0, i) + cur.substring(i + 1);
                    if(!visited.contains(temp)){
                        queue.offer(temp);
                        visited.add(temp);
                    }
                }
            }
        }
        return res;
    }
    // helper function checks if string s contains valid parantheses
    private boolean isValid(String s){
        int count = 0;
        char[] arr = s.toCharArray();
        for(char c : arr){
            if(c == '('){
                count++;
            } else if(c == ')'){
                count--;
                if(count < 0){
                    return false;
                }
            }
        }
        return count == 0;
    }
}
