package string;

import java.util.List;

/*
#524
Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output:
"apple"
Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]

Output:
"a"
Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.
 */
public class LongestWordInDictionaryThroughDeleting {
    public String findLongestWord(String s, List<String> d) {
        String res = "";
        for(String str : d){
            if(isSub(str, s)){
                if(str.length() > res.length()){
                    res = str;
                } else if(str.length() == res.length() && str.compareTo(res) < 0){
                    res = str;
                }
            }
        }
        return res;
    }

    private boolean isSub(String str, String s) {
        int j = 0;
        for(int i = 0; i < s.length(); i++){
            if(j < str.length() && str.charAt(j) == s.charAt(i)){
                j++;
            }
            if(j == str.length()){
                return true;
            }
        }
        return false;
    }
}
