package stack.parenthese;

import java.util.Stack;

/*
Given a balanced parentheses string S, compute the score of the string based on the following rule:

() has score 1
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.


Example 1:

Input: "()"
Output: 1

Example 2:
Input: "(())"
Output: 2

Example 3:
Input: "()()"
Output: 2

Example 4:
Input: "(()(()))"
Output: 6


Note:

S is a balanced parentheses string, containing only ( and ).
2 <= S.length <= 50

analysis:
Time Complexity: O(N)
space Complexity: O(1)
 */
public class ScoreOfParentheses {
    public int scoreOfParentheses(String s) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(1);
            } else {
                int cur = stack.pop();
                if(s.charAt(i - 1) == '('){
                    cur += 1;
                } else {
                    cur *= 2;
                }
            }
        }
        return res;
    }

    public int scoreOfParenthesesII(String s) {
        int res = 0, left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                left--;
                if (s.charAt(i - 1) == '(') {
                    res += 1 << left;
                }
            }
        }
        return res;
    }
}
