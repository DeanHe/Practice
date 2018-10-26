package DFS;

import java.util.*;

public class WordBreakII {
	/**
     * @param s a string
     * @param wordDict a set of words
     */
	//DFS
    public List<String> wordBreak(String s, Set<String> wordDict) {
        // Write your code here
        if(s == null || s.length() == 0 || wordDict ==null|| wordDict.size() == 0){
            return new ArrayList<String>();
        }
        Map<String, List<String>> breakMap = new HashMap<>();
        return helper(s, wordDict, breakMap);
    }
    private List<String> helper(String s, Set<String> wordDict, Map<String, List<String>> breakMap){
        if(breakMap.containsKey(s)){
            return breakMap.get(s);
        }
        List<String> res = new ArrayList<>();
        if(s.length() == 0){
            res.add("");
            return res;
        }
        for(int i = 1; i <= s.length(); i++){
            String left = s.substring(0, i);
            String right = s.substring(i);
            if(wordDict.contains(left)){
                List<String> rightCombination = helper(right, wordDict, breakMap);
                for(String comb : rightCombination){
                    if(comb.length() != 0){
                        res.add(left + " " + comb);
                    } else {
                        res.add(left);
                    }
                }
            }
        }
        breakMap.put(s, res);
        return res;
    }
}
