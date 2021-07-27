package stack.monotonicStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/*
#316

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
        Map<Character, Integer> last = new HashMap<>();
        Set<Character> visited = new HashSet<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            last.put(arr[i], i);
        }
        for (int i = 0; i < arr.length; i++) {
            if (!visited.contains(arr[i])) {
                while (!stack.isEmpty() && arr[i] < stack.peek() && i < last.get(stack.peek())) {
                    char pre_c = stack.pop();
                    visited.remove(pre_c);
                }
                stack.push(arr[i]);
                visited.add(arr[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }
}
