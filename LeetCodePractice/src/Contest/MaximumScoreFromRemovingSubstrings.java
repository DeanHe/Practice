package Contest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/*
You are given a string s and two integers x and y. You can perform two types of operations any number of times.

Remove substring "ab" and gain x points.
For example, when removing "ab" from "cabxbae" it becomes "cxbae".
Remove substring "ba" and gain y points.
For example, when removing "ba" from "cabxbae" it becomes "cabxe".
Return the maximum points you can gain after applying the above operations on s.



Example 1:

Input: s = "cdbcbbaaabab", x = 4, y = 5
Output: 19
Explanation:
- Remove the "ba" underlined in "cdbcbbaaabab". Now, s = "cdbcbbaaab" and 5 points are added to the score.
- Remove the "ab" underlined in "cdbcbbaaab". Now, s = "cdbcbbaa" and 4 points are added to the score.
- Remove the "ba" underlined in "cdbcbbaa". Now, s = "cdbcba" and 5 points are added to the score.
- Remove the "ba" underlined in "cdbcba". Now, s = "cdbc" and 5 points are added to the score.
Total score = 5 + 4 + 5 + 5 = 19.
Example 2:

Input: s = "aabbaaxybbaabb", x = 5, y = 4
Output: 20


Constraints:

1 <= s.length <= 10^5
1 <= x, y <= 10^4
s consists of lowercase English letters.

tag: greedy first remove all the pairs with bigger score, then try remove the remaining
 */
public class MaximumScoreFromRemovingSubstrings {
    public int maximumGain(String s, int x, int y) {
        int res = 0;
        int big = Math.max(x, y);
        int small = Math.min(x, y);
        char first = x > y ? 'a' : 'b';
        char second = x > y ? 'b' : 'a';
        Stack<Character> st1 = new Stack<>();
        Stack<Character> st2 = new Stack<>();
        for(char c :  s.toCharArray()){
            if(!st1.isEmpty() && st1.peek() == first && c == second){
                st1.pop();
                res += big;
            } else {
                st1.push(c);
            }
        }
        while(!st1.isEmpty()){
            char c = st1.pop();
            if(!st2.isEmpty() && c == second && st2.peek() == first){
                st2.pop();
                res += small;
            } else {
                st2.push(c);
            }
        }
        return res;
    }
}
