package dp;
/*
Given a list of words, each word consists of English lowercase letters.

Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".

A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.

Return the longest possible length of a word chain with words chosen from the given list of words.

 

Example 1:

Input: ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: one of the longest word chain is "a","ba","bda","bdca".
 

Note:

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of English lowercase letters.

TC: O(N log N + N * len(word))
*/

import java.util.*;

public class LongestStringChain {
	public int longestStrChain(String[] words) {
		Arrays.sort(words, (a, b) -> a.length() - b.length());
		// word : max steps to reach it
        Map<String, Integer> freq = new HashMap<>();
        for(String word : words){
        	freq.put(word, 1);
        }
        int res = 1;
        for(String w : words){
        	int len = w.length(), f = 1;
        	for(int i = 0; i < len; i++){
        		String pre = w.substring(0, i) + w.substring(i + 1);
        		if(freq.containsKey(pre)){
        			f = Math.max(f, freq.get(pre) + 1);
        		}
        	}
			freq.put(w, f);
			res = Math.max(res, f);
        }
        return res;
    }
}
