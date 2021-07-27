package stack.Parenthese;

import java.util.*;
/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

        Example 1:

        Input: "(()"
        Output: 2
        Explanation: The longest valid parentheses substring is "()"
        Example 2:

        Input: ")()())"
        Output: 4
        Explanation: The longest valid parentheses substring is "()()"

analysis:
approach1: stack
approach2: dp


*/

public class LongestValidParentheses {
	public int longestValidParentheses(String s) {
        int res = 0;
     // save '(' and unmatched ')' location
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }
	//dp[i] represents the length of the longest valid substring ending at s[:i]
	public int longestValidParenthesesDP(String s) {
        int res = 0, len = s.length();
        int dp[] = new int[len];
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    if(i == 1){
                        dp[i] = 2;
                    } else {
                        dp[i] = dp[i - 2] + 2;
                    }
                } else { // s.charAt(i - 1) == ')'
                    int preLeftIdx = i - dp[i - 1] - 1;
                    if (preLeftIdx >= 0 && s.charAt(preLeftIdx) == '(') {
                        dp[i] = dp[i - 1] + 2;
                        if(preLeftIdx - 1 >= 0){
                            dp[i] += dp[preLeftIdx - 1];
                        }

                    }
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }
}
