package TwoPointers;
/*Given a string, find the length of the longest substring without repeating characters.

Example
Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The longest substring is "abc".
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The longest substring is "b".
Challenge
time complexity O(n)*/
import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {
	public int lengthOfLongestSubstring(String s) {
		int len =s.length();
         if(len == 0){
        	 return 0;
         }
         // map from character's ASCII to its last occured index
         int[] freq = new int[256];
         int maxLen = 0;
         int l = 0;
         int r = 0;
         for(l = 0; l < len; l++){
        	 while(r < len && freq[s.charAt(r)] == 0){
        		 freq[s.charAt(r)]++;
        		 maxLen = Math.max(maxLen, r - l + 1);
        		 r++;
        	 }
        	 freq[s.charAt(l)]--;
         }
         return maxLen;
	}
}
