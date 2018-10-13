package HashMap;

import java.util.*;

public class FindAllAnagramsInaString {
	/**
     * @param s a string
     * @param p a non-empty string
     * @return a list of index
     */
    public List<Integer> findAnagrams(String s, String p) {
        // Write your code here
        List<Integer> res = new ArrayList<>();
        if(s == null || p == null || p.length() == 0 || s.length() == 0 || s.length() < p.length()){
            return res;
        }
        int[] dict = new int[26];
        char[] arr = p.toCharArray();
        for(char c : arr){
            dict[c - 'a']++;
        }
        int s_len = s.length();
        int p_len = p.length();
        for(int i = 0; i < s_len - p_len + 1; i++){
            String temp = s.substring(i, i + p_len);
            if(isAnagram(temp, dict)){
                res.add(i);
            }
        }
        return res;
    }
    private boolean isAnagram(String temp, int[] dict){
        int[] match = Arrays.copyOf(dict, dict.length);
        for(int i = 0; i < temp.length(); i++){
            char c = temp.charAt(i);
            match[c - 'a']--;
            if(match[c - 'a'] < 0){
                return false;
            }
        }
        return true;
    }
}
