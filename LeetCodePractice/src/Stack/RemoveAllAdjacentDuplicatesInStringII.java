package Stack;

import java.util.*;

/*Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made.

It is guaranteed that the answer is unique.

Example 1:

Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.
Example 2:

Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation: 
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"
Example 3:

Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"
 
Constraints:

1 <= s.length <= 10^5
2 <= k <= 10^4
s only contains lower case English letters.
*/
public class RemoveAllAdjacentDuplicatesInStringII {
	public String removeDuplicates(String s, int k) {
		Deque<letter> deque = new ArrayDeque<>();
		char[] arr = s.toCharArray();
		for(char c : arr) {
			if(!deque.isEmpty() && deque.peekLast().c == c) {
				deque.peekLast().cnt++;
			} else {
				deque.offerLast(new letter(c, 1));
			}
			if(deque.peekLast().cnt == k) {
				deque.pollLast();
			}
		}
		StringBuilder sb = new StringBuilder();
		while(!deque.isEmpty()) {
			letter front = deque.pollFirst();
			for(int i = 0; i < front.cnt; i++) {
				sb.append(front.c);
			}
		}
		return sb.toString();
    }

	class letter {
		char c;
		int cnt;
		public letter(char cha, int freq) {
			this.c = cha;
			this.cnt = freq;
		}
	}

	//way 2
	public String removeDuplicatesII(String s, int k) {
		int len = s.length();
		int[] count = new int[len];
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < len; i++){
			char c = s.charAt(i);
			sb.append(c);
			int last = sb.length() - 1;
			if(last > 0 && sb.charAt(last - 1) == sb.charAt(last)){
				count[last] = 1 + count[last - 1];
			} else {
				count[last] = 1;
			}
			if(count[last] == k){
				sb.delete(sb.length() - k, sb.length());
			}
		}
		return sb.toString();
	}
}
