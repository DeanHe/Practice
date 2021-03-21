package Stack.Parenthese;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]

time complexity: 2^n
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
