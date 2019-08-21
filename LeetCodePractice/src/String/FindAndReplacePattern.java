package String;

import java.util.*;

/*You have a list of words and a pattern, and you want to know which words in words matches the pattern.

A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.

(Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.)

Return a list of the words in words that match the given pattern.

You may return the answer in any order.

Example
Example 1:

Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
Output: ["aqq","mee"]
Explanation: 
"mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}. 
"ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
since a and b map to the same letter.
Example 2:

Input: words = ["a","b","c"], pattern = "a"
Output: ["a","b","c"]
Explanation: 
All strings match.
Notice
1 <= words.length <= 501<=words.length<=50
1 <= pattern.length = words[i].length <= 201<=pattern.length=words[i].length<=20*/
public class FindAndReplacePattern {
	/**
     * @param words: word list
     * @param pattern: pattern string
     * @return: list of matching words
     */
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        int[] pEncode = hash(pattern);
        for(String w : words){
        	if(Arrays.equals(hash(w), pEncode)){
        		res.add(w);
        	}
        }
        return res;
    }
    private int[] hash(String s){ 
    	Map<Character, Integer> map = new HashMap<>();
    	char[] arr = s.toCharArray();
    	int[] encode = new int[arr.length];
    	for(int i = 0; i < arr.length; i++){
    		char c = arr[i];
    		map.putIfAbsent(c, map.size());
    		encode[i] = map.get(c);
    	}
    	return encode;
    }
}
