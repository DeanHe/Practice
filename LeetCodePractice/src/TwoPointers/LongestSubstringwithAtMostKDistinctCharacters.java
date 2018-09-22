package TwoPointers;

import java.util.*;

public class LongestSubstringwithAtMostKDistinctCharacters {
	/**
     * @param s : A string
     * @return : The length of the longest substring 
     *           that contains at most k distinct characters.
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        int maxLen = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        int i = 0, j = 0;
        int len = s.length();
        char c;
        for(i = 0; i < len; i++){
            while(j < len){
                c = s.charAt(j);
                if(charMap.containsKey(c)){
                    int count = charMap.get(c);
                    charMap.put(c, count + 1);
                } else {
                    if(charMap.size() == k){
                        break;
                    }
                    charMap.put(c, 1);
                }
                j++;
            }
            maxLen = Math.max(maxLen, j - i);
            c = s.charAt(i);
            if(charMap.containsKey(c)){
                int count = charMap.get(c);
                if(count > 1){
                    charMap.put(c, count - 1);
                } else {
                    charMap.remove(c);
                }    
            }
        }
        return maxLen;
    }
}
