package string;

import java.util.*;

// https://www.lintcode.com/problem/find-substring/description
/*Given the length k, find all substrings of length k in the string str.The characters of a substring can not be repeated and output the number of substrings that satisfy such conditions (the same substring is counted only 1 times).*/
public class FindSubstring {
	/**
     * @param str: The string
     * @param k: The length of the substring
     * @return: The answer
     */
    public int findSubstring(String str, int k) {
        // Write your code here
        if(k > 26){
            return 0;
        }
        int len = str.length();
        Set<String> resultSet = new HashSet<>();
        int count = 0;
        for(int i = 0; i + k - 1 < len; i++){
            String candidate = str.substring(i, i + k);
            if(isUnique(candidate)){
                if(!resultSet.contains(candidate)){
                    count++;
                    resultSet.add(candidate);
                }
            }
        }
        return count;
    }
    
    private boolean isUnique(String s){
        char[] arr = s.toCharArray();
        Set<Character> set = new HashSet<>();
        for(char c : arr){
            if(set.contains(c)){
                return false;
            } else {
                set.add(c);
            }
        }
        return true;
    }
}
