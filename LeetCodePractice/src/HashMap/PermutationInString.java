package HashMap;

/*
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.



        Example 1:

        Input: s1 = "ab" s2 = "eidbaooo"
        Output: True
        Explanation: s2 contains one permutation of s1 ("ba").
        Example 2:

        Input:s1= "ab" s2 = "eidboaoo"
        Output: False


        Note:

        The input strings only contain lower case letters.
        The length of both given strings is in range [1, 10,000].

        tag:
        similar to 'find all Anagrams'
*/

public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int[] dict = new int[26];
        for(char c : s1.toCharArray()){
            int idx = c - 'a';
            dict[idx]++;
        }
        int start = 0, end = 0, match = 0;
        while(end < s2.length()){
            int end_idx = s2.charAt(end) - 'a';
            if(dict[end_idx] > 0){
                match++;
            }
            dict[end_idx]--;
            end++;
            if(match == s1.length()){
                return true;
            }
            if(end - start == s1.length()){
                int start_idx = s2.charAt(start) - 'a';
                if(dict[start_idx] >= 0){
                    match--;
                }
                dict[start_idx]++;
                start++;
            }
        }
        return false;
    }
}
