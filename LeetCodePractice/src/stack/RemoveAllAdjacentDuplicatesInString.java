package stack;
/*
Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.

We repeatedly make duplicate removals on S until we no longer can.

Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.

 

Example 1:

Input: "abbaca"
Output: "ca"
Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.
The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".

analysis:
Keep a sb as a characters stack.
Iterate characters of S one by one.

time complexity: O(N)
space complexity: O(N)
*/
public class RemoveAllAdjacentDuplicatesInString {
	public String removeDuplicates(String S) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < S.length(); i++){
            char c = S.charAt(i);
            int len = sb.length();
            if(len > 0 && sb.charAt(len - 1) == c){
                sb.deleteCharAt(len - 1);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
