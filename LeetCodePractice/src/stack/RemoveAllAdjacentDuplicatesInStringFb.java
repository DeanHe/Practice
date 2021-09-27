package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/*
Given a string s, a duplicate removal consists of choosing two or more adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make duplicate removals (k >=2) on s until we no longer can.

Return the final string after all such duplicate removals have been made.

It is guaranteed that the answer is unique.

Example 1:

Input: s = "abcd"
Output: "abcd"
Explanation: There's nothing to delete.
Example 2:

Input: s = "deeedbbcccbdaa"
Output: "d"
Explanation: 
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"
Example 3:

Input: s = "pbbcggttciiippooaais"
Output: "s"
 
Constraints:

1 <= s.length <= 10^5
2 <= k <= 10^4
s only contains lower case English letters.

analysis:
TC O(N)
SC O(N)
*/
public class RemoveAllAdjacentDuplicatesInStringFb {
    public String removeDuplicates(String s) {
        Deque<Letter> deque = new ArrayDeque<>();
        char[] arr = s.toCharArray();
        for (char c : arr) {
            if (!deque.isEmpty() && deque.peekLast().c == c) {
                deque.peekLast().cnt++;
            } else {
                if (!deque.isEmpty() && deque.peekLast().cnt > 1) {
                    deque.pollLast();
                }
                deque.offerLast(new Letter(c, 1));
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            Letter front = deque.pollFirst();
            for (int i = 0; i < front.cnt; i++) {
                sb.append(front.c);
            }
        }
        return sb.toString();
    }

    class Letter {
        char c;
        int cnt;

        public Letter(char cha, int freq) {
            this.c = cha;
            this.cnt = freq;
        }
    }

    //way 2
    public String removeDuplicatesII(String s, int k) {
        int len = s.length();
        int[] count = new int[len];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            sb.append(c);
            int last = sb.length() - 1;
            if (last > 0 && sb.charAt(last - 1) == sb.charAt(last)) {
                count[last] = 1 + count[last - 1];
            } else {
                count[last] = 1;
            }
            if (count[last] == k) {
                sb.delete(sb.length() - k, sb.length());
            }
        }
        return sb.toString();
    }
}
