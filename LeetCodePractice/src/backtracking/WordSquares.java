package backtracking;
/*Given a set of words without duplicates, find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

b a l l
a r e a
l e a d
l a d y
Example
Given a set ["area","lead","wall","lady","ball"]
return [["wall","area","lead","lady"],["ball","area","lead","lady"]]
Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).

Given a set ["abat","baba","atan","atal"]
return [["baba","abat","baba","atan"],["baba","abat","baba","atal"]]
Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
Notice
There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSquares {
	int len;
	/*
     * @param words: a set of words without duplicates
     * @return: all word squares
     */
    public List<List<String>> wordSquares(String[] words) {
        // write your code here
    	List<List<String>> res = new ArrayList<>();
    	if(words.length == 0){
    		return res;
    	}
    	len = words[0].length();
    	Map<String, List<String>> prefixMap = buildPrefixMap(words);
    	List<String> square = new ArrayList<>();
    	dfs(0, res, square, prefixMap);
    	return res;
    }
    
    private Map<String, List<String>> buildPrefixMap(String[] words){
    	//prefx : words
    	Map<String, List<String>> map = new HashMap<>();
    	map.put("", new ArrayList<>());
    	for(String w : words){
    		int len = w.length();
    		for(int i = 1; i <= len; i++){
    			String prefix = w.substring(0, i);
    			if(!map.containsKey(prefix)){
    				map.put(prefix, new ArrayList<>());
    			}
    			map.get(prefix).add(w);
    		}
    		map.get("").add(w);
    	}
    	return map;
    }
    private boolean checkPrefix(String cand, int row, List<String> square, Map<String, List<String>> prefixMap){
    	for(int c = row + 1; c < len; c++){
    		String prefix = "";
    		for(int r = 0; r < row; r++){
    			prefix += square.get(r).charAt(c);
    		}
    		prefix += cand.charAt(c);
    		if(!prefixMap.containsKey(prefix)){
    			return false;
    		}
    	}
    	return true;
    }
    private void dfs(int row, List<List<String>> res, List<String> square, Map<String, List<String>> prefixMap){
    	if(row == len){
    		res.add(new ArrayList<>(square));
    		return;
    	}
    	String prefix = "";
    	for(int r = 0; r < row; r++){
    		// row here is same as col index
    		prefix += square.get(r).charAt(row);
    	}
    	for(String cand : prefixMap.get(prefix)){
    		if(checkPrefix(cand, row, square, prefixMap)){
    			square.add(cand);
    			dfs(row + 1, res, square, prefixMap);
    			square.remove(square.size() - 1);
    		}
    	}
    }
}
