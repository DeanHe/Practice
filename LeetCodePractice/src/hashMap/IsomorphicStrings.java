package hashMap;

/*Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

Example
Example 1:

Input : s = "egg", t = "add"
Output : true 
Explanation : 
e -> a, g -> d.
Example 2:

Input : s = "foo", t = "bar"
Output : false
Explanation : 
No solution.
Example 3:

Input : s = "paper", t = "title"
Output : true 
Explanation : 
p -> t, a -> i, e -> l, r -> e.
Notice
You may assume both s and t have the same length.*/
public class IsomorphicStrings {
	/**
     * @param s: a string
     * @param t: a string
     * @return: true if the characters in s can be replaced to get t or false
     */
    public boolean isIsomorphic(String s, String t) {
        // write your code here
    	int[] map1 = new int[256];
    	int[] map2 = new int[256];
    	int len = s.length();
    	for(int i = 0; i < len; i++){
    		char sc = s.charAt(i);
    		char tc = t.charAt(i);
    		if(map1[sc] == 0){
    			map1[sc] = tc;
    		} else {
    			if(map1[sc] != tc){
    				return false;
    			}
    		}
    	}
    	for(int i = 0; i < len; i++){
    		char sc = s.charAt(i);
    		char tc = t.charAt(i);
    		if(map2[tc] == 0){
    			map2[tc] = sc;
    		} else {
    			if(map2[tc] != sc){
    				return false;
    			}
    		}
    	}
    	return true;
    }
}
