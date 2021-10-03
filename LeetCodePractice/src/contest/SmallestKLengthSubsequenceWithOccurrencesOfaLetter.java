package contest;

import java.util.Stack;

/*
You are given a string s, an integer k, a letter letter, and an integer repetition.

Return the lexicographically smallest subsequence of s of length k that has the letter letter appear at least repetition times. The test cases are generated so that the letter appears in s at least repetition times.

A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.

A string a is lexicographically smaller than a string b if in the first position where a and b differ, string a has a letter that appears earlier in the alphabet than the corresponding letter in b.



Example 1:

Input: s = "leet", k = 3, letter = "e", repetition = 1
Output: "eet"
Explanation: There are four subsequences of length 3 that have the letter 'e' appear at least 1 time:
- "lee" (from "leet")
- "let" (from "leet")
- "let" (from "leet")
- "eet" (from "leet")
The lexicographically smallest subsequence among them is "eet".
Example 2:

example-2
Input: s = "leetcode", k = 4, letter = "e", repetition = 2
Output: "ecde"
Explanation: "ecde" is the lexicographically smallest subsequence of length 4 that has the letter "e" appear at least 2 times.
Example 3:

Input: s = "bb", k = 2, letter = "b", repetition = 2
Output: "bb"
Explanation: "bb" is the only subsequence of length 2 that has the letter "b" appear at least 2 times.


Constraints:

1 <= repetition <= k <= s.length <= 5 * 104
s consists of lowercase English letters.
letter is a lowercase English letter, and appears in s at least repetition times.

hint:
1 Use stack. For every character to be appended, decide how many character(s) from the stack needs to get popped based on the stack length and the count of the required character.
2 Pop the extra characters out from the stack and return the characters in the stack (reversed).
 */
public class SmallestKLengthSubsequenceWithOccurrencesOfaLetter {
    public String smallestSubsequence(String s, int k, char letter, int repetition) {
        int sLen = s.length(), letterCnt = 0;
        for(int i = 0; i < sLen; i++){
            if(s.charAt(i) == letter){
                letterCnt++;
            }
        }
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < sLen; i++){
            char c = s.charAt(i);
            while(!st.isEmpty() && c < st.peek() && (sLen  - i + st.size() > k) && (st.peek() != letter || letterCnt > repetition)){
                char pre_c = st.pop();
                if(pre_c == letter){
                    repetition++;
                }
            }
            if(st.size() < k){
                if(c == letter){
                    st.push(c);
                    repetition--;
                } else if(k - st.size() > repetition){
                    st.push(c);
                }
            }
            if(c == letter){
                letterCnt--;
            }
        }
        StringBuilder sb = new StringBuilder();
        // this is FIFO
        for(char c : st){
            sb.append(c);
        }
        return sb.toString();
    }
}

