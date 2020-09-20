package Contest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
Given two strings s and t, you want to transform string s into string t using the following operation any number of times:

Choose a non-empty substring in s and sort it in-place so the characters are in ascending order.
For example, applying the operation on the underlined substring in "14234" results in "12344".

Return true if it is possible to transform string s into string t. Otherwise, return false.

A substring is a contiguous sequence of characters within a string.



Example 1:

Input: s = "84532", t = "34852"
Output: true
Explanation: You can transform s into t using the following sort operations:
"84532" (from index 2 to 3) -> "84352"
"84352" (from index 0 to 2) -> "34852"
Example 2:

Input: s = "34521", t = "23415"
Output: true
Explanation: You can transform s into t using the following sort operations:
"34521" -> "23451"
"23451" -> "23415"
Example 3:

Input: s = "12345", t = "12435"
Output: false
Example 4:

Input: s = "1", t = "2"
Output: false


Constraints:

s.length == t.length
1 <= s.length <= 105
s and t only contain digits from '0' to '9'.

analysis:
reduce the sort operation, to whether can swap by two nearby elements
for each digit tc in t, try to move the tc in string s from its position to the leftmost,
if we can't move its position to leftmost (find there is a digit smaller exist before it), return false
remove from the queue, after processing each character (shorten t and s by removing tc)
 */
public class CheckIfStringIsTransformableWithSubstringSortOperations {
    public boolean isTransformable(String s, String t) {
        int len = s.length();
        Queue<Integer>[] idx = new Queue[10];
        for (int i = 0; i <= 9; i++) {
            idx[i] = new LinkedList<>();
        }
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            idx[c - '0'].add(i);
        }
        for (int i = 0; i < len; i++) {
            char c = t.charAt(i);
            int pos = c - '0';
            if (idx[pos].isEmpty()) {
                return false;
            }
            for (int j = 0; j < pos; j++) {
                if (!idx[j].isEmpty() && idx[j].peek() < idx[pos].peek()) {
                    return false;
                }
            }
            idx[pos].poll();
        }
        return true;
    }
}
