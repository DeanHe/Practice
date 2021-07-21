package bitManipulation;
/*
Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.
Example 1:

Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
Output: 16 
Explanation: The two words can be "abcw", "xtfn".
Example 2:

Input: ["a","ab","abc","d","cd","bcd","abcd"]
Output: 4 
Explanation: The two words can be "ab", "cd".
Example 3:

Input: ["a","aa","aaa","aaaa"]
Output: 0 
Explanation: No such pair of words.

TC O(N ^ 2)
*/
public class MaximumProductOfWordLengths {
	public int maxProduct(String[] words) {
        int len = words.length;
        int[] hash = new int[len];
        int maxProductLen = 0;
        for(int i = 0; i < len; i++){
        	String s = words[i];
        	for(int j = 0; j < s.length(); j++){
        		int visited = 1 << (s.charAt(j) - 'a');
        		hash[i] = hash[i] | visited;
        	}
        }
        for(int i = 0; i < len; i++){
        	for(int j = i + 1; j < len; j++){
        		if((hash[i] & hash[j]) == 0){
        			maxProductLen = Math.max(maxProductLen, words[i].length() * words[j].length());
        		}
        	}
        }
        return maxProductLen;
    }
}
