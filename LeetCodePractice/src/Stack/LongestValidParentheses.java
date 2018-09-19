package Stack;

import java.util.*;

public class LongestValidParentheses {
	public int longestValidParentheses(String s) {
        int maxans = 0;
     // save '(' location
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
                	// 如果栈内仍有元素，则当前合法序列的长度为当前栈顶元素的位置下一位到当前元素的距离
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }
	//a dp array where ith element of dp represents the length of the longest valid substring ending at ith index
	public int longestValidParenthesesDP(String s) {
        int maxans = 0;
        int dp[] = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }
}
