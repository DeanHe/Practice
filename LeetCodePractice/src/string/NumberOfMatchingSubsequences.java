package string;
/*
Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".


Example 1:

Input: s = "abcde", words = ["a","bb","acd","ace"]
Output: 3
Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
Example 2:

Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
Output: 2


Constraints:

1 <= s.length <= 5 * 104
1 <= words.length <= 5000
1 <= words[i].length <= 50
s and words[i] consist of only lowercase English letters.

analysis:
create map of 26 alphabet to list of words start with that alphabet
loop through the characters in s, and update the map with words shrinking, if the word shrink to empty, add to res.
TC O(N)
 */
import java.util.*;


public class NumberOfMatchingSubsequences {
    public int numMatchingSubseq(String s, String[] words) {
    	Map<Character, List<StringBuilder>> map = new HashMap<>();
    	int res = 0;
    	for(char c = 'a'; c <= 'z'; c++){
    		map.put(c, new ArrayList<>());
		}
    	for(String word : words){
    		char c = word.charAt(0);
    		map.get(c).add(new StringBuilder(word));
		}
    	for(char c : s.toCharArray()){
    		List<StringBuilder> ls = map.get(c);
    		map.put(c, new ArrayList<>());
    		for(StringBuilder sb : ls){
    			sb.deleteCharAt(0);
    			if(sb.length() > 0){
    				map.get(sb.charAt(0)).add(sb);
				} else {
    				res++;
				}
			}
		}
    	return res;
    }
}
