package Stack.MonotonicStack;

import java.util.Stack;

/*
Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once.
You must make sure your result is the smallest in lexicographical order among all possible results.

Example 1:

Input: "bcabc"
Output: "abc"
Example 2:

Input: "cbacdcbc"
Output: "acdb"

analysis:
maintain monotonic increasing stack of characters
The runtime is O(26 * n) = O(n).
tag:greedy
stack
*/
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        Stack<Character> stack = new Stack<>();
        int[] count = new int[26];
        boolean[] visited = new boolean[26];
        char[] arr = s.toCharArray();
        for (char c : arr) {
            count[c - 'a']++;
        }
        for (char c : arr) {
            int idx = c - 'a';
            count[idx]--;
            if (!visited[idx]) {
                while (!stack.isEmpty() && c < stack.peek() && count[stack.peek() - 'a'] > 0) {
                    char pre_c = stack.pop();
                    visited[pre_c - 'a'] = false;
                }
                stack.push(c);
                visited[idx] = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }
}
