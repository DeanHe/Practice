package TwoPointers;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {
	public int lengthOfLongestSubstring(String s) {
		int length =s.length();
         if(length == 0){
        	 return 0;
         }        
        Map<Character, Integer> map = new HashMap<Character, Integer>();         
         int maxLen = 0;
         int start = 0;
         int currentLen = 0;
         for(int i = 0; i < length; i++){
        	 Integer key = map.get(s.charAt(i));
        	 if(key == null){
        		 currentLen++; 
        	 } else if(key >= start){
        		 currentLen = i - key;
        		 start = key + 1;
        	 } else {
        		currentLen++; 
        	 }
        	 map.put(s.charAt(i), i);
        	 if(currentLen > maxLen){
        		 maxLen = currentLen;
        	 }
         }
         return maxLen;
	}
}
